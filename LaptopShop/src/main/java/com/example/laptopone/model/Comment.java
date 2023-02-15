package com.example.laptopone.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String authorName;
    private String content;
    private int rating;
    private int status;

    @ManyToOne()
    @JoinColumn(name = "order_id")
    private OrderDetail orderDetail;

    @ManyToOne()
    @JoinColumn(name = "entry_id")
    private ProductEntry productEntry;
}
