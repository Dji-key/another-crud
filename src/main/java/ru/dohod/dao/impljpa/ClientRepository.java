package ru.dohod.dao.impljpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dohod.entity.Client;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findByNameContainsIgnoreCase(String name);
}
