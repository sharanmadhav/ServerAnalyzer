package com.serveranalyzer.models;

import com.serveranalyzer.utils.ResponseSizeWrapper;
import com.serveranalyzer.utils.ServerUtils;

import javax.servlet.http.HttpServletRequest;

public class RequestData {
    private final long timestamp;
    private final String method;
    private final String uri;
    private final int requestSize;
    private final int responseSize;
    private final String protocol;
    private final String contentType;
    private final String schema;
    private final String serverName;
    private final String localAddress;
    private final String localName;
    private final String headers;
    private final String locale;
    private final String userAgent;
    private final String parameters;
    private final String queryString;
    private final long totalMemory = ServerUtils.totalMemory;
    private final long freeMemory = ServerUtils.runtime.freeMemory();;
    private final long usedMemory = totalMemory - freeMemory;

    public RequestData(HttpServletRequest servletRequest,ResponseSizeWrapper responseWrapper){
        this.timestamp = System.currentTimeMillis();
        this.method = servletRequest.getMethod();
        this.uri = servletRequest.getRequestURI();
        this.requestSize = servletRequest.getContentLength();
        this.responseSize = responseWrapper.getDataSize();
        this.protocol = servletRequest.getProtocol();
        this.schema = servletRequest.getScheme();
        this.serverName = servletRequest.getServerName();
        this.localAddress = servletRequest.getLocalAddr();
        this.localName = servletRequest.getLocalName();
        this.contentType = servletRequest.getContentType();
        this.locale = servletRequest.getLocale().toString();
        this.userAgent = servletRequest.getHeader("user-agent");
        this.headers = servletRequest.getHeaderNames().toString();
        this.parameters = servletRequest.getParameterNames().toString();
        this.queryString = servletRequest.getQueryString();
    }

    public String toJson() {
        return new StringBuilder()
                .append("{")
                .append("\"timestamp\":\"").append(timestamp).append("\",")
                .append("\"method\":\"").append(method).append("\",")
                .append("\"uri\":\"").append(uri).append("\",")
                .append("\"requestSize\":").append(requestSize).append(",")
                .append("\"responseSize\":").append(responseSize).append(",")
                .append("\"protocol\":\"").append(protocol).append("\",")
                .append("\"schema\":\"").append(schema).append("\",")
                .append("\"serverName\":\"").append(serverName).append("\",")
                .append("\"localAddress\":\"").append(localAddress).append("\",")
                .append("\"localName\":\"").append(localName).append("\",")
                .append("\"contentType\":\"").append(contentType).append("\",")
                .append("\"locale\":\"").append(locale).append("\",")
                .append("\"userAgent\":\"").append(userAgent).append("\",")
                .append("\"headers\":\"").append(headers).append("\"")
                .append("\"parameters\":\"").append(parameters).append("\",")
                .append("\"queryString\":\"").append(queryString).append("\",")
                .append("\"totalMemory\":\"").append(totalMemory).append("\",")
                .append("\"freeMemory\":\"").append(freeMemory).append("\",")
                .append("\"usedMemory\":\"").append(usedMemory).append("\",")
                .append("}")
                .toString();
    }
} 
