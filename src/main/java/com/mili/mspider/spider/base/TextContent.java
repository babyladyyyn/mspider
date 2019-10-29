package com.mili.mspider.spider.base;

import org.apache.commons.lang3.builder.ToStringBuilder;
import us.codecraft.webmagic.selector.Html;

abstract public class TextContent implements ChapterContent {
    private Article article;

    public TextContent(Article article) {
        this.article = article;
    }

    public Html getHtml() {
        return article.getHtml();
    }

    @Override
    abstract public Object getArticleContent();

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("article", article)
                .toString();
    }
}
