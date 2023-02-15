package com.example.laptopone.service;

import com.example.laptopone.model.Comment;
import com.example.laptopone.model.OrderDetail;
import com.example.laptopone.model.OrderItem;
import com.example.laptopone.repository.CommentRepository;
import com.example.laptopone.repository.OrderDetailResository;
import com.example.laptopone.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private OrderDetailResository orderDetailResository;

    public boolean checkCommented(int orderId, int entryId) {
        List<Comment> comments = commentRepository.getCommentByEntryIdAndOrderId(entryId, orderId);
        return comments.isEmpty();
    }

    public boolean checkIfOrderDelivered(int orderId, int entryId) {
        OrderDetail orderDetail = orderDetailResository.getById(orderId);
        if (orderDetail.getStatus() == 0 || orderDetail.getStatus() == 1) return false; // check if order delivered

        List<OrderItem> items =  orderItemRepository.findOrderItemsByOrderDetailAndProductEntry(orderId, entryId);

        if (items.isEmpty()) return false;
        return true;
    }
}
