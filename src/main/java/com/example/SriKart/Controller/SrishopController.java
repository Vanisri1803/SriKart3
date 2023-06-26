package com.example.SriKart.Controller;

import com.example.SriKart.ExceptionHandling.NoSuchElementException;
import com.example.SriKart.Service.SrishopService;
import com.example.SriKart.SriClass.Srishop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/sriKart")
public class SrishopController {
    @Autowired
    SrishopService srishopService;
    @PostMapping("/sriShop/srishopProducts")  //step 1
    //http://localhost:8080/sriKart/sriShop/srishopProducts
    public ResponseEntity<Srishop> shopProducts(@RequestBody Srishop srishop){
        return new ResponseEntity<Srishop>(srishopService.srishopAllProducts(srishop), HttpStatus.CREATED);
    }
    @GetMapping("/sriShop/getAllSriShopProducts") //step 2
    //http://localhost:8080/sriKart/sriShop/getAllSriShopProducts
    public ResponseEntity<List<Srishop>> getAllSriShopProducts(){
        return new ResponseEntity<List<Srishop>>(srishopService.getAllSriShopProducts(),HttpStatus.ACCEPTED);
    }
    @GetMapping("/sriShop/getSrishopProductById/{product_id}")  //step 3
    //http://localhost:8080/sriKart/sriShop/getSrishopProductById/3
    public ResponseEntity<Srishop> getSrishopProductById(@PathVariable Long product_id){
        return new ResponseEntity<Srishop>(srishopService.getSrishopProductById(product_id),HttpStatus.ACCEPTED);
    }
    @GetMapping("/sriShop/{categories}")  //step 4
    //http://localhost:8080/sriKart/sriShop/Men
    public List<Srishop> getProductByCategories(@PathVariable String categories) {
            return srishopService.getProductByCategories(categories);
    }
    @PostMapping("/sriCart/addToSricart/{product_id}/{customer_id}/{quantity}") //step 5
    //http://localhost:8080/sriKart/sriCart/addToSricart/3/1/2
    public String addProductToSricart(@PathVariable Long product_id,@PathVariable Long customer_id,@PathVariable Integer quantity) {
        srishopService.addProductToSricart(product_id,customer_id,quantity);
        return "Product Details Added to Cart Successfully";
    }
    @GetMapping("/sriShop/getSrishopLimitedProduct") //step 8
    //http://localhost:8080/sriKart/sriShop/getSrishopLimitedProduct?limit=3
    public List<Srishop> getSrishopLimitedProduct(@RequestParam(name = "limit") int limit) {
            return srishopService.getSrishopLimitedProduct(limit);
    }
    @GetMapping("/sriShop/getSrishopRange") //step 10
    //http://localhost:8080/sriKart/sriShop/getSrishopRange?minRate=1000&maxRate=3000
    public List<Srishop> getSrishopRange(@RequestParam("minRate") double minRate,@RequestParam("maxRate") double maxRate) {
            return srishopService.getSrishopRange(minRate,maxRate);
    }
    @GetMapping("/sriShop/getRangeByCategories") //step 11
    //http://localhost:8080/sriKart/sriShop/getRangeByCategories?categories=Men&minRate=100&maxRate=1000.00
    public List<Srishop> getRangeByCategories(@RequestParam("categories") String categories,@RequestParam("minRate") double minRate,@RequestParam("maxRate") double maxRate){
            return srishopService.getRangeByCategories(minRate, maxRate,categories);
    }
    @PutMapping("/sriShop/updateSrishop/{product_id}/{stock}/{rate}/{region}/{region_availability}") //step 12
    //http://localhost:8080/sriKart/sriShop/updateSrishop/24/0/1000.00/Coimbatore/Available
    public Srishop updateSrishopByID(@PathVariable Long product_id, @PathVariable Integer stock, @PathVariable double rate,@PathVariable String region,@PathVariable String region_availability){
        return srishopService.updateSrishopByID(product_id,stock,rate,region,region_availability);
    }
    @PatchMapping("/sriShop/updateSrishopByPatch/{product_id}") //step 13
    //http://localhost:8080/sriKart/sriShop/updateSrishopByPatch/24
    public ResponseEntity<String> updateSrishopByPatch(@PathVariable Long product_id, @RequestBody Srishop srishopList) {
        srishopService.updateSrishopByPatch(product_id, srishopList);
        return ResponseEntity.ok("Product details updated successfully");
    }
    @GetMapping("/sriShop/stock")
    //http://localhost:8080/sriKart/sriShop/stock?product_id=5
    public String checkProductStockAvailability(@RequestParam("product_id") Long product_id) {
        return srishopService.isProductStockAvailable(product_id);
    }
    @GetMapping("/sriShop/region")
    //http://localhost:8080/sriKart/sriShop/region?product_id=18
    public String checkProductRegionAvailability(@RequestParam("product_id") Long product_id) {
        return srishopService.isProductRegionAvailable(product_id);
    }
    @GetMapping("/sriShop/RateAscDesc") //step 14
    //http://localhost:8080/sriKart/sriShop/RateAscDesc?sort=asc
    public List<Srishop> getProductsSortedByRateAscDesc(@RequestParam("sort") String sort) {
        if(sort.equalsIgnoreCase("asc")){
            return srishopService.getProductsSortedByRateAsc();
        }
        else if (sort.equalsIgnoreCase("desc")){
            return srishopService.getProductsSortedByRateDesc();
        }
        else {
            throw new IllegalArgumentException("Invalid sort order. Please use 'asc' or 'desc'.");
        }
    }
    @GetMapping("/sriShop/CategoriesAscDesc") //step 15
    //http://localhost:8080/sriKart/sriShop/CategoriesAscDesc?sort=asc
    public List<Srishop> getProductsSortedByCategories(@RequestParam("sort") String sort) {
       if (sort.equalsIgnoreCase("asc")) {
           return srishopService.getProductsSortedByCategoriesAsc();
       } else if (sort.equalsIgnoreCase("desc")) {
            return srishopService.getProductsSortedByCategoriesDesc();
        }
        throw new NoSuchElementException("Invalid sort order. Please use 'asc' or 'desc'.");
    }
    @GetMapping("/sriShop/ratingsAscDesc") //step 16
    //http://localhost:8080/sriKart/sriShop/ratingsAscDesc?sort=asc
    public List<Srishop> getProductsSortedByRatingAscDesc(@RequestParam("sort") String sort) {
        if(sort.equalsIgnoreCase("asc")){
            return srishopService.getProductsSortedByRatingAsc();
        }
        else if (sort.equalsIgnoreCase("desc")){
            return srishopService.getProductsSortedByRatingDesc();
        }
        else {
            throw new NoSuchElementException("Invalid sort order. Please use 'asc' or 'desc'.");
        }
    }
    @GetMapping("/sriShop/ProductNameAscDesc") //step 17
    //http://localhost:8080/sriKart/sriShop/ProductNameAscDesc?sort=asc
    public List<Srishop> getProductsSortedByProductNameAscDesc(@RequestParam("sort") String sort) {
        if(sort.equalsIgnoreCase("asc")){
            return srishopService.getProductsSortedByProductNameAsc();
        }
        else if (sort.equalsIgnoreCase("desc")){
            return srishopService.getProductsSortedByProductNameDesc();
        }
        else {
            throw new NoSuchElementException("Invalid sort order. Please use 'asc' or 'desc'.");
        }
    }
    @GetMapping("/sriShop/ReviewCountAscDesc") //step 18
    //http://localhost:8080/sriKart/sriShop/ReviewCountAscDesc?sort=asc
    public List<Srishop> getProductsSortedByReviewCountAscDesc(@RequestParam("sort") String sort) {
        if(sort.equalsIgnoreCase("asc")){
            return srishopService.getProductsSortedByReviewCountAsc();
        }
        else if (sort.equalsIgnoreCase("desc")){
            return srishopService.getProductsSortedByReviewCountDesc();
        }
        else {
            throw new NoSuchElementException("Invalid sort order. Please use 'asc' or 'desc'.");
        }
    }
    @DeleteMapping("/sriShop/DeleteProductById/{product_id}") //step 19
    //http://localhost:8080/sriKart/sriShop/DeleteProductById/1
    public void DeleteProductById(@PathVariable Long product_id) {
        srishopService.DeleteProductById(product_id);
    }
    @GetMapping("/sriShop/getProductsSortedByNameAndRatings/{sort}") //step 21
    //http://localhost:8080/sriKart/sriShop/getProductsSortedByNameAndRatings/desc (step 21)
    public List<Srishop>  getProductsSortedByNameAndRatings(@PathVariable String sort) {
        return srishopService.getProductsSortedByNameAndRatings(sort);
    }
}
