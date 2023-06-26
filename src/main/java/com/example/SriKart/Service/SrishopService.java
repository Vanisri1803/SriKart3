package com.example.SriKart.Service;

import com.example.SriKart.SriClass.Srishop;
import java.util.List;

public interface SrishopService {
    Srishop srishopAllProducts(Srishop srishop); //Step 1
    List<Srishop> getAllSriShopProducts(); //Step 2
    Srishop getSrishopProductById(Long product_id); //Step 3
    List<Srishop> getProductByCategories(String categories); //Step 4
    void addProductToSricart(Long product_id,Long customer_id,Integer quantity); //Step 5
    List<Srishop> getSrishopLimitedProduct(int limit); //Step 8
    List<Srishop> getSrishopRange(double minRate, double maxRate); //Step 10
    List<Srishop> getRangeByCategories(double minRate, double maxRate,String categories); //Step 11
    Srishop updateSrishopByID(Long product_id, Integer stock, double rate, String region,String region_availability); //Step 12
    Srishop updateSrishopByPatch(Long product_id, Srishop srishopList); //Step 13
    String isProductStockAvailable(Long product_id);
    String isProductRegionAvailable(Long product_id);
    List<Srishop> getProductsSortedByRateAsc(); //Step 14
    List<Srishop> getProductsSortedByRateDesc(); //Step 14
    List<Srishop> getProductsSortedByCategoriesAsc(); //Step 15
    List<Srishop> getProductsSortedByCategoriesDesc(); //Step 15
    List<Srishop> getProductsSortedByRatingAsc(); //Step 16
    List<Srishop> getProductsSortedByRatingDesc(); //Step 16
    List<Srishop> getProductsSortedByProductNameAsc(); //Step 17
    List<Srishop> getProductsSortedByProductNameDesc(); //Step 17
    List<Srishop> getProductsSortedByReviewCountAsc(); //Step 18
    List<Srishop> getProductsSortedByReviewCountDesc(); //Step 18
    void DeleteProductById(Long product_id); //Step 19
    List<Srishop> getProductsSortedByNameAndRatings(String sort);
}
