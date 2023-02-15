package com.example.laptopone.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class ProductEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String sku;
    private String nameEntry;
    private String screen;
    private String cpu;
    private String gpu;
    private String year;
    private String price;
    private String weight;
    private String size;
    private String origin;
    private int quantity;
    private String image;
    private String rom;
    private String color;
    private String ram;
    private float rating;
    @ManyToOne()
    @JoinColumn(name = "product_id")
    private Product product;
    @OneToMany(mappedBy = "productEntry", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<OrderItem> orderItems;
    @OneToMany(mappedBy = "productEntry", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private  List<Comment> comments;
}
