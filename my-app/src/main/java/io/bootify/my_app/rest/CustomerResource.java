package io.bootify.my_app.rest;

import io.bootify.my_app.model.CustomerDTO;
import io.bootify.my_app.service.CustomerService;
import io.bootify.my_app.util.ReferencedException;
import io.bootify.my_app.util.ReferencedWarning;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/customers", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerResource {

    private final CustomerService customerService;

    public CustomerResource(final CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        return ResponseEntity.ok(customerService.findAll());
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> getCustomer(
            @PathVariable(name = "customerId") final Integer customerId) {
        return ResponseEntity.ok(customerService.get(customerId));
    }

    @PostMapping
    public ResponseEntity<Integer> createCustomer(
            @RequestBody @Valid final CustomerDTO customerDTO) {
        final Integer createdCustomerId = customerService.create(customerDTO);
        return new ResponseEntity<>(createdCustomerId, HttpStatus.CREATED);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<Integer> updateCustomer(
            @PathVariable(name = "customerId") final Integer customerId,
            @RequestBody @Valid final CustomerDTO customerDTO) {
        customerService.update(customerId, customerDTO);
        return ResponseEntity.ok(customerId);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(
            @PathVariable(name = "customerId") final Integer customerId) {
        final ReferencedWarning referencedWarning = customerService.getReferencedWarning(customerId);
        if (referencedWarning != null) {
            throw new ReferencedException(referencedWarning);
        }
        customerService.delete(customerId);
        return ResponseEntity.noContent().build();
    }

}
