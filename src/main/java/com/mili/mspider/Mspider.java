package com.mili.mspider;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.selector.Html;

import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Mspider {
    private final static Logger logger = LoggerFactory.getLogger(Mspider.class);

    public final static void start(Class<? extends SpiderProcess> clazz) {
        start(clazz, Integer.MAX_VALUE);
    }

    public final static void start(Class<? extends SpiderProcess> clazz, int limit) {
        final SpiderProcess process = instanceSpiderProcess(clazz);
        Stream.of(process.entryUrl())
                .flatMap(url -> {
                    Html html = process.category(url);
                    return Stream.concat(Stream.of(html), StreamSupport.stream(Spliterators.spliteratorUnknownSize(process.createIterator(html), 0), false));
                })
                .flatMap(process::streamArticleByCategory)
                .flatMap(process::streamChapterByArticle)
                .limit(limit)
                .forEach(process::handelChapter);
    }

    private final static SpiderProcess instanceSpiderProcess(Class<? extends SpiderProcess> clazz) {
        try {
            SpiderProcess process = clazz.newInstance();
            return process;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
