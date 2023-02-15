package com.example.laptopone.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String name;
    private String email;
    private String address;
    private String image;
    @Column(length = 200000)
    private String description;
    private String banner;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    List<Product> products = new LinkedList<>();

//    public Brand(String name, String email, String address, String image, String description, String banner) {
//        this.name = name;
//        this.email = email;
//        this.address = address;
//        this.image = image;
//        this.description = description;
//        this.banner = banner;
//    }
}
