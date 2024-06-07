package com.project.shortenerURL.service;

import com.project.shortenerURL.entities.UrlEntity;
import com.project.shortenerURL.repository.UrlRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
class UrlServiceTest {

    @Autowired
    private UrlService urlService;
    @Autowired
    private UrlRepository urlRepository;

    @BeforeEach
    void setUp() {
        urlService.saveUrl("https://www.instagram.com/?hl=pt-br", null, "shortUrl");
        urlService.saveUrl("https://www.instagram.com/?hl=pt-br", "shortUrl", null);
        urlService.saveUrl("https://www.instagram.com/?hl=pt-br", "shortUrl", null);
    }

    @AfterEach
    void afterEach() {
        urlRepository.deleteAll();
    }

    @Test
    void saveUrlTest() {
        assertNotNull(urlService.findByShortUrl("shortUrl"));
    }

    @Test
    void findByShortUrlTest() {
        assertNotNull(urlService.findByShortUrl("shortUrl"));
    }

    @Test
    void findAllAccessByShortUrlUseTest() {
        List<UrlEntity> urlEntityList = urlService.findAllAccessBySearchUrl("shortUrl");
        assertEquals(urlEntityList.size(), 2);
    }

    @Test
    void findAllAccessByShortUrlUseAndLocalDateTest() {
        List<UrlEntity> urlEntityList = urlService.findAllAccessBySearchUrlAndLocalDate("shortUrl", LocalDate.now());
        assertEquals(urlEntityList.size(), 2);
    }
}