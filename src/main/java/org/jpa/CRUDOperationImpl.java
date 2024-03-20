package org.jpa;

import org.jpa.utils.ID;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class CRUDOperationImpl<T> {
    private Connection getConnection() {
        return DBConnection.getConnection();
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
            e.printStackTrace();
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
    };

    public final T getById(String idColumn, Integer id) {
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

    public final T getById(Integer id) {
        return getById("id", id);
    };

    private String createSQLInsertQuery(boolean useId){
        StringBuilder sql = new StringBuilder("INSERT INTO %s(".formatted(getActualClassName()));
        List<String> columns = Arrays
                .stream(getActualClass().getDeclaredFields())
                .filter(champ -> !(!useId && champ.isAnnotationPresent(ID.class)))
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
    private String createSQLInsertQuery(){
        return createSQLInsertQuery(false);
    }

    public T insert(T newT, boolean useId) {
        try {
            String sql = createSQLInsertQuery(useId);
            PreparedStatement pr = getConnection().prepareStatement(sql);
            int index = 1;
            for (Field champ : getActualClass().getDeclaredFields()) {
                if (!(!useId && champ.isAnnotationPresent(ID.class))) {
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
            pr.executeUpdate();
        } catch (SQLException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e){
            e.printStackTrace();
        }
        return newT;
    }

    public T insert(T newT) {
        return insert(newT, false);
    }
}
