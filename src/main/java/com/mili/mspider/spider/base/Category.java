package com.mili.mspider.spider.base;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

abstract public class Category extends UrlObject {
    private Index index;
    public String name;
    public String img;

    public Category(Index index, String url) {
        super(url);
        this.index = index;
    }

    public Index getIndex() {
        return index;
    }

    public void setIndex(Index index) {
        this.index = index;
    }

    public Stream<Article> generateArticleStream() {
        CategoryPage categoryPage = generateCategoryPage();
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(categoryPage, 0), false).flatMap(
                _categoryPage -> {
                    return categoryPage.generateArticleStream();
                }
        );
    }

    public abstract CategoryPage generateCategoryPage();

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("index", index)
                .append("name", name)
                .append("img", img)
                .toString();
    }
}
