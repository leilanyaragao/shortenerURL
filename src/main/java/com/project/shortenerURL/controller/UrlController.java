package com.project.shortenerURL.controller;

import com.project.shortenerURL.controller.dto.UrlRequest;
import com.project.shortenerURL.controller.dto.UrlResponse;
import com.project.shortenerURL.entities.UrlEntity;
import com.project.shortenerURL.service.UrlService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@Controller
public class UrlController {
    private final UrlService urlService;

    @PostMapping(value = "/url")
    public ResponseEntity<UrlResponse> shortenUrl(@RequestBody UrlRequest request,
                                                  HttpServletRequest servletRequest) {
        return new ResponseEntity<>(urlService.addNewUrl(request, servletRequest), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Void> redirectById(@PathVariable("id") String id) {

        UrlEntity url = urlService.findByShortUrl(id);

        if (url.getFullUrl().isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(url.getFullUrl()));

        return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
    }

    @GetMapping("/all/{searchUrl}")
    public int accessionNumberBySearchUrl(@PathVariable("searchUrl") String searchUrl) {
        List<UrlEntity> allAccessBySearchUrlList = urlService.findAllAccessBySearchUrl(searchUrl);

        return allAccessBySearchUrlList.size();
    }

    @GetMapping("/all/{searchUrl}/date/{localDate}")
    public int accessionNumberBySearchUrlAndDDate(@PathVariable("searchUrl") String searchUrl, @PathVariable("localDate") LocalDate localDate) {
        List<UrlEntity> allAccessBySearchUrlAndDateList = urlService.findAllAccessBySearchUrlAndLocalDate(searchUrl, localDate);

        return allAccessBySearchUrlAndDateList.size();
    }
}
