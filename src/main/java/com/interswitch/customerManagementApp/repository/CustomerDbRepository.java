package com.interswitch.customerManagementApp.repository;

import com.interswitch.customerManagementApp.model.Customer;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Primary
//@Repository
@Repository
@RequiredArgsConstructor
public class CustomerDbRepository implements CustomCustomerRepository {
    private  final JpaRepository<Customer, Long> jpaRepository;

    @Override
    public void save(Customer customer) {
        jpaRepository.save(customer);
    }

    @Override
    public Customer update(Long id, Customer customer) {
        var foundCustomer= jpaRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Customer not found"));
        foundCustomer.setPhoneNumber(customer.getPhoneNumber());
        foundCustomer.setFullName(customer.getFullName());
        return  foundCustomer;
    }

    @Override
    public Customer findCustomerById(Long id) {
        return jpaRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Customer not found"));
    }

    @Override
    public void deleteCustomerById(Long id) {
        Customer customerToDelete = jpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        jpaRepository.deleteById(id);
    }


    @Override
    public List<Customer> findAll() {
        return jpaRepository.findAll();
    }
}
