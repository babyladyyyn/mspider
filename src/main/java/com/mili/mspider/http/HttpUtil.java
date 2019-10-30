package com.mili.mspider.http;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.io.FileUtil;
import jodd.util.StringUtil;
import jodd.util.ThreadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Json;

import java.io.IOException;

public class HttpUtil {

    public final static String responseFile(String url, String filePath) {
        HttpResponse httpResponse = httpResponse(url);
        byte[] bytes = httpResponse.bodyBytes();
        try {
            FileUtil.writeBytes(filePath, bytes);
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
        return filePath;
    }

    public final static HttpResponse httpResponse(String url) {
        HttpRequest httpRequest = httpRequest(url);
        HttpResponse httpResponse;
        try {
            httpResponse = httpRequest.send();
        } catch (Exception e) {
            logger.error(e.getMessage()+ "[url = " + url + "]", e);
            ThreadUtil.sleep(10000);
            return httpResponse(url);
        }
        return httpResponse;
    }

    private final static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    public final static HttpRequest httpRequest(String url) {
        return HttpRequest.get(url).connectionTimeout(5000).timeout(600000).acceptEncoding("gzip").accept("text/html");
    }

    public final static Json responseJson(String url) {
        return responseJson(url, "utf-8");
    }

    public final static Json responseJson(String url, String charset) {
        String content = responseText(url, charset);
        return new Json(content);
    }

    public final static String responseText(String url, String charset) {
        HttpResponse httpResponse = httpResponse(url);
        String content = StringUtil.newString(httpResponse.unzip().bodyBytes(), charset);
        return content;
    }

    public final static String responseText(String url) {
        return responseText(url, "utf-8");
    }

    public final static Html responseHtml(String url) {
        return responseBody(url, "utf8");
    }

    public final static Html responseBody(String url, String charset) {
        String content = responseText(url, charset);
        return new Html(content);
    }

    public final static Html responseHtml(HttpResponse response,String charset){
        String content =  StringUtil.newString(response.unzip().bodyBytes(), charset);
        return new Html(content);
    }

    public final static Json responseJson(HttpResponse response,String charset){
        String content =  StringUtil.newString(response.unzip().bodyBytes(), charset);
        return new Json(content);
    }

    public final static String responseBody(HttpResponse response,String charset){
        String content =  StringUtil.newString(response.unzip().bodyBytes(), charset);
        return content;
    }

    public final static String responseFile(HttpResponse response,String filePath){
        byte[] bytes = response.bodyBytes();
        try {
            FileUtil.writeBytes(filePath, bytes);
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
        return filePath;
    }
}
