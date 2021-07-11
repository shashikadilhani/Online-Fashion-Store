package com.shoppingcart.demo.Repository;


import com.shoppingcart.demo.Entity.RoleEntity;
import com.shoppingcart.demo.Enum.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(RoleName roleName);
}
