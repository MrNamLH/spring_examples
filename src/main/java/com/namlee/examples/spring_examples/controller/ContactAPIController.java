package com.namlee.examples.spring_examples.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.namlee.examples.spring_examples.domain.Contact;
import com.namlee.examples.spring_examples.service.ContactService;

@CrossOrigin
@RestController
public class ContactAPIController {
	private ContactService contactService;

	@Autowired
    public ContactAPIController(ContactService contactService) {
        this.contactService = contactService;
    }

	@GetMapping("/api/contact")
	public Iterable<Contact> listContact() {
		return contactService.findAll();
	}

	@GetMapping("/api/contact/search")
	public List<Contact> searchContact(@RequestParam("term") String term) {
		return contactService.search(term);
	}

	@GetMapping("/api/contact/{id}")
	public Contact getContact(@PathVariable("id") Integer id) {
		return contactService.findOne(id);
	}

	@PostMapping("/api/contact/save")
	public Contact saveContact(@RequestBody Contact contact) {
		return contactService.save(contact);
	}

	@DeleteMapping("/api/contact/delete/{id}")
	public void deleteContact(@PathVariable("id") Integer id) {
		contactService.delete(id);
	}
}
