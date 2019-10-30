package com.mili.mspider;

import us.codecraft.webmagic.selector.Html;

import java.util.stream.Stream;

public class SpiderProcessWapper implements SpiderProcess {
    private SpiderProcess spiderProcess;

    public SpiderProcessWapper(SpiderProcess spiderProcess) {
        this.spiderProcess = spiderProcess;
    }

    @Override
    public String entryUrl() {
        return spiderProcess.entryUrl();
    }

    @Override
    public Html category(String url) {
        return spiderProcess.category(url);
    }

    @Override
    public Html nextCategory(Html html) {
        return spiderProcess.nextCategory(html);
    }

    @Override
    public Stream<Html> streamArticleByCategory(Html html) {
        return spiderProcess.streamArticleByCategory(html);
    }

    @Override
    public Stream<Html> streamChapterByArticle(Html html) {
        return spiderProcess.streamChapterByArticle(html);
    }

    @Override
    public void handelChapter(Html html) {
        spiderProcess.handelChapter(html);
    }
}
