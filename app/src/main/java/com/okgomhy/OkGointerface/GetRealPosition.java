package com.okgomhy.OkGointerface;

import com.okgomhy.BasePositionUtils.LagLat;

import java.util.List;

/**
 * Created by Administrator on 2018/8/9.
 */

public class GetRealPosition {

    private volatile static GetRealPosition instance;

    public GetRealPosition(){}
    private static GetRealPosition getInstance(){
        if (instance==null){
            synchronized (GetRealPosition.class){
                if(instance==null){
                    instance=new GetRealPosition();
                }
            }
        }
        return instance;

    }

   public static LagLat GetPosition(List<LagLat>lagLats){
        int index=lagLats.size();
        if(index==1){
            return  lagLats.get(0);
        }else if(index==2){
            return  getCenterPoint400(lagLats);
        }
         return null;
    }


    public static LagLat getCenterPoint(List<LagLat> geoCoordinateList) {
        int total = geoCoordinateList.size();
        double X = 0, Y = 0, Z = 0;
        for (LagLat g : geoCoordinateList) {
            double lat, lon, x, y, z;
            lat = Double.parseDouble(g.getLat()) * Math.PI / 180;
            lon = Double.parseDouble(g.getLog()) * Math.PI / 180;
            x = Math.cos(lat) * Math.cos(lon);
            y = Math.cos(lat) * Math.sin(lon);
            z = Math.sin(lat);
            X += x;
            Y += y;
            Z += z;
        }

        X = X / total;
        Y = Y / total;
        Z = Z / total;
        double Lon = Math.atan2(Y, X);
        double Hyp = Math.sqrt(X * X + Y * Y);
        double Lat = Math.atan2(Z, Hyp);
        return new LagLat(Double.toString(Lat * 180 / Math.PI), Double.toString(Lon * 180 / Math.PI));
    }

    /**
     * 根据输入的地点坐标计算中心点（适用于400km以下的场合）
     * @param geoCoordinateList
     * @return
     */
    public static LagLat getCenterPoint400(List<LagLat> geoCoordinateList) {
        // 以下为简化方法（400km以内）
        int total = geoCoordinateList.size();
        double lat = 0, lon = 0;
        for (LagLat g : geoCoordinateList) {
            lat += Double.parseDouble(g.getLat()) * Math.PI / 180;
            lon +=Double.parseDouble(g.getLog() ) * Math.PI / 180;
        }
        lat /= total;
        lon /= total;

        return new LagLat(Double.toString(lat * 180 / Math.PI),Double.toString(lon * 180 / Math.PI) );
    }


}
