package ru.dohod.service.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dohod.dao.interfaces.ClientDao;
import ru.dohod.dto.ClientDto;
import ru.dohod.entity.Client;
import ru.dohod.service.interfaces.ClientService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientDao clientDao;

    private ModelMapper mapper;

    @Autowired
    public ClientServiceImpl(ClientDao clientDao, ModelMapper mapper) {
        this.mapper = mapper;
        this.clientDao = clientDao;
    }

    public List<ClientDto> getAll() {
        return clientDao.getAll()
                .parallelStream()
                .map(client -> mapper.map(client, ClientDto.class))
                .collect(Collectors.toList());
    }

    public List<ClientDto> findByName(String name) {
        return clientDao.findByName(name)
                .parallelStream()
                .map(client -> mapper.map(client, ClientDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        clientDao.delete(id);
    }

    @Override
    public ClientDto save(ClientDto clientDto) {
        Client entity = mapper.map(clientDto, Client.class);
        Client saved = clientDao.save(entity);
        return mapper.map(saved, ClientDto.class);
    }
}
