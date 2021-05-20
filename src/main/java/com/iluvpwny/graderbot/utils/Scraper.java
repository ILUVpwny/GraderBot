package com.iluvpwny.graderbot.utils;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Scraper {

    public static Score getData(String name, String password){
        try {
            String html = "https://posnwu.xyz/";
            Connection.Response loginForm = Jsoup.connect(html)
                    .validateTLSCertificates(false)
                    .method(Connection.Method.GET)
                    .execute();

            String auth_token = loginForm.parse().select(".form-horizontal").get(0).select("input").get(1).attr("value");

            int sum = 0;
            int full = 0;
            Document doc = Jsoup.connect("https://posnwu.xyz/login/login")
                    .data("utf8", "✓")
                    .data("authenticity_token", auth_token)
                    .data("login", name)
                    .data("password", password)
                    .data("commit", "Login")
                    .cookies(loginForm.cookies())
                    .validateTLSCertificates(false)
                    .post();


            Elements tableElements = doc.select("table");


            Elements tableRowElements = tableElements.select(":not(thead) tr");

            for (int i = 2; i < tableRowElements.size(); i++) {
                Element row = tableRowElements.get(i);
                Elements rowItems = row.select("td");
                for (Element rowItem : rowItems) {
                    String text = rowItem.text();
                    if (text.contains("score")) {
                        int score = Integer.parseInt(text.substring(text.indexOf("score: "), text.indexOf("[") - 1).substring(7));
                        if (score == 100) full++;
                        sum += score;
                    }
                }
            }
            return new Score(sum,100 * (tableRowElements.size() - 2),full,(tableRowElements.size() - 2));
        }catch (IOException e){
            return null;
        }
    }

    public static String getRandom(String name, String password, int maxScore){
        List<String> toRandom = new ArrayList<>();

        try {
            String html = "https://posnwu.xyz/";
            Connection.Response loginForm = Jsoup.connect(html)
                    .validateTLSCertificates(false)
                    .method(Connection.Method.GET)
                    .execute();

            String auth_token = loginForm.parse().select(".form-horizontal").get(0).select("input").get(1).attr("value");

            int sum = 0;
            int full = 0;
            Document doc = Jsoup.connect("https://posnwu.xyz/login/login")
                    .data("utf8", "✓")
                    .data("authenticity_token", auth_token)
                    .data("login", name)
                    .data("password", password)
                    .data("commit", "Login")
                    .cookies(loginForm.cookies())
                    .validateTLSCertificates(false)
                    .post();

            Elements tableElements = doc.select("table");
            Elements tableRowElements = tableElements.select(":not(thead) tr");

            for (int i = 2; i < tableRowElements.size(); i++) {
                Element row = tableRowElements.get(i);
                Elements rowItems = row.select("td");
                int score;
                if(rowItems.get(3).text().contains("score"))score = Integer.parseInt(rowItems.get(3).text().substring(rowItems.get(3).text().indexOf("score: "), rowItems.get(3).text().indexOf("[") - 1).substring(7));
                else score = 0;
                if(score <= maxScore){
                    toRandom.add(rowItems.get(0).text());
                }
            }
            return toRandom.get(new Random().nextInt(toRandom.size()));
        }catch(IOException e){
            return null;
        }
    }
}
