package br.com.jandernery.precojusto.presenters;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jandernery.precojusto.application.dto.CustomerRecord;
import br.com.jandernery.precojusto.application.services.CustomerService;
import br.com.jandernery.precojusto.domain.entities.CustomerEntity;

@RestController
@RequestMapping("/customers")
public class CustomersController {

    private final CustomerService customerService;

    public CustomersController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public void createCustomer(@RequestBody CustomerRecord customerDto) {
        customerService.createCustomer(customerDto);
    }

    @GetMapping
    public ResponseEntity<List<CustomerEntity>> getAllCustomers() {
        List<CustomerEntity> customer = customerService.getAllCustomers();
        return ResponseEntity.ok().body(customer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerEntity> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(customerService.findById(id));
    }

    @PatchMapping("/discount/{id}")
    public ResponseEntity<CustomerEntity> setDiscount(@PathVariable UUID id) {
        return ResponseEntity.ok(customerService.setDiscount(id));
    }

}
