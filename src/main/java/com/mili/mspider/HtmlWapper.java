package com.mili.mspider;

import org.apache.commons.lang3.builder.ToStringBuilder;
import us.codecraft.webmagic.selector.Html;

import java.util.HashMap;
import java.util.Map;

public class HtmlWapper extends Html {
    private String url;
    private Map<String, Object> exactMap = new HashMap<>();
    public HtmlWapper(String url ,String content) {
        this(url, new Html(content));
    }
    public HtmlWapper(String url ,Html html) {
        super(html.getDocument());
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return new ToStringBuilder("wapper")
                .append("url", url)
                .toString();
    }
}
