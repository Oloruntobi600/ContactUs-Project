package com.contact.Controller;

import com.contact.Model.Contact;
import com.contact.Service.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
@Tag(name = "Contact Management System", description = "Operations pertaining to contact submissions")
public class ContactController {

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping
    @Operation(summary = "Submit a new contact message", description = "Sends an email and saves the message to a CSV file")
    public ResponseEntity<String> contactUs(@RequestBody Contact contact) {
        try {
            contactService.saveContactToCsv(contact);
            return ResponseEntity.ok("Message received, we'll get back to you shortly.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to process your message: " + e.getMessage());
        }
    }

    @GetMapping
    @Operation(summary = "Retrieve all contacts", description = "Fetches all submitted contact messages from the CSV file")
    public ResponseEntity<?> getAllContacts() {
        List<Contact> contacts = contactService.getAllContacts();
        if (contacts.isEmpty()) {
            // Return an empty list with a 200 OK status
            return ResponseEntity.ok("No contacts found.");
        }
        return ResponseEntity.ok(contacts);
    }

}
