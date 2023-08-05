package com.interswitch.customerManagementApp.service;

import com.interswitch.customerManagementApp.model.Customer;
import com.interswitch.customerManagementApp.repository.CustomCustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerDbService {
    private final CustomCustomerRepository customerRepository;
    public void addCustomer(Customer customer) {
        customer.setFullName(customer.getFullName());
        customer.setPhoneNumber(customer.getPhoneNumber());
        customer.setDateJoined(LocalDateTime.now());
        customerRepository.save(customer);
    }
    public Optional<Customer> getCustomerById(Long id){
        return Optional.ofNullable(customerRepository.findCustomerById(id));
    }
    public List<Customer> getAllCustomer(){
        return customerRepository.findAll();
    }

    public void updateCustomer(Long id, Customer customer) throws IllegalAccessException {
        Optional<Customer> registeredCustomer = Optional.ofNullable(customerRepository.findCustomerById(id));
        if (registeredCustomer.isPresent()){
            Customer customerUpdate = new Customer();
            customerUpdate.setFullName(customer.getFullName());
            customerUpdate.setPhoneNumber(customer.getPhoneNumber());
            customerUpdate.setDateJoined(registeredCustomer.get().getDateJoined());
            customerUpdate.setId(registeredCustomer.get().getId());
            customerRepository.save(customerUpdate);
            return;
        }throw new IllegalAccessException("Customer does not exist");
    }

    public String deleteCustomer(Long id){
        customerRepository.deleteCustomerById(id);
        return "Customer deleted succesffully";
    }
}