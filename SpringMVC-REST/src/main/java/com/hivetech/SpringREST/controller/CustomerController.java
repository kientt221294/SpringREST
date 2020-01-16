package com.hivetech.SpringREST.controller;

import com.hivetech.SpringREST.model.Customer;
import com.hivetech.SpringREST.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class CustomerController {

    private static final Logger LOGGER = Logger.getLogger(CustomerController.class.getTypeName());

    @Autowired
    private CustomerService customerService;
    @Autowired
    private ApplicationContext applicationContext;

    @GetMapping(value = "/customers")
    public List<Customer> getListCustomer(){
        return customerService.listCustomer();
    }

    @GetMapping(value = "/")
    public ResponseEntity<?> home() {
        return new ResponseEntity<>("REST API - Base 1", HttpStatus.OK);
    }

    @GetMapping(value = "/customers/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Customer> getCutomer(@PathVariable("id") int id) {
        LOGGER.info("Fetching Customer with id " + id);
        return customerService.getCustomerById(id);
    }

    @GetMapping(value = "/customers/getById", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Customer> getCutomerByRequestParam(@RequestParam("id") int id, HttpServletRequest request) {
        LOGGER.info("Fetching Customer with id " + id);
        LOGGER.info("URL: " + request.getRequestURL().toString());
        return customerService.getCustomerById(id);
    }

    @PostMapping("/customers/add")
    public boolean addCustomer(@RequestBody Customer customer){
        return customerService.add(customer);
    }

}
