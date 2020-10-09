package ru.dohod.dao.interfaces;

import ru.dohod.entity.Address;

import java.util.List;

public interface AddressDao {

    List<Address> getByClientId(Long clientId);
}
