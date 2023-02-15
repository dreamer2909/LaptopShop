package com.example.laptopone.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO {
    private long time;
    private String address;
    private float total;
    private String name;
    private String phone;
    private String email;
}
