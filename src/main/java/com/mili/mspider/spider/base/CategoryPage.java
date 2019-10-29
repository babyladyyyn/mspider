package com.mili.mspider.spider.base;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Iterator;
import java.util.stream.Stream;

abstract public class CategoryPage extends UrlObject implements Iterator<CategoryPage> {

    private Category category;

    public CategoryPage(Category category, String url) {
        super(url);
        this.category = category;
    }


    protected abstract Stream<Article> generateArticleStream();

    @Override
    abstract public boolean hasNext();

    @Override
    public CategoryPage next() {
        CategoryPage categoryPage = generateCategoryPage();
        if (categoryPage != null) {
            categoryPage.category = categoryPage.getCategory();
        }
        return categoryPage;
    }

    abstract public CategoryPage generateCategoryPage();

    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("category", category)
                .toString();
    }
}
