package com.hivetech.SpringREST.service;


import com.hivetech.SpringREST.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> listCustomer();
    Customer getCustomerById(int customerId);
    Integer getNextMaxId();
    boolean add(Customer customer);
    boolean update(Customer customer);
    boolean delete(int id);
}
