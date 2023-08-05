package com.interswitch.customerManagementApp.repository;

import com.interswitch.customerManagementApp.model.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//@Repository
public class CustomerRepo implements CustomCustomerRepository{

    private final Map<Long, Customer> customerMap;

    public CustomerRepo(Map<Long, Customer> customerMap) {
        this.customerMap = customerMap;
    }

    @Override
    public void save(Customer customer) {
        customerMap.put(customer.getId(),customer);
    }

    @Override
    public Customer update(Long id, Customer customer) {
        customerMap.remove(id,customer);
        return customerMap.get(id);
    }

    @Override
    public Customer findCustomerById(Long id) {
        return customerMap.get(id);
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerMap.remove(id);
    }

    @Override
    public List<Customer> findAll() {
        return new ArrayList<>(customerMap.values());
    }
}
