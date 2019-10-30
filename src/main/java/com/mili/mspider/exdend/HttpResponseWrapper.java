package com.mili.mspider.exdend;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class HttpResponseWrapper implements Serializable {
    private String httpVersion;
    private int statusCode;
    private String statusPhrase;
    private Map<String,String> headers = new HashMap<>();
    private byte[] body;

    public String getHttpVersion() {
        return httpVersion;
    }

    public void setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusPhrase() {
        return statusPhrase;
    }

    public void setStatusPhrase(String statusPhrase) {
        this.statusPhrase = statusPhrase;
    }

    public Map<String,String> getHeaders() {
        return headers;
    }

    public void putHeader(String key,String value) {
        this.headers.put(key,value);
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }
}
