package com.example.bogdashka.controllers;

import com.example.bogdashka.models.PaymentModel;
import com.example.bogdashka.repos.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/mylist")
public class MyListController {
    @Autowired
    PaymentRepo paymentRepo;

    String name;
    List<PaymentModel> paymentModels = new ArrayList<>();

    @GetMapping
    public String getMyList(Model model){

        if (!paymentModels.isEmpty()){
            model.addAttribute("list", paymentModels);
        }
        return "buy.html";
    }
    @PostMapping
    public RedirectView setList(@RequestParam String name){
        this.name = name;
        paymentModels = paymentRepo.getPaymentModelsByName(name);
        return new RedirectView("/mylist");
    }
}
