package com.example.SriKart.Service;

import com.example.SriKart.SriClass.OrderedProducts;

public interface OrderedService {
    OrderedProducts addItemsToOrder(Long product_id, Long cart_id, Long customerId);
    Double calculateTotalAmountByID(Long productsOrderId);
    Double calculateTotalAmount(Long orderId); //step 20
    OrderedProducts getOrderedById(Long Id);
    void DeleteOrderById(Long Id);
}
