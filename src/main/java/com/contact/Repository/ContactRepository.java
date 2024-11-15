package com.contact.Repository;

import com.contact.Model.Contact;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ContactRepository {

    private static final String CSV_FILE_PATH = "src/main/resources/contact-us.csv";  // Changed to a static folder

    public ContactRepository() {
        // Ensure the file exists
        File file = new File(CSV_FILE_PATH);
        if (!file.exists()) {
            try {
                Files.createDirectories(Paths.get("src/main/resources"));
                boolean fileCreated = file.createNewFile();
                if (fileCreated) {
                    System.out.println("File created successfully at: " + CSV_FILE_PATH);
                } else {
                    System.out.println("File already exists at: " + CSV_FILE_PATH);
                }
            } catch (IOException e) {
                throw new RuntimeException("Error initializing CSV file: " + e.getMessage(), e);
            }
        }
    }

    public void saveToCsv(Contact contact) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(CSV_FILE_PATH, true))) {
            String[] data = {contact.getFullName(), contact.getEmail(), contact.getMessage()};
            writer.writeNext(data);
            System.out.println("Written to CSV: " + String.join(", ", data));
        } catch (IOException e) {
            throw new RuntimeException("Error writing to CSV file: " + e.getMessage(), e);
        }
    }


    public List<Contact> findAll() {
        List<Contact> contacts = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(CSV_FILE_PATH))) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                if (nextLine.length == 3) { // Validate row structure
                    Contact contact = new Contact();
                    contact.setFullName(nextLine[0]);
                    contact.setEmail(nextLine[1]);
                    contact.setMessage(nextLine[2]);
                    contacts.add(contact);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("CSV file not found: " + CSV_FILE_PATH);
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException("Error reading from CSV file: " + e.getMessage(), e);
        }

        return contacts;
    }
}
