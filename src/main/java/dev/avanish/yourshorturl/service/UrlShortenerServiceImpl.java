package dev.avanish.yourshorturl.service;

import com.google.common.hash.Hashing;
import dev.avanish.yourshorturl.dto.UrlShortenerReqDto;
import dev.avanish.yourshorturl.dto.UrlShortenerResDto;
import dev.avanish.yourshorturl.model.UrlShortener;
import dev.avanish.yourshorturl.repository.UrlShortenerRepo;
import io.netty.util.internal.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Service
public class UrlShortenerServiceImpl implements UrlShortenerService{

    @Autowired
    private UrlShortenerRepo urlShortenerRepo;

    @Override
    public UrlShortener getShortUrl(String shortUrl) {
        UrlShortener urlShortener = urlShortenerRepo.findByShortUrl(shortUrl);
        return urlShortener;
    }

    @Override
    public UrlShortener createShortUrl(UrlShortenerReqDto urlShortenerReqDto) {
        if (StringUtils.isNoneEmpty(urlShortenerReqDto.getLongUrl())){
            String encodedUrl = encoderUrl(urlShortenerReqDto.getLongUrl());
            UrlShortener urlShortener = new UrlShortener();
            urlShortener.setLongUrl(urlShortenerReqDto.getLongUrl());
            urlShortener.setShortUrl(encodedUrl);
            urlShortener.setCreatedDate(LocalDateTime.now());
            urlShortener.setExpirationDate(getExpireationDate(urlShortenerReqDto.getExpirationDate()));

            urlShortenerRepo.save(urlShortener);

            return urlShortener;

        }
        //throw exception if null found
        return null;
    }

    private LocalDateTime getExpireationDate(String expirationDate) {
        if (StringUtils.isBlank(expirationDate)){
            return LocalDateTime.now().plusSeconds(120);
        }

        return LocalDateTime.parse(expirationDate);
    }

    private String encoderUrl(String longUrl) {
        String encodeUrl = "";
        LocalDateTime time = LocalDateTime.now();
        encodeUrl = Hashing.murmur3_32_fixed()
                .hashString(longUrl.concat(time.toString()), StandardCharsets.UTF_8)
                .toString();
        return encodeUrl;
    }

    @Override
    public void deleteShortUrl(String shortUrl) {
        Long id = urlShortenerRepo.findByShortUrl(shortUrl).getId();
        if (id!=0)
            urlShortenerRepo.deleteById(id);
        //else throw exception
    }
}
