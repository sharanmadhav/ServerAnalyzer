package com.serveranalyzer.models;

import java.util.ArrayList;

public class MemoryInfo {
    private String totalMemory;
    private String speed;
    private String type;
    private String channelCount;
    private String installedModuleCount;
    private ArrayList<MemoryStats> MemoryStatsList = new ArrayList<>();
    public String getTotalMemory() {
        return totalMemory;
    }

    public void setTotalMemory(String totalMemory) {
        this.totalMemory = totalMemory;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getChannelCount() {
        return channelCount;
    }

    public void setChannelCount(String channelCount) {
        this.channelCount = channelCount;
    }

    public String getInstalledModuleCount() {
        return installedModuleCount;
    }

    public void setInstalledModuleCount(String installedModuleCount) {
        this.installedModuleCount = installedModuleCount;
    }

    public ArrayList<MemoryStats> getMemoryStatsList() {
        return MemoryStatsList;
    }

    public void setMemoryStatsList(ArrayList<MemoryStats> memoryStatsList) {
        MemoryStatsList = memoryStatsList;
    }
}
