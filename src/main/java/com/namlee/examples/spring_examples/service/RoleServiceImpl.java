package com.namlee.examples.spring_examples.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.namlee.examples.spring_examples.domain.Role;
import com.namlee.examples.spring_examples.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role findByName(String name) {
		return roleRepository.findByName(name);
	}

	@Override
	public void save(Role role) {
		roleRepository.save(role);
	}

}
