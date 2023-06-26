package com.example.SriKart.SriClass;

import lombok.Data;
import javax.persistence.*;
@Data
@Entity
@Table (name = "Srishop")
public class Srishop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long product_id;
    @Column
    String product_name;
    @Column
    String categories;
    @Column
    double rate;
    @Column
    Integer quantity;
    @Column
    String description;
    @Column
    Integer stock;
    @Column
    boolean stock_availability;
    @Column
    String location;
    @Column
    String region;
    @Column
    String region_availability;
    @Column
    float ratings;
    @Column
    Integer review_count;
}
