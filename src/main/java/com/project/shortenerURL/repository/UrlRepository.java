package com.project.shortenerURL.repository;

import com.project.shortenerURL.entities.UrlEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UrlRepository extends MongoRepository<UrlEntity, String> {
    UrlEntity findByShortUrl(String shortUrl);

    List<UrlEntity> findAllBySearchUrl(String searchUrl);

    List<UrlEntity> findAllBySearchUrlAndLocalDate(String searchUrl, LocalDate localDate);
}