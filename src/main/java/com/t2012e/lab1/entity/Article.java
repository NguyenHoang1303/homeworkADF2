package com.t2012e.lab1.entity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Article extends Thread {
    private String url;
    private String title;
    private String description;
    private String content;
    private String thumbnail;
    private LocalDate createAt;
    private LocalDate updateAt;
    private int status;
    private Article article;
    @Override
    public void run() {
        crawlData();
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public void crawlData() {
        try {
            article = new Article();
            Document docTT = Jsoup.connect(url).get();
            Element titleNode = docTT.selectFirst("h1.title-detail");
            if (titleNode != null) {
                String title = titleNode.text();
                article.setTitle(title);
            } else {
                article.setTitle(null);
            }
            Element descriptionNode = docTT.selectFirst(".description");
            if (descriptionNode != null) {
                String description = descriptionNode.text();
                article.setDescription(description);
            } else {
                article.setDescription(null);
            }
            Element contentNode = docTT.selectFirst(".normal");
            if (contentNode != null) {
                String content = contentNode.text();
                article.setContent(content);
            } else {
                article.setContent(null);
            }
            Element thumbnailNode = docTT.selectFirst("article figure .fig-picture picture img");
            if (thumbnailNode != null) {
                String thumbnail = thumbnailNode.attr("data-src");
                article.setThumbnail(thumbnail);
            } else {
                article.setThumbnail(null);
            }
            article.setUrl(url);
            article.setStatus(1);
            article.setCreateAt(LocalDate.now());
            article.setUpdateAt(LocalDate.now());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<String> getListUrlVnExpress(String url) {
        Document doc;
        try {
            doc = Jsoup.connect(url).get();
            Elements newsHeadlines = doc.select(".title-news a");
            List<String> listUrl = new ArrayList<>();
            if (newsHeadlines.size() > 0) {
                for (Element headline : newsHeadlines) {
                    listUrl.add(headline.attr("href"));
                }
            }
            return listUrl;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Article(String url) {
        this.url = url;
    }

    public Boolean isValid() {
        if (this.title == null) {
            System.out.println("title not found by url:\n" + url);
            return false;
        }
        if (this.description == null) {
            System.out.println("description not found by url:\n" + url);
            return false;
        }
        if (this.content == null) {
            System.out.println("content not found by url:\n" + url);
            return false;
        }
        if (this.thumbnail == null) {
            System.out.println("thumbnail not found by url:\n" + url);
            return false;
        }
        return true;
    }

    public Article() {
    }

    public Article(String url, String title, String description, String content, String thumbnail, int status) {
        this.url = url;
        this.title = title;
        this.description = description;
        this.content = content;
        this.thumbnail = thumbnail;
        this.createAt = LocalDate.now();
        this.updateAt = LocalDate.now();
        this.status = status;
    }

    @Override
    public String toString() {
        return "Article{" +
                "url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", content='" + content + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                ", status=" + statusString() +
                '}';
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getCreatDateString() {
        return createAt.toString();
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    public LocalDate getUpdateAt() {
        return updateAt;
    }

    public String getUpdateDateString() {
        return updateAt.toString();
    }

    public void setUpdateAt(LocalDate updateAt) {
        this.updateAt = updateAt;
    }

    public int getStatus() {
        return status;
    }

    public String statusString() {
        if (status == 1) return "đã hoàn thiện";
        if (status == 0) return "chưa hoàn thiện";
        if (status == -1) return " đã xoá";
        return null;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
