package mysql1.dao;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Bios on 29.11.2017.
 */
public class AbstractDAO<T> {
    public static final String ID = "id=?";
    public static final String WHERE = " WHERE ";
    public static final String FROM = " FROM ";
    public static final String SELECT = "SELECT *";
    public static final String DELETE = "DELETE";
    public static final String UPDATE = "UPDATE ";
    public static final String INSERT_INTO = "INSERT INTO ";
    public static final String SET = " SET ";
    public static final String VALUES = "VALUES";
    private final Connection connection;
    private final String table;

    public AbstractDAO(Connection connection, String table) {
        this.connection = connection;
        this.table = table;
    }

    void init() {

    }

    public List<T> getAll(Class<T> cls)
            throws IllegalAccessException, InstantiationException {
        List<T> result = new ArrayList<>();
        try {
            try (Statement st = connection.createStatement()) {
                try (ResultSet resultSet = st.executeQuery(SELECT + FROM + table)) {
                    ResultSetMetaData md = resultSet.getMetaData();
                    while (resultSet.next()) {
                        T client = (T) cls.newInstance();
                        objectConstructor(cls, resultSet, md, client);
                        result.add(client);
                    }
                } catch (IllegalAccessException | NoSuchFieldException e) {
                    e.printStackTrace();
                }
            }
            return result;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }


    public T findById(Class<T> cls, long id)
            throws IllegalAccessException, InstantiationException {
        T client = (T) cls.newInstance();
        try (PreparedStatement query = connection.prepareStatement(SELECT + FROM + table + WHERE + ID)) {
            query.setLong(1, id);
            ResultSet resultSet = query.executeQuery();
            ResultSetMetaData md = resultSet.getMetaData();
            if (resultSet.next()) {
                objectConstructor(cls, resultSet, md, client);
            }
        } catch (SQLException | IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        return client;
    }

    public void create(Class<T> cls, T t)
            throws IllegalAccessException, InstantiationException, NoSuchFieldException {
        Field[] fields = cls.getDeclaredFields();
        String stringFields = toStringFields(fields);
        String stringParameters = toStringCountParameters(fields);
        try (PreparedStatement query = connection.prepareStatement(INSERT_INTO + table + stringFields + VALUES + stringParameters)) {
            setColumnsFromObject(t, query, fields);
            query.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Class<T> cls, T t, long id)
            throws IllegalAccessException, InstantiationException, NoSuchFieldException {
        Field[] fields = cls.getDeclaredFields();
        String s = toStringFields(fields);
        try (PreparedStatement query = connection.prepareStatement(UPDATE + table + SET + s + WHERE + ID)) {
            query.setLong(1, id);
            setColumnsFromObject(t, query, fields);
            query.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(long id) throws SQLException {
        try (PreparedStatement query = connection.prepareStatement(DELETE + FROM + table + WHERE + ID)) {
            query.setLong(1, id);
            query.executeUpdate();
        }
    }

    private void objectConstructor(Class<T> cls, ResultSet resultSet, ResultSetMetaData md, T client)
            throws SQLException, NoSuchFieldException, IllegalAccessException {
        for (int i = 1; i <= md.getColumnCount(); i++) {
            String columnName = md.getColumnName(i);
            Field field = cls.getDeclaredField(columnName);
            field.setAccessible(true);
            getColumn(resultSet, client, columnName, field);
        }
    }

    private void getColumn(ResultSet resultSet, T client, String columnName, Field field) throws IllegalAccessException, SQLException {
        if (field.getType().equals(int.class)) {
            field.set(client, Integer.valueOf(resultSet.getString(columnName)));
        }
        if (field.getType().equals(long.class)) {
            field.set(client, Long.valueOf(resultSet.getString(columnName)));
        }
        if (field.getType().equals(String.class)) {
            field.set(client, resultSet.getString(columnName));
        }
    }

    private void setColumnsFromObject(T t, PreparedStatement query, Field[] fields) throws SQLException, IllegalAccessException {
        for (int i = 1; i < fields.length; i++) {
            fields[i].setAccessible(true);
            if (fields[i].getType().equals(long.class)) {
                query.setLong(i, Long.valueOf(String.valueOf(fields[i].get(t))));
            } else if (fields[i].getType().equals(int.class)) {
                query.setInt(i, Integer.valueOf(String.valueOf(fields[i].get(t))));
            } else {
                query.setString(i, String.valueOf(fields[i].get(t)));
            }
        }
    }

    private String toStringFields(Field[] fields) {
        String s = "(";
        for (int i = 1; i < fields.length; i++) {
            if (i < fields.length - 1) {
                s = s + fields[i].getName() + ",";
            } else if (i == fields.length - 1) {
                s = s + fields[i].getName();
            }
        }
        s = s + ")";
        return s;
    }

    private String toStringCountParameters(Field[] fields) {
        String s = "(";
        for (int i = 1; i < fields.length; i++) {
            if (i < fields.length - 1) {
                s = s + "?" + ",";
            } else if (i == fields.length - 1) {
                s = s + "?";
            }
        }
        s = s + ")";
        return s;
    }
}
