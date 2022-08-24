package com.example.bogdashka.controllers;

import com.example.bogdashka.helper.Cont;
import com.example.bogdashka.models.DataModel;
import com.example.bogdashka.repos.CountRepo;
import com.example.bogdashka.repos.DataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    DataRepo dataRepo;

    @Autowired
    CountRepo countRepo;

    public static String name;

    @GetMapping
    public String getMainPage(Model model){
        List<DataModel> dataModel =  dataRepo.findAll();
        model.addAttribute("list", dataModel);
         
        return "transfer";
    }
    @PostMapping
    public RedirectView getBuy(@RequestParam String name){
        MainController.name = name;
        return new RedirectView("/buy");
    }
}
