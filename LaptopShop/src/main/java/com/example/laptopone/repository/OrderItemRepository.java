package com.example.laptopone.repository;

import com.example.laptopone.model.OrderDetail;
import com.example.laptopone.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    @Query(value = "select * from ORDER_ITEM where order_id =:id", nativeQuery = true)
    List<OrderItem> findOrderItemsByOrderId(int id);

    @Query(value = "select * from ORDER_ITEM where order_id =:orderId and entry_id=:entryId", nativeQuery = true)
    List<OrderItem> findOrderItemsByOrderDetailAndProductEntry(int orderId, int entryId);
}
