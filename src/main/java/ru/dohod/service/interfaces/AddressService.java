package ru.dohod.service.interfaces;

import ru.dohod.dao.exception.ClientNotFoundException;
import ru.dohod.dto.AddressDto;

import java.util.List;

/**
 * Сервис для работы с адресом
 */
public interface AddressService {

    /**
     * Возвращает список из адресов клиента по его идентификатору.
     *
     * @param clientId идентификатор клиента {@link ru.dohod.entity.Client}
     * @return список из {@link AddressDto}
     */
    List<AddressDto> getByClientId(Long clientId);

    /**
     * Добавляет к клиенту с указанным идентификатором новый адрес
     *
     * @param clientId идентификатор клиента {@link ru.dohod.entity.Client}
     * @param addressDto адрес для добавления
     * @return сохранённый адрес {@link AddressDto}
     * @throws ClientNotFoundException в случае если клиент с таким id не найден
     */
    AddressDto addByClientId(Long clientId, AddressDto addressDto) throws ClientNotFoundException;

    /**
     * Удаляет адрес с указанным идентификатором
     *
     * @param id идентификатор адреса
     */
    void deleteById(Long id);
}
