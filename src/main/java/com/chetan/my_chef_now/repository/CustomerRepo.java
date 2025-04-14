package com.chetan.my_chef_now.repository;

import com.chetan.my_chef_now.model.Admin;
import com.chetan.my_chef_now.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {
    Customer findByCustomerEmail(String email);
}
