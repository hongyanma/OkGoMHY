package com.okgomhy.okgoHttpRequest;

import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.okgomhy.OkGoUtils;
import com.okgomhy.OkGointerface.HttpRequest;
import com.okgomhy.OkGointerface.ICallBack;

/**
 * Created by Administrator on 2018/8/3.
 */

public class OkGoHttpRequrstPresent implements HttpRequest {

    private OkGoUtils httpRequest=new OkGoUtils();
    private static volatile OkGoHttpRequrstPresent instance;

    public OkGoHttpRequrstPresent(){

    }

    public static  OkGoHttpRequrstPresent getInstance(){
        if(null==instance){
            synchronized(OkGoHttpRequrstPresent.class){
                if(null==instance){
                    instance=new OkGoHttpRequrstPresent();
                }
            }
        }
        return instance;
    }
    @Override
    public void get(String url, HttpParams httpParams, ICallBack okGoCallBack) {
        httpRequest.get(url,httpParams,okGoCallBack);
    }

    @Override
    public void Post(String url, HttpParams httpParams, ICallBack okGoCallBack) {
        httpRequest.Post(url,httpParams,okGoCallBack);
    }
}
