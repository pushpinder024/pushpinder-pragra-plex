package com.example.pragraplex.service;

import com.example.pragraplex.entity.Customer;
import com.example.pragraplex.exceptions.EmailAlreadyInUseException;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Customer createCustomer(Customer customer) throws EmailAlreadyInUseException;

    List<Customer> getAllCustomer();

    Optional<Customer> getCustomerById(Integer id);

    List<Customer> createAll(List<Customer> customers);

    List<Customer> getByLastName(String lastName);

    List<Customer> getByLoginLength(int length);

    void removeCustomerById(int custId);
    Customer updateCustomerById(Customer customer);
    Optional<Customer> getCustomerByEmail(String email);

}
