package ru.dohod.dao.jpaimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.dohod.dao.interfaces.ClientDao;
import ru.dohod.entity.Client;

import java.util.List;

@Repository
public class ClientDaoImpl implements ClientDao {

    private ClientRepository clientRepository;

    @Autowired
    public ClientDaoImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    @Override
    public List<Client> findByName(String name) {
        return clientRepository.findByNameContainsIgnoreCase(name);
    }

    @Override
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }
}
