package com.example.bogdashka.helper;

import lombok.Data;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

@Data
public class PaymentData {
    ArrayList<String> arrayList = new ArrayList<>();

    public PaymentData() throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost("https://payok.io/api/transaction");

// Request parameters and other properties.
        List<NameValuePair> params = new ArrayList<NameValuePair>(2);
        params.add(new BasicNameValuePair("API_ID", "883"));
        params.add(new BasicNameValuePair("API_KEY", "36F38C6DB2B3E4019F9AD33B844C0FA5-43FA5332421C0BEE67CCC2AE52CE2454-BEFF2C0B67C333A3B8C25CDAD44EB352"));
        params.add(new BasicNameValuePair("shop", "1723"));
        httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));


//Execute and get the response.
        HttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();

        if (entity != null) {
            try (InputStream instream = entity.getContent()) {
                int bufferSize = 1024;
                char[] buffer = new char[bufferSize];
                StringBuilder out = new StringBuilder();
                Reader in = new InputStreamReader(instream, StandardCharsets.UTF_8);
                for (int numRead; (numRead = in.read(buffer, 0, buffer.length)) > 0; ) {
                    out.append(buffer, 0, numRead);
                }
                System.out.println(out);
                String[] strings = out.toString().split("\\{");
                ArrayList<String> list = new ArrayList<>();
                for (int i = 0; i < strings.length-1; i++) {
                    if (strings[i].contains("success"))
                        list.add(strings[i]+strings[i+1]);
                    System.out.println(strings[i]);
                }
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println(list.get(0));

                strings = list.get(0).split(",");
                List<String> list1 = new ArrayList<>();
                for (int i = 0; i < strings.length; i++) {
                    System.out.println(strings[i]);
                    if (strings[i].contains("description")||strings[i].contains("currency_amount")){
                        list1.add(strings[i]);
                    }
                }
                System.out.println(list1);
                String st = "";
                ArrayList<String> arrayList = new ArrayList<>();
                for (String str : list1){
                    st = str.replaceAll("\"", "");
                    arrayList.add(st);
                }
                for (int i = 0; i < arrayList.size(); i++) {
                    if (i%2==0){
                        arrayList.set(i, arrayList.get(i).replaceAll("currency_amount:",""));
                    }else {
                        arrayList.set(i, arrayList.get(i).replaceAll("description:",""));
                    }
                }
                this.arrayList = arrayList;
            }
        }
    }

}
