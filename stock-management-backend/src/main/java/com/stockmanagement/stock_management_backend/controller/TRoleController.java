package com.stockmanagement.stock_management_backend.controller;

import com.stockmanagement.stock_management_backend.dto.TRoleDTO;
import com.stockmanagement.stock_management_backend.dto.TRoleRequestDTO;
import com.stockmanagement.stock_management_backend.service.TRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/roles")
@Tag(name = "Role Management", description = "API endpoints for managing roles")
public class TRoleController {

    private final TRoleService roleService;

    @Autowired
    public TRoleController(TRoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    @Operation(summary = "Get all roles", description = "Retrieve a list of all non-deleted roles")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of roles")
    public ResponseEntity<List<TRoleDTO>> getAllRoles() {
        List<TRoleDTO> roles = roleService.getAllRoles();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get role by ID", description = "Retrieve a specific role by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role found"),
            @ApiResponse(responseCode = "404", description = "Role not found")
    })
    public ResponseEntity<TRoleDTO> getRoleById(
            @Parameter(description = "ID of the role to retrieve") @PathVariable Integer id) {
        Optional<TRoleDTO> role = roleService.getRoleById(id);
        return role.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create a new role", description = "Create a new role with the provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Role created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input or role name already exists")
    })
    public ResponseEntity<?> createRole(@Valid @RequestBody TRoleRequestDTO requestDTO) {
        try {
            TRoleDTO createdRole = roleService.createRole(requestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdRole);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing role", description = "Update a role with the provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role updated successfully"),
            @ApiResponse(responseCode = "404", description = "Role not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input or role name already exists")
    })
    public ResponseEntity<?> updateRole(
            @Parameter(description = "ID of the role to update") @PathVariable Integer id,
            @Valid @RequestBody TRoleRequestDTO requestDTO) {
        try {
            Optional<TRoleDTO> updatedRole = roleService.updateRole(id, requestDTO);
            return updatedRole.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a role", description = "Soft delete a role by setting IsDeleted flag to true")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Role not found")
    })
    public ResponseEntity<String> deleteRole(
            @Parameter(description = "ID of the role to delete") @PathVariable Integer id) {
        boolean deleted = roleService.deleteRole(id);
        if (deleted) {
            return ResponseEntity.ok("Role deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

