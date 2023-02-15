package com.example.laptopone.controller;

import com.example.laptopone.DTO.CommentDTO;
import com.example.laptopone.model.Comment;
import com.example.laptopone.model.OrderDetail;
import com.example.laptopone.repository.CommentRepository;
import com.example.laptopone.repository.OrderDetailResository;
import com.example.laptopone.repository.ProductEntryRepository;
import com.example.laptopone.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ProductEntryRepository productEntryRepository;

    @Autowired
    private OrderDetailResository orderDetailResository;

    @Autowired
    private CommentService commentService;

    @GetMapping("/entry_id={id}/status=1")
    public List<Comment> getVerifiedCommentsByEntryId(@PathVariable int id) {
        return commentRepository.getCommentByEntryId(id, 1);
    }

    @GetMapping("/status=0")
    public List<Comment> getPendingComments() {
        return commentRepository.getCommentsByStatus(0);
    }

    @GetMapping("/status=1")
    public List<Comment> getVerifiedComments() {
        return commentRepository.getCommentsByStatus(1);
    }

    @PostMapping()
    public int addComment(@RequestBody CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setContent(commentDTO.getContent());
        comment.setAuthorName(commentDTO.getAuthorName());
        comment.setStatus(commentDTO.getStatus());
        comment.setRating(commentDTO.getRating());
        comment.setOrderDetail(orderDetailResository.getById(commentDTO.getOrderId()));
        comment.setProductEntry(productEntryRepository.getById(commentDTO.getEntryId()));

        int orderId = commentDTO.getOrderId();
        int entryId = commentDTO.getEntryId();

        if (!commentService.checkIfOrderDelivered(orderId, entryId)) return 0;
        if (!commentService.checkCommented(orderId, entryId)) return 1;
        OrderDetail orderDetail = orderDetailResository.getById(commentDTO.getOrderId());
        orderDetail.setStatus(3);
        commentRepository.save(comment);
        return 2;
    }

    @PutMapping("/id={id}/status={status}")
    public void updateStatus(@PathVariable int id, @PathVariable int status) {
        commentRepository.updateStatusById(status, id);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable int id) {
        commentRepository.deleteById(id);
    }
}
