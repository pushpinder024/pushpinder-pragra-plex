package com.example.pragraplex.rest;

import com.example.pragraplex.entity.Customer;
import com.example.pragraplex.exceptions.CustomerNotFoundException;
import com.example.pragraplex.service.CustomerService;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CustomerApi {

    private CustomerService customerService;

    public CustomerApi(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customer")
    public ResponseEntity<List<Customer>> getAll() {
        return ResponseEntity.status(HttpStatus.FOUND).body(customerService.getAllCustomer());
    }

    @GetMapping("/customer/byLastName")
    public ResponseEntity<List<Customer>> getByLastName(@RequestParam String lastName){
        return ResponseEntity.status(HttpStatus.FOUND).body(customerService.getByLastName(lastName));
    }

    @PostMapping("/customer")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        customer.setCreateDate(new Date());
        customer.setUpdateDate(new Date());
        return ResponseEntity.status(HttpStatus.CREATED).header("X-CREATOR","PUSHPINDER").body(customerService.createCustomer(customer));
    }

    @PostMapping("/customers")
    public ResponseEntity<List<Customer>> createAll(@RequestBody List<Customer> customers) {
        customers.forEach(cust -> cust.setUpdateDate(new Date()));
        customers.forEach(cust -> cust.setCreateDate(new Date()));
        return ResponseEntity.status(HttpStatus.CREATED).header("X-CREATOR","PUSHPINDER").body(customerService.createAll(customers));
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<Optional<Customer>> getById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.FOUND).body(customerService.getCustomerById(id));
    }

    @GetMapping("/customers/byLogin/{length}")
    public ResponseEntity<List<Customer>> getByLoginLessthanN(@PathVariable int length) {
        return ResponseEntity.status(HttpStatus.FOUND).body(customerService.getByLoginLength(length));
    }


    @DeleteMapping("/customer/remove")
    public void removeCustomerById(@RequestParam(name = "custId") int custId) {
          customerService.removeCustomerById(custId);
    }


    @PutMapping("/customer/update")
    public Customer updateCustomerById(@RequestBody Customer customer) {
       return customerService.updateCustomerById(customer);
    }

    @GetMapping("/customer/byEmail/{email}")
    public Optional<Customer> getWithEmail(@PathVariable String email){

        return customerService.getCustomerByEmail(email);
    }

}
