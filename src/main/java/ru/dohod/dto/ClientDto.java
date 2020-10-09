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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientDto clientDto = (ClientDto) o;

        if (id != null ? !id.equals(clientDto.id) : clientDto.id != null) return false;
        return name != null ? name.equals(clientDto.name) : clientDto.name == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
