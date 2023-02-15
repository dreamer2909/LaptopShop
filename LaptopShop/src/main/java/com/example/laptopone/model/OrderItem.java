package com.example.laptopone.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int quantity;
    private float price;

    @ManyToOne()
    @JoinColumn(name = "entry_id")
    private ProductEntry productEntry;


    @ManyToOne()
    @JoinColumn(name = "order_id")
    private OrderDetail orderDetail;
}
