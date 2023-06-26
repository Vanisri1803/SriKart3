package com.example.SriKart.Controller;


import com.example.SriKart.Service.SricartService;
import com.example.SriKart.SriClass.Sricart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/sriKart")
public class SriCartController {
    @Autowired
    SricartService sricartService;
    @GetMapping("/sriCart/getAllSricartProducts") //step 6
    //http://localhost:8080/sriKart/sriCart/getAllSricartProducts
    public ResponseEntity<List<Sricart>> getAllSricartProducts(){
        return new ResponseEntity<List<Sricart>>(sricartService.getAllSricartProducts(), HttpStatus.ACCEPTED);
    }
    @GetMapping("/sriCart/getSricartProductById/{cart_id}") //step 7
    //http://localhost:8080/sriKart/sriCart/getSricartProductById/18
    public ResponseEntity<Sricart> getSricartProductById(@PathVariable Long cart_id){
        return new ResponseEntity<Sricart>(sricartService.getSricartProductById(cart_id),HttpStatus.CREATED);
    }
    @GetMapping("/sriCart/getSricartLimitedProduct") //step 9
    //http://localhost:8080/sriKart/sriCart/getSricartLimitedProduct?limit=3
    public List<Sricart> getSricartLimitedProduct(@RequestParam(name = "limit") int limit) {
        return sricartService.getSricartLimitedProduct(limit);
    }
    @GetMapping("/sriCart/getSricartCustomerById/{customerId}")
    //http://localhost:8080/sriKart/sriCart/getSricartCustomerById/2
    public ResponseEntity<List<Sricart>> getSricartCustomerById(@PathVariable Long customerId){
        return new ResponseEntity<List<Sricart>>(sricartService.getSricartCustomerById(customerId),HttpStatus.CREATED);
    }
    @PutMapping("/sriCart/updateSriCart/{cart_id}/{product_id}/{quantity}")
    //http://localhost:8080/sriKart/sriCart/updateSriCart/20/18/2
    public Sricart updateSriCartByID(@PathVariable Long cart_id,@PathVariable Long product_id, @PathVariable Integer quantity){
        return sricartService.updateSriCartByID(cart_id,product_id,quantity);
    }
    @DeleteMapping("/sriCart/DeleteCartById/{cart_id}")
    //http://localhost:8080/sriKart/sriCart/DeleteCartById/2
    public void DeleteCartById(@PathVariable Long cart_id){
        sricartService.DeleteCartById(cart_id);
    }
}
