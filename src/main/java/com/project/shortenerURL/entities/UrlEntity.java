package com.project.shortenerURL.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "urls")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UrlEntity {

    private String fullUrl;
    @Id
    private String id;
    private LocalDate localDate;
    private String searchUrl;
    private String shortUrl;
}