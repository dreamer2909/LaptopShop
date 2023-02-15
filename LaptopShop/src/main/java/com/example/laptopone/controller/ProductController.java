package com.example.laptopone.controller;

import com.example.laptopone.DTO.ProductDTO;
import com.example.laptopone.model.Brand;
import com.example.laptopone.model.Product;
import com.example.laptopone.repository.BrandRepository;
import com.example.laptopone.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    BrandRepository brandRepository;

    @GetMapping()
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/laptops")
    public List<Product> getAllLaptops() {
        return productRepository.findAllLaptops("laptop");
    }

    @GetMapping("/product_id={id}")
    public Product getLaptop(@PathVariable int id) {
        return productRepository.getById(id);
    }

    @PostMapping()
    public void addProduct(@RequestBody ProductDTO product) {
        Brand brand = brandRepository.findBrandByName(product.getBrandName());
        productRepository.save(new Product(product.getName(), brand, product.getType(), product.getDescription(), product.getTag()));
    }

    @DeleteMapping("/product_id={id}")
    public void deleteProduct(@PathVariable int id) {
        productRepository.deleteById(id);
    }
}
