package com.hivetech.SpringREST.service.impl;

import com.hivetech.SpringREST.model.Customer;
import com.hivetech.SpringREST.service.CustomerService;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class CustomerServiceImpl implements CustomerService {

    private static final Logger LOGGER = Logger.getLogger(CustomerServiceImpl.class.getName());

    private JdbcTemplate jdbcTemplate;

    public CustomerServiceImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
//    public void setTemplate(JdbcTemplate template) {
//        this.jdbcTemplate = jdbcTemplate;
//    }

    @Override
    public List<Customer> listCustomer() {
        String sql = "SELECT * FROM customers";
        List<Customer> listCustomer = jdbcTemplate.query(sql, new RowMapper<Customer>() {
            @Override
            public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
                Customer cus = new Customer();

                cus.setCustomerNumber(rs.getInt("customerNumber"));
                cus.setCustomerName(rs.getString("customerName"));
                cus.setContactLastName(rs.getString("contactLastName"));
                cus.setContactFirstName(rs.getString("contactFirstName"));
                cus.setPhone(rs.getString("phone"));
                cus.setAddressLine1(rs.getString("addressLine1"));
                cus.setAddressLine2(rs.getString("addressLine2"));
                cus.setCity(rs.getString("city"));
                cus.setState(rs.getString("state"));
                cus.setPostalCode(rs.getString("postalCode"));
                cus.setCountry(rs.getString("country"));
                cus.setSalesRepEmployeeNumber(rs.getInt("salesRepEmployeeNumber"));
                cus.setCreditLimit(rs.getDouble("creditLimit"));
                cus.setBirthday(null);

                return cus;
            }
        });

        return listCustomer;
    }

    @Override
    public List<Customer> getCustomerById(int customerId) {
        String sql = "SELECT * FROM customers WHERE customerNumber=" + customerId;
        List<Customer> listCustomer = jdbcTemplate.query(sql, new RowMapper<Customer>() {
            @Override
            public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
                Customer cus = new Customer();

                cus.setCustomerNumber(rs.getInt("customerNumber"));
                cus.setCustomerName(rs.getString("customerName"));
                cus.setContactLastName(rs.getString("contactLastName"));
                cus.setContactFirstName(rs.getString("contactFirstName"));
                cus.setPhone(rs.getString("phone"));
                cus.setAddressLine1(rs.getString("addressLine1"));
                cus.setAddressLine2(rs.getString("addressLine2"));
                cus.setCity(rs.getString("city"));
                cus.setState(rs.getString("state"));
                cus.setPostalCode(rs.getString("postalCode"));
                cus.setCountry(rs.getString("country"));
                cus.setSalesRepEmployeeNumber(rs.getInt("salesRepEmployeeNumber"));
                cus.setCreditLimit(rs.getDouble("creditLimit"));
                cus.setBirthday(rs.getDate("birthday").toLocalDate());

                return cus;
            }
        });

        return listCustomer;
    }

    @Override
    public Integer getNextMaxId() {
        String sql = "SELECT MAX(customerNumber) FROM customers;";
        Integer nextMaxId = jdbcTemplate.query(sql, new ResultSetExtractor<Integer>() {
            @Override
            public Integer extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                resultSet.next();
                return Integer.valueOf(resultSet.getInt(1));
            }
        });
        return nextMaxId;
    }



    @Override
    public boolean add(Customer customer) {
        String sql = "INSERT INTO customers VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        int isUpdated =
        jdbcTemplate.update(sql, getNextMaxId() +1, customer.getCustomerName(),
                customer.getContactLastName(), customer.getContactFirstName(), customer.getPhone(),
                customer.getAddressLine1(), customer.getAddressLine2(), customer.getCity(),
                customer.getState(), customer.getPostalCode(), customer.getCountry(),
                customer.getSalesRepEmployeeNumber(), customer.getCreditLimit(), customer.getBirthday());

        return isUpdated >0;
    }

    @Override
    public boolean update(Customer customer) {

        return true;
    }

    @Override
    public boolean delete(int id) {

        return true;
    }


}
