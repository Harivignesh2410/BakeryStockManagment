package com.stockmanagement.stock_management_backend.repository;

import com.stockmanagement.stock_management_backend.entity.TRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TRoleRepository extends JpaRepository<TRole, Integer> {

    // Find all non-deleted roles
    List<TRole> findByIsDeletedFalse();

    // Find role by name (excluding deleted)
    Optional<TRole> findByRoleNameAndIsDeletedFalse(String roleName);

    // Check if role name exists (excluding deleted)
    boolean existsByRoleNameAndIsDeletedFalse(String roleName);

    // Find role by ID (excluding deleted)
    @Query("SELECT r FROM TRole r WHERE r.roleId = :id AND r.isDeleted = false")
    Optional<TRole> findByIdAndNotDeleted(Integer id);
}

