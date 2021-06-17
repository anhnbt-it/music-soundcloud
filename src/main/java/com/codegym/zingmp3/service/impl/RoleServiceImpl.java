package com.codegym.zingmp3.service.impl;

import com.codegym.zingmp3.model.Role;
import com.codegym.zingmp3.repository.RoleRepository;
import com.codegym.zingmp3.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }
}
