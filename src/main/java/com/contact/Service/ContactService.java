package com.contact.Service;

import com.contact.Model.Contact;

import java.util.List;

public interface ContactService {
    void sendEmail(Contact contact);
    void saveContactToCsv(Contact contact);
    List<Contact> getAllContacts();
}

