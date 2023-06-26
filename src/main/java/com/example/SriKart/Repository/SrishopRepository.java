package com.example.SriKart.Repository;

import com.example.SriKart.SriClass.Srishop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface SrishopRepository extends JpaRepository<Srishop,Long> {
    Optional<List<Srishop>> findByCategories(String categories);
}
