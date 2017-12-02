package mysql1.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by User on 11/30/2017.
 */
public interface GenericDAO<T> {

    List<T> getAll() throws InstantiationException, IllegalAccessException;

    T findById(int id) throws InstantiationException, IllegalAccessException;

    void create(T t) throws IllegalAccessException, NoSuchFieldException, InstantiationException;

    void update(T t) throws IllegalAccessException, NoSuchFieldException, InstantiationException;

    void delete(long id) throws SQLException;

}
