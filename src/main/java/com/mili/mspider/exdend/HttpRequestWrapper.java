package com.mili.mspider.exdend;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.io.FileUtil;
import jodd.net.HttpMethod;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class HttpRequestWrapper extends HttpRequest {
    private final static Logger logger = LoggerFactory.getLogger(HttpRequestWrapper.class);
    private String catchDirPath = "/tmp/mspider/";

    public static HttpRequest get(final String destination) {
        return new HttpRequestWrapper()
                .method(HttpMethod.GET)
                .set(destination);
    }

    @Override
    public HttpRequest set(String destination) {
        super.set(destination);
        return this;
    }

    public HttpRequestWrapper() {
        super();
    }

    public void setCatchDirPath(String catchDirPath) {
        this.catchDirPath = catchDirPath;
    }

    @Override
    public HttpResponse send() {
        logger.info("get url."+ "[url = " + this.url() + "]");
        String url = this.url();
        HttpResponse httpResponse = getFromCache(url);
        if (httpResponse == null) {
            logger.info("no cache for url." + "[url = " + url + "]");
            httpResponse = super.send();
            if (httpResponse.statusCode() == 200) {
                cache(url, httpResponse);
            }
        } else {
            logger.info("get from cache." + "[url = " + url + "]");
        }
        return httpResponse;
    }

    private HttpResponse getFromCache(String url) {
        String cacheFilePath = cacheFilePath(url);
        File cacheFile = new File(cacheFilePath);
        if (!FileUtil.isExistingFile(cacheFile)) {
            return null;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(cacheFile);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            HttpResponseWrapper httpResponseWrapper = (HttpResponseWrapper) objectInputStream.readObject();
            HttpResponse httpResponse = new HttpResponse();
            httpResponse.header(httpResponseWrapper.getHeaders());
            httpResponse.body(httpResponseWrapper.getBody(), httpResponseWrapper.getHeaders().get("Content-Type"));
            httpResponse.httpVersion(httpResponseWrapper.getHttpVersion());
            httpResponse.statusCode(httpResponseWrapper.getStatusCode());
            httpResponse.statusPhrase(httpResponseWrapper.getStatusPhrase());
            return httpResponse;
        } catch (Exception e) {
            logger.error("get from cache error." + e.getMessage(), e);
            try {
                FileUtil.delete(cacheFile);
            } catch (Exception e1) {
                logger.error("delte cache file error." + e.getMessage(), e1);
            }
        }
        return null;
    }

    private void cache(String url, HttpResponse httpResponse) {
        logger.info("cache." + "[url = " + url + "]");
        try {
            HttpResponseWrapper httpResponseWrapper = new HttpResponseWrapper();
            httpResponseWrapper.setHttpVersion(httpResponse.httpVersion());
            httpResponse.headerNames().stream().forEach(key -> {
                httpResponseWrapper.putHeader(key, httpResponse.header(key));
            });
            httpResponseWrapper.setBody(httpResponse.bodyBytes());
            httpResponseWrapper.setStatusCode(httpResponse.statusCode());
            httpResponseWrapper.setStatusPhrase(httpResponse.statusPhrase());
            File cacheFile = new File(cacheFilePath(url));
            FileOutputStream fileOutputStream = FileUtils.openOutputStream(cacheFile);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(httpResponseWrapper);
            objectOutputStream.close();
        } catch (Exception e) {
            logger.error("cache error." + e.getMessage(), e);
        }
    }

    private String cacheFilePath(String url) {
        String urlCachePath = StringUtils.substringAfter(url, "://");
        urlCachePath = catchDirPath + urlCachePath;
        try {
            String cacheDir = StringUtils.substringBeforeLast(urlCachePath, "/");
            File cacheDirFile = new File(cacheDir);
            if(!FileUtil.isExistingFolder(cacheDirFile)){
                FileUtil.mkdirs(cacheDir);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return urlCachePath;
    }


}
