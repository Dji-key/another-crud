package ru.dohod.service.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dohod.dao.interfaces.AddressDao;
import ru.dohod.dto.AddressDto;
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
}
