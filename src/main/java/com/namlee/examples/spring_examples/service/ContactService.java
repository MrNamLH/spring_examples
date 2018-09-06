package com.namlee.examples.spring_examples.service;

import java.util.List;

import com.namlee.examples.spring_examples.domain.Contact;

public interface ContactService {

	Iterable<Contact> findAll();

	List<Contact> search(String q);

	Contact findOne(int id);

	void save(Contact contact);

	void delete(int id);

}