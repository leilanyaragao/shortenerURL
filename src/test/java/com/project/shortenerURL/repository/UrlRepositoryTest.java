package com.project.shortenerURL.repository;

import com.project.shortenerURL.entities.UrlEntity;
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
class UrlRepositoryTest {

    @Autowired
    private UrlRepository urlRepository;

    @BeforeEach
    void setUp() {
        urlRepository.save(new UrlEntity("https://www.instagram.com/?hl=pt-br", "1", LocalDate.now(), null, "shortUrl"));
        urlRepository.save(new UrlEntity("https://www.instagram.com/?hl=pt-br", "2", LocalDate.now(), "shortUrl", null));
        urlRepository.save(new UrlEntity("https://www.instagram.com/?hl=pt-br", "3", LocalDate.now(), "shortUrl", null));
    }

    @AfterEach
    void afterEach() {
        urlRepository.deleteAll();
    }

    @Test
    void findByShortUrlTest() {
        UrlEntity urlEntity = urlRepository.findByShortUrl("shortUrl");

        assertNotNull(urlEntity);
        assertEquals(urlEntity.getFullUrl(), "https://www.instagram.com/?hl=pt-br");
        assertEquals(urlEntity.getShortUrl(), "shortUrl");
    }

    @Test
    void findAllBySearchUrlTest() {
        List<UrlEntity> urlEntityList = urlRepository.findAllBySearchUrl("shortUrl");
        assertEquals(urlEntityList.size(), 2);
    }

    @Test
    void findAllBySearchUrlAndLocalDateTest() {
        List<UrlEntity> urlEntityList = urlRepository.findAllBySearchUrlAndLocalDate("shortUrl", LocalDate.now());
        assertEquals(urlEntityList.size(), 2);
    }
}