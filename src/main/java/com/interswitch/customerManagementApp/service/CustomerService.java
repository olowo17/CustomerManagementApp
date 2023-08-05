package com.interswitch.customerManagementApp.service;

import com.interswitch.customerManagementApp.model.Customer;
import com.interswitch.customerManagementApp.repository.CustomCustomerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class CustomerService {

    private final CustomCustomerRepository customerRepository;

    private static Map<Long, Customer> customerRepo = new HashMap<>();
    static {
        Customer wale = new Customer();
        wale.setId(1L);
        wale.setFullName("Adewale Thompson");
        wale.setPhoneNumber("0816345372");
        customerRepo.put(wale.getId(), wale);

        Customer chioma = new Customer();
        chioma.setId(2L);
        chioma.setFullName("Chukweze Chioma");
        chioma.setPhoneNumber("070145179");
        customerRepo.put(chioma.getId(), chioma);
    }

    public CustomerService(CustomCustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Collection<Customer> getCustomer() {
        return customerRepo.values();
    }

    public String addCustomer(Customer customer) {
        if (customerRepo.containsKey(customer.getId())) {
            throw new IllegalArgumentException("Customer with id " + customer.getId() + " already exists.");
        } else if (customerRepo.values().stream().anyMatch(c -> c.getPhoneNumber().equals(customer.getPhoneNumber()))) {
            throw new IllegalArgumentException("Customer with phone number " + customer.getPhoneNumber() + " already exists.");
        }

        customer.setDateJoined(LocalDateTime.now());
        customerRepo.put(customer.getId(), customer);
        customerRepository.save(customer);
        return "Customer created successfully";
    }

    public void deleteCustomer(Long id) {
        customerRepo.remove(id);
    }
    public void updateCustomer(Long id, Customer customer) {
        if (customerRepo.containsKey(id)) {
            customerRepo.remove(id);
            customerRepo.put(customer.getId(), customer);
        }
    }

    public Customer getCustomer(Long id) {
        return customerRepo.get(id);
    }
//    public Collection <Customer> getCustomer(){
//        return  customerRepo.values();
//    }

}
