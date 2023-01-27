package com.shortUrl.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.ZonedDateTime;
import java.util.Date;

@Data
@Document(collection = "Url")
@AllArgsConstructor
@NoArgsConstructor
public class UrlModel
{
    @Id
    private String _id;
    private String LongUrl;
    private String UrlCode;
    private String ShortUrl;
    public Date Created;

}
