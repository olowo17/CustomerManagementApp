package com.interswitch.customerManagementApp.controller;

import com.interswitch.customerManagementApp.model.Customer;
import com.interswitch.customerManagementApp.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @RequestMapping(value = "/customers/register", method = RequestMethod.POST)
    public ResponseEntity<Object> registerCustomer(@RequestBody Customer customer){
        try {
            customerService.addCustomer(customer);
            return new ResponseEntity<>("Customer created successfully", HttpStatus.CREATED);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }


    @RequestMapping(value = "/customers/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getCustomer(@PathVariable("id") String id){
        return new ResponseEntity<>(customerService.getCustomer(Long.valueOf(id)), HttpStatus.OK);
    }

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public ResponseEntity<Object> getCustomer(){
        return new ResponseEntity<>(customerService.getCustomer(), HttpStatus.OK);
    }
    @RequestMapping(value = "/customers/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customer){
        customerService.updateCustomer(id, customer);
        return new ResponseEntity<>("Customer updated successfully", HttpStatus.OK);
    }
}