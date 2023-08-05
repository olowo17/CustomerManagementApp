package com.interswitch.customerManagementApp.service;

import com.interswitch.customerManagementApp.model.Customer;
import com.interswitch.customerManagementApp.repository.CustomCustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

public class CustomerTest {
    private CustomCustomerRepository customCustomerRepository;
    private CustomerDbService customerDbService;
    Customer customer = new Customer();

    @BeforeEach
    void setUp() {
        customCustomerRepository = mock(CustomCustomerRepository.class);
        customerDbService = new CustomerDbService(customCustomerRepository);
        customer.setFullName("Kolapo Chiwendu");
        customer.setPhoneNumber("081339833521");
        customer.setId(4L);

    }

    @Test
    void testCanAddCustomer() throws Exception {
        customerDbService.addCustomer(customer);
        assertEquals("Kolapo Chiwendu", customer.getFullName());
    }

    @Test
    void testCanUpdateCustomer() throws Exception {
        Customer update = new Customer();
        update.setFullName("Ezekiel Chiwendu");

        when(customCustomerRepository.findCustomerById(customer.getId())).thenReturn(customer);
        customerDbService.updateCustomer(customer.getId(), update);
        assertEquals("Kolapo Chiwendu", customer.getFullName());
    }

    @Test
    void testThatExceptionThrownIfIdNotFound() throws Exception {
        Customer update = new Customer();
        update.setFullName("Kolapo Chiwendu");

        when(customCustomerRepository.findCustomerById(customer.getId()))
                .thenReturn(customer);
        assertThrows(IllegalAccessException.class, () -> customerDbService.updateCustomer(2L, update));
    }

    @Test
    void testGetCustomerById() {
        when(customCustomerRepository.findCustomerById(customer.getId())).thenReturn(customer);
    }

    @Test
    void testDeleteCustomerById() {
//        when(customCustomerRepository.deleteCustomerById(customer.getId()));
        String message = customerDbService.deleteCustomer(customer.getId());
        assertEquals("Customer deleted succesffully", message);

    }
}