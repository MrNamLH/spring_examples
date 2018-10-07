package com.namlee.examples.spring_examples.repository;

import org.springframework.data.repository.CrudRepository;

import com.namlee.examples.spring_examples.domain.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {

    Role findByName(String name);

}
