package com.hivetech.SpringREST.service;


import com.hivetech.SpringREST.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> listCustomer();
    List<Customer> getCustomerById(int customerId);
    Integer getNextMaxId();
    boolean add(Customer customer);
    void update(Customer customer);
    void delete(int id);
}
