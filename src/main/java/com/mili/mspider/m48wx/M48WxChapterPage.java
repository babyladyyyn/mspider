package com.mili.mspider.m48wx;

import com.mili.mspider.spider.base.Article;
import com.mili.mspider.spider.base.Chapter;
import com.mili.mspider.spider.base.ChapterPage;

import java.util.stream.Stream;

public class M48WxChapterPage extends ChapterPage {
    public M48WxChapterPage(Article article, String url) {
        super(article, url);
    }

    @Override
    public Stream<Chapter> generateChapter() {
        return null;
    }

    @Override
    public ChapterPage parseNextChapterPage() {
        return null;
    }

    @Override
    public boolean hasNext() {
        return false;
    }
}
