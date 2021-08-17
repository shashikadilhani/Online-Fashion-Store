package com.fashion.demo.Repository;

import com.fashion.demo.Entity.OderEntity;
import com.fashion.demo.Entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<OderEntity, Long> {

    @Query(value = "select* from tbl_order where user_id =?1 and status='PENDING'", nativeQuery = true)
    Optional<OderEntity> findOrderByUserId(long user_id);

    @Query(value = "select* from tbl_order where user_id =?1 and ", nativeQuery = true)
    List<OderEntity> findAllOrdersByUserId(long user_id);
}
