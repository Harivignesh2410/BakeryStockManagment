package com.stockmanagement.stock_management_backend.service;

import com.stockmanagement.stock_management_backend.dto.TRoleDTO;
import com.stockmanagement.stock_management_backend.dto.TRoleRequestDTO;
import com.stockmanagement.stock_management_backend.entity.TRole;
import com.stockmanagement.stock_management_backend.repository.TRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class TRoleService {

    private final TRoleRepository roleRepository;

    @Autowired
    public TRoleService(TRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    // Get all non-deleted roles
    public List<TRoleDTO> getAllRoles() {
        List<TRole> roles = roleRepository.findByIsDeletedFalse();
        return roles.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Get role by ID
    public Optional<TRoleDTO> getRoleById(Integer id) {
        Optional<TRole> role = roleRepository.findByIdAndNotDeleted(id);
        return role.map(this::convertToDTO);
    }

    // Create new role
    public TRoleDTO createRole(TRoleRequestDTO requestDTO) {
        // Check if role name already exists
        if (roleRepository.existsByRoleNameAndIsDeletedFalse(requestDTO.getRoleName())) {
            throw new RuntimeException("Role with name '" + requestDTO.getRoleName() + "' already exists");
        }

        TRole role = new TRole();
        role.setRoleName(requestDTO.getRoleName());
        role.setDescription(requestDTO.getDescription());
        role.setLastUpdatedBy(requestDTO.getLastUpdatedBy());
        role.setIsDeleted(false);
        role.setLastUpdatedDate(java.time.LocalDateTime.now());

        TRole savedRole = roleRepository.save(role);
        return convertToDTO(savedRole);
    }

    // Update existing role
    public Optional<TRoleDTO> updateRole(Integer id, TRoleRequestDTO requestDTO) {
        Optional<TRole> roleOpt = roleRepository.findByIdAndNotDeleted(id);
        
        if (roleOpt.isEmpty()) {
            return Optional.empty();
        }

        TRole role = roleOpt.get();
        
        // Check if new role name conflicts with existing role (excluding current role)
        Optional<TRole> existingRole = roleRepository.findByRoleNameAndIsDeletedFalse(requestDTO.getRoleName());
        if (existingRole.isPresent() && !existingRole.get().getRoleId().equals(id)) {
            throw new RuntimeException("Role with name '" + requestDTO.getRoleName() + "' already exists");
        }

        role.setRoleName(requestDTO.getRoleName());
        role.setDescription(requestDTO.getDescription());
        role.setLastUpdatedBy(requestDTO.getLastUpdatedBy());
        role.setLastUpdatedDate(java.time.LocalDateTime.now());

        TRole updatedRole = roleRepository.save(role);
        return Optional.of(convertToDTO(updatedRole));
    }

    // Soft delete role
    public boolean deleteRole(Integer id) {
        Optional<TRole> roleOpt = roleRepository.findByIdAndNotDeleted(id);
        
        if (roleOpt.isEmpty()) {
            return false;
        }

        TRole role = roleOpt.get();
        role.setIsDeleted(true);
        role.setLastUpdatedDate(java.time.LocalDateTime.now());
        roleRepository.save(role);
        return true;
    }

    // Convert Entity to DTO
    private TRoleDTO convertToDTO(TRole role) {
        TRoleDTO dto = new TRoleDTO();
        dto.setRoleId(role.getRoleId());
        dto.setRoleName(role.getRoleName());
        dto.setDescription(role.getDescription());
        dto.setIsDeleted(role.getIsDeleted());
        dto.setLastUpdatedBy(role.getLastUpdatedBy());
        dto.setLastUpdatedDate(role.getLastUpdatedDate());
        return dto;
    }
}

