package com.t2012e.lab1;


import com.t2012e.lab1.entity.Article;
import com.t2012e.lab1.model.ArticleModel;

import java.util.ArrayList;
import java.util.List;


public class Maint2012 {
    public static void main(String[] args) {
        Long star = System.currentTimeMillis();
        String url = "https://vnexpress.net/the-thao";
        Article article = new Article(url);
        String urlTitle = article.getUrl();
        List<String> listUrlDetail = article.getListUrlVnExpress(urlTitle);
        List<Article> listThread = new ArrayList<>();
        for (String urlDetail : listUrlDetail) {
            Article at1 = new Article(urlDetail);
            at1.start();
            listThread.add(at1);
        }
        for (Thread thread : listThread) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Boolean isValid;
        ArticleModel articleModel = new ArticleModel();
        Article articleValid;
        for (Article thread : listThread) {
            articleValid = thread.getArticle();
            isValid = articleValid.isValid();
            if (isValid){
                articleModel.insertArticle(articleValid);
            }
        }
        Long end = System.currentTimeMillis();
        System.out.println(end - star + " mls");
    }
}


