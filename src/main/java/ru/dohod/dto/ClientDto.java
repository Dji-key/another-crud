package ru.dohod.dto;

import java.util.HashSet;
import java.util.Set;

public class ClientDto {

    private Long id;

    private String name;

    private Set<AddressDto> addresses = new HashSet<>();

    public Long getId() {
        return id;
    }

    public ClientDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ClientDto setName(String name) {
        this.name = name;
        return this;
    }

    public Set<AddressDto> getAddresses() {
        return addresses;
    }

    public ClientDto setAddresses(Set<AddressDto> addresses) {
        this.addresses = addresses;
        return this;
    }
}
