package com.serveranalyzer.models;

public class BootLoaderInfo {

    private boolean isBIOS;
    private boolean isUEFI;
    private String version;

    public boolean isBIOS() {
        return isBIOS;
    }

    public void setBIOS(boolean BIOS) {
        isBIOS = BIOS;
    }

    public boolean isUEFI() {
        return isUEFI;
    }

    public void setUEFI(boolean UEFI) {
        isUEFI = UEFI;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
