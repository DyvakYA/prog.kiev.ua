package main.prog.dao;

import main.prog.entity.Client;

import java.util.List;

/**
 * Created by User on 12/9/2017.
 */
public interface DaoInterface {

    void create(Client client);

    List<Client> get();

    void delete(int id);
}
