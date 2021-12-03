package com.mohamed.inventorymanagementsystem.resource;

import com.mohamed.inventorymanagementsystem.dao.CustomerRepository;
import com.mohamed.inventorymanagementsystem.dto.Customer;
import com.mohamed.inventorymanagementsystem.exception.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class CustomerResource {
    private final CustomerRepository customerRepository;

    public CustomerResource(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    @PostMapping(value = "/Customer",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveNewCustomer(@RequestBody @Valid Customer customer){
        Optional<Customer>customerOptional=customerRepository.findCustomerByEmail(customer.getEmail());
        customerOptional.ifPresent(value -> new ResponseEntity<>(value, HttpStatus.FOUND));
        return new ResponseEntity(customerRepository.save(customer),HttpStatus.CREATED);
    }
    @PutMapping(value = "/changeAddress/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer changeAddressById(@PathVariable("id")Long id,@RequestBody String address)throws ResourceNotFound{
        Optional<Customer>optionalCustomer=customerRepository.findById(id);
        Customer customer= optionalCustomer.orElseThrow(()->new ResourceNotFound("Object not found"));
        customer.setAddress(address);
        customerRepository.save(customer);
        return customer;
    }
}
