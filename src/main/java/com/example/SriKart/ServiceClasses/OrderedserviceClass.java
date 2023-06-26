package com.example.SriKart.ServiceClasses;

import com.example.SriKart.ExceptionHandling.NoSuchElementException;
import com.example.SriKart.ExceptionHandling.ProductNotFoundException;
import com.example.SriKart.ExceptionHandling.UserIDNotFoundException;
import com.example.SriKart.Repository.*;
import com.example.SriKart.Service.OrderedService;
import com.example.SriKart.SriClass.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OrderedserviceClass implements OrderedService {
    @Autowired
    SrishopRepository srishopRepository;
    @Autowired
    SricartRepository sricartRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    OrderedProductsRepository orderedProductsRepository;
    @Autowired
    SalesRepository salesRepository;
    @Autowired
    SMSserviceClass smSserviceClass;
    @Autowired
    EmailRequest emailRequest;
    @Override
    public OrderedProducts addItemsToOrder(Long product_id, Long cart_id, Long customerId) {
        Srishop addProduct = srishopRepository.findById(product_id).orElseThrow(() -> new NoSuchElementException("Given productId does not exist in Srishop"));
        Sricart addCart = sricartRepository.findById(cart_id).orElseThrow(() -> new NoSuchElementException("Given Cart ID does not exist in Sricart"));
        Customer existingCustomer = customerRepository.findById(customerId).orElseThrow(() -> new NoSuchElementException("Given customerId does not exist in Customer"));
        OrderedProducts order = new OrderedProducts();
        order.setCustomer(existingCustomer);
        order.setSrishop(addProduct);
        order.setSricart(addCart);
        order.setOrderId(customerId);
        order.setProductsOrderId(cart_id);
        return orderedProductsRepository.save(order);
    }
    @Override
    public Double calculateTotalAmountByID(Long productsOrderId) {
        Optional<List<OrderedProducts>> orderedProducts = orderedProductsRepository.findByProductsOrderId(productsOrderId);
        if(orderedProducts.get().isEmpty()) {
            smSserviceClass.sendSms("+919025414828","Dear Customer,Your Order is Out of Stock...Thanks and Regards -SriKart!!!.");
        }
        double totalProductAmount = 0.0;
        for (OrderedProducts orderedProductsList : orderedProducts.get()) {
            totalProductAmount = orderedProducts.get().stream().mapToDouble(cal -> cal.getSricart().getRate()).sum();
        }
        double gst = totalProductAmount * 0.14;
        double deliveryCharge = 40.0;
        double discount = 11.0 / totalProductAmount * 100;
        double totalAmount = Math.ceil(totalProductAmount + gst + deliveryCharge - discount);
        for (OrderedProducts orderedProducts1 : orderedProducts.get()) {
            Optional<Srishop> srishopProducts = Optional.ofNullable(srishopRepository.findById(orderedProducts1.getSrishop().getProduct_id())
                    .orElseThrow(() -> new ProductNotFoundException("Given Product ID Does Not Exits in Srishop")));
            Optional<Sricart> sricartProducts = Optional.ofNullable(sricartRepository.findById(orderedProducts1.getSricart().getCart_id())
                    .orElseThrow(() -> new ProductNotFoundException("Given Sricart ID Does Not Exits in Sricart")));
            int purchasedQuantity = sricartProducts.get().getQuantity();
            int currentStock = srishopRepository.findById(orderedProducts1.getSrishop().getProduct_id()).get().getStock();
            int newStock = currentStock - purchasedQuantity;

            srishopProducts.get().setStock(newStock);
            srishopRepository.save(srishopProducts.get());
            orderedProductsRepository.delete(orderedProducts1);
            sricartRepository.delete(orderedProducts1.getSricart());
            if(orderedProducts.isPresent())
            {
                smSserviceClass.sendSms("+919025414828","Dear Customer, Congrats!..Your order is on the way...Thanks and Regards  -SriKart!!!.");
                String recipientEmail = orderedProducts1.getSricart().getEmail_id();
                String subject ="Thank You For Ur Order";
                String body = ( ("Dear " + orderedProducts1.getCustomer().getCustomerName()+ ",\n\nYour purchase has been confirmed.") + ("Total amount is " +totalAmount+ ",\n\nPaid Confirmed"));
                emailRequest.sendEmail(recipientEmail, subject, body);
            }

            SalesDetails salesDetails = new SalesDetails();
            salesDetails.setOrderedId(orderedProducts1.getProductsOrderId());
            salesDetails.setCartId(sricartProducts.get().getCart_id());
            salesDetails.setProduct_id(sricartProducts.get().getProduct_id());
            salesDetails.setProduct_name(sricartProducts.get().getProduct_name());
            salesDetails.setCategories(sricartProducts.get().getCategories());
            salesDetails.setLocation(sricartProducts.get().getLocation());
            salesDetails.setRegion(sricartProducts.get().getRegion());
            salesDetails.setRatings(sricartProducts.get().getRatings());
            salesDetails.setReview_count(sricartProducts.get().getReview_count());
            salesDetails.setCustomerId(sricartProducts.get().getCustomerId());
            salesDetails.setCustomer_name(sricartProducts.get().getCustomer_name());
            salesDetails.setPhone_num(sricartProducts.get().getPhone_num());
            salesDetails.setEmail_id(sricartProducts.get().getEmail_id());
            salesDetails.setDelivery_address(sricartProducts.get().getDelivery_address());
            salesDetails.setRate(sricartProducts.get().getRate());
            salesDetails.setQuantity(sricartProducts.get().getQuantity());
            salesDetails.setGstAmount(gst);
            salesDetails.setDeliveryCharge(deliveryCharge);
            salesDetails.setDiscount(discount);
            salesDetails.setTotalAmount(totalAmount);
            salesRepository.save(salesDetails);
        }
        return totalAmount;
    }
    @Override //step 20
    public Double calculateTotalAmount(Long orderId) {
        Optional<List<OrderedProducts>> orderedProducts = Optional.ofNullable(orderedProductsRepository.findByOrderId(orderId).orElseThrow(() -> new ProductNotFoundException("Given Order Id Does Not Exits in Ordered Products :" + orderId)));
        if(orderedProducts.get().isEmpty()) {
            smSserviceClass.sendSms("+919025414828","Dear Customer,Your Order is Out of Stock...Thanks and Regards -SriKart!!!.");
        }
        double totalProductAmount = 0.0;
        for (OrderedProducts orderedProductsList : orderedProducts.get()) {
            totalProductAmount = orderedProducts.get().stream().mapToDouble(cal -> cal.getSricart().getRate()).sum();
        }
        double gst = totalProductAmount * 0.14;
        double deliveryCharge = 40.0;
        double discount = 18.0;
        double totalAmount = totalProductAmount + gst + deliveryCharge - discount;
        for (OrderedProducts orderedProducts1 : orderedProducts.get()) {
            Optional<Srishop> srishopProducts = Optional.ofNullable(srishopRepository.findById(orderedProducts1.getSrishop().getProduct_id())
                    .orElseThrow(() -> new ProductNotFoundException(" Given Product ID Does Not Exits")));
            Optional<Sricart> sricartProducts = Optional.ofNullable(sricartRepository.findById(orderedProducts1.getSricart().getCart_id())
                    .orElseThrow(() -> new ProductNotFoundException("Given Sricart ID Does Not Exits")));
            int purchasedQuantity = sricartProducts.get().getQuantity();
            int currentStock = srishopRepository.findById(orderedProducts1.getSrishop().getProduct_id()).get().getStock();
            int newStock = currentStock - purchasedQuantity;

            srishopProducts.get().setStock(newStock);
            srishopRepository.save(srishopProducts.get());
            orderedProductsRepository.delete(orderedProducts1);
            sricartRepository.delete(orderedProducts1.getSricart());

            if(orderedProducts.isPresent())
            {
                smSserviceClass.sendSms("+919025414828","Dear Customer, Congrats!..Your order is on the way...Thanks and Regards  -SriKart!!!.");
                String recipientEmail = orderedProducts1.getSricart().getEmail_id();
                String subject ="Thank You For Ur Order";
                String body = ( ("Dear " + orderedProducts1.getCustomer().getCustomerName()+ ",\n\nYour purchase has been confirmed.") + ("Total amount is " +totalAmount+ ",\n\nPaid Confirmed"));
                emailRequest.sendEmail(recipientEmail, subject, body);
            }

            SalesDetails salesDetails = new SalesDetails();
            salesDetails.setOrderedId(orderedProducts1.getOrderId());
            salesDetails.setCartId(sricartProducts.get().getCart_id());
            salesDetails.setProduct_id(sricartProducts.get().getProduct_id());
            salesDetails.setProduct_name(sricartProducts.get().getProduct_name());
            salesDetails.setCategories(sricartProducts.get().getCategories());
            salesDetails.setLocation(sricartProducts.get().getLocation());
            salesDetails.setRegion(sricartProducts.get().getRegion());
            salesDetails.setRatings(sricartProducts.get().getRatings());
            salesDetails.setReview_count(sricartProducts.get().getReview_count());
            salesDetails.setCustomerId(sricartProducts.get().getCustomerId());
            salesDetails.setCustomer_name(sricartProducts.get().getCustomer_name());
            salesDetails.setPhone_num(sricartProducts.get().getPhone_num());
            salesDetails.setEmail_id(sricartProducts.get().getEmail_id());
            salesDetails.setDelivery_address(sricartProducts.get().getDelivery_address());
            salesDetails.setRate(sricartProducts.get().getRate());
            salesDetails.setQuantity(sricartProducts.get().getQuantity());
            salesDetails.setGstAmount(gst);
            salesDetails.setDeliveryCharge(deliveryCharge);
            salesDetails.setDiscount(discount);
            salesDetails.setTotalAmount(totalAmount);
            salesRepository.save(salesDetails);

        }
        return totalAmount;
    }
    @Override
    public OrderedProducts getOrderedById(Long Id) {
        Optional<OrderedProducts> orderedProducts = orderedProductsRepository.findById(Id);
        if(orderedProducts.isPresent()){
            return orderedProducts.get();
        }
        throw new UserIDNotFoundException("Given Order ID Does Not Exits in OrderedProducts : " + Id);
    }

    @Override
    public void DeleteOrderById(Long Id) {
        Optional<OrderedProducts> orderedProducts = Optional.ofNullable(orderedProductsRepository.findById(Id)
                .orElseThrow(() -> new UserIDNotFoundException("Given Cart ID Does Not Exits in Sricart" + Id)));
        if (orderedProducts.isPresent()) {
            orderedProductsRepository.deleteById(Id);
            throw new NoSuchElementException("Order ID Deleted Successfully");
        }
    }
}
