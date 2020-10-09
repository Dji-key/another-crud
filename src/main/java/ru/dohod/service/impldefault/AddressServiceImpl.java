package ru.dohod.service.impldefault;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dohod.dao.exception.ClientNotFoundException;
import ru.dohod.dao.interfaces.AddressDao;
import ru.dohod.dto.AddressDto;
import ru.dohod.entity.Address;
import ru.dohod.service.interfaces.AddressService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    private AddressDao addressDao;

    private ModelMapper mapper;

    @Autowired
    public AddressServiceImpl(AddressDao addressDao, ModelMapper mapper) {
        this.addressDao = addressDao;
        this.mapper = mapper;
    }

    @Override
    public List<AddressDto> getByClientId(Long clientId) {
        return addressDao.getByClientId(clientId)
                .parallelStream()
                .map(address -> mapper.map(address, AddressDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public AddressDto addByClientId(Long clientId, AddressDto addressDto) throws ClientNotFoundException {
        Address address = mapper.map(addressDto, Address.class);
        Address saved = addressDao.addByClientId(clientId, address);
        return mapper.map(saved, AddressDto.class);
    }

    @Override
    public void deleteById(Long id) {
        addressDao.deleteById(id);
    }
}
