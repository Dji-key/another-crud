package ru.dohod.dao.interfaces;

import ru.dohod.dao.exception.ClientNotFoundException;
import ru.dohod.entity.Address;

import java.util.List;

/**
 * DAO для работы с адресом {@link Address}
 */
public interface AddressDao {

    List<Address> getByClientId(Long clientId);

    Address addByClientId(Long clientId, Address address) throws ClientNotFoundException;

    void deleteById(Long id);
}
