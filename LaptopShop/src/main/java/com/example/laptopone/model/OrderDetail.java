package com.example.laptopone.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int status;
    @JsonFormat(pattern = "MM/dd/yyyy, hh:mm:ss a",shape=JsonFormat.Shape.STRING)
    private LocalDateTime createdAt;
    private String address;
    private String email;
    private String name;
    private String phone;

    @OneToMany(mappedBy = "orderDetail", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    List<OrderItem> orderItems = new LinkedList<>();

    @OneToMany(mappedBy = "orderDetail", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    List<Comment> comments = new LinkedList<>();

    private float total;
}
