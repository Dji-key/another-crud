package ru.dohod.dao.interfaces;

import ru.dohod.entity.Client;

import java.util.List;

public interface ClientDao {

    List<Client> getAll();

    List<Client> findByName(String name);

    void delete(Long id);

    Client save(Client client);
}
