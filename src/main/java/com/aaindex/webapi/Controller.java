package com.aaindex.webapi;

import javax.jms.MessageProducer;
import javax.jms.TextMessage;

import org.owasp.encoder.Encode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

public class Controller {

    private static final Logger log = LoggerFactory.getLogger(Controller.class);
    private static final String AUTHORIZATION_HEADER = "Authorization";

    @GetMapping 
    public String getUsers (@RequestParam(value="page", defaultValue="1", required = false) int page,
            @RequestParam(value="limit", defaultValue="50") int limit,
            @RequestParam(value="sort", defaultValue="dest", required = false) String sort ) {
        return "get users was called page ="+page +" limit="+limit +" sort="+sort;
    }

    @GetMapping (path="/{userId}",
                produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
                })
    public UserRest getUser(@PathVariable String userId) {
        log.info("Before Encoding: {}",userId);
        log.info("After Encoding forJava:{}", Encode.forJava(userId));

        UserRest ust = new UserRest();
        ust.setEmail("testemail@gmail.com");
        ust.setFirstName("FirstName");
        ust.setLastName("LastName");
        ust.setUserId(userId);
        return ust;
    } 


    @PostMapping(value = "/adduser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> CreateUser (
        @PathVariable String username)
    {
        return ResponseEntity.ok("New Useer!"); 
    }

    @PutMapping("/good")
    public ResponseEntity<String> goodput (@RequestBody String body) {
        log.info("input:{}", body);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        return new ResponseEntity<String>("Hello World", responseHeaders, HttpStatus.CREATED);
    }

    @PutMapping("/bad")
    public ResponseEntity<String> badput (@RequestBody String body) {
        log.info("input:{}", body);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        return new ResponseEntity<String>(body, responseHeaders, HttpStatus.CREATED);
    }
}
