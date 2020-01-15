package com.hivetech.SpringREST.controller;

import com.hivetech.SpringREST.model.Customer;
import com.hivetech.SpringREST.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping("/customers")
    public List<Customer> getListCustomer(){
        return customerService.listCustomer();
    }

    @GetMapping("/")
    public ResponseEntity<?> home() {
        return new ResponseEntity<>("REST API - Base 1", HttpStatus.OK);
    }

    @GetMapping(value = "/customers/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Customer> getCutomer(@PathVariable("id") int id) {
        System.out.println("Fetching Customer with id " + id);
        return customerService.getCustomerById(id);
    }

    @PostMapping("/customers/add")
    public boolean addCustomer(@RequestBody Customer customer){
        return customerService.add(customer);
    }
}
