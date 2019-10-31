package com.mili.mspider.examples;

import com.mili.mspider.HtmlWapper;
import com.mili.mspider.SpiderProcess;
import com.mili.mspider.util.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

public class M48wxSpiderProcess implements SpiderProcess {

    @Override
    public String entryUrl() {
        return "https://m.48wx.org/xclass/0/1.html";
    }

    @Override
    public HtmlWapper category(HtmlWapper html) {
        return html;
    }

    @Override
    public HtmlWapper nextCategory(HtmlWapper html) {
        return null;
    }

    @Override
    public Stream<HtmlWapper> streamArticleByCategory(HtmlWapper html) {
        Stream<HtmlWapper> htmlWapperStream = html.$("#main .hot_sale").nodes().stream().map(node -> {
            return node.$("a", "href").toString();
        }).map(url -> {
            return HttpUtil.responseHtml(fixUrl(url + "all.html"));
        });
        return htmlWapperStream.parallel();
    }

    private final static Logger logger = LoggerFactory.getLogger(M48wxSpiderProcess.class);

    @Override
    public Stream<HtmlWapper> streamChapterByArticle(HtmlWapper html) {
        return html.$("#chapterlist p").nodes().stream().map(node -> {
            return node.$("a", "href").toString();
        })
                .filter(url -> !url.equals("#bottom"))
                .map(url -> fixUrl(url))
                .map(HttpUtil::responseHtml).parallel();
    }

    @Override
    public void handelChapter(HtmlWapper html) {
        System.out.println(html.$("header .title","text"));
    }
}
