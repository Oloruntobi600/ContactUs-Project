package com.contact.Controller;

import com.contact.Model.Contact;
import com.contact.Service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping
    public String contactUs(@RequestBody Contact contact) {
        contactService.sendEmail(contact);

        contactService.saveContactToCsv(contact);

        return "Message received, we'll get back to you shortly.";
    }

    @GetMapping
    public List<Contact> getAllContacts() {
        return contactService.getAllContacts();
    }
}