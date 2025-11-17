package com.stockmanagement.stock_management_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TRoleRequestDTO {
    
    @NotBlank(message = "Role name is required")
    @Size(max = 50, message = "Role name must not exceed 50 characters")
    private String roleName;

    @Size(max = 100, message = "Description must not exceed 100 characters")
    private String description;

    @NotNull(message = "Last updated by is required")
    private Integer lastUpdatedBy;

    // Default constructor
    public TRoleRequestDTO() {
    }

    // Constructor with fields
    public TRoleRequestDTO(String roleName, String description, Integer lastUpdatedBy) {
        this.roleName = roleName;
        this.description = description;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    // Getters and Setters
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

    public Integer getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(Integer lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
}

