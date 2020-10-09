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
import ru.dohod.dto.ClientDto;
import ru.dohod.service.interfaces.ClientService;

@RestController
@RequestMapping("/client")
public class ClientController {

    private static final Logger logger = LogManager.getLogger(ClientController.class);

    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(clientService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> findByName(@RequestParam String name) {
        return new ResponseEntity<>(clientService.findByName(name), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ClientDto clientDto) {
        return new ResponseEntity<>(clientService.save(clientDto), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            clientService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            logger.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
