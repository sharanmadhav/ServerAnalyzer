package com.serveranalyzer.models;

public class UrlResponseInfo {

    private String averageResponseSize;
    private String performanceSpeed;
    private String requestRate;
    private String ResponseTime;

    public String getAverageResponseSize() {
        return averageResponseSize;
    }

    public void setAverageResponseSize(String averageResponseSize) {
        this.averageResponseSize = averageResponseSize;
    }

    public String getPerformanceSpeed() {
        return performanceSpeed;
    }

    public void setPerformanceSpeed(String performanceSpeed) {
        this.performanceSpeed = performanceSpeed;
    }

    public String getRequestRate() {
        return requestRate;
    }

    public void setRequestRate(String requestRate) {
        this.requestRate = requestRate;
    }

    public String getResponseTime() {
        return ResponseTime;
    }

    public void setResponseTime(String responseTime) {
        ResponseTime = responseTime;
    }
}
