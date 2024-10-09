package com.contact.ServiceImpl;

import com.contact.Model.Contact;
import com.contact.Repository.ContactRepository;
import com.contact.Service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private JavaMailSenderImpl mailSender;

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public void sendEmail(Contact contact) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("oloruntobiajayi@yahoo.com"); // Footprint email address
        message.setSubject("New Contact Us Form Submission");
        message.setText("Full Name: " + contact.getFullName() + "\n"
                + "Email: " + contact.getEmail() + "\n"
                + "Message: " + contact.getMessage());

        mailSender.send(message);
    }

    @Override
    public void saveContactToCsv(Contact contact) {
        try {
            contactRepository.saveToCsv(contact);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}