package ru.dohod.dao.interfaces;

import ru.dohod.dao.exception.ClientNotFoundException;
import ru.dohod.entity.Client;

import java.util.List;

/**
 * DAO для работы с клиентом {@link Client}
 */
public interface ClientDao {

    List<Client> getAll();

    List<Client> findByName(String name);

    void deleteById(Long id);

    Client save(Client client);

    Client findById(Long clientId) throws ClientNotFoundException;
}
