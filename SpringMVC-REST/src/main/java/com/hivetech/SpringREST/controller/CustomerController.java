package com.hivetech.SpringREST.controller;

import com.hivetech.SpringREST.model.Customer;
import com.hivetech.SpringREST.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
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
    public List<Customer> getListCustomer() {
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
    public boolean addCustomer(@RequestBody Customer customer) {
        return customerService.add(customer);
    }

    @GetMapping("/customers/getIDFromHeader")
    public void getCustomNumberFromHeader(@RequestHeader("customerNumber") String customerNumber) {
        LOGGER.info("getCustomNumberFromHeader : " + customerNumber);
    }

    @GetMapping("/customers1")
    public void multipleValue(@RequestParam MultiValueMap<String, ?> queryMap) {
        List<?> itemIds = queryMap.get("id");
        LOGGER.info("multipleValue: " + itemIds);
    }

    @GetMapping("/customers2")
    public void multipleValue2(@RequestParam("id") String[] itemIds) {
        String response = "";
        LOGGER.info("multipleValue2: " + Arrays.asList(itemIds));
    }

    @PostMapping("/customers2")
    public void multipleValue2_POST(@RequestParam("id") String[] itemIds) {
        LOGGER.info("multipleValue2_POST: " + Arrays.asList(itemIds));
    }

    @DeleteMapping("/customer/delete")
    public void deleteCustomer(@RequestParam("customerName") int id){
        LOGGER.info("deleteCustomer: " + id + " ; isDeleted: " + customerService.delete(id));
    }

    @GetMapping("/customer/delete")
    public void deleteCustomer_GET(@RequestParam("customerName") int id){
        LOGGER.info("deleteCustomer_GET: " + id + " ; isDeleted: " + customerService.delete(id));
    }

    @PostMapping(path = "/endpoint", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<?> form(@RequestBody MultiValueMap<String, String> formParameters) {
        // iterate through `formParameters` as you wish

        // get all values for a given parameter
        List<String> contentValues = formParameters.get("content");
        List<String> contentValues2 = formParameters.get("key");

       LOGGER.info("contentValues: " + contentValues + "contentValues2 " + contentValues2);

        return new ResponseEntity<Void>(HttpStatus.CONFLICT);
    }


}
