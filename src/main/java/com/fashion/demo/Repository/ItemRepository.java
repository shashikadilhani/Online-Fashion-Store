package com.fashion.demo.Repository;

import com.fashion.demo.Entity.ItemEntity;
import com.fashion.demo.Enum.ItemCategory;
import com.fashion.demo.Enum.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

    @Query(value = "select distinct item_type from tbl_item where item_category =?1 ", nativeQuery = true)
    List<ItemType> findItemTypesByCategory(String category);
}
