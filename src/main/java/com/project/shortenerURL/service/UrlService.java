package com.project.shortenerURL.service;

import com.project.shortenerURL.controller.dto.UrlRequest;
import com.project.shortenerURL.controller.dto.UrlResponse;
import com.project.shortenerURL.entities.UrlEntity;
import com.project.shortenerURL.repository.UrlRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@AllArgsConstructor
@Service
public class UrlService {


    private final UrlRepository urlRepository;

    public UrlEntity findByShortUrl(String shortUrl) {
        UrlEntity findByShortUrl = urlRepository.findByShortUrl(shortUrl);

        saveUrl(findByShortUrl.getFullUrl(), findByShortUrl.getShortUrl(), null);

        return findByShortUrl;
    }

    public List<UrlEntity> findAllAccessBySearchUrl(String searchUrl) {
        return urlRepository.findAllBySearchUrl(searchUrl);
    }

    public List<UrlEntity> findAllAccessBySearchUrlAndLocalDate(String searchUrl, LocalDate localDate) {
        return urlRepository.findAllBySearchUrlAndLocalDate(searchUrl, localDate);
    }

    public void saveUrl(String fullUrl, String searchUrl, String shortUrl) {
        urlRepository.save(new UrlEntity(fullUrl, new Random().toString(), LocalDate.now(), searchUrl, shortUrl));

    }

    public UrlResponse addNewUrl(@RequestBody UrlRequest request,
                                 HttpServletRequest servletRequest) {

        String shortUrl = null;

        try {
            if (request.url() != null) {
                do {
                    shortUrl = RandomStringUtils.randomAlphanumeric(5, 10);
                } while (urlRepository.existsById(shortUrl));
            }

        } catch (Exception exception) {
            System.out.println(exception);
        }

        saveUrl(request.url(), null, shortUrl);

        var redirectUrl = servletRequest.getRequestURL().toString().replace("url", shortUrl);

        return ResponseEntity.ok(new UrlResponse(redirectUrl)).getBody();
    }

}
