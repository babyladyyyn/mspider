package com.mili.mspider.spider.base;

import java.util.stream.Stream;

abstract public class Chapter<T> extends UrlObject {

    private String name;
    private String img;
    private String desc;
    private Article article;

    public Chapter(Article article, String url) {
        super(url);
        this.article = article;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    abstract public Stream<T> generateChapterContent();
}
