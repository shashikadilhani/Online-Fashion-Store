package com.fashion.demo.Repository;

import com.fashion.demo.Entity.ItemEntity;
import com.fashion.demo.Entity.RoleEntity;
import com.fashion.demo.Enum.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

    List<ItemEntity> findByCategory(ItemCategory category);
}
