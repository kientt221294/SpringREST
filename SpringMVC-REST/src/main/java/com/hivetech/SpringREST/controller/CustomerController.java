package com.hivetech.SpringREST.controller;

import com.hivetech.SpringREST.model.Customer;
import com.hivetech.SpringREST.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return new ResponseEntity<>("CRM REST API - Base 1", HttpStatus.OK);
    }
}
