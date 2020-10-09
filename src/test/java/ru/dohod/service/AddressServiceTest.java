package ru.dohod.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.dohod.dao.exception.ClientNotFoundException;
import ru.dohod.dao.interfaces.AddressDao;
import ru.dohod.dto.AddressDto;
import ru.dohod.entity.Address;
import ru.dohod.service.interfaces.AddressService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@SpringBootTest
class AddressServiceTest {

    @Autowired
    private AddressService addressService;

    @MockBean
    private AddressDao addressDao;

    Address address = new Address().setId(1L).setName("Name");

    @Test
    void getByClientId() {
        ArrayList<Address> addresses = new ArrayList<>();
        addresses.add(address);
        when(addressDao.getByClientId(1L)).thenReturn(addresses);

        List<AddressDto> byClientId = addressService.getByClientId(1L);
        assertEquals(byClientId.size(), 1);
        AddressDto addressDto = byClientId.get(0);
        assertEquals(addressDto.getId(), 1L);
        assertEquals(addressDto.getName(), "Name");
    }

    @Test
    void addByClientId() throws ClientNotFoundException {
        AddressDto addressDto = new AddressDto().setId(1L).setName("Name");
        when(addressDao.addByClientId(1L, address)).thenReturn(address);
        when(addressDao.addByClientId(2L, address)).thenThrow(new ClientNotFoundException("Test"));

        AddressDto kindaSaved = addressService.addByClientId(1L, addressDto);
        assertEquals(addressDto, kindaSaved);
        assertThrows(ClientNotFoundException.class, () -> {addressService.addByClientId(2L, addressDto);});
    }

    @Test
    void deleteById() {
        doNothing().when(addressDao).deleteById(1L);
        doThrow(EmptyResultDataAccessException.class).when(addressDao).deleteById(2L);

        assertDoesNotThrow(() -> {addressService.deleteById(1L);});
        assertThrows(EmptyResultDataAccessException.class, () -> {addressService.deleteById(2L);});
    }
}