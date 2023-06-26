package com.example.SriKart.SriClass;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "SalesDetails")
public class SalesDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long SalesId;
    @Column
    Long orderedId;
    @Column
    Long cartId;
    @Column
    Long product_id;
    @Column
    String product_name;
    @Column
    String categories;
    @Column
    String location;
    @Column
    String region;
    @Column
    float ratings;
    @Column
    Integer review_count;
    @Column
    Long customerId;
    @Column
    String customer_name;
    @Column
    String phone_num;
    @Column
    String email_id;
    @Column
    String delivery_address;
    @Column
    double rate;
    @Column
    Integer quantity;
    @Column
    double gstAmount;
    @Column
    double deliveryCharge;
    @Column
    double discount;
    @Column
    double totalAmount;
}
