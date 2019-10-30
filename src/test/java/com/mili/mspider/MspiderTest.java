package com.mili.mspider;

import com.mili.mspider.http.HttpUtil;
import org.junit.Test;
import us.codecraft.webmagic.selector.Html;

import java.util.stream.Stream;

public class MspiderTest {

    @Test
    public void start() {
        Mspider.start(M48wx.class,1);
    }

    public static class M48wx implements SpiderProcess {

        @Override
        public String entryUrl() {
            return "https://m.48wx.org/xclass/0/1.html";
        }

        @Override
        public Html category(String url) {
            return HttpUtil.responseHtml(url);
        }

        @Override
        public Html nextCategory(Html html) {
            return null;
        }

        @Override
        public Stream<Html> streamArticleByCategory(Html html) {
            return html.$("#main .hot_sale").nodes().stream().map(node -> {
                return node.$("a", "href").toString();
            }).map(url -> {
                return HttpUtil.responseHtml(fixUrl(url + "all.html"));
            });
        }

        @Override
        public Stream<Html> streamChapterByArticle(Html html) {
            return html.$("#chapterlist p").nodes().stream().map(node -> {
                return node.$("a", "href").toString();
            })
                    .filter(url -> !url.equals("#bottom"))
                    .map(url -> fixUrl(url))
                    .map(HttpUtil::responseHtml);
        }

        @Override
        public void handelChapter(Html html) {
            System.out.println(html);
        }
    }
}
