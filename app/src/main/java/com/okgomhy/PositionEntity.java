package com.okgomhy;

/**
 * Created by Administrator on 2018/7/17.
 */

public class PositionEntity {

    /**
     * returncode : 200
     * returnstr : 成功
     * data : {"longitude":"","latitude":""}
     */

    private String returncode;
    private String returnstr;
    private DataBean data;

    public String getReturncode() {
        return returncode;
    }

    public void setReturncode(String returncode) {
        this.returncode = returncode;
    }

    public String getReturnstr() {
        return returnstr;
    }

    public void setReturnstr(String returnstr) {
        this.returnstr = returnstr;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * longitude :
         * latitude :
         */

        private String longitude;
        private String latitude;

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }
    }
}
