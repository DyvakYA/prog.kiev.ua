package main.prog.dao;

import main.prog.entity.one2many.Client;

import java.util.List;

/**
 * Created by User on 12/9/2017.
 */
public interface BaseDao {

    void create(Client client);

    List<Client> get();

    void delete(Long id);
}
