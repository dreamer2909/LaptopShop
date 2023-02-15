package com.example.laptopone;

import com.example.laptopone.model.Brand;
import com.example.laptopone.model.Product;
import com.example.laptopone.model.ProductEntry;
import com.example.laptopone.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class LaptopOneApplication implements Runnable {
    @Autowired
    private ProductRepository laptopRepository;

    @Autowired
    private ProductEntryRepository laptopEntryRepository;

    @Autowired
    private BrandRepository brandRepository;

    public static void main(String[] args) {
        SpringApplication.run(LaptopOneApplication.class, args);
    }

    @Override
    public void run() {
        Brand macbook = new Brand();
        macbook.setName("Macbook");
        macbook.setAddress("USA");
        macbook.setDescription("Desc");

        Product product = new Product();
        product.setName("Macbook Pro M1");
        product.setType("laptop");
        product.setBrand(macbook);

        ProductEntry entry = new ProductEntry();
        entry.setNameEntry("Blue");
        entry.setCpu("Apple M1");
        entry.setProduct(product);

        brandRepository.save(macbook);
    }
}
