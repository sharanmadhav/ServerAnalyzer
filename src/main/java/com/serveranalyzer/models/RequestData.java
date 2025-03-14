package com.serveranalyzer.models;

public class RequestData {
    private final long timestamp;
    private final String method;
    private final String uri;
    private final int requestSize;
    private final int responseSize;
    private final String protocol;

    public RequestData(String method, String uri, int requestSize, int responseSize, String protocol) {
        this.timestamp = System.currentTimeMillis();
        this.method = method;
        this.uri = uri;
        this.requestSize = requestSize;
        this.responseSize = responseSize;
        this.protocol = protocol;
    }

    public String toJson() {
        return String.format("{\"timestamp\":\"%d\",\"method\":\"%s\",\"uri\":\"%s\",\"requestSize\":%d,\"responseSize\":%d,\"protocol\":\"%s\"}", timestamp, method, uri, requestSize, responseSize, protocol);
    }
} 
