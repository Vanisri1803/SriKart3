package com.example.SriKart.Repository;

import com.example.SriKart.SriClass.SalesDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepository extends JpaRepository<SalesDetails,Long> {
}
