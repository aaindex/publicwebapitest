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

@RestController
@RequestMapping("users-test")
public class Controller {

    private static final Logger log = LoggerFactory.getLogger(Controller.class);
    private static final String AUTHORIZATION_HEADER = "Authorization";

    @GetMapping 
    public String getUsers (@RequestParam(value="page", defaultValue="1", required = false) String page,
            @RequestParam(value="limit", defaultValue="50") String limit,
            @RequestParam(value="sort", defaultValue="dest", required = false) String sort ) {

        return "get users was called page ="+Encode.forHtml(page) +" limit="+Encode.forHtml(limit) +" sort="+Encode.forHtml(sort);
    }

    @GetMapping (path="/get-bad")
    public String getUsersBad (@RequestParam(value="page", defaultValue="1", required = false) String page,
            @RequestParam(value="limit", defaultValue="50") String limit,
            @RequestParam(value="sort", defaultValue="dest", required = false) String sort ) {

        return "get users was called page ="+page +" limit="+ limit+" sort="+ sort;
    }

    @GetMapping (path="/{userId}",
                produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
                })
    public UserRest getUser(@PathVariable String userId) {
        log.info("Before Encoding: {}", userId);
        log.info("After Encoding forJava:{}", Encode.forHtml(userId));

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

    @PutMapping("/bad1")
    public ResponseEntity<String> badput (@RequestBody String body) {
        log.info("input:{}", body);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        return new ResponseEntity<String>(body, responseHeaders, HttpStatus.CREATED);
    }

    @PutMapping("/bad1-fix")
    public ResponseEntity<String> badput1 (@RequestBody String body) {
        log.info("input:{}", body);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        return new ResponseEntity<String>("body", responseHeaders, HttpStatus.CREATED);
    }

    @PutMapping("/bad2")
    public ResponseEntity<String> badput2 (@RequestBody String body) {
        log.info("input:{}", body);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        
        return ResponseEntity.ok(body);
        //return new ResponseEntity<String>(body, responseHeaders, HttpStatus.CREATED);
    }

}
