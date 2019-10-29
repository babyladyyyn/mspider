package com.mili.mspider.m48wx;

import com.mili.mspider.spider.base.Article;
import com.mili.mspider.spider.base.Category;
import com.mili.mspider.spider.base.ChapterPage;

public class M48WxArticle extends Article {

    public M48WxArticle(Category category, String url) {
        super(category, url);
    }

    @Override
    public ChapterPage generateChapterPage() {
        String chapterPageUrl = getHtml().$("div.recommend h2 a").nodes().stream().filter(node -> {
            String allPageUrl = node.$("a", "text").toString();
            return allPageUrl.equals("查看完整目录");
        }).findFirst().get().$("a", "href").toString();
        return new M48WxChapterPage(this, fixWholeUrl(chapterPageUrl));
    }

}
