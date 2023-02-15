package com.example.laptopone.controller;

import com.example.laptopone.model.Product;
import com.example.laptopone.model.ProductEntry;
import com.example.laptopone.repository.ProductEntryRepository;
import com.example.laptopone.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/product_entries")
public class ProductEntryController {
    @Autowired
    private ProductEntryRepository productEntryRepository;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("")
    public List<ProductEntry> getAllEntries() {
        return productEntryRepository.findAll();
    }

    @GetMapping("/product_id={id}")
    public List<ProductEntry> getAllEntriesByProductId(@PathVariable int id) {
        return productEntryRepository.findAllByProductId(id);
    }

    @PostMapping("/product_id={id}")
    public void addLaptopEntry(
            @RequestParam("sku") String sku,
            @RequestParam("name") String nameEntry,
            @RequestParam("screen") String screen,
            @RequestParam("cpu") String cpu,
            @RequestParam("gpu") String gpu,
            @RequestParam("year") String year,
            @RequestParam("price") String price,
            @RequestParam("weight") String weight,
            @RequestParam("size") String size,
            @RequestParam("origin") String origin,
            @RequestParam("quantity") int quantity,
            @RequestParam("image")MultipartFile image,
            @RequestParam("rom") String rom,
            @RequestParam("color") String color,
            @RequestParam("ram") String ram,
            @PathVariable int id) {
        Product product = productRepository.getById(id);
        ProductEntry productEntry = new ProductEntry();
        productEntry.setSku(sku);
        productEntry.setNameEntry(product.getName() + " " + nameEntry);
        productEntry.setScreen(screen);
        productEntry.setColor(color);
        productEntry.setGpu(gpu);
        productEntry.setCpu(cpu);
        productEntry.setRam(ram);
        productEntry.setRom(rom);
        productEntry.setWeight(weight);
        productEntry.setSize(size);
        productEntry.setOrigin(origin);
        productEntry.setYear(year);
        productEntry.setPrice(price);
        productEntry.setQuantity(quantity);
        productEntry.setImage(image.getOriginalFilename());
        productEntry.setProduct(product);
        productEntryRepository.save(productEntry);
    }

    @PutMapping("/{id}")
    public void updateLaptopEntry(
            @RequestParam("sku") String sku,
            @RequestParam("name") String nameEntry,
            @RequestParam("screen") String screen,
            @RequestParam("cpu") String cpu,
            @RequestParam("gpu") String gpu,
            @RequestParam("year") String year,
            @RequestParam("price") String price,
            @RequestParam("weight") String weight,
            @RequestParam("size") String size,
            @RequestParam("origin") String origin,
            @RequestParam("quantity") int quantity,
            @RequestParam("image")MultipartFile image,
            @RequestParam("rom") String rom,
            @RequestParam("color") String color,
            @RequestParam("ram") String ram,
            @PathVariable String id) {
        productEntryRepository.updateLaptopEntry(sku, nameEntry, screen, cpu, gpu, color, ram, rom, weight, size, origin, price, year, image.getOriginalFilename(), quantity, id);
    }

    @PutMapping("/{id}/quantity={quantity}")
    public void updateQuantity(@PathVariable int id, @PathVariable int quantity) {
        productEntryRepository.updateQuantity(id, productEntryRepository.getById(id).getQuantity() - quantity);
    }

    @DeleteMapping("/{id}")
    public void deleteBrand(@PathVariable int id) {
        productEntryRepository.deleteById(id);
    }
}
