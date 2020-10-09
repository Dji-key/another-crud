package ru.dohod.service.interfaces;

import ru.dohod.dto.AddressDto;

import java.util.List;

public interface AddressService {

    List<AddressDto> getByClientId(Long clientId);
}
