package com.example.laptopone.repository;

import com.example.laptopone.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    @Query(value = "select * from COMMENT where entry_id =:id and status=:status", nativeQuery = true)
    List<Comment> getCommentByEntryId(int id, int status);

    @Query(value = "select * from COMMENT where entry_id =:entryId and order_id=:orderId", nativeQuery = true)
    List<Comment> getCommentByEntryIdAndOrderId(int entryId, int orderId);

    @Query(value = "select * from COMMENT where status =:status", nativeQuery = true)
    List<Comment> getCommentsByStatus(int status);

    @Transactional
    @Modifying
    @Query("update Comment set status=:status where id=:id")
    void updateStatusById(int status, int id);
}
