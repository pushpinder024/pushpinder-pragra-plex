package com.example.pragraplex.repo;

import com.example.pragraplex.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {
//    @Modifying
//    @Transactional
//    @Query(value = "DELETE FROM TABLE_ORDER OA WHERE OA.ORDER_ID =:orderId", nativeQuery = true)
//    void deleteByOrderId(int orderId);
}
