package com.shoppingcart.demo.Repository;

import com.shoppingcart.demo.Entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<AdminEntity, Long> {
}
