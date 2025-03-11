package com.serveranalyzer.models;

public class RequestData {
    private final long timestamp;
    private final String method;
    private final String uri;
    private final int requestSize;
    private final int responseSize;

    public RequestData(String method, String uri, int requestSize, int responseSize) {
        this.timestamp = System.currentTimeMillis();
        this.method = method;
        this.uri = uri;
        this.requestSize = requestSize;
        this.responseSize = responseSize;
    }

    public String toJson() {
        return String.format(
            "{\"timestamp\":\"%d\",\"method\":\"%s\",\"uri\":\"%s\",\"requestSize\":%d,\"responseSize\":%d}",
            timestamp,
            method,
            uri,
            requestSize,
            responseSize
        );
    }
} 