package com.serveranalyzer.models;

public class NetworkInfo {

    private String NICModel;
    private String macAddress;
    private IpAddressInfo ipAddress;
    private String InterfaceStatus;

    public String getNICModel() {
        return NICModel;
    }

    public void setNICModel(String NICModel) {
        this.NICModel = NICModel;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public IpAddressInfo getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(IpAddressInfo ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getInterfaceStatus() {
        return InterfaceStatus;
    }

    public void setInterfaceStatus(String interfaceStatus) {
        InterfaceStatus = interfaceStatus;
    }
}
