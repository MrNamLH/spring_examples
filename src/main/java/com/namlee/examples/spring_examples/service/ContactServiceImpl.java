package com.namlee.examples.spring_examples.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.namlee.examples.spring_examples.domain.Contact;
import com.namlee.examples.spring_examples.repository.ContactRepository;

@Service
@Transactional(readOnly = true)
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public Iterable<Contact> findAll() {

        return contactRepository.findAll();
    }

    @Override
    public List<Contact> search(String q) {

        return contactRepository.findByNameContaining(q);
    }

    @Override
    public Contact findOne(int id) {

        return contactRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Contact save(Contact contact) {

        return contactRepository.save(contact);
    }

    @Override
    @Transactional
    public void delete(int id) {

        contactRepository.deleteById(id);
    }

}
