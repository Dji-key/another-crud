package ru.dohod.service.interfaces;

import ru.dohod.dto.ClientDto;

import java.util.List;

/**
 * Сервис для работы с клиентом
 */
public interface ClientService {

    /**
     * Возвращает список всех клиентов
     *
     * @return список из {@link ClientDto}
     */
    List<ClientDto> getAll();

    /**
     * Находит всех клиентов, в имя которых входит указанная подстрока
     * @param name искомая подстрока
     * @return список из {@link ClientDto}
     */
    List<ClientDto> findByName(String name);

    /**
     * Удаляет клиента с указанным идентификатором
     *
     * @param id идентификатор клиента
     */
    void deleteById(Long id);

    /**
     * Сохраняет клиента
     *
     * @param clientDto клиент
     * @return {@link ClientDto} клиента
     */
    ClientDto save(ClientDto clientDto);
}
