package com.shortUrl;

import com.shortUrl.Model.UrlModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShortUrlApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShortUrlApplication.class, args);
//		UrlModel url=new UrlModel();
//		url.setShortUrl("rsd");
//		System.out.println(url);
	}

}
