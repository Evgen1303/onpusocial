package com.hunghost.onpusocial.service.role;

import com.hunghost.onpusocial.entity.Role;
import com.hunghost.onpusocial.exception.ResourceNotFoundException;
import com.hunghost.onpusocial.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleQueryService {
    private RoleRepository roleRepository;

    @Autowired
    public RoleQueryService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getRoleById(Long id) {
         return roleRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }
}
