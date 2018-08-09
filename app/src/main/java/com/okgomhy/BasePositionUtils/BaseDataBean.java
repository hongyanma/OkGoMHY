package com.okgomhy.BasePositionUtils;

/**
 * Created by Administrator on 2018/7/2.
 */

public class BaseDataBean {
    private String cell_id;// cellid连接基站编码
    private String lac;// lac连接基站位置区域码
    private String mcc;// mcc MCC国家码
    private String mnc;// mnc MNC网号
    private String signalstrength;// signalstrength连接基站信号强度

    private String  NetType;

    public BaseDataBean(String cellid, String sing){
        this.cell_id=cellid;
        this.signalstrength=sing;
    }
    public BaseDataBean(){
    }

    public String getNetType() {
        return NetType;
    }

    public void setNetType(String netType) {
        NetType = netType;
    }

    public String getCell_id() {
        return cell_id;
    }
    public void setCell_id(String cell_id) {
        this.cell_id = cell_id;
    }
    public String getLac() {
        return lac;
    }
    public void setLac(String lac) {
        this.lac = lac;
    }
    public String getMcc() {
        return mcc;
    }
    public void setMcc(String mcc) {
        this.mcc = mcc;
    }
    public String getMnc() {
        return mnc;
    }
    public void setMnc(String mnc) {
        this.mnc = mnc;
    }
    public String getSignalstrength() {
        return signalstrength;
    }
    public void setSignalstrength(String signalstrength) {
        this.signalstrength = signalstrength;
    }
}
