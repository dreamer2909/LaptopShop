package com.example.laptopone.repository;

import com.example.laptopone.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderDetailResository extends JpaRepository<OrderDetail, Integer> {
    @Query(value = "select * from ORDER_DETAIL order by id desc limit 1", nativeQuery = true)
    OrderDetail getLastOrder();

    @Query(value = "select * from ORDER_DETAIL where status =:status", nativeQuery = true)
    List<OrderDetail> getOrderDetailByStatus(int status);

    @Transactional
    @Modifying
    @Query("update OrderDetail set status=:status where id=:id")
    void updateStatusById(int status, int id);
}
