package com.serveranalyzer.utils;

public class ServerUtils {
    public static final String secureURLPrefix = "https://";
    public static final String unSecureURLPrefix = "http://";
    public static Runtime runtime = Runtime.getRuntime();
    public static long totalMemory = runtime.totalMemory();
}
