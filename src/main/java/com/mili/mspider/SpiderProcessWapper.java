package com.mili.mspider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SpiderProcessWapper implements SpiderProcess {

    private final static Logger logger = LoggerFactory.getLogger(SpiderProcessWapper.class);
    private int limit = 0;
    private SpiderProcess spiderProcess;
    private boolean dev = false;

    public SpiderProcessWapper(SpiderProcess spiderProcess, int limit) {
        this.spiderProcess = spiderProcess;
        this.limit = limit;
    }

    private static Stream<HtmlWapper> filterForDev(HtmlWapper htmlWapper, Stream<HtmlWapper> stream) {
        List<HtmlWapper> streamList = stream.collect(Collectors.toList());
        logger.info("stream info." + "[htmlWapper = " + htmlWapper + "]" + "[size = " + streamList.size() + "]");
        return streamList.stream();
    }

    public void setDev(boolean dev) {
        this.dev = dev;
    }

    @Override
    public String entryUrl() {
        String url = spiderProcess.entryUrl();
        logger.info("entry url:" + "[url = " + url + "]");
        return url;
    }

    @Override
    public Stream<HtmlWapper> streamCategory(String url) {
        Stream<HtmlWapper> stream = spiderProcess.streamCategory(url);
        if (limit > 0) {
            stream = stream.limit(limit);
        }
        return stream;
    }

    @Override
    public HtmlWapper category(HtmlWapper html) {
        logger.info("category" + "[html = " + html + "]");
        return spiderProcess.category(html);
    }

    @Override
    public HtmlWapper nextCategory(HtmlWapper html) {
        logger.info("nextCategory" + "[html = " + html + "]");
        return spiderProcess.nextCategory(html);
    }

    @Override
    public Stream<HtmlWapper> streamArticleByCategory(HtmlWapper html) {
        logger.info("streamArticleByCategory" + "[html = " + html + "]");
        Stream<HtmlWapper> stream = spiderProcess.streamArticleByCategory(html);
        if (limit > 0) {
            stream = stream.limit(limit);
        }
        return stream;
    }

    @Override
    public Stream<HtmlWapper> streamChapterByArticle(HtmlWapper html) {
        logger.info("streamChapterByArticle" + "[html = " + html + "]");
        Stream<HtmlWapper> stream = spiderProcess.streamChapterByArticle(html);
        if (limit > 0) {
            stream = stream.limit(limit);
        }
        return stream;
    }

    @Override
    public void handelChapter(HtmlWapper html) {
        logger.info("handelChapter"+ "[html = " + html + "]");
        spiderProcess.handelChapter(html);
    }
}
