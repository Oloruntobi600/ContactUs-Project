package com.contact.Repository;

import com.contact.Model.Contact;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.stereotype.Repository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ContactRepository {

    private static final String CSV_FILE_PATH = "contact-us.csv";

    public void saveToCsv(Contact contact) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(CSV_FILE_PATH, true))) {
            String[] data = {contact.getFullName(), contact.getEmail(), contact.getMessage()};
            writer.writeNext(data);
        }
    }

    public List<Contact> findAll() {
        List<Contact> contacts = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(CSV_FILE_PATH))) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                // Create a Contact object from the read data
                Contact contact = new Contact();
                contact.setFullName(nextLine[0]);
                contact.setEmail(nextLine[1]);
                contact.setMessage(nextLine[2]);
                contacts.add(contact);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace(); // Handle the exception as needed
        }

        return contacts;
    }
}
