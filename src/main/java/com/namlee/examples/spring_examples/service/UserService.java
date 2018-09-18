package com.namlee.examples.spring_examples.service;

import com.namlee.examples.spring_examples.domain.User;

public interface UserService {

	User findByEmail(String email);

	void save(User user);
}
