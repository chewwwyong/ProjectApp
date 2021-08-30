package com.example.projectapp.Tools;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class GetData {

    private static final String TAG ="GetData" ;

    /**
     * 抓取什么值得买首页的精选文章
     * @param html
     * @return  ArrayList<Article> articles
     */




    public static ArrayList<Article> spiderArticle(String html){
        ArrayList<Article> articles = new ArrayList<>();

        Document document = Jsoup.parse(html);
        /*Elements elements = document
                .select("ul[class=feed-list-hits feed-list-index]")
                .select("li[class=feed-row-wide J_feed_za feed-haojia]");

        Log.i(TAG, "spiderArticle: elements " +elements.html());
        */
/*
        for (Element element : elements) {
            String title = element
                    .select("h5[class=feed-block-title has-price]")
                    .text();
            String author = element
                    .select("div[class=z-feed-foot]")
                    .select("span[class=feed-block-extras]")
                    .select("a")
                    .select("span")
                    .text();

            String imgurl = element
                    .select("div[class=z-feed-img]")
                    .select("a")
                    .select("img")
                    .attr("src");
            String context = element
                    .select("div[class=feed-block-descripe]")
                    .text();
            String articleUrl = element
                    .select("div[class=z-feed-img ]")
                    .select("a")
                    .attr("href");

            String scanResult = ;
            Article article = new Article(scanResult);
            articles.add(article);
            //Log.e("DATA>>",article.toString());
        }
*/

        Elements elements = document
                .select("p");

        for (Element element : elements) {
            String data = element.text();

            //String scanResult = "";
            Article article = new Article(data);
            articles.add(article);
            //Log.e("DATA>>",article.toString());
        }

        //Log.i(TAG, "spiderArticle: elements " +elements.html());


        //String scanResult = document.select("p").text();
        //Article article = new Article(scanResult);
        //articles.add(article);
        return articles;
    }
}