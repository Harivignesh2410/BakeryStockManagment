package com.stockmanagement.stock_management_backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "tRole")
public class TRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_RoleID")
    private Integer roleId;

    @NotBlank(message = "Role name is required")
    @Size(max = 50, message = "Role name must not exceed 50 characters")
    @Column(name = "RoleName", nullable = false, length = 50)
    private String roleName;

    @Size(max = 100, message = "Description must not exceed 100 characters")
    @Column(name = "Description", length = 100)
    private String description;

    @Column(name = "IsDeleted", nullable = false)
    private Boolean isDeleted = false;

    @Column(name = "LastUpdatedBy", nullable = false)
    private Integer lastUpdatedBy;

    @Column(name = "LastUpdatedDate", nullable = false)
    private LocalDateTime lastUpdatedDate;

    // Default constructor
    public TRole() {
    }

    // Constructor with required fields
    public TRole(String roleName, Integer lastUpdatedBy) {
        this.roleName = roleName;
        this.lastUpdatedBy = lastUpdatedBy;
        this.isDeleted = false;
        this.lastUpdatedDate = LocalDateTime.now();
    }

    // Getters and Setters
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(Integer lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public LocalDateTime getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(LocalDateTime lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    @PrePersist
    @PreUpdate
    protected void onUpdate() {
        this.lastUpdatedDate = LocalDateTime.now();
    }
}

