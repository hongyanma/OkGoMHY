package com.okgomhy.BasePositionUtils;

/**
 * Created by Administrator on 2018/8/6.
 */

public class LagLat {

    private String lat;
    private String log;
    public LagLat(){}
    public LagLat(String v, String v1) {
        this.lat= (String) v;
        this.log= (String) v1;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
}
