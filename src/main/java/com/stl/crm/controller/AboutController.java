package com.stl.crm.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class AboutController {
    /**
     * 
     * this method maps the following URL & http method
     * URL: http://hostname:port/appName/about
     * HTTP method: GET
     * 
     */
    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public ResponseEntity<?> home() {
    	return new ResponseEntity<>("This is a demo application to show how to secure REST API using Spring Security and OAuth2", HttpStatus.OK);
    }
}
