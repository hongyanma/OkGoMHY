package com.okgomhy.OkGointerface;

/**
 * Created by Administrator on 2018/8/3.
 */

public interface ICallBack {
    void Success(String result);
    void OnFailse(int code,Exception e);
}
