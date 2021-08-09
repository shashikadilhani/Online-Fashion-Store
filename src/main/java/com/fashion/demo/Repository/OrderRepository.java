package com.fashion.demo.Repository;

import com.fashion.demo.Entity.OderEntity;
import com.fashion.demo.Entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OderEntity, Long> {
}
