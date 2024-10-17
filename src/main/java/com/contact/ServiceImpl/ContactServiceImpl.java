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
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private JavaMailSenderImpl mailSender;

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public void sendEmail(Contact contact) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo("oloruntobiajayi@yahoo.com"); // Your email address
            message.setSubject("New Contact Us Form Submission");
            message.setText("Full Name: " + contact.getFullName() + "\n"
                    + "Email: " + contact.getEmail() + "\n"
                    + "Message: " + contact.getMessage());

            System.out.println("Sending email to: " + message.getTo());
            System.out.println("Email Subject: " + message.getSubject());

            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error sending email: " + e.getMessage());
        }

    }

    @Override
    public void saveContactToCsv(Contact contact) {
        try {
            contactRepository.saveToCsv(contact);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error saving contact to CSV: " + e.getMessage());
        }
    }

    @Override
    public List<Contact> getAllContacts() {
        return contactRepository.findAll(); // Assuming you have a method in your repository
    }

}