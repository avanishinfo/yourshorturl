package dev.avanish.yourshorturl.dto;

import java.time.LocalDateTime;

public class UrlShortenerResDto {

    private String status;
    private String message;

    private String longUrl;
    private String shortUrl;
    private LocalDateTime expirationDate;

    public UrlShortenerResDto() {
    }

    public UrlShortenerResDto(String status, String message, String longUrl, String shortUrl, LocalDateTime expirationDate) {
        this.status = status;
        this.message = message;
        this.longUrl = longUrl;
        this.shortUrl = shortUrl;
        this.expirationDate = expirationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }
}
