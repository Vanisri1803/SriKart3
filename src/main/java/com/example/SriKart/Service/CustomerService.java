package com.example.SriKart.Service;

import com.example.SriKart.SriClass.Customer;
import com.example.SriKart.SriClass.Sricart;

import java.util.List;

public interface CustomerService {
    Customer CustomerDetails(Customer customer);
    List<Customer> getAllCustomerDetails();
    Customer updateCustomerDetails(Long customerId, Customer customerList);
    void DeleteCustomerById(Long customerId);
    Customer getCustomerDetailsById(Long customerId);
}
