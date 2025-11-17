package com.stockmanagement.stock_management_backend.dto;

import java.time.LocalDateTime;

public class TRoleDTO {
    private Integer roleId;
    private String roleName;
    private String description;
    private Boolean isDeleted;
    private Integer lastUpdatedBy;
    private LocalDateTime lastUpdatedDate;

    // Default constructor
    public TRoleDTO() {
    }

    // Constructor with all fields
    public TRoleDTO(Integer roleId, String roleName, String description, Boolean isDeleted, 
                    Integer lastUpdatedBy, LocalDateTime lastUpdatedDate) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.description = description;
        this.isDeleted = isDeleted;
        this.lastUpdatedBy = lastUpdatedBy;
        this.lastUpdatedDate = lastUpdatedDate;
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
}

