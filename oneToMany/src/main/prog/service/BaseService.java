package main.prog.service;

import main.prog.entity.one2many.Client;

import java.util.List;

/**
 * Created by User on 12/17/2017.
 */
public interface BaseService {

    void create(Client client);

    List<Client> get();

    void delete(Long id);
}