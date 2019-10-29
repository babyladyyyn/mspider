package com.mili.mspider.spider.base;

import org.junit.Test;

public class IndexTest {

    @Test
    public void getCategoryStream() {
//        final String indexUrl = "https://m.48wx.org/xclass/0/1.html";
//        Index index = new Index(indexUrl) {
//
//            @Override
//            protected Stream<Category> generateCategoryStream() {
//                return Stream.of(new Category(indexUrl) {
//                    @Override
//                    public CategoryPage generateCategoryPage() {
//                        Html html = getHtml();
//                        return new CategoryPage(this,indexUrl) {
//                            @Override
//                            List<Article> generateArticleStream() {
//                                return getHtml().$("#main .hot_sale").nodes().stream().flatMap(node->{
//
//                                });
//                            }
//
//                            @Override
//                            public boolean hasNext() {
//                                return false;
//                            }
//
//                            @Override
//                            public CategoryPage generateCategoryPage() {
//                                return null;
//                            }
//                        };
//                    }
//                });
//            }
//        };
//        index.generateCategoryStream().forEach(category -> {
//            System.out.println("[category = " + category + "]");
//        });
    }
}
