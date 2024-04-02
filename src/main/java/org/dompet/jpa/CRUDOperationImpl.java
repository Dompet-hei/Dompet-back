package org.dompet.jpa;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.dompet.utils.annotations.Column;
import org.dompet.utils.annotations.Id;
import org.dompet.utils.annotations.Model;
import org.dompet.utils.database.DBConnector;

@AllArgsConstructor
public abstract class CRUDOperationImpl<T> {
  private final DBConnector dbConnector;

  private Connection getConnection() {
    try {
      return dbConnector.getConnection();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  private String getActualClassName() {
    Model annotation = getActualClass().getAnnotation(Model.class);
    if (annotation.table() != null) {
      return "\"%s\"".formatted(annotation.table());
    }
    return "\"%s\"".formatted(getActualClass().getSimpleName());
  }

  protected abstract Class<T> getActualClass();

  private T createT(ResultSet resultSet) {
    T newT = null;
    try {
      newT = (T) Class.forName(getActualClass().getName()).newInstance();
      for (Field champ : getActualClass().getDeclaredFields()) {
        Method setter =
            getActualClass()
                .getDeclaredMethod(
                    "set"
                        + Character.toUpperCase(champ.getName().charAt(0))
                        + champ.getName().substring(1),
                    champ.getType());
        String columnName = getColumnName(champ);
        int columnType = resultSet.getMetaData().getColumnType(resultSet.findColumn(columnName));
        switch (columnType) {
          case Types.VARCHAR -> setter.invoke(newT, resultSet.getString(columnName));
          case Types.INTEGER -> setter.invoke(newT, resultSet.getInt(columnName));
          case Types.DATE -> {
            switch (champ.getType().getName()) {
              case "java.time.LocalDate" -> setter.invoke(
                  newT, resultSet.getDate(columnName).toLocalDate());
              default -> setter.invoke(newT, resultSet.getDate(columnName));
            }
          }
          case Types.TIMESTAMP -> setter.invoke(newT, resultSet.getTimestamp(columnName));
          case Types.BOOLEAN, Types.BIT -> setter.invoke(newT, resultSet.getBoolean(columnName));
          case Types.BIGINT -> setter.invoke(newT, resultSet.getLong(columnName));
          case Types.FLOAT -> setter.invoke(newT, resultSet.getFloat(columnName));
          case Types.DOUBLE -> setter.invoke(newT, resultSet.getDouble(columnName));
          case Types.NUMERIC -> setter.invoke(
              newT, BigDecimal.valueOf(resultSet.getFloat(columnName)));
          default -> throw new Error(
              String.format(
                  "The Type with id %s in the result set is not implemented", columnType));
        }
      }
    } catch (SQLException
        | NoSuchMethodException
        | IllegalAccessException
        | InstantiationException
        | InvocationTargetException
        | ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
    return newT;
  }

  public final List<T> getAll() {

    List<T> TList = new ArrayList<>();
    try {
      ResultSet resultSet =
          getConnection().createStatement().executeQuery("SELECT * FROM " + getActualClassName());

      while (resultSet.next()) {
        TList.add(createT(resultSet));
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return TList;
  }

  public final Optional<T> getById(String idColumn, String id) {
    try {
      ResultSet resultSet =
          getConnection()
              .createStatement()
              .executeQuery(
                  "SELECT * FROM "
                      + getActualClassName()
                      + " WHERE "
                      + idColumn
                      + " = '"
                      + id
                      + "'");

      while (resultSet.next()) {
        return Optional.ofNullable(createT(resultSet));
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return null;
  }

  public final Optional<T> getById(Object id) {
    String idColumnName = getIdColumnName(getActualClass());
    return getById(idColumnName, id.toString());
  }

  private String getIdColumnName(Class<?> modelClass) {
    for (Field field : modelClass.getDeclaredFields()) {
      if (field.isAnnotationPresent(Id.class)) {
        Column columnAnnotation = field.getAnnotation(Column.class);
        return columnAnnotation.name();
      }
    }
    throw new IllegalArgumentException("No field annotated with @Id found in the class.");
  }

  private String createSQLInsertQuery(boolean useId) {
    StringBuilder sql = new StringBuilder("INSERT INTO %s(".formatted(getActualClassName()));
    List<String> columns =
        Arrays.stream(getActualClass().getDeclaredFields())
            .filter(
                field ->
                    field.isAnnotationPresent(Column.class)
                        && (useId || !field.isAnnotationPresent(Id.class)))
            .map(this::getColumnName)
            .collect(Collectors.toList());
    sql.append(String.join(",", columns));
    sql.append(") VALUES (");
    sql.append(String.join(",", Collections.nCopies(columns.size(), "?")));
    sql.append(")");
    return sql.toString();
  }

  public T insert(T newT, boolean useId) {
    try {
      String sql = createSQLInsertQuery(useId);
      PreparedStatement pr = getConnection().prepareStatement(sql);
      int index = 1;
      for (Field field : getActualClass().getDeclaredFields()) {
        if (field.isAnnotationPresent(Column.class)
            && (useId || !field.isAnnotationPresent(Id.class))) {
          Method getter =
              getActualClass()
                  .getDeclaredMethod(
                      "get"
                          + Character.toUpperCase(field.getName().charAt(0))
                          + field.getName().substring(1));
          Object value = getter.invoke(newT);
          setPreparedStatementValue(pr, index++, value, field.getType());
        }
      }
      pr.executeUpdate();
    } catch (SQLException
        | NoSuchMethodException
        | IllegalAccessException
        | InvocationTargetException e) {
      throw new RuntimeException(e);
    }
    return newT;
  }

  private void setPreparedStatementValue(
      PreparedStatement pr, int index, Object value, Class<?> type) throws SQLException {
    if (type == String.class) {
      pr.setString(index, (String) value);
    } else if (type == Integer.class) {
      pr.setInt(index, (Integer) value);
    } else if (type == LocalDate.class) {
      pr.setDate(index, Date.valueOf((LocalDate) value));
    } else if (type == BigDecimal.class) {
      pr.setBigDecimal(index, (BigDecimal) value);
    } else if (type == Boolean.class) {
      pr.setBoolean(index, (Boolean) value);
    } else if (type == Double.class) {
      pr.setDouble(index, (Double) value);
    } else {
      throw new IllegalArgumentException("Unsupported type: " + type.getName());
    }
  }

  public T save(T entity) {
    try {
      Field idField = getActualClass().getDeclaredField("id");
      idField.setAccessible(true);
      String id = (String) idField.get(entity);

      String checkSql = "SELECT COUNT(*) FROM " + getActualClassName() + " WHERE id = ?";
      PreparedStatement checkStmt = getConnection().prepareStatement(checkSql);
      checkStmt.setString(1, id);
      ResultSet checkResult = checkStmt.executeQuery();
      checkResult.next();
      int count = checkResult.getInt(1);

      if (count > 0) {
        String saveSql = createSQLsaveQuery();
        PreparedStatement saveStmt = getConnection().prepareStatement(saveSql);
        int index = 1;
        for (Field field : getActualClass().getDeclaredFields()) {
          if (!field.isAnnotationPresent(Id.class)) {
            Method getter =
                getActualClass()
                    .getDeclaredMethod(
                        "get"
                            + Character.toUpperCase(field.getName().charAt(0))
                            + field.getName().substring(1));
            Object value = getter.invoke(entity);
            if (value instanceof String) {
              saveStmt.setString(index, (String) value);
            } else if (value instanceof Integer) {
              saveStmt.setInt(index, (Integer) value);
            }
            index++;
          }
        }
        saveStmt.setString(index, id);
      } else {
        insert(entity, true);
      }
    } catch (SQLException
        | NoSuchFieldException
        | NoSuchMethodException
        | InvocationTargetException
        | IllegalAccessException e) {
      throw new RuntimeException(e);
    }
    return entity;
  }

  private String createSQLsaveQuery() {
    StringBuilder sql = new StringBuilder("save " + getActualClassName() + " SET ");
    List<String> columns =
        Arrays.stream(getActualClass().getDeclaredFields())
            .filter(field -> !field.isAnnotationPresent(Id.class))
            .map(field -> field.getName() + " = ?")
            .collect(Collectors.toList());

    sql.append(String.join(", ", columns));
    sql.append(" WHERE id = ?");

    return sql.toString();
  }

  public void deleteById(Object id) {
    try {
      String deleteSql = "DELETE FROM " + getActualClassName() + " WHERE id = ?";
      PreparedStatement deleteStmt = getConnection().prepareStatement(deleteSql);
      if (id instanceof String) {
        deleteStmt.setString(1, (String) id);
      } else if (id instanceof Long) {
        deleteStmt.setLong(1, (Long) id);
      } else if (id instanceof Integer) {
        deleteStmt.setInt(1, (Integer) id);
      } else {
        throw new IllegalArgumentException("Unsupported id type. Only Long or Integer accepted.");
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  private String getColumnName(Field champ) {
    Column annotation = champ.getAnnotation(Column.class);
    if (annotation != null) {
      return annotation.name();
    }
    return champ.getName();
  }
}
