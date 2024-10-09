package com.contact.Controller;

import com.contact.Model.Contact;
import com.contact.Service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}