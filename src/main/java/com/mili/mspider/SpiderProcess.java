package com.mili.mspider;

import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.utils.UrlUtils;

import java.util.Iterator;
import java.util.stream.Stream;

public interface SpiderProcess {

    String entryUrl();

    Html category(String url);

    default Iterator<Html> createIterator(Html html) {
        final Html nextCategory = nextCategory(html);
        return new Iterator<Html>() {
            @Override
            public boolean hasNext() {
                return nextCategory!=null;
            }

            @Override
            public Html next() {
                return nextCategory;
            }
        };
    }

    default String fixUrl(String url){
        String baseUrl = UrlUtils.getHost(entryUrl());
        if(!url.startsWith("https://")&&!url.startsWith("http://")){
            url = baseUrl + url;
        }
        return url;
    }

    Html nextCategory(Html html);

    Stream<Html> streamArticleByCategory(Html html);

    Stream<Html> streamChapterByArticle(Html html);

    void handelChapter(Html html);

}
