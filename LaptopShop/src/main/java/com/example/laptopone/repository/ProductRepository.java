package com.example.laptopone.repository;

import com.example.laptopone.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "select * from PRODUCT where type=:type", nativeQuery = true)
    List<Product> findAllLaptops(String type);

    @Query(value = "select distinct tag from PRODUCT where type=:type", nativeQuery = true)
    List<String> getProductTags(String type);
}
