package ru.dohod.dao.impljpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.dohod.dao.exception.ClientNotFoundException;
import ru.dohod.dao.interfaces.ClientDao;
import ru.dohod.entity.Client;

import java.text.MessageFormat;
import java.util.List;

@Repository
public class ClientDaoImpl implements ClientDao {

    private ClientRepository clientRepository;

    @Autowired
    public ClientDaoImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    @Override
    public List<Client> findByName(String name) {
        return clientRepository.findByNameContainsIgnoreCase(name);
    }

    @Override
    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client findById(Long clientId) throws ClientNotFoundException {
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException(MessageFormat.format("Client with id {0} was not found", clientId)));
    }
}
