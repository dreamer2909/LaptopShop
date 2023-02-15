package com.example.laptopone.controller;

import com.example.laptopone.model.Brand;
import com.example.laptopone.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping("/brands")
public class BrandController {
    @Autowired
    private BrandRepository brandRepository;

    @GetMapping()
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    @PostMapping()
    public void addBrand(@RequestParam("name") String name,
                         @RequestParam("email") String email,
                         @RequestParam("address") String address,
                         @RequestParam("image") MultipartFile image,
                         @RequestParam("description") String description,
                         @RequestParam("banner") MultipartFile banner) {
        Brand brand = Brand.builder()
                .name(name)
                .email(email)
                .address(address)
                .image(image.getOriginalFilename())
                .description(description)
                .banner(banner.getOriginalFilename())
                .build();

        System.out.println(brand);

        brandRepository.save(brand);
    }

    @DeleteMapping("/{id}")
    public void deleteBrand(@PathVariable int id) {
        brandRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public void updateBrand(@RequestParam("name") String name,
                            @RequestParam("email") String email,
                            @RequestParam("address") String address,
                            @RequestParam("image") MultipartFile image,
                            @RequestParam("description") String description,
                            @RequestParam("banner") MultipartFile banner,
                            @PathVariable int id) {
        brandRepository.updateById(name, email, address, image.getOriginalFilename(), description, banner.getOriginalFilename(), id);
    }
}
