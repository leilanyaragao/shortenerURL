package com.project.shortenerURL.controller;

import com.project.shortenerURL.entities.UrlEntity;
import com.project.shortenerURL.repository.UrlRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
class UrlControllerTest {

    @Autowired
    private UrlController urlController;
    @Autowired
    private UrlRepository urlRepository;

    @BeforeEach
    void setUp() {
        urlRepository.save(new UrlEntity("https://www.instagram.com/?hl=pt-br", "1", LocalDate.now(), null, "urlShort"));
        urlRepository.save(new UrlEntity("https://www.instagram.com/?hl=pt-br", "2", LocalDate.now(), "urlShort", null));
        urlRepository.save(new UrlEntity("https://www.instagram.com/?hl=pt-br", "3", LocalDate.now(), "urlShort", null));
    }

    @AfterEach
    void afterEach() {
        urlRepository.deleteAll();
    }

    @Test
    void redirectByIdTest() {
        ResponseEntity<Void> shortUrlResponseEntity = urlController.redirectById("urlShort");
        assertEquals(shortUrlResponseEntity.getStatusCode(), HttpStatus.FOUND);
    }

    @Test
    void accessionNumberBySearchUrlTest() {
        assertEquals(urlController.accessionNumberBySearchUrl("urlShort"), 2);
    }

    @Test
    void accessionNumberBySearchUrlAndDDateTest() {
        assertEquals(urlController.accessionNumberBySearchUrlAndDDate("urlShort", LocalDate.now()), 2);
    }
}