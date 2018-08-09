package com.okgomhy.OkGointerface;

import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;

/**
 * Created by Administrator on 2018/8/3.
 */
/*网络访问接口*/
public interface  HttpRequest {
    void get(String url, HttpParams httpParams,ICallBack okGoCallBack);
    void Post(String url, HttpParams httpParams,ICallBack okGoCallBack);
}
