package org.dompet.jpa;

import org.dompet.utils.annotations.Id;
import org.dompet.utils.database.DBConnector;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class CRUDOperationImpl<T> {
    private final DBConnector dbConnector;

    public CRUDOperationImpl(DBConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    private Connection getConnection() {
        try {
            return dbConnector.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String getActualClassName(){
        return "\"%s\"".formatted(getActualClass().getSimpleName());
    }

    protected abstract Class<T> getActualClass();

    private T createT(ResultSet resultSet){
        T newT = null;
        try {
            newT = (T) Class.forName(getActualClass().getName()).newInstance();
            for (Field champ : getActualClass().getDeclaredFields()) {
                Method setter = getActualClass().getDeclaredMethod("set" + Character.toUpperCase(champ.getName().charAt(0)) + champ.getName().substring(1), champ.getType());
                int columnType = resultSet.getMetaData().getColumnType(resultSet.findColumn(champ.getName()));
                switch (columnType) {
                    case Types.VARCHAR -> setter.invoke(newT, resultSet.getString(champ.getName()));
                    case Types.INTEGER -> setter.invoke(newT, resultSet.getInt(champ.getName()));
                    default -> throw new Error(String.format("The Type with id %s in the result set is not implemented", columnType));
                }
            }
        } catch (SQLException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return newT;
    }

    public final List<T> getAll() {

        List<T> TList = new ArrayList<>();
        try {
            ResultSet resultSet = getConnection().createStatement().executeQuery("SELECT * FROM " + getActualClassName());

            while (resultSet.next()) {
                TList.add(createT(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return TList;
    }

    public final T getById(String idColumn, String id) {
        try {
            ResultSet resultSet = getConnection().createStatement().executeQuery("SELECT * FROM " + getActualClassName() + " WHERE " + idColumn + " = " + id);

            while (resultSet.next()) {
                return createT(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    };

    public final T getById(String id) {
        return getById("id", id);
    }

    private String createSQLInsertQuery(boolean useId){
        StringBuilder sql = new StringBuilder("INSERT INTO %s(".formatted(getActualClassName()));
        List<String> columns = Arrays
                .stream(getActualClass().getDeclaredFields())
                .filter(champ -> !(!useId && champ.isAnnotationPresent(Id.class)))
                .map(Field::getName)
                .toList();
        for (String column: columns){
            sql.append("%s,".formatted(column));
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(") VALUES (");
        for (String column: columns){
            sql.append("?,");
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(")");
        return sql.toString();
    }

    public T insert(T newT, boolean useId) {
        try {
            String sql = createSQLInsertQuery(useId);
            PreparedStatement pr = getConnection().prepareStatement(sql);
            int index = 1;
            for (Field champ : getActualClass().getDeclaredFields()) {
                if (!(!useId && champ.isAnnotationPresent(Id.class))) {
                    Method getter = getActualClass().getDeclaredMethod("get%s%s".formatted(Character.toUpperCase(champ.getName().charAt(0)), champ.getName().substring(1)));
                    switch (champ.getType().getName()) {
                        case "java.lang.String" -> pr.setString(index, getter.invoke(newT).toString());
                        case "java.lang.Integer" -> pr.setInt(index, (Integer) getter.invoke(newT));
                        default ->
                                throw new Error(String.format("The Type with id %s in the result set is not implemented", champ.getType()));
                    }
                    index++;
                }
            };
        } catch (SQLException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e){
            throw new RuntimeException(e);
        }
        return newT;
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
                        Method getter = getActualClass().getDeclaredMethod("get" + Character.toUpperCase(field.getName().charAt(0)) + field.getName().substring(1));
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
        } catch (SQLException | NoSuchFieldException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    private String createSQLsaveQuery() {
        StringBuilder sql = new StringBuilder("save " + getActualClassName() + " SET ");
        List<String> columns = Arrays
                .stream(getActualClass().getDeclaredFields())
                .filter(field -> !field.isAnnotationPresent(Id.class))
                .map(field -> field.getName() + " = ?")
                .collect(Collectors.toList());

        sql.append(String.join(", ", columns));
        sql.append(" WHERE id = ?");

        return sql.toString();
    }

    public void deleteById(String id) {
        try {
            String deleteSql = "DELETE FROM " + getActualClassName() + " WHERE id = ?";
            PreparedStatement deleteStmt = getConnection().prepareStatement(deleteSql);
            deleteStmt.setString(1, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
