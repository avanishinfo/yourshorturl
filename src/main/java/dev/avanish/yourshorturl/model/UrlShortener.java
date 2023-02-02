package dev.avanish.yourshorturl.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

import java.time.LocalDateTime;

@Entity
public class UrlShortener {

    @Id
    @GeneratedValue
    private Long id;
    @Lob
    private String longUrl;
    private String shortUrl;
    private LocalDateTime createdDate;
    private LocalDateTime expirationDate;

    public UrlShortener() {
    }

    public UrlShortener(String longUrl, String shortUrl, LocalDateTime createdDate, LocalDateTime expirationDate) {
        this.longUrl = longUrl;
        this.shortUrl = shortUrl;
        this.createdDate = createdDate;
        this.expirationDate = expirationDate;
    }

    public UrlShortener(Long id, String longUrl, String shortUrl, LocalDateTime createdDate, LocalDateTime expirationDate) {
        this.id = id;
        this.longUrl = longUrl;
        this.shortUrl = shortUrl;
        this.createdDate = createdDate;
        this.expirationDate = expirationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }
}
