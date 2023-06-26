package com.example.SriKart.ServiceClasses;

import com.example.SriKart.ExceptionHandling.NoSuchElementException;
import com.example.SriKart.ExceptionHandling.ProductNotFoundException;
import com.example.SriKart.ExceptionHandling.UserIDNotFoundException;
import com.example.SriKart.Repository.CustomerRepository;
import com.example.SriKart.Repository.OrderedProductsRepository;
import com.example.SriKart.Repository.SricartRepository;
import com.example.SriKart.Repository.SrishopRepository;
import com.example.SriKart.Service.SrishopService;
import com.example.SriKart.SriClass.Customer;
import com.example.SriKart.SriClass.Sricart;
import com.example.SriKart.SriClass.Srishop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SrishopServiceClass implements SrishopService {
    @Autowired
    SrishopRepository srishopRepository;
    @Autowired
    SricartRepository sricartRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Override //step 1
    public Srishop srishopAllProducts(Srishop srishop) {
        srishop.setStock_availability(srishop.getStock() > 0);
        return srishopRepository.save(srishop);
    }
    @Override //step 2
    public List<Srishop> getAllSriShopProducts() {
        //return srishopRepository.findAll();
        Optional<List<Srishop>> srishop = Optional.of(srishopRepository.findAll());
        if (srishop.get().isEmpty()){
            throw new IllegalArgumentException("Your Srishop is Empty. Please add Some Products to Srishop");
        }
        return srishop.get();
    }
    @Override //step 3
    public Srishop getSrishopProductById(Long product_id) {
        Optional<Srishop> srishop = srishopRepository.findById(product_id);
        if(srishop.isPresent()){
            return srishop.get();
        }
        throw new UserIDNotFoundException("Given Product ID Does Not Exits in Srishop : " + product_id);
    }
    @Override //step 4
    public List<Srishop> getProductByCategories(String categories) {
        Optional<List<Srishop>> srishop = srishopRepository.findByCategories(categories);
        if (srishop.isPresent())
        {
            return srishop.get();
        }
        throw new UserIDNotFoundException("Given Categories Does Not Exits in Srishop: " + categories);

    }
    @Override //step 5
    public void addProductToSricart(Long product_id,Long customerId,Integer quantity) {
        Optional<Srishop> srishop = Optional.ofNullable(srishopRepository.findById(product_id).orElseThrow(() -> new UserIDNotFoundException("Given Product ID Dose Not Exits in Srishop:" + product_id)));
        Optional<Customer> customer = Optional.ofNullable(customerRepository.findById(customerId).orElseThrow(() -> new UserIDNotFoundException("Given Customer ID Dose Not Exits in Customer :" + customerId)));
        double price = quantity * srishop.get().getRate();
        Sricart sricart = new Sricart();
        sricart.setProduct_id(srishop.get().getProduct_id());
        sricart.setCustomerId(customer.get().getCustomerId());
        sricart.setProduct_name(srishop.get().getProduct_name());
        sricart.setCategories(srishop.get().getCategories());
        sricart.setRate(price);
        sricart.setQuantity(quantity);
        sricart.setDescription(srishop.get().getDescription());
        sricart.setStock(srishop.get().getStock());
        sricart.setStock_availability(srishop.get().getStock() > 0);
        sricart.setLocation(srishop.get().getLocation());
        sricart.setRegion(srishop.get().getRegion());
        sricart.setRegion_availability(srishop.get().getRegion_availability());
        sricart.setRatings(srishop.get().getRatings());
        sricart.setReview_count(srishop.get().getReview_count());
        sricart.setCustomer_name(customer.get().getCustomerName());
        sricart.setPhone_num(customer.get().getPhoneNum());
        sricart.setEmail_id(customer.get().getEmailId());
        sricart.setDelivery_address(customer.get().getDeliveryAddress());
        sricartRepository.save(sricart);
    }
    @Override  //step 8
    public List<Srishop> getSrishopLimitedProduct(int limit) {
        Optional<List<Srishop>> srishops = Optional.of(srishopRepository.findAll().stream().limit(limit).collect(Collectors.toList()));
        if (srishops.get().isEmpty()){
            throw new NoSuchElementException("Given Limit Does Not Exits in Srishop");
        }
        return srishops.get();
    }
    @Override //step 10
    public List<Srishop> getSrishopRange(double minRate, double maxRate) {
        Optional<List<Srishop>> srishops = Optional.of(srishopRepository.findAll().stream().filter(srishop -> srishop.getRate() >= minRate && srishop.getRate() <= maxRate).collect(Collectors.toList()));
        if(srishops.get().isEmpty()){
            throw new NoSuchElementException("Given minRate and maxRate Does Not Exits in Srishop");
        }
        return srishops.get();
    }
    @Override //step 11
    public List<Srishop> getRangeByCategories(double minRate, double maxRate,String categories) {
        Optional<List<Srishop>> srishops = Optional.ofNullable(srishopRepository.findByCategories(categories).get().stream().filter(srishop -> srishop.getRate() >= minRate && srishop.getRate() <= maxRate).collect(Collectors.toList()));
        if(srishops.get().isEmpty()){
            throw new NoSuchElementException("Given minRate and maxRate Does Not Exits in Srishop");
        }
        return srishops.get();
    }
    @Override //step 12
    public Srishop updateSrishopByID(Long product_id, Integer stock, double rate, String region,String region_availability) {
            Optional<Srishop> srishop = Optional.ofNullable(srishopRepository.findById(product_id)
                    .orElseThrow(() -> new UserIDNotFoundException("Given Product ID Dose Not Exits in Srishop :" + product_id)));
            Srishop srishop1 = new Srishop();
      if (srishop.isPresent()){
          srishop1 = srishop.get();
          srishop1.setStock(stock);
          srishop1.setStock_availability(srishop.get().getStock() > 0);
          srishop1.setRate(rate);
          srishop1.setRegion(region);
          srishop1.setRegion_availability(region_availability);
      }
        return srishopRepository.save(srishop1);
    }
    @Override //step 13
    public Srishop updateSrishopByPatch(Long product_id, Srishop srishopList) {
        Optional<Srishop> srishop = Optional.ofNullable(srishopRepository.findById(product_id)
                .orElseThrow(() -> new UserIDNotFoundException("Given Product ID Dose Not Exits :" + product_id)));
        Srishop srishop1 = new Srishop();
        if(srishop.isPresent()) {
            srishop1 = srishop.get();
            srishop1.setStock(srishopList.getStock());
            srishop1.setStock_availability(srishop.get().getStock() > 0);
            srishop1.setRate(srishopList.getRate());
            srishop1.setRegion(srishopList.getRegion());
            srishop1.setRegion_availability(srishopList.getRegion_availability());
        }
        return srishopRepository.save(srishop1);
    }
    @Override
    public String isProductStockAvailable(Long product_id) {
        Optional<Srishop> srishop = Optional.ofNullable(srishopRepository.findById(product_id).orElseThrow(() -> new ProductNotFoundException("Given Product ID Does Not Exits in Srishop :" + product_id)));
        if(srishop != null && srishop.get().isStock_availability()) {
            return "Stock is Available for this Product";
        }
        else {
            return "Stock is not Available for this Product";
        }
    }
    @Override
    public String isProductRegionAvailable(Long product_id) {
        Optional<Srishop> srishop = srishopRepository.findById(product_id);
        if (srishop.get().getRegion_availability().equalsIgnoreCase("Available")){
            return "Region is Available in the given Product Id";
        } else if (srishop.get().getRegion_availability().equalsIgnoreCase("Not Available")) {
            return "Region is Not Available in the given Product Id";
        }
        else {
            throw new ProductNotFoundException("Given Product ID Does Not Exits :" + product_id);
        }
    }
    @Override //step 14
    public List<Srishop> getProductsSortedByRateAsc() {
        //Optional<List<Srishop>> srishops = Optional.of(srishopRepository.findAll(Sort.by(Sort.Direction.ASC, "rate")));
        Optional<List<Srishop>> srishops = Optional.of(srishopRepository.findAll());
        if(srishops.get().isEmpty()){
            throw new NoSuchElementException("Given Rate Does Not Exits in Srishop");
        }
        return srishops.get().stream().sorted(Comparator.comparing(Srishop::getRate)).collect(Collectors.toList());
    }
    @Override //step 14
    public List<Srishop> getProductsSortedByRateDesc() {
        //Optional<List<Srishop>> srishops = Optional.of(srishopRepository.findAll(Sort.by(Sort.Direction.DESC, "rate")));
        Optional<List<Srishop>> srishops = Optional.of(srishopRepository.findAll());
        if(srishops.get().isEmpty()){
            throw new NoSuchElementException("Given Rate Does Not Exits in Srishop");
        }
        return srishops.get().stream().sorted(Comparator.comparing(Srishop::getRate).reversed()).collect(Collectors.toList());
    }
    @Override //step 15
    public List<Srishop> getProductsSortedByCategoriesAsc() {
        //Optional<List<Srishop>> srishops = Optional.of(srishopRepository.findAll(Sort.by(Sort.Direction.ASC, "categories")));
        Optional<List<Srishop>> srishops = Optional.of(srishopRepository.findAll());
        if (srishops.get().isEmpty()) {
            throw new NoSuchElementException("Given Categories Does Not Exits in Srishop");
        }
        return srishops.get().stream().sorted(Comparator.comparing(Srishop::getCategories)).collect(Collectors.toList());
    }
    @Override //step 15
    public List<Srishop> getProductsSortedByCategoriesDesc() {
        //Optional<List<Srishop>> srishops = Optional.of(srishopRepository.findAll(Sort.by(Sort.Direction.DESC, "categories")));
        Optional<List<Srishop>> srishops = Optional.of(srishopRepository.findAll());
        if(srishops.get().isEmpty()){
            throw new NoSuchElementException("Given Categories Does Not Exits in Srishop");
        }
        return srishops.get().stream().sorted(Comparator.comparing(Srishop::getCategories).reversed()).collect(Collectors.toList());
    }
    @Override //step 16
    public List<Srishop> getProductsSortedByRatingAsc() {
        //Optional<List<Srishop>> srishops = Optional.of(srishopRepository.findAll(Sort.by(Sort.Direction.ASC, "ratings")));
        Optional<List<Srishop>> srishops = Optional.of(srishopRepository.findAll());
        if(srishops.get().isEmpty()){
            throw new NoSuchElementException("Given Ratings Does Not Exits in Srishop");
        }
        return srishops.get().stream().sorted(Comparator.comparing(Srishop::getRatings)).collect(Collectors.toList());
    }
    @Override //step 16
    public List<Srishop> getProductsSortedByRatingDesc() {
        //Optional<List<Srishop>> srishops = Optional.of(srishopRepository.findAll(Sort.by(Sort.Direction.DESC, "ratings")));
        Optional<List<Srishop>> srishops = Optional.of(srishopRepository.findAll());
        if(srishops.get().isEmpty()){
            throw new NoSuchElementException("Given Ratings Does Not Exits in Srishop");
        }
        return srishops.get().stream().sorted(Comparator.comparing(Srishop::getRatings).reversed()).collect(Collectors.toList());
    }
    @Override //step 17
    public List<Srishop> getProductsSortedByProductNameAsc() {
        //Optional<List<Srishop>> srishops = Optional.of(srishopRepository.findAll(Sort.by(Sort.Direction.ASC, "product_name")));
        Optional<List<Srishop>> srishops = Optional.of(srishopRepository.findAll());
        if (srishops.get().isEmpty()) {
            throw new NoSuchElementException("Given Product Name Does Not Exits in Srishop");
        }
        return srishops.get().stream().sorted(Comparator.comparing(Srishop::getProduct_name)).collect(Collectors.toList());
    }
    @Override //step 17
    public List<Srishop> getProductsSortedByProductNameDesc() {
        //Optional<List<Srishop>> srishops = Optional.of(srishopRepository.findAll(Sort.by(Sort.Direction.DESC, "product_name")));
        Optional<List<Srishop>> srishops = Optional.of(srishopRepository.findAll());
        if (srishops.get().isEmpty()) {
            throw new NoSuchElementException("Given Product Name Does Not Exits in Srishop");
        }
        return srishops.get().stream().sorted(Comparator.comparing(Srishop::getProduct_name).reversed()).collect(Collectors.toList());
    }
    @Override //step 18
    public List<Srishop> getProductsSortedByReviewCountAsc() {
        //Optional<List<Srishop>> srishops = Optional.of(srishopRepository.findAll(Sort.by(Sort.Direction.ASC, "review_count")));
        Optional<List<Srishop>> srishops = Optional.of(srishopRepository.findAll());
        if(srishops.get().isEmpty()){
            throw new NoSuchElementException("Given Review Count Does Not Exits in Srishop");
        }
        return srishops.get().stream().sorted(Comparator.comparing(Srishop::getReview_count)).collect(Collectors.toList());
    }
    @Override //step 18
    public List<Srishop> getProductsSortedByReviewCountDesc() {
        //Optional<List<Srishop>> srishops = Optional.of(srishopRepository.findAll(Sort.by(Sort.Direction.DESC, "review_count")));
        Optional<List<Srishop>> srishops = Optional.of(srishopRepository.findAll());
        if(srishops.get().isEmpty()){
            throw new NoSuchElementException("Given Review Count Does Not Exits in Srishop");
        }
        return srishops.get().stream().sorted(Comparator.comparing(Srishop::getReview_count).reversed()).collect(Collectors.toList());
    }
    @Override //Step 19
    public void DeleteProductById(Long product_id) {
        Optional<Srishop> srishop = Optional.ofNullable(srishopRepository.findById(product_id)
                .orElseThrow(() -> new UserIDNotFoundException("Given Product ID Does Not Exits in Srishop : " + product_id)));
        if (srishop.isPresent()) {
            srishopRepository.deleteById(product_id);
            throw new NoSuchElementException("Product ID Deleted Successfully");
        }
    }
    @Override //step 21
    public List<Srishop> getProductsSortedByNameAndRatings(String sort) {
        Optional<List<Srishop>> srishopList = Optional.of(srishopRepository.findAll());
        if (sort.equalsIgnoreCase("asc")) {
            if (srishopList.isPresent()) {
                List<Srishop> srishops = srishopList.get();
                List<Srishop> list = srishops.stream()
                        .sorted(Comparator.comparing(Srishop::getProduct_name).thenComparing(Srishop::getRatings)).collect(Collectors.toList());
                return list;
            }
        } else if (sort.equalsIgnoreCase("desc")) {
            if (srishopList.isPresent()) {
                List<Srishop> srishops = srishopList.get();
                List<Srishop> list = srishops.stream()
                        .sorted(Comparator.comparing(Srishop::getProduct_name).thenComparing(Srishop::getRatings).reversed()).collect(Collectors.toList());
                return list;
            }
        }
        throw new NoSuchElementException("Invalid sort order. Please use 'asc' or 'desc'.");
    }
}
