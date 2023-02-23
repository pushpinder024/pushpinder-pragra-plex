package com.example.pragraplex.service;

import com.example.pragraplex.entity.Customer;
import com.example.pragraplex.exceptions.CustomerNotFoundException;
import com.example.pragraplex.exceptions.EmailAlreadyInUseException;
import com.example.pragraplex.exceptions.UnsupportedLoginNameException;
import com.example.pragraplex.repo.CustomerRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepo repo;

    public CustomerServiceImpl(CustomerRepo repo) {
        this.repo = repo;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        if (customer.getLogin() == null || customer.getLogin().equals("")) {
            throw new UnsupportedLoginNameException("Login can't be null");
        }
        Optional<Customer> customer1 = repo.findCustomerByEmail(customer.getEmail());

        if(customer1.isPresent()){
            throw new EmailAlreadyInUseException(String.format("Email %s already in use",customer.getEmail()));
        }

//        if(repo.findCustomerByEmail(customer.getEmail())!=null){
//            throw new EmailAlreadyInUseException(String.format("Email %s already in use",customer.getEmail()));
//        }

        return repo.save(customer);
    }


    @Override
    public List<Customer> getAllCustomer() {
        return repo.findAll();
    }

    @Override
    public Optional<Customer> getCustomerById(Integer id) {
        Optional<Customer> customerById = repo.findById(id);
        Customer customer = customerById.orElseThrow(
                () -> new CustomerNotFoundException(String.format("Customer with Id %d not found", id))
        );
        return customerById;
    }

    @Override
    public List<Customer> createAll(List<Customer> customers) {
        customers.forEach(cust -> {
            if (cust.getLogin() == null || cust.getLogin().equals("")) {
                throw new UnsupportedLoginNameException("Login cannot be null");
            }
        });

        customers.forEach(cust1-> {
            if(repo.findCustomerByEmail(cust1.getEmail()).isPresent()){
                throw new EmailAlreadyInUseException(String.format("Email %s already in use",cust1.getEmail()));
            }
        });
        return repo.saveAll(customers);


    }

    @Override
    public List<Customer> getByLastName(String lastName) {
        return repo.findCustomerByLastName(lastName);
    }

    @Override
    public List<Customer> getByLoginLength(int length) {
        return repo.withLoginLessThanN(length);
    }

    @Override
    public void removeCustomerById(int custId) {
        repo.findById(custId).orElseThrow(
                () -> new CustomerNotFoundException(String.format("Customer with Id %d not found", custId))
        );
        repo.deleteById(custId);
    }

    @Override
    public Customer updateCustomerById(Customer customer) {
        return repo.save(customer);

    }

    @Override
    public Optional<Customer> getCustomerByEmail(String email) {
//        Optional<Customer> customerByEmail = repo.findCustomerByEmail(email);
//        if(customerByEmail==null){
//            throw new CustomerNotFoundException(String.format("Customer with Email %s not found",email));
//        }
        return repo.findCustomerByEmail(email);
    }
}
