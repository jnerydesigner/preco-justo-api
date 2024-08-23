package br.com.jandernery.precojusto.infra.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.jandernery.precojusto.domain.entities.CustomerEntity;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, UUID> {
    @Query("SELECT c FROM CustomerEntity c")
    List<CustomerEntity> findAllCustomers();

}
