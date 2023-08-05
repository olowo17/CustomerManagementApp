package com.interswitch.customerManagementApp.controller;
import com.interswitch.customerManagementApp.model.Customer;
import com.interswitch.customerManagementApp.service.CustomerDbService;
import com.interswitch.customerManagementApp.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("customers/api/v1/")
public class CustomerDbController {
    private  final CustomerDbService customerDbService;

//    @RequestMapping (value ="/customers")
    @GetMapping("get-all")
    public  ResponseEntity <Object> getCustomers (){
        return new ResponseEntity<>(customerDbService.getAllCustomer() ,HttpStatus.OK);
    }

//    @RequestMapping(value="/customers/{id}")
    @GetMapping("{id}")
    public ResponseEntity <Object> getCustomerById(@PathVariable("id")Long id){
        return new ResponseEntity<>(customerDbService.getCustomerById(id) ,HttpStatus.OK);
    }

//    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> createCustomer(@RequestBody Customer customer) {
        try {
            customerDbService.addCustomer(customer);
            return new ResponseEntity<>("Customer created successfully", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
//    @RequestMapping(value = "customers/{id}", method = RequestMethod.PUT)
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public  ResponseEntity <Object> updateCustomer(@PathVariable("id") Long id,@RequestBody Customer customer) throws IllegalAccessException {
        customerDbService.updateCustomer(id, customer);
        return  new ResponseEntity<>("Customer is updated" + "successfully",HttpStatus.OK);
    }
//    @RequestMapping(value = "/customers/{id}", method = RequestMethod.DELETE)
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity <Object> delete(@PathVariable("id") Long id){
        customerDbService.deleteCustomer(id);
        return new ResponseEntity<>("Customer is deleted successfully", HttpStatus.OK);
    }

}
