package com.example.bogdashka.controllers;

import com.example.bogdashka.models.DataModel;
import com.example.bogdashka.repos.DataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    DataRepo dataRepo;

    @GetMapping
    public String getMainPage(Model model){
        List<DataModel> dataModel =  dataRepo.findAll();
        model.addAttribute("list", dataModel);
        return "transfer";
    }
}
