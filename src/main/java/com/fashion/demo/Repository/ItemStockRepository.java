package com.fashion.demo.Repository;
import com.fashion.demo.Entity.ItemStockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ItemStockRepository extends JpaRepository<ItemStockEntity, Long> {

    @Query(value = "select*  from tbl_item_stock where item_id =?1 ", nativeQuery = true)
    Optional<ItemStockEntity> findByItemId(long item_id);
}
