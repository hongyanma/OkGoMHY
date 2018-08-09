package com.okgomhy;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.okgomhy.OkGointerface.HttpRequest;
import com.okgomhy.OkGointerface.ICallBack;

/**
 * Created by Administrator on 2018/8/3.
 */

public class OkGoUtils implements HttpRequest {



    @Override
    public void get(String url, HttpParams httpParams, final ICallBack okGoCallBack) {
            OkGo.<String>get(url)
                    .tag(this)
                    .params(httpParams)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            okGoCallBack.Success(response.body());
                        }
                        @Override
                        public void onError(Response<String> response) {
                            okGoCallBack.OnFailse(response.code(), (Exception) response.getException());
                        }
                    });
    }

    @Override
    public void Post(String url, HttpParams httpParams, final ICallBack okGoCallBack) {
        OkGo.<String>post(url)
                .tag(this)
                .params(httpParams)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        okGoCallBack.Success(response.body());
                    }
                    @Override
                    public void onError(Response<String> response) {
                        okGoCallBack.OnFailse(response.code(), (Exception) response.getException());
                    }
                });
    }

}
