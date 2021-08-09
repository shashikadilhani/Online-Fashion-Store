package com.fashion.demo.Repository;


import com.fashion.demo.Entity.RoleEntity;
import com.fashion.demo.Enum.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    @Query(value = "select*  from tbl_roles where name =?1 ", nativeQuery = true)
    Optional<RoleEntity> findByName(String roleName);
}
