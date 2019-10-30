package com.mili.mspider;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

public class Mspider {
    private final static Logger logger = LoggerFactory.getLogger(Mspider.class);

    private int limit;
    private boolean cached = true;
    private Class<? extends SpiderProcess> clazz;

    public static final Mspider create(Class<? extends SpiderProcess> clazz) {
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "50");
        Mspider mspider = new Mspider();
        mspider.clazz = clazz;
        return mspider;
    }

    public Mspider limit(int limit) {
        if(limit<=0){
            throw new IllegalStateException("illeal limit." + "[limit = " + limit + "]");
        }
        this.limit = limit;
        return this;
    }

    public Mspider cache(boolean cached) {
        this.cached = cached;
        return this;
    }

    public Mspider thread(int threads){
        if(threads<=0){
            throw new IllegalStateException("illeal thread." + "[thread = " + threads + "]");
        }
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", threads+"");
        return this;
    }

    public Mspider start() {
        final SpiderProcess process = instanceSpiderProcess(clazz, limit);
        Stream.of(process.entryUrl())
                .flatMap(process::streamCategory)
                .flatMap(process::streamArticleByCategory)
                .flatMap(process::streamChapterByArticle)
                .forEach(process::handelChapter);
        return this;
    }


    private final static SpiderProcess instanceSpiderProcess(Class<? extends SpiderProcess> clazz, int limit) {
        try {
            SpiderProcess process = clazz.newInstance();
            return new SpiderProcessWapper(process, limit);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
