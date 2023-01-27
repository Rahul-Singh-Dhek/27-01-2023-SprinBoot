package com.shortUrl.Repository;

import com.shortUrl.Model.UrlModel;
import org.apache.catalina.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface UrlRepository extends MongoRepository<UrlModel,String> {
    @Query("{UrlCode:?0}")
    List<UrlModel> findByUrlCode(String UrlCode);

    @Query("{Created : {$gt : ?0}")
    List<UrlModel> findByCreatedGreaterThanEqual(Date Created);


//    List<User> find(org.springframework.data.mongodb.core.query.Query query4, Class<User> userClass);

//    void find(org.springframework.data.mongodb.core.query.Query query, Class<YourClazz> yourClazzClass, String collectionName);

//    @Query("{'$or':[ {'Created':"+ Date.from(Instant.now())+"}, {'Created':"+Date.from(Instant.now())"} ] }")
//    List<UrlModel> findByDate();

//    List<UrlModel> find(org.springframework.data.mongodb.core.query.Query query, Class<com.shortUrl.Model.UrlModel> urlModelClass, String url);
}
