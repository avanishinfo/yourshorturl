package dev.avanish.yourshorturl.dto;

import java.time.LocalDateTime;

public class UrlShortenerReqDto {

    private String longUrl;
    private String expirationDate; // optional

    public UrlShortenerReqDto() {
    }

    public UrlShortenerReqDto(String longUrl, String expirationDate) {
        this.longUrl = longUrl;
        this.expirationDate = expirationDate;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
}
