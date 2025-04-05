package com.serveranalyzer.models;

public class UrlMethodCount {

    private int GETMethodCount = 0;
    private int POSTMethodCount = 0;
    private int PUTMethodCount = 0;
    private int DELETEMethodCount = 0;

    public int getGETMethodCount() {
        return GETMethodCount;
    }

    public void setGETMethodCount(int GETMethodCount) {
        this.GETMethodCount = GETMethodCount;
    }

    public int getPOSTMethodCount() {
        return POSTMethodCount;
    }

    public void setPOSTMethodCount(int POSTMethodCount) {
        this.POSTMethodCount = POSTMethodCount;
    }

    public int getPUTMethodCount() {
        return PUTMethodCount;
    }

    public void setPUTMethodCount(int PUTMethodCount) {
        this.PUTMethodCount = PUTMethodCount;
    }

    public int getDELETEMethodCount() {
        return DELETEMethodCount;
    }

    public void setDELETEMethodCount(int DELETEMethodCount) {
        this.DELETEMethodCount = DELETEMethodCount;
    }
}
