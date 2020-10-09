package ru.dohod.service.interfaces;

import ru.dohod.dto.ClientDto;

import java.util.List;

public interface ClientService {

    List<ClientDto> getAll();

    List<ClientDto> findByName(String name);

    void delete(Long id);

    ClientDto save(ClientDto clientDto);
}
