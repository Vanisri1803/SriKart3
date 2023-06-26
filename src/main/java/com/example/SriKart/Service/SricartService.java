package com.example.SriKart.Service;

import com.example.SriKart.SriClass.Sricart;
import java.util.List;

public interface SricartService {
    List<Sricart> getAllSricartProducts(); //Step 6
    Sricart getSricartProductById(Long cart_id); //Step 7
    List<Sricart> getSricartLimitedProduct(int limit); //Step 9
    List<Sricart> getSricartCustomerById(Long customer_id);
    Sricart updateSriCartByID(Long cart_id,Long product_id, Integer quantity);
    void DeleteCartById(Long cart_id);
}
