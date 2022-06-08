package com.souradeep.springbootdemo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @Value("${welcome.message}")
    private String welcomeMessage;

    @GetMapping("/")
    public String home() {
        return "<!DOCTYPE html>" +
                "<body>" +
                "<h1>" + welcomeMessage + "</h1>" +
                "</body>";
    }
}
