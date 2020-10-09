package ru.dohod.dao.jpaimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.dohod.dao.interfaces.AddressDao;
import ru.dohod.entity.Address;

import java.util.List;

@Repository
public class AddressDaoImpl implements AddressDao {

    private AddressRepository addressRepository;

    @Autowired
    public AddressDaoImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Address> getByClientId(Long clientId) {
        return addressRepository.findAllByClientId(clientId);
    }
}
