package dev.avanish.yourshorturl.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

import java.time.LocalDateTime;

@Entity
public class UrlRedirection {

    @Id
    @GeneratedValue
    private Long id;

    @Lob
    private String yourUrl;
    private String redirectionUrl;
    private LocalDateTime createdDate;
    private LocalDateTime expirationDate;

}
