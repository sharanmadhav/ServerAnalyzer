package com.serveranalyzer.models;

public class CPUInfo {
    private String modelName;
    private String architecture;
    private String physicalCores;
    private String logicalCores;
    private String clockSpeed;
    private String vendor;
    private String cacheSize;

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getArchitecture() {
        return architecture;
    }

    public void setArchitecture(String architecture) {
        this.architecture = architecture;
    }

    public String getPhysicalCores() {
        return physicalCores;
    }

    public void setPhysicalCores(String physicalCores) {
        this.physicalCores = physicalCores;
    }

    public String getLogicalCores() {
        return logicalCores;
    }

    public void setLogicalCores(String logicalCores) {
        this.logicalCores = logicalCores;
    }

    public String getClockSpeed() {
        return clockSpeed;
    }

    public void setClockSpeed(String clockSpeed) {
        this.clockSpeed = clockSpeed;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getCacheSize() {
        return cacheSize;
    }

    public void setCacheSize(String cacheSize) {
        this.cacheSize = cacheSize;
    }
}
