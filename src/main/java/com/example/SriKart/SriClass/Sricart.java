package com.example.SriKart.SriClass;

import lombok.Data;
import javax.persistence.*;
@Data
@Entity
@Table(name = "Sricart")
public class Sricart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long cart_id;
    Long product_id;
    Long customerId;
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
    @Column
    String customer_name;
    @Column
    String phone_num;
    @Column
    String email_id;
    @Column
    String delivery_address;
}
