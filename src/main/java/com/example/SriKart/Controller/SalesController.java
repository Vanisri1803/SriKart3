package com.example.SriKart.Controller;

import com.example.SriKart.Service.SalesService;
import com.example.SriKart.SriClass.SalesDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sriKart")
public class SalesController {
    @Autowired
    SalesService salesService;
    @GetMapping("/sales/getSalesProductsById/{SalesId}")
    //http://localhost:8080/sriKart/sales/getSalesProductsById/1
    public ResponseEntity<SalesDetails> getSalesProductsById(@PathVariable Long SalesId){
        return new ResponseEntity<SalesDetails>(salesService.getSalesProductsById(SalesId), HttpStatus.CREATED);
    }
}
