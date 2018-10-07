package com.namlee.examples.spring_examples.service;

import com.namlee.examples.spring_examples.domain.Role;

public interface RoleService {

    Role findByName(String name);

    void save(Role role);

}
