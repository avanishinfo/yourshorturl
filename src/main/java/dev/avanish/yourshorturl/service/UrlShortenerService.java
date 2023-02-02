package dev.avanish.yourshorturl.service;

import dev.avanish.yourshorturl.dto.UrlShortenerReqDto;
import dev.avanish.yourshorturl.dto.UrlShortenerResDto;
import dev.avanish.yourshorturl.model.UrlShortener;

public interface UrlShortenerService {

    UrlShortener getShortUrl(String shortUrl);
    UrlShortener createShortUrl(UrlShortenerReqDto urlShortener);
    void deleteShortUrl(String shortUrl);
}
