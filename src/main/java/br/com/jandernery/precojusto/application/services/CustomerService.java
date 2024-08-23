package br.com.jandernery.precojusto.application.services;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.NotFound;
import org.springframework.stereotype.Service;

import br.com.jandernery.precojusto.application.dto.CustomerRecord;
import br.com.jandernery.precojusto.domain.entities.CustomerEntity;
import br.com.jandernery.precojusto.infra.repositories.CustomerRepository;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerEntity> getAllCustomers() {
        List<CustomerEntity> customer = customerRepository.findAll();

        return customer;
    }

    public void createCustomer(CustomerRecord customerDto) {
        CustomerEntity customer = new CustomerEntity();
        customer.setName(customerDto.name());
        customer.setEmail(customerDto.email());
        customer.setPhone(customerDto.phone());

        customerRepository.save(customer);
    }

    public CustomerEntity setDiscount(UUID id) {
        CustomerEntity customer = customerRepository.findById(id).get();
        customer.setEligibleDiscount(true);
        return customerRepository.save(customer);
    }

    public CustomerEntity findById(UUID id) {
        CustomerEntity customer = customerRepository.findById(id).get();
        return customer;
    }

}
