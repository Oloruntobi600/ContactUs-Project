package com.contact.ServiceImpl;

import com.contact.Model.Contact;
import com.contact.Repository.ContactRepository;
import com.contact.Service.ContactService;
import com.contact.Service.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    private final MailSenderService mailSenderService;
    private final ContactRepository contactRepository;

    @Autowired
    public ContactServiceImpl(MailSenderService mailSenderService, ContactRepository contactRepository) {
        this.mailSenderService = mailSenderService;
        this.contactRepository = contactRepository;
    }


    @Override
    public void saveContactToCsv(Contact contact) {
        try {
            // Save to CSV
            contactRepository.saveToCsv(contact);
            System.out.println("Contact saved: " + contact.getFullName() + ", " + contact.getEmail());

            // Attempt to send the email
            String subject = "New Contact Us Submission";
            String body = "Full Name: " + contact.getFullName() + "\n"
                    + "Email: " + contact.getEmail() + "\n"
                    + "Message: " + contact.getMessage();
            mailSenderService.sendNewMail("tobiajayi60@gmail.com", subject, body);

        } catch (Exception e) {
            throw new RuntimeException("Error processing contact submission: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }


}