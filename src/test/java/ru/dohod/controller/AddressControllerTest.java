package ru.dohod.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.dohod.dao.exception.ClientNotFoundException;
import ru.dohod.dto.AddressDto;
import ru.dohod.service.interfaces.AddressService;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.AdditionalMatchers.not;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressService addressService;

    @Test
    void getByClientId() throws Exception {
        when(addressService.getByClientId(1L)).thenReturn(new ArrayList<>());

        MvcResult mvcResult = mockMvc.perform(get("/address/list?clientId=1")).andExpect(status().isOk()).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "[]");
    }

    @Test
    void add() throws Exception {
        when(addressService.addByClientId(eq(1L), any(AddressDto.class))).thenReturn(new AddressDto());
        when(addressService.addByClientId(not(eq(1L)), any(AddressDto.class))).thenThrow(new ClientNotFoundException("test"));

        AddressDto address = new AddressDto().setId(1L).setName("AddressName");
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(address);

        mockMvc.perform(post("/address/add/1").content(content).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
        mockMvc.perform(post("/address/add/2").content(content).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
    }

    @Test
    void deleteById() throws Exception {
        doNothing().when(addressService).deleteById(1L);
        doThrow(EmptyResultDataAccessException.class).when(addressService).deleteById(2L);

        mockMvc.perform(delete("/address/delete/1")).andExpect(status().isOk());
        mockMvc.perform(delete("/address/delete/2")).andExpect(status().isNoContent());
    }
}