package com.example.laptopone.repository;

import com.example.laptopone.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
    @Transactional
    @Modifying
    @Query("update Brand set name=:name, email=:email, address=:address, image=:image, description=:description, banner=:banner where id=:id")
    void updateById(@Param("name") String name,
                    @Param("email") String email,
                    @Param("address") String address,
                    @Param("image") String image,
                    @Param("description") String description,
                    @Param("banner") String banner,
                    @Param("id") int id);

    Brand findBrandByName(String name);
}
