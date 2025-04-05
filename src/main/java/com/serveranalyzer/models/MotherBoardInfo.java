package com.serveranalyzer.models;

public class MotherBoardInfo {

    private String manufacturer;
    private String model;
    private String SerialNumber;
    private BootLoaderInfo BootLoader;
    private String baseBoardVersion;

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerialNumber() {
        return SerialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        SerialNumber = serialNumber;
    }

    public BootLoaderInfo getBootLoader() {
        return BootLoader;
    }

    public void setBootLoader(BootLoaderInfo bootLoader) {
        BootLoader = bootLoader;
    }

    public String getBaseBoardVersion() {
        return baseBoardVersion;
    }

    public void setBaseBoardVersion(String baseBoardVersion) {
        this.baseBoardVersion = baseBoardVersion;
    }
}
