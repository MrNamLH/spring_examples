package com.namlee.examples.spring_examples.repository;

import org.springframework.data.repository.CrudRepository;

import com.namlee.examples.spring_examples.domain.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	User findByName(String name);

	User findByEmail(String email);

}