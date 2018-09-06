package com.namlee.examples.spring_examples.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.namlee.examples.spring_examples.domain.Contact;

public interface ContactRepository extends CrudRepository<Contact, Integer> {

	List<Contact> findByNameContaining(String q);

}