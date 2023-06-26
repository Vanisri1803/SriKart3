package com.example.SriKart.Repository;

import com.example.SriKart.SriClass.OrderedProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderedProductsRepository extends JpaRepository<OrderedProducts,Long> {
    Optional<List<OrderedProducts>> findByProductsOrderId(Long productsOrderId);
    Optional<List<OrderedProducts>> findByOrderId(Long orderId); //step 20
}
