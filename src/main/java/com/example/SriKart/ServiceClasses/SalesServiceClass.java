package com.example.SriKart.ServiceClasses;

import com.example.SriKart.ExceptionHandling.UserIDNotFoundException;
import com.example.SriKart.Repository.SalesRepository;
import com.example.SriKart.Service.SalesService;
import com.example.SriKart.SriClass.SalesDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
@Service
public class SalesServiceClass implements SalesService {
    @Autowired
    SalesRepository salesRepository;
    @Override
    public SalesDetails getSalesProductsById(Long SalesId) {
        Optional<SalesDetails> salesDetails = salesRepository.findById(SalesId);
        if(salesDetails.isPresent()){
            return salesDetails.get();
        }
        throw new UserIDNotFoundException("Given Product ID Does Not Exits : " + SalesId);
    }
}
