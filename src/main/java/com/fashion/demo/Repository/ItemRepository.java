package com.fashion.demo.Repository;

import com.fashion.demo.Entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

    @Query(value = "select * from tbl_item where item_category =?1", nativeQuery = true)
    List<ItemEntity> findByCategory(String category);
}
