package com.contact.Repository;

import com.contact.Model.Contact;
import com.opencsv.CSVWriter;
import org.springframework.stereotype.Repository;

import java.io.FileWriter;
import java.io.IOException;

@Repository
public class ContactRepository {

    private static final String CSV_FILE_PATH = "contact-us.csv";

    public void saveToCsv(Contact contact) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(CSV_FILE_PATH, true))) {
            String[] data = {contact.getFullName(), contact.getEmail(), contact.getMessage()};
            writer.writeNext(data);
        }
    }
}
