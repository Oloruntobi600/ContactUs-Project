package com.contact.Model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "contacts")
@Schema(description = "Details about the contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "The unique ID of the contact")
    private Long id;

    @Schema(description = "Full name of the person contacting FootPrint")
    private String fullName;

    @Schema(description = "Email address of the person contacting FootPrint")
    private String email;

    @Schema(description = "Message provided by the person contacting FootPrint")
    private String message;
}
