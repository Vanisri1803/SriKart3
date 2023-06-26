package com.example.SriKart.SriClass;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "Customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long customerId;
    @Column
    String customerName;
    @Column
    String phoneNum;
    @Column
    String emailId;
    @Column
    String deliveryAddress;
}
