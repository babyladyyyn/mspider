package com.mili.mspider.spider.base;

import java.util.stream.Stream;

abstract public class Index extends UrlObject {

    public Index(String url) {
        super(url);
    }

    public Stream<Category> getCategoryStream() {
        return this.generateCategoryStream().filter(category -> {
            category.setIndex(this);
            return true;
        });
    }

    abstract protected Stream<Category> generateCategoryStream();

}
