package com.serveranalyzer.models;

public class StorageInfo {

    private String diskPresent;
    private String diskModel;
    private String diskVendor;
    private String diskSize;
    private String interfaceType;
    private String partitionLayout;
    private String readSpeed;
    private String writeSpeed;
    private String smartStatus;

    public String getDiskPresent() {
        return diskPresent;
    }

    public void setDiskPresent(String diskPresent) {
        this.diskPresent = diskPresent;
    }

    public String getDiskModel() {
        return diskModel;
    }

    public void setDiskModel(String diskModel) {
        this.diskModel = diskModel;
    }

    public String getDiskVendor() {
        return diskVendor;
    }

    public void setDiskVendor(String diskVendor) {
        this.diskVendor = diskVendor;
    }

    public String getDiskSize() {
        return diskSize;
    }

    public void setDiskSize(String diskSize) {
        this.diskSize = diskSize;
    }

    public String getInterfaceType() {
        return interfaceType;
    }

    public void setInterfaceType(String interfaceType) {
        this.interfaceType = interfaceType;
    }

    public String getPartitionLayout() {
        return partitionLayout;
    }

    public void setPartitionLayout(String partitionLayout) {
        this.partitionLayout = partitionLayout;
    }

    public String getReadSpeed() {
        return readSpeed;
    }

    public void setReadSpeed(String readSpeed) {
        this.readSpeed = readSpeed;
    }

    public String getWriteSpeed() {
        return writeSpeed;
    }

    public void setWriteSpeed(String writeSpeed) {
        this.writeSpeed = writeSpeed;
    }

    public String getSmartStatus() {
        return smartStatus;
    }

    public void setSmartStatus(String smartStatus) {
        this.smartStatus = smartStatus;
    }


}
