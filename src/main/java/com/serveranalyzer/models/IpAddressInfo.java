package com.serveranalyzer.models;

public class IpAddressInfo {
    private String Ipv4Address;
    private String Ipv6Address;

    public String getIpv4Address() {
        return Ipv4Address;
    }

    public void setIpv4Address(String ipv4Address) {
        Ipv4Address = ipv4Address;
    }

    public String getIpv6Address() {
        return Ipv6Address;
    }

    public void setIpv6Address(String ipv6Address) {
        Ipv6Address = ipv6Address;
    }
}
