package com.codegym.zingmp3.service;

import com.codegym.zingmp3.model.Role;

public interface RoleService {
    Role findByName(String name);

    Role save(Role role);
}
