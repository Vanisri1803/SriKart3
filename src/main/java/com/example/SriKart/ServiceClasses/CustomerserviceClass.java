package com.example.SriKart.ServiceClasses;

import com.example.SriKart.ExceptionHandling.NoSuchElementException;
import com.example.SriKart.ExceptionHandling.UserIDNotFoundException;
import com.example.SriKart.Repository.CustomerRepository;
import com.example.SriKart.Service.CustomerService;
import com.example.SriKart.SriClass.Customer;
import com.example.SriKart.SriClass.Sricart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerserviceClass implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Override
    public Customer CustomerDetails(Customer customer) {
        return customerRepository.save(customer);
    }
    @Override
    public List<Customer> getAllCustomerDetails() {
        //return customerRepository.findAll();
        Optional<List<Customer>> customer = Optional.of(customerRepository.findAll());
        if (customer.get().isEmpty()){
            throw new IllegalArgumentException("Your Customer is Empty. Please add Some Customers");
        }
        return customer.get();
    }
    @Override
    public Customer getCustomerDetailsById(Long customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if(customer.isPresent()){
            return customer.get();
        }
        throw new UserIDNotFoundException("Given Customer Id Does Not Exits in Customer : " + customerId);
    }
    @Override
    public Customer updateCustomerDetails(Long customerId, Customer customerList) {
        Optional<Customer> customer = Optional.ofNullable(customerRepository.findById(customerId)
                .orElseThrow(() -> new UserIDNotFoundException("Given Customer ID Does Not Exits in Customer" + customerId)));
        Customer customer1 = new Customer();
        if (customer.isPresent()) {
            customer1 = customer.get();
            customer1.setCustomerName(customerList.getCustomerName());
            customer1.setPhoneNum(customerList.getPhoneNum());
            customer1.setEmailId(customerList.getEmailId());
            customer1.setDeliveryAddress(customerList.getDeliveryAddress());
        }
        return customerRepository.save(customer1);
    }
    @Override
    public void DeleteCustomerById(Long customerId) {
        Optional<Customer> customer = Optional.ofNullable(customerRepository.findById(customerId)
                .orElseThrow(() -> new UserIDNotFoundException("Given Customer ID Does Not Exits in Customer : " + customerId)));
        if (customer.isPresent()) {
            customerRepository.deleteById(customerId);
            throw new NoSuchElementException("Customer ID Deleted Successfully");
        }
    }
}
