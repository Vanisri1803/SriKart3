package com.example.SriKart.Repository;

import com.example.SriKart.SriClass.Sricart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SricartRepository extends JpaRepository<Sricart,Long> {
    Optional<List<Sricart>> findByCustomerId(Long customerId);
}
