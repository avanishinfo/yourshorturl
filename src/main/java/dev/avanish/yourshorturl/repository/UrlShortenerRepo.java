package dev.avanish.yourshorturl.repository;

import dev.avanish.yourshorturl.model.UrlShortener;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlShortenerRepo extends JpaRepository<UrlShortener, Long> {

    UrlShortener findByShortUrl(String shortUrl);
}
