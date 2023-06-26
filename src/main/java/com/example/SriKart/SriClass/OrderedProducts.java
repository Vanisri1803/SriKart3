package com.example.SriKart.SriClass;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "OrderedProducts")
public class OrderedProducts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;
    @Column
    Long orderId;
    @Column
    Long productsOrderId;
    @OneToOne
    Srishop srishop;
    @OneToOne
    Sricart sricart;
    @OneToOne
    Customer customer;
}
