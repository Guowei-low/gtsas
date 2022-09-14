package com.gtassignment.sas.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {

    @GetMapping(value = "/home")
    public String GetTest()
    {
        return "Welcome";
    }

    @GetMapping(value = "/testing")
    public ResponseEntity<List<String>> BookList(String genre) {
       return ResponseEntity.status(204).build();
    }
}