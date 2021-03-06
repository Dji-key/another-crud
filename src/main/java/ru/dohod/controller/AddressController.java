package ru.dohod.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.dohod.dao.exception.ClientNotFoundException;
import ru.dohod.dto.AddressDto;
import ru.dohod.service.interfaces.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {

    private static final Logger logger = LogManager.getLogger(ClientController.class);

    private AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/list")
    public ResponseEntity<?> getByClientId(@RequestParam Long clientId) {
        return new ResponseEntity<>(addressService.getByClientId(clientId), HttpStatus.OK);
    }

    @PostMapping("/add/{clientId}")
    public ResponseEntity<?> add(@PathVariable Long clientId, @RequestBody AddressDto addressDto) {
        try {
            return new ResponseEntity<>(addressService.addByClientId(clientId, addressDto), HttpStatus.OK);
        } catch (ClientNotFoundException e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            addressService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            logger.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
