package com.mili.mspider.spider.base;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ImgContent extends UrlObject implements ChapterContent {

    private Article article;

    public ImgContent(Article article, String url) {
        super(url);
        this.article = article;
    }

    @Override
    public Object getArticleContent(){
        return this.getImg();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("article", article)
                .toString();
    }
}
