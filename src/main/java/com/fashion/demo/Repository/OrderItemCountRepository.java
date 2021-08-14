package com.fashion.demo.Repository;

import com.fashion.demo.Entity.OrderItemCountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderItemCountRepository extends JpaRepository<OrderItemCountEntity, Long> {

    @Query(value = "select* from tbl_order_item_count where fk_order_id =?1", nativeQuery = true)
    List<OrderItemCountEntity> findItemsByOrderId(long order_id);
}
