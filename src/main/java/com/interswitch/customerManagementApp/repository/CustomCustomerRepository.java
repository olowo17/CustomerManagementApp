package com.interswitch.customerManagementApp.repository;

import com.interswitch.customerManagementApp.model.Customer;


import java.util.List;

public interface CustomCustomerRepository {
    void save (Customer customer);
    Customer update(Long id, Customer customer);
    Customer findCustomerById(Long id);

    void deleteCustomerById(Long id);
   List<Customer> findAll();
}

