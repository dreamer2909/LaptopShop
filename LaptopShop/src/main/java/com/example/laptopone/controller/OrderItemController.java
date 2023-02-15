package com.example.laptopone.controller;

import com.example.laptopone.DTO.OrderItemDTO;
import com.example.laptopone.model.OrderDetail;
import com.example.laptopone.model.OrderItem;
import com.example.laptopone.model.ProductEntry;
import com.example.laptopone.repository.OrderDetailResository;
import com.example.laptopone.repository.OrderItemRepository;
import com.example.laptopone.repository.ProductEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
@CrossOrigin
public class OrderItemController {
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderDetailResository orderDetailResository;

    @Autowired
    private ProductEntryRepository productEntryRepository;

    @PostMapping("/{order_id}")
    public void addItem(@RequestBody OrderItemDTO orderItemDTO, @PathVariable int order_id) {
        OrderDetail orderDetail = orderDetailResository.getById(order_id);
        OrderItem orderItem = new OrderItem();
        ProductEntry entry = productEntryRepository.getById(orderItemDTO.getEntryId());

        orderItem.setPrice(orderItemDTO.getPrice());
        orderItem.setQuantity(orderItemDTO.getQuantity());
        orderItem.setOrderDetail(orderDetail);
        orderItem.setProductEntry(entry);

        orderItemRepository.save(orderItem);
    }

    @GetMapping("/{order_id}")
    public List<OrderItem> orderItems(@PathVariable int order_id) {
        return orderItemRepository.findOrderItemsByOrderId(order_id);
    }
}
