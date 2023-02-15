package com.example.laptopone.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    @ManyToOne()
    @JoinColumn(name = "brand_id")
    private Brand brand;
    private String type;
    @Column(length = 200000)
    private String description;
    private String tag;

    public Product(String name, Brand brand, String type, String description, String tag) {
        this.name = name;
        this.brand = brand;
        this.type = type;
        this.description = description;
        this.tag = tag;
    }

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<ProductEntry> productEntries = new LinkedList<>();
}
