package com.okgomhy.BasePositionUtils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellSignalStrengthCdma;
import android.telephony.CellSignalStrengthGsm;
import android.telephony.CellSignalStrengthLte;
import android.telephony.NeighboringCellInfo;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/7/2.
 */

public class GetIdUtil {

    private Context mContext;
    TelephonyManager mTelephonyManager;

    private String NetType;
    private String mnc="";
    public GetIdUtil(Context context) {
        this.mContext = context;
    }

    @SuppressLint("NewApi")
    public List<BaseDataBean> getBaseData(final Context mContext) {

        List<BaseDataBean> list = new ArrayList<BaseDataBean>();
        List<BaseDataBean> allCID = new ArrayList<BaseDataBean>();
        BaseDataBean beans = new BaseDataBean();
        TelephonyManager telephonyManager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);

        //  MCC国家码  MNC网号  中国国家码460 00代表移动网号 01代表电信
        String operator = telephonyManager.getNetworkOperator();
        beans.setMcc(operator.substring(0, 3));
        beans.setMnc(operator.substring(3));
        mnc=operator.substring(3);
        if (telephonyManager.getPhoneType() == TelephonyManager.PHONE_TYPE_CDMA) {// 这是电信的
            @SuppressLint("MissingPermission") CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) telephonyManager
                    .getCellLocation();
            beans.setCell_id(cdmaCellLocation.getBaseStationId() + "");
            beans.setLac(cdmaCellLocation.getNetworkId() + "");
        } else {// 这是移动和联通的
            @SuppressLint("MissingPermission")
            GsmCellLocation gsmCellLocation = (GsmCellLocation) telephonyManager.getCellLocation();

            beans.setCell_id(gsmCellLocation.getCid() + "");
            beans.setLac(gsmCellLocation.getLac() + "");
        }
        //因为获取不到信号强度 所以默认设个强度值
        beans.setSignalstrength("0");
        list.add(beans);
        allCID.add(beans);
        if (Build.VERSION.SDK_INT < 17) {//低版的android系统使用getNeighboringCellInfo方法
            @SuppressLint("MissingPermission") List<NeighboringCellInfo> infos = mTelephonyManager.getNeighboringCellInfo();
            if (infos == null) {
                Log.i("AlexLocation", "手机型号不支持基站定位1");
                return list;
            }
            if (infos.size() == 0) {
                return list;//附近没有基站
            }
            BaseDataBean bean = new BaseDataBean();
            for (NeighboringCellInfo info1 : infos) { // 根据邻区总数进行循环
                bean.setSignalstrength(info1.getRssi() + "");
                bean.setCell_id(info1.getCid() + "");
                bean.setLac(info1.getLac() + "");
                bean.setMcc("0");
                bean.setMnc(mnc);
                list.add(bean);
            }
        } else {
            @SuppressLint("MissingPermission")
            List<CellInfo> infoLists = telephonyManager.getAllCellInfo();
            if (infoLists.size() != 0) {
                for (CellInfo info : infoLists) {
                    /** 1、GSM是通用的移动联通电信2G的基站。
                     2、CDMA是3G的基站。
                     3、LTE，则证明支持4G的基站。*/
                    BaseDataBean bean = new BaseDataBean();
                    if (info.toString().contains("CellInfoLte")) {
                        NetType="lte";
                        CellInfoLte cellInfoLte = (CellInfoLte) info;
                        CellIdentityLte cellIdentityLte = cellInfoLte.getCellIdentity();
                        CellSignalStrengthLte cellSignalStrengthLte = cellInfoLte.getCellSignalStrength();
                        bean.setSignalstrength(cellSignalStrengthLte.getDbm() + "");
                        bean.setCell_id(cellIdentityLte.getCi() + "");
                        bean.setLac(cellIdentityLte.getTac() + "");
                        bean.setMcc(cellIdentityLte.getMcc() + "");
                        bean.setMnc(mnc+ "");
                    } else if (info.toString().contains("CellInfoGsm")) {
                        NetType="Gsm";
                        CellInfoGsm cellInfoGsm = (CellInfoGsm) info;
                        CellIdentityGsm cellIdentityGsm = cellInfoGsm
                                .getCellIdentity();
                        CellSignalStrengthGsm cellSignalStrengthGsm = cellInfoGsm
                                .getCellSignalStrength();
                        bean.setSignalstrength(cellSignalStrengthGsm.getDbm() + "");
                        bean.setCell_id(cellIdentityGsm.getCid() + "");
                        bean.setLac(cellIdentityGsm.getLac() + "");
                        bean.setMcc(cellIdentityGsm.getMcc() + "");
                        bean.setMnc(mnc + "");
                    } else if (info.toString().contains("CellInfoCdma")) {
                        NetType="cdma";
                        CellInfoCdma cellInfoCdma = (CellInfoCdma) info;
                        CellIdentityCdma cellIdentityCdma = cellInfoCdma
                                .getCellIdentity();


                        CellSignalStrengthCdma cellSignalStrengthCdma = cellInfoCdma
                                .getCellSignalStrength();
                        bean.setCell_id(cellIdentityCdma.getBasestationId() + "");
                        bean.setSignalstrength(cellSignalStrengthCdma.getCdmaDbm()
                                + "");
                        /**因为待会我要把这个list转成gson，所以这个对象的所有属性我都赋一下值，不必理会这里*/
                        bean.setLac("0");
                        bean.setMcc("0");
                        bean.setMnc(mnc);
                    }
                    allCID.add(bean);
                    int j=0;
                    for(int i=0;i<list.size();i++){
                        if(!list.get(i).getCell_id().equals(bean.getCell_id())){
                             j++;
                        }
                        if(j==list.size()){
                            list.add(bean);
                        }
                    }
                }
            }
        }

        return list;
    }

public String getNetType(){
        return NetType;
}
}
