package com.contact.Service;

import com.contact.Model.Contact;

public interface ContactService {
    void sendEmail(Contact contact);
    void saveContactToCsv(Contact contact);
}

