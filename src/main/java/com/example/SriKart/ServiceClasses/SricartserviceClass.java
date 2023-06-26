package com.example.SriKart.ServiceClasses;

import com.example.SriKart.ExceptionHandling.NoSuchElementException;
import com.example.SriKart.ExceptionHandling.ProductNotFoundException;
import com.example.SriKart.ExceptionHandling.UserIDNotFoundException;
import com.example.SriKart.Repository.SricartRepository;
import com.example.SriKart.Repository.SrishopRepository;
import com.example.SriKart.Service.SricartService;
import com.example.SriKart.SriClass.Sricart;
import com.example.SriKart.SriClass.Srishop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SricartserviceClass implements SricartService {

    @Autowired
    SricartRepository sricartRepository;
    @Autowired
    SrishopRepository srishopRepository;
    @Override //step 6
    public List<Sricart> getAllSricartProducts() {
        Optional<List<Sricart>> sricart = Optional.of(sricartRepository.findAll());
        if (sricart.get().isEmpty()) {
            throw new ProductNotFoundException("Your Sricart is Empty. Please add Some Products to Sricart");
        }
        return sricart.get();
    }
    @Override //step 7
    public Sricart getSricartProductById(Long cart_id) {
        Optional<Sricart> sricart = sricartRepository.findById(cart_id);
        if(sricart.isPresent()){
            return sricart.get();
        }
        throw new UserIDNotFoundException("Given Cart ID Does Not Exits in Srishop : " + cart_id);
    }
    @Override //step 9
    public List<Sricart> getSricartLimitedProduct(int limit) {
        Optional<List<Sricart>> sricarts = Optional.of(sricartRepository.findAll().stream().limit(limit).collect(Collectors.toList()));
        if (sricarts.get().isEmpty()) {
            throw new NoSuchElementException("Given Limit Does Not Exits in Sricart");
        }
        return sricarts.get();
    }
    @Override
    public List<Sricart> getSricartCustomerById(Long customerId) {
        Optional<List<Sricart>> sricart = sricartRepository.findByCustomerId(customerId);
        if(sricart.get().isEmpty()){
            throw new UserIDNotFoundException("Given Customer ID Does Not Exits in Sricart : " + customerId);
        }
        return sricart.get();
    }
    @Override
    public Sricart updateSriCartByID(Long cart_id,Long product_id, Integer quantity) {
        Optional<Sricart> sricart = Optional.ofNullable(sricartRepository.findById(cart_id)
                .orElseThrow(() -> new UserIDNotFoundException("Given Cart ID Dose Not Exits in Sricart :" + cart_id)));
        Optional<Srishop> srishop = Optional.ofNullable(srishopRepository.findById(product_id)
                .orElseThrow(() -> new UserIDNotFoundException("Given Product ID Dose Not Exits in Srishop :" + product_id)));
        Sricart sricart1 = new Sricart();
        if (sricart.isPresent()) {
            sricart1 = sricart.get();
            double price = quantity * srishop.get().getRate();
            sricart1.setRate(price);
            sricart1.setQuantity(quantity);
        }
        return sricartRepository.save(sricart1);
    }
    @Override
    public void DeleteCartById(Long cart_id) {
        Optional<Sricart> sricart = Optional.ofNullable(sricartRepository.findById(cart_id)
                .orElseThrow(() -> new UserIDNotFoundException("Given Cart ID Does Not Exits in Sricart" + cart_id)));
        if (sricart.isPresent()) {
            sricartRepository.deleteById(cart_id);
            throw new NoSuchElementException("Cart ID Deleted Successfully");
        }
    }
}
