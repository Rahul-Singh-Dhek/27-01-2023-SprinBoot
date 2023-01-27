package com.shortUrl.Controller;

import com.shortUrl.Model.UrlModel;
import com.shortUrl.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Url")
public class UrlController {
    @Autowired
    private UrlService Service;

    @PostMapping("/createUrl")
    public UrlModel createUrl(@RequestBody UrlModel Url){
        Url.setCreated(Date.from(Instant.now()));
//        System.out.println(ZonedDateTime.now(ZoneOffset.UTC));
        return Service.createUrl(Url);
    }

    @GetMapping("/{UrlCode}")
    public Object relocate(@PathVariable String UrlCode){

        String output=Service.getUrl(UrlCode);
        if(output.equals(Date.from(Instant.now()))){
            return new ResponseEntity<>("No such url", HttpStatus.NOT_FOUND);
        }else{
            return new RedirectView(output);
//            return new ResponseEntity<>(new RedirectView(output).getUrl(),HttpStatus.PERMANENT_REDIRECT);
//
        }
//        return "I am in";
    }

@GetMapping("/recent")
    public List<UrlModel> recent(){
        return Service.getRecent();
    }

@GetMapping("specialCharecters")
    public ResponseEntity<res> specialCharecters(@RequestBody req obj){
        res output = Service.getSpecialCharecters(obj.getStr());
        return ResponseEntity.status(HttpStatus.OK).body(output);
    }

}

