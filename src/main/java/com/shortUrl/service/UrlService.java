package com.shortUrl.service;

import com.google.common.escape.CharEscaper;
import com.shortUrl.Controller.res;
import com.shortUrl.Model.UrlModel;
import com.shortUrl.Repository.UrlRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;


import javax.swing.text.html.Option;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class UrlService {
    @Autowired
    private UrlRepository Repository;
    public UrlModel createUrl(UrlModel  Url){
        Url.setUrlCode(UUID.randomUUID().toString());
        Url.setShortUrl("http://localhost:8080/Url/relocate/"+Url.getUrlCode());
        return Repository.save(Url);
    }

    public String getUrl(String UrlCode){
//        List<UrlModel> Data=Repository.findAll();
        List<UrlModel> CustomQueryData = Repository.findByUrlCode(UrlCode);

//        System.out.println(CustomQueryData);
        if(CustomQueryData.size()==0){
            return "";
        }else{
            return CustomQueryData.get(0).getLongUrl();
        }
//        return CustomQueryData.get(0).getLongUrl();
//        System.out.println(CustomQueryData);
//        System.out.println(UrlCode.getLongUrl());
//        for (UrlModel ele: Data) {
//            if(ele.getUrlCode().equals(UrlCode))
//                return ele.getLongUrl();
//        }
//        return "";

    }

    public List<UrlModel> getRecent(){

//        Query query = new Query();
//        query.addCriteria(
//                Criteria.where("").andOperator(
//                        Criteria.where("Created").lte(Date.from(Instant.now())),
//                        Criteria.where("Created").gte(Date.from(Instant.now().minus(1, ChronoUnit.HOURS)))
//                )
//        );

        Query query = new Query();
        query.addCriteria(
                Criteria.where("Created").exists(true)
                        .andOperator(
                                Criteria.where("Created").lt(Date.from(Instant.now())),
                                Criteria.where("Created").gt(Date.from(Instant.now().minus(1, ChronoUnit.HOURS)))
                        )
        );
        System.out.println(query);
        System.out.println(Date.from(Instant.now().minus(1, ChronoUnit.HOURS)));
        System.out.println(Date.from(Instant.now()));

//        List<UrlModel> userTest4 = Repository.find(query, UrlModel);
//        return userTest4;
        return Repository.findByCreatedGreaterThanEqual(Date.from(Instant.now().minus(1, ChronoUnit.HOURS)));
    }

    public res getSpecialCharecters(String str){
        Integer countSpecialChar=0;
        Integer countVowelChar=0;
        Integer countConsonentChar=0;
        for(int i=0;i<str.length();i++){

            char ele=str.charAt(i);
            Integer code=(int) ele;
            if((code>=32&&code<=47)||(code>=58&&code<=64)||(code>=91&&code<=96)||(code>=123&&code<=126)){
                countSpecialChar++;
            }else if(ele=='a'||ele=='e'||ele=='i'||ele=='o'||ele=='u'||ele=='A'||ele=='E'||ele=='I'||ele=='O'||ele=='U'){
                countVowelChar++;
            }else{
                countConsonentChar++;
            }
        }
        res r1=new res();
        r1.setUniqueCharCount(countSpecialChar);
        r1.setVowelCharCount(countVowelChar);
        r1.setConsonantCharCount(countConsonentChar);
        System.out.println(Date.from(Instant.now()));
        System.out.println(Date.from(Instant.now().minus(1, ChronoUnit.HOURS)));
        return r1;
    }

}


