package ru.dohod.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private Set<Address> addresses = new HashSet<>();

    public Long getId() {
        return id;
    }

    public Client setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Client setName(String name) {
        this.name = name;
        return this;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public Client setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (id != null ? !id.equals(client.id) : client.id != null) return false;
        return name != null ? name.equals(client.name) : client.name == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
