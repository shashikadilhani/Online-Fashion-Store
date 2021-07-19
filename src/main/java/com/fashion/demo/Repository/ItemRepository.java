package com.fashion.demo.Repository;

import com.fashion.demo.Entity.ItemEntity;
import com.fashion.demo.Entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
}
