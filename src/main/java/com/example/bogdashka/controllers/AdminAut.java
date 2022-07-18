package com.example.bogdashka.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/admin")
public class AdminAut {
    @GetMapping
    public String page(){
        return "admin1";
    }
    @PostMapping
    public RedirectView po(@RequestParam String login, @RequestParam String password){
        if (login.equals("bogdan")&&password.equals("Kollena0202")){
            return new RedirectView("/admin1");
        }
        return new RedirectView("/");
    }
}
