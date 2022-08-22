package com.example.bogdashka.controllers;

import com.example.bogdashka.helper.Cont;
import com.example.bogdashka.helper.Counter;
import com.example.bogdashka.helper.Data1;
import com.example.bogdashka.helper.Data2;
import com.example.bogdashka.models.DataModel;
import com.example.bogdashka.repos.CountRepo;
import com.example.bogdashka.repos.DataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Controller
@RequestMapping("/buy")
public class BuyController {


    private static final Map<Integer, String> map = new HashMap<>();
    private static final List<String> list = new ArrayList<>();
    private String url;
    private double sum;
    private double amount;

    @Autowired
    DataRepo dataRepo;

    @Autowired
    CountRepo countRepo;

    private String name = MainController.name;

    @GetMapping
    public String getBuyPage(Model model){
        List<DataModel> dataModel =  dataRepo.findAll();
        Data1 data1 = new Data1();
        data1.setCourse(dataModel.get(0).getCourse());
        data1.setFreeRobux(dataModel.get(0).getFreeRobux());
        data1.setName(MainController.name);
        String course = data1.getCourse();
        int in1 = course.indexOf('-');
        int in2 = course.indexOf('R');
        int ind3 = course.indexOf('₽');
        String rub = course.substring(0, ind3);
        String rob = course.substring(in1+1, in2);
        String oneRub = String.valueOf(Math.round((Double.parseDouble(rob)/Double.valueOf(rub))));
        data1.setOneRub(oneRub);
        model.addAttribute("list", Collections.singletonList(data1));
        return "buy-methods";
    }

    @PostMapping()
    public RedirectView toPayok(@RequestParam String username, @RequestParam String robox) throws NoSuchAlgorithmException {
        DataModel dataModel = new DataModel();
        long id = 1;
        List<DataModel> list = dataRepo.findAll();
        dataModel = list.get(0);

        String str = dataModel.getCourse();
        str = str.replaceAll(" ", "");
        char[] ch = str.toCharArray();
        int indRub= 0 , intRob = 0, intSign = 0;
        for (int i = 0; i < ch.length; i++) {
            if (ch[i]=='₽')
                indRub = i;
            if (ch[i]=='R')
                intRob = i;
        }
        String strRub, StrRob;
        strRub = str.substring(0,indRub);
        StrRob = str.substring(indRub+2, intRob);

        int course = Integer.valueOf(StrRob)/Integer.valueOf(strRub);

        amount = Double.parseDouble(robox);
        sum = amount/(course);
        /*map.put(100, "https://payok.io/payment_link/f8944-3fth9-2lc7n");
        map.put(150,  "https://payok.io/payment_link/t5174-6zeko-6v35l");
        map.put(200, "https://payok.io/payment_link/alk9r-3i5oe-cjz0c");
        map.put(250, "https://payok.io/payment_link/c5w43-ym18e-m744g");
        map.put(300, "https://payok.io/payment_link/yiiii-8e56z-67rnq");
        map.put(350, "https://payok.io/payment_link/z3u7u-bq2x5-gj516");
        map.put(400, "https://payok.io/payment_link/9o09w-fb9ul-n8h7e");
        map.put(450, "https://payok.io/payment_link/42ghj-3x31q-4j909");
        map.put(500, "https://payok.io/payment_link/k906x-6k3m9-bx9gw");
        map.put(550, "https://payok.io/payment_link/5hg2v-v7osz-1wf7f");
        map.put(600, "https://payok.io/payment_link/086yu-306fp-z75ps");
        map.put(650, "https://payok.io/payment_link/yu7xw-81921-yet2s");
        map.put(700, "https://payok.io/payment_link/0h1d9-j7168-c86ik");
        map.put(750, "https://payok.io/payment_link/769kb-3og22-10fw4");
        map.put(800, "https://payok.io/payment_link/fr3v4-4epd1-0wk1h");
        map.put(850, "https://payok.io/payment_link/mvru6-353fz-122s7");
        map.put(900, "https://payok.io/payment_link/d0cf5-rgnwt-ne9z6");
        map.put(950, "https://payok.io/payment_link/674bl-o0xiv-iix80");
        map.put(1000, "https://payok.io/payment_link/xz6gr-ko888-et0l0");


        sum = sum - sum%50;

        if (sum<100 && sum>0){
            url = map.get(100);
        }
        else if (sum>1000) {
            url = map.get(1000);
        }else {
        url = map.get((int) sum);
        }*/


        Payment payment = new Payment(username,  countRepo.findAll().get(0).getCount(), String.valueOf(sum));
        System.out.println(payment);
        String str1 = payment.toString();
        MessageDigest md =  MessageDigest.getInstance("MD5");
        byte[] bytes = md.digest(str1.getBytes(StandardCharsets.UTF_8));
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b:bytes) {
            stringBuilder.append(String.format("%02X",b));
        }
        String key = stringBuilder.toString().toLowerCase(Locale.ROOT);

        url = "https://payok.io/pay?" + "amount=" + payment.getAmount()+
                "&payment=" + payment.getPayment() + "&desc=" + payment.getDesc()
                + "&shop=" + payment.getShop() + "&currency=" + payment.getCurrency()
                + "&sign=" + key;
        System.out.println(url);

        RedirectView redirectView = new RedirectView();

        Cont cont = new Cont();
        int c = Integer.parseInt(countRepo.findAll().get(0).getCount());
        c++;
        cont.setCount(String.valueOf(c));
        countRepo.save(cont);

        redirectView.setUrl("/buy/byu-methods");

        return redirectView;
    }
    @GetMapping("/byu-methods")
    public String getInstruction(Model model){
        Data2 data2 = new Data2();
        data2.setUrl(url);
        int sum1 = (int) (amount/0.7);
        String sum2 = "Установите цену на "+ sum1 + " R$";
        data2.setSum(sum2);
        model.addAttribute("link", data2);
        return "buy-robux";
    }
}
