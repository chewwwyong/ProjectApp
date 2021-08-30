package com.example.projectapp.Tools;

/***
 * 抓取到的文章數據封裝
 */
public class Article {

    private String scanResult;

    // 有幾個屬性還沒用到，所以構造方法先用上這四個有爬取到數據的
    public Article(String scanResult) {
        this.scanResult = scanResult;
    }


    public String getscanResult() {
        return scanResult;
    }

    public void setscanResult(String scanResult) {
        this.scanResult = scanResult;
    }

   

    @Override
    public String toString() {
//        return "Article{" +
//                "scanResult='" + scanResult + '\'' +
//                '}';
        return scanResult;
    }
}