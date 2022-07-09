package com.example.bogdashka.controllers;

import com.example.bogdashka.helper.PaymentData;
import com.example.bogdashka.models.DataModel;
import com.example.bogdashka.models.PaymentModel;
import com.example.bogdashka.repos.DataRepo;
import com.example.bogdashka.repos.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    PaymentRepo paymentRepo;

    @GetMapping
    public String getMainPage(Model model) throws Exception {
        List<PaymentModel> paymentModels = new ArrayList<>();
        paymentModels = paymentRepo.findAll();
        PaymentData paymentData = new PaymentData();
        List<String> newPaymentModels = new ArrayList<>();
        newPaymentModels = paymentData.getArrayList();
        System.out.println(newPaymentModels);

        List<PaymentModel> newPayment = new ArrayList<>();
        for (int i = 0; i < newPaymentModels.size()-1; i+=2) {
            newPayment.add(new PaymentModel(newPaymentModels.get(i+1), Double.parseDouble(newPaymentModels.get(i)), false));
        }
        for (PaymentModel p: newPayment){
            if (!paymentModels.contains(p)){
                paymentRepo.save(p);
            }
        }
        List<PaymentModel> models = paymentRepo.getPaymentModelsByIfDone(false);
        model.addAttribute("models", models);
        return "indexx";
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
    @GetMapping("/all/{id}")
    public RedirectView a(@PathVariable long id) throws Exception {
        PaymentModel paymentModel = new PaymentModel();
        Optional<PaymentModel> optionalPaymentData = paymentRepo.findById(id);
        List<PaymentModel> paymentModels = new ArrayList<>();
        optionalPaymentData.ifPresent(paymentModels::add);
        paymentModel = paymentModels.get(0);
        paymentRepo.deleteById(id);
        paymentModel.setIfDone(true);
        paymentRepo.save(paymentModel);
        return new RedirectView("/admin");
    }
}
