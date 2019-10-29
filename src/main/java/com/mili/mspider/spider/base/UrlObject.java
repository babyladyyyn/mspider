package com.mili.mspider.spider.base;

import com.mili.mspider.http.HttpUtil;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import org.apache.commons.lang3.builder.ToStringBuilder;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Json;
import us.codecraft.webmagic.utils.UrlUtils;

import java.util.HashMap;
import java.util.Map;

abstract public class UrlObject {
    private String url;
    private String baseUrl;
    private String charset = "utf-8";
    private String filePath = "/tmp/mspider/url";
    private HttpResponse response;
    private HttpRequest request;
    private Map<String, Object> exacts = new HashMap<>();

    public UrlObject(String url) {
        this.url = url;
        this.baseUrl = UrlUtils.getHost(url);
        this.request = HttpRequest.get(url);
    }

    protected String fixWholeUrl(String url){
        if(!url.startsWith("http://")&&!url.startsWith("https://")){
            url = baseUrl + url;
        }
        return url;
    }

    public HttpRequest getRequest() {
        return request;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    protected HttpResponse getResponse() {
        if (response == null) {
            response = request.send();
        }
        return response;
    }

    protected Html getHtml() {
        return HttpUtil.responseHtml(getResponse(), charset);
    }

    public String getUrl() {
        return url;
    }

    protected Json getJson() {
        return HttpUtil.responseJson(getResponse(), charset);
    }

    protected String getFile() {
        return HttpUtil.responseFile(getResponse(), filePath);
    }

    protected String getBody() {
        return HttpUtil.responseBody(getResponse(), filePath);
    }

    protected String getImg(){return this.getFile();}

    public Object getExact(String key) {
        return exacts.get(key);
    }

    public void putExact(String key, String value) {
        exacts.put(key, value);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("url", url)
                .append("filePath", filePath)
                .toString();
    }
}
