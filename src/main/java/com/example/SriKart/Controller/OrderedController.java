package com.example.SriKart.Controller;

import com.example.SriKart.Service.OrderedService;
import com.example.SriKart.SriClass.OrderedProducts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/sriKart")
public class OrderedController {
    @Autowired
    OrderedService orderedService;
    @PostMapping("/order/addItemsToOrder/{cart_id}/{customerId}/{product_id}")
    //http://localhost:8080/sriKart/order/addItemsToOrder/23/9/2
    public OrderedProducts addItemsToOrder( @PathVariable Long cart_id,@PathVariable Long customerId,@PathVariable Long product_id) {
        return orderedService.addItemsToOrder(product_id, cart_id, customerId);
    }
    @GetMapping("/order/totalProductAmount/{productsOrderId}")
    //http://localhost:8080/sriKart/order/totalProductAmount/13
    public ResponseEntity<Double> calculateTotalAmountByID(@PathVariable Long productsOrderId) {
        return new ResponseEntity<Double>(orderedService.calculateTotalAmountByID(productsOrderId), HttpStatus.OK);
    }
    @GetMapping("/order/totalAmount/{orderId}") //step 20
    //http://localhost:8080/sriKart/order/totalAmount/1
    public ResponseEntity<Double> calculateTotalAmount(@PathVariable Long orderId) {
        return new ResponseEntity<Double>(orderedService.calculateTotalAmount(orderId), HttpStatus.OK);
    }
    @GetMapping("/order/getOrderedById/{Id}")
    //http://localhost:8080/sriKart/order/getOrderedById/3
    public ResponseEntity<OrderedProducts> getOrderedById(@PathVariable Long Id){
        return new ResponseEntity<OrderedProducts>(orderedService.getOrderedById(Id),HttpStatus.CREATED);
    }
    @DeleteMapping("/order/DeleteOrderById/{Id}")
    //http://localhost:8080/sriKart/order/DeleteOrderById/2
    public void DeleteOrderById(@PathVariable Long Id){
        orderedService.DeleteOrderById(Id);
    }
}
