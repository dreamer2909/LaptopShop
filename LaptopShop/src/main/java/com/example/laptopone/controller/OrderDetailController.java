package com.example.laptopone.controller;

import com.example.laptopone.DTO.EmailSendingDTO;
import com.example.laptopone.DTO.OrderDetailDTO;
import com.example.laptopone.model.OrderDetail;
import com.example.laptopone.repository.OrderDetailResository;
import com.example.laptopone.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/order")
public class OrderDetailController {
    @Autowired
    private OrderDetailResository orderDetailResository;

    @Autowired
    private EmailSenderService emailSenderService;

    @PostMapping()
    public int addOrder(@RequestBody OrderDetailDTO orderDetail) {
        OrderDetail order = new OrderDetail();
        order.setStatus( 0);
        LocalDateTime ldt = Instant.ofEpochMilli(orderDetail.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
        order.setCreatedAt(ldt);
        order.setAddress(orderDetail.getAddress());
        order.setTotal(orderDetail.getTotal());
        order.setEmail(orderDetail.getEmail());
        order.setPhone(orderDetail.getPhone());
        order.setName(orderDetail.getName());
        return orderDetailResository.save(order).getId();
    }

    @GetMapping("/status={status}")
    public List<OrderDetail> getOrders(@PathVariable int status) {
        return orderDetailResository.getOrderDetailByStatus(status);
    }

    @GetMapping("/id={id}")
    public OrderDetail getOrder(@PathVariable int id) {
        return orderDetailResository.getById(id);
    }

    @PutMapping("/id={id}/status={status}")
    public void updateStatus(@PathVariable int id, @PathVariable int status) {
        orderDetailResository.updateStatusById(status, id);
    }

    @PostMapping("/send_email")
    public void sendEmail(@RequestBody EmailSendingDTO email) {
        emailSenderService.sendEmail(email.getEmail(), email.getSubject(), email.getBody());
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable int id) {
        orderDetailResository.deleteById(id);
    }
}
