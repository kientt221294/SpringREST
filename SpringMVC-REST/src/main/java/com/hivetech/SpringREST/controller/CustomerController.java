package com.hivetech.SpringREST.controller;

import com.hivetech.SpringREST.model.Customer;
import com.hivetech.SpringREST.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
public class CustomerController {

    private static final Logger LOGGER = Logger.getLogger(CustomerController.class.getTypeName());

    @Autowired
    CustomerService customerService;

    @GetMapping(value = "/customers", consumes = "application/json")
    public List<Customer> getListCustomer(){
        return customerService.listCustomer();
    }

    @GetMapping(value = "/", consumes = "application/json")
    public ResponseEntity<?> home() {
        return new ResponseEntity<>("CRM REST API - Base 1", HttpStatus.OK);
    }

    //=================
    @PostMapping(value = "/customer/update")
    public boolean updateCustomerWithCustomerType(@RequestBody Customer customer){
        boolean isUpdated = customerService.update(customer);

        LOGGER.info(String.format("updateCustomerWithCustomerType: [%s], is Updated: [%b]", customer, isUpdated));

        return isUpdated;
    }

    @PostMapping(value = "/customer/update/params?customerNumber={1},customerName={2},contactLastName={3}")
    public boolean updateCustomerWithPathVarible(@RequestParam("1") int customerNumber, String customerName, String contactLastName){

        Customer temp = new Customer(customerNumber, customerName, contactLastName);
        boolean isUpdated = customerService.update(temp);
        LOGGER.info(String.format("updateCustomerWithPathVarible: [%s], is Updated: [%b]", temp, isUpdated));

        return isUpdated;
    }

    @PostMapping(value = "/customer/add")
    public boolean addCustomerWithCustType(@RequestAttribute Customer customer){

        boolean isAdded = customerService.add(customer);
        LOGGER.info(String.format("addCustomerWithCustType: [%s], is Added: [%b]", customer, isAdded));

        return isAdded;

    }

    @DeleteMapping(value = "/customer/delete/{1}")
    public boolean deleteCustomerWithPath(@RequestPart("1") int id){

        boolean isDeleted = customerService.delete(id);
        LOGGER.info(String.format("deleteCustomerWithPath, customerNumber: [%d], is Deleted: [%b]", id, isDeleted));

        return isDeleted;
    }


}
