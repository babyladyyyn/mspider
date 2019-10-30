package com.mili.mspider;

import com.mili.mspider.util.HttpUtil;
import us.codecraft.webmagic.utils.UrlUtils;

import java.util.Iterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public interface SpiderProcess {

    default String fixUrl(String url) {
        String baseUrl = UrlUtils.getHost(entryUrl());
        if (!url.startsWith("https://") && !url.startsWith("http://")) {
            url = baseUrl + url;
        }
        return url;
    }

    String entryUrl();

    default Stream<HtmlWapper> streamCategory(String url) {
        HtmlWapper html = HttpUtil.responseHtml(url);
        HtmlWapper htmlCategory = this.category(html);
        return Stream.concat(Stream.of(htmlCategory), StreamSupport.stream(Spliterators.spliteratorUnknownSize(this.createIterator(htmlCategory), 0), false));
    }

    HtmlWapper category(HtmlWapper html);

    default Iterator<HtmlWapper> createIterator(HtmlWapper html) {
        final HtmlWapper nextCategory = nextCategory(html);
        return new Iterator<HtmlWapper>() {
            @Override
            public boolean hasNext() {
                return nextCategory != null;
            }

            @Override
            public HtmlWapper next() {
                return nextCategory;
            }
        };
    }

    HtmlWapper nextCategory(HtmlWapper html);

    Stream<HtmlWapper> streamArticleByCategory(HtmlWapper html);

    Stream<HtmlWapper> streamChapterByArticle(HtmlWapper html);

    void handelChapter(HtmlWapper html);

}
