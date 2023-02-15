package com.example.laptopone.repository;

import com.example.laptopone.model.ProductEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductEntryRepository extends JpaRepository<ProductEntry, Integer> {
    @Query(value = "select * from PRODUCT_ENTRY where product_id = :id", nativeQuery = true)
    List<ProductEntry> findAllByProductId(int id);

    @Transactional
    @Modifying
    @Query(value = "update PRODUCT_ENTRY set " +
            "sku=:sku, " +
            "name_entry=:name, " +
            "screen=:screen, " +
            "cpu=:cpu, " +
            "gpu=:gpu, " +
            "color=:color, " +
            "ram=:ram, " +
            "rom=:rom, " +
            "weight=:weight, " +
            "size=:size, " +
            "origin=:origin, " +
            "price=:price, " +
            "year=:year, " +
            "quantity=:quantity, " +
            "image=:image where id=:id", nativeQuery = true)
    void updateLaptopEntry(@Param("sku") String sku,
                           @Param("name") String name,
                           @Param("screen") String screen,
                           @Param("cpu") String cpu,
                           @Param("gpu") String gpu,
                           @Param("color") String color,
                           @Param("ram") String ram,
                           @Param("rom") String rom,
                           @Param("weight") String weight,
                           @Param("size") String size,
                           @Param("origin") String origin,
                           @Param("price") String price,
                           @Param("year") String year,
                           @Param("image") String image,
                           @Param("quantity") int quantity,
                           @Param("id") String id);

    @Transactional
    @Modifying
    @Query(value = "update PRODUCT_ENTRY set quantity=:quantity where id=:id", nativeQuery = true)
    void updateQuantity(@Param("id") int id, @Param("quantity") int quantity);
}
