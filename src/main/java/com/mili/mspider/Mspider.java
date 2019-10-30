package com.mili.mspider;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

public class Mspider {
    private final static Logger logger = LoggerFactory.getLogger(Mspider.class);

    public final static void start(Class<? extends SpiderProcess> clazz) {
        start(clazz, Integer.MAX_VALUE);
    }

    public final static void start(Class<? extends SpiderProcess> clazz, int limit) {
        final SpiderProcess process = instanceSpiderProcess(clazz,limit);
        Stream.of(process.entryUrl())
                .flatMap(process::streamCategory)
                .flatMap(process::streamArticleByCategory)
                .flatMap(process::streamChapterByArticle)
                .forEach(process::handelChapter);
    }


    private final static SpiderProcess instanceSpiderProcess(Class<? extends SpiderProcess> clazz,int limit) {
        try {
            SpiderProcess process = clazz.newInstance();
            return new SpiderProcessWapper(process,limit);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
