package com.mili.mspider.spider.base;

import java.util.Iterator;
import java.util.stream.Stream;

abstract public class ChapterPage extends UrlObject implements Iterator<ChapterPage> {
    private Article article;

    public ChapterPage(Article article, String url) {
        super(url);
        this.article = article;
    }

    public abstract Stream<Chapter> generateChapter();

    @Override
    public ChapterPage next() {
        ChapterPage chapterPage = parseNextChapterPage();
        if (chapterPage != null) {
            chapterPage.article = article;
        }
        return chapterPage;
    }

    public abstract ChapterPage parseNextChapterPage();
}
