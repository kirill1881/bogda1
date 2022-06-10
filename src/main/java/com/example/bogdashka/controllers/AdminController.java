package com.example.bogdashka.controllers;

import com.example.bogdashka.models.DataModel;
import com.example.bogdashka.repos.DataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping
    public String getMainPage(){
        return "index";
    }
    @Autowired
    DataRepo dataRepo;

    @PostMapping
    public RedirectView save(@RequestParam String course, @RequestParam
                             String free){
        DataModel dataModel = new DataModel();
        dataModel.setCourse(course);
        dataModel.setFreeRobux(free);
        dataRepo.save(dataModel);
        return new RedirectView("/");
    }
}
