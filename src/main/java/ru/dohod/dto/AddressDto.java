package ru.dohod.dto;

public class AddressDto {

    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public AddressDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AddressDto setName(String name) {
        this.name = name;
        return this;
    }
}
