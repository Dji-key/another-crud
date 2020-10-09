package ru.dohod.dao.impljpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.dohod.dao.exception.ClientNotFoundException;
import ru.dohod.dao.interfaces.AddressDao;
import ru.dohod.dao.interfaces.ClientDao;
import ru.dohod.entity.Address;
import ru.dohod.entity.Client;

import java.util.List;

@Repository
public class AddressDaoImpl implements AddressDao {

    private AddressRepository addressRepository;

    private ClientDao clientDao;

    @Autowired
    public AddressDaoImpl(AddressRepository addressRepository, ClientDao clientDao) {
        this.addressRepository = addressRepository;
        this.clientDao = clientDao;
    }

    @Override
    public List<Address> getByClientId(Long clientId) {
        return addressRepository.findAllByClientId(clientId);
    }

    @Override
    public Address addByClientId(Long clientId, Address address) throws ClientNotFoundException {
        Client found = clientDao.findById(clientId);
        address.setClient(found);
        return addressRepository.save(address);
    }

    @Override
    public void deleteById(Long id) {
        addressRepository.deleteById(id);
    }
}
