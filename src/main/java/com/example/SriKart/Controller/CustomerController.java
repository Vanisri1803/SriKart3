package com.example.SriKart.Controller;

import com.example.SriKart.Service.CustomerService;
import com.example.SriKart.SriClass.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/sriKart")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @PostMapping("/customer/customerDetails")
    //http://localhost:8080/sriKart/customer/customerDetails
    public ResponseEntity<Customer> database(@RequestBody Customer customer){
        return new ResponseEntity<Customer>(customerService.CustomerDetails(customer), HttpStatus.CREATED);
    }
    @GetMapping("/customer/getAllCustomerDetails")
    //http://localhost:8080/sriKart/customer/getAllCustomerDetails
    public ResponseEntity<List<Customer>> getAllCustomerDetails(){
        return new ResponseEntity<List<Customer>>(customerService.getAllCustomerDetails(), HttpStatus.ACCEPTED);
    }
    @GetMapping("/customer/getCustomerDetailsById/{customerId}") //step 7
        //http://localhost:8080/sriKart/customer/getCustomerDetailsById/3
    public ResponseEntity<Customer> getCustomerDetailsById(@PathVariable Long customerId){
        return new ResponseEntity<Customer>(customerService.getCustomerDetailsById(customerId),HttpStatus.CREATED);
    }
    @PatchMapping("/customer/updateCustomerDetails/{customerId}")
    //http://localhost:8080/sriKart/customer/updateCustomerDetails/1
    public ResponseEntity<String> updateCustomerDetails(@PathVariable Long customerId, @RequestBody Customer customerList) {
        customerService.updateCustomerDetails(customerId, customerList);
        return ResponseEntity.ok("Customer details updated successfully");
    }
    @DeleteMapping("/customer/DeleteCustomerById/{customerId}")
    //http://localhost:8080/sriKart/customer/DeleteCustomerById/3
    public void DeleteCustomerById(@PathVariable Long customerId) {
        customerService.DeleteCustomerById(customerId);
    }
}

