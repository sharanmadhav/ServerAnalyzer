package com.serveranalyzer.models;

import com.serveranalyzer.utils.ResponseSizeWrapper;
import com.serveranalyzer.utils.ServerUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

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
        this.headers = getCSV(servletRequest.getHeaderNames());
        this.parameters = getCSV(servletRequest.getParameterNames());
        this.queryString = servletRequest.getQueryString();
    }
    private String getCSV(Enumeration<String> names){
        StringBuilder csv = new StringBuilder();
        boolean firstVal = true;
        while (names.hasMoreElements()) {
            String headerName = names.nextElement();

            if(firstVal){
                csv.append(headerName);
                firstVal = false;
            }
            else {
                csv.append(",").append(headerName);
            }
        }
        return csv.toString();
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
                .append("\"ipV4Address\":\"").append(ServerUtils.ipV4Address).append("\",")
                .append("\"ipV6Address\":\"").append(ServerUtils.ipV6Address).append("\",")
                .append("\"localName\":\"").append(localName).append("\",")
                .append("\"contentType\":\"").append(contentType).append("\",")
                .append("\"locale\":\"").append(locale).append("\",")
                .append("\"userAgent\":\"").append(userAgent).append("\",")
                .append("\"headers\":\"").append(headers).append("\",")
                .append("\"parameters\":\"").append(parameters).append("\",")
                .append("\"queryString\":\"").append(queryString).append("\",")
                .append("\"totalMemory\":\"").append(ServerUtils.totalMemory).append("\",")
                .append("\"freeMemory\":\"").append(ServerUtils.runtime.freeMemory()).append("\",")

                .append("\"usedMemory\":\"").append(ServerUtils.totalMemory - ServerUtils.runtime.freeMemory()).append("\"")
                .append("}")
                .toString();
    }
} 
