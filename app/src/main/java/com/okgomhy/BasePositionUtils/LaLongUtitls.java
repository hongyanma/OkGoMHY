package com.okgomhy.BasePositionUtils;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/8/6.
 */

public class LaLongUtitls {

    private List<LagLat> baseDataBeans=new ArrayList<>();

    public LaLongUtitls(String Lang, String Long) {
        for (int i = 0; i < Lang.split(",").length; i++) {
            LagLat coordinate=new LagLat();
            coordinate.setLat(Lang.split(",")[i]);
            coordinate.setLog(Long.split(",")[i]);
            baseDataBeans.add(coordinate);
        }
    }

    public List<LagLat> GetLalong() { //拿到了经纬度集合
     return baseDataBeans;
    }
}
