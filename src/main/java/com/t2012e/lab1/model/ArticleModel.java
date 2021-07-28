package com.t2012e.lab1.model;

import com.t2012e.lab1.entity.Article;
import com.t2012e.util.ConnectionHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ArticleModel {
    public void insertArticle(Article article) {
        try {
            Connection cnn = ConnectionHelper.getConnection();
            if (cnn == null) {
                System.err.println("can not open connect database");
                return;
            }
            PreparedStatement pp = cnn.prepareStatement
                    ("insert into articles" +
                    "(url, title, description, content, thumbnail, creatAt, updateAt, status)" +
                    "values (?, ?, ?, ?, ?, ?, ?, ?)");
            pp.setString(1, article.getUrl());
            pp.setString(2, article.getTitle());
            pp.setString(3, article.getDescription());
            pp.setString(4, article.getContent());
            pp.setString(5, article.getThumbnail());
            pp.setString(6, article.getCreatDateString());
            pp.setString(7, article.getUpdateDateString());
            pp.setInt(8, article.getStatus());
            pp.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("loi");
        }
    }

}
