package com.example.pragraplex.repo;

import com.example.pragraplex.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {


    List<Customer> findCustomerByLastName(String lastName);

    //    @Query("select c from Customer c where length(c.login)< :length ")
    @Query(value = "SELECT * FROM CUSTOMER C WHERE LENGTH(C.login) <:length", nativeQuery = true)
    List<Customer> withLoginLessThanN(int length);

//    @Query(value = "SELECT * FROM CUSTOMER C WHERE C.email =:email",nativeQuery = true)
    Optional<Customer> findCustomerByEmail(String email);




}
