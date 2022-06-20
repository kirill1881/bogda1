package com.example.bogdashka.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/mylist")
public class MyListController {
    @GetMapping
    public String getMyList(){
        return "buy.html";
    }
}
