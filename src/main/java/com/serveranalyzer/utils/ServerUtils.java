package com.serveranalyzer.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class ServerUtils {
    public static final String secureURLPrefix = "https://";
    public static final String unSecureURLPrefix = "http://";
    public static Runtime runtime = Runtime.getRuntime();
    public static long totalMemory = runtime.totalMemory();
    public static String ipV4Address = getIPv4Address();
    public static String ipV6Address = getIPv6Address();

    private static String getIPv4Address() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface ni = interfaces.nextElement();
                if (!ni.isUp() || ni.isLoopback()) {
                    continue;
                }
                Enumeration<InetAddress> addresses = ni.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();
                    if (addr.getAddress().length == 4) { // IPv4 addresses are 4 bytes
                        return addr.getHostAddress(); // e.g., "192.168.1.100"
                    }
                }
            }
            InetAddress localhost = InetAddress.getLocalHost();
            if (localhost.getAddress().length == 4) {
                return localhost.getHostAddress();
            }
            return "NA";
        } catch (SocketException | UnknownHostException e) {
            return "unknown-ipv4";
        }
    }
    private static String getIPv6Address() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface ni = interfaces.nextElement();
                if (!ni.isUp() || ni.isLoopback()) {
                    continue;
                }
                Enumeration<InetAddress> addresses = ni.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();
                    if (addr.getAddress().length == 16) {
                        return addr.getHostAddress();
                    }
                }
            }
            // Fallback: Try localhost IPv6
            InetAddress localhost = InetAddress.getLocalHost();
            if (localhost.getAddress().length == 16) {
                return localhost.getHostAddress();
            }
            return "NA";
        } catch (SocketException | UnknownHostException e) {
            return "unknown-ipv6";
        }
    }
}
