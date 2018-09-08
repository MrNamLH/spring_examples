package com.namlee.examples.spring_examples.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.namlee.examples.spring_examples.domain.CustomUserDetails;
import com.namlee.examples.spring_examples.domain.Role;
import com.namlee.examples.spring_examples.domain.User;
import com.namlee.examples.spring_examples.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByName(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}

//		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//		Set<Role> roles = user.getRoles();
//		for (Role role : roles) {
//			grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
//		}
//
//		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
//				grantedAuthorities);
		// Get roles of user

		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		Set<Role> roles = user.getRoles();
		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}

		CustomUserDetails customUserDetails = new CustomUserDetails();
		customUserDetails.setUser(user);
		customUserDetails.setAuthorities(authorities);

		return customUserDetails;
	}

}
