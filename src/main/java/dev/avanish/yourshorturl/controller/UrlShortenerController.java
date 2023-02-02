package dev.avanish.yourshorturl.controller;

import dev.avanish.yourshorturl.dto.UrlShortenerReqDto;
import dev.avanish.yourshorturl.dto.UrlShortenerResDto;
import dev.avanish.yourshorturl.error.UrlShortenerErrorResponse;
import dev.avanish.yourshorturl.model.UrlShortener;
import dev.avanish.yourshorturl.service.UrlShortenerService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@RestController
public class UrlShortenerController {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    private UrlShortenerService urlShortenerService;

    @GetMapping("{shortUrl}")
    public ResponseEntity<?> getShortUrl(@PathVariable("shortUrl") String shortUrl, HttpServletResponse response) throws IOException {

        UrlShortener urlShortener = urlShortenerService.getShortUrl(shortUrl);

        String url = redisTemplate.opsForValue().get(shortUrl);
        if (!Objects.requireNonNull(url).isEmpty()){
            response.sendRedirect(url);
        }else{
            if (urlShortener != null){
                if (urlShortener.getExpirationDate().isBefore(LocalDateTime.now())){
                    urlShortenerService.deleteShortUrl(shortUrl);
                }else{
                    UrlShortenerResDto urlRes =
                            new UrlShortenerResDto(
                                    "success", "Your short url is here.",
                                    urlShortener.getLongUrl(), urlShortener.getShortUrl(), LocalDateTime.now());

                    //return urlRes;
                    response.sendRedirect(urlRes.getLongUrl());
                    return new ResponseEntity<>(urlRes, HttpStatus.OK);
                }
            }
        }

        UrlShortenerErrorResponse urlErrorRes = new UrlShortenerErrorResponse();
        urlErrorRes.setStatus("Failed");
        urlErrorRes.setMessage("Somethings went wrong...");
        urlErrorRes.setError(List.of("Data isn't found.", "Your shorten url may be expired."));
        return new ResponseEntity<>(urlErrorRes, HttpStatus.OK);//throw exception if null found
    }

    @PostMapping("api/v1/shorturl")
    public ResponseEntity<?> createShortUrl(@RequestBody UrlShortenerReqDto urlShortenerReqDto, HttpServletResponse response) throws IOException {
        UrlShortener urlShortener = urlShortenerService.createShortUrl(urlShortenerReqDto);
        if (urlShortener != null){
            UrlShortenerResDto urlRes = new UrlShortenerResDto(
                    "success", "url shortening is successful.",
                    urlShortenerReqDto.getLongUrl(), "http://localhost:8080/"+urlShortener.getShortUrl(), LocalDateTime.now());
            //return urlRes;
            redisTemplate.opsForValue().set(urlRes.getShortUrl(), urlRes.getLongUrl());
            return new ResponseEntity<>(urlRes, HttpStatus.OK);
        }

        UrlShortenerErrorResponse urlErrorRes = new UrlShortenerErrorResponse();
        urlErrorRes.setStatus("Failed");
        urlErrorRes.setMessage("Somethings went wrong...");
        urlErrorRes.setError(List.of("Server isn't responding at this moment.", "Not valid data."));
        return new ResponseEntity<>(urlErrorRes, HttpStatus.OK);//throw exception if null found
    }

    @DeleteMapping("api/v1/shorturl")
    public void deleteShortUrl(String shortUrl) {
       urlShortenerService.deleteShortUrl(shortUrl);
    }
}
