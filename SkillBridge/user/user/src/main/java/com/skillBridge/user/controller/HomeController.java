package com.skillBridge.user.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Welcome to Skill Bridge Application";
    }


}
