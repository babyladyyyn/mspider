package com.mili.mspider.m48wx;

import org.junit.Test;

import static org.junit.Assert.*;

public class M48WxIndexTest {

    @Test
    public void generateCategoryStream() {
        M48WxIndex m48WxIndex = new M48WxIndex();
        m48WxIndex.generateCategoryStream().flatMap(category -> {
            return category.generateArticleStream();
        }).flatMap(article -> {
            return article.generateChapters();
        }).forEach(System.out::println);
    }
}
