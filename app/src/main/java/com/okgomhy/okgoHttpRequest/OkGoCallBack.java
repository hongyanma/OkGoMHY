package com.okgomhy.okgoHttpRequest;

import com.google.gson.Gson;
import com.okgomhy.OkGointerface.ICallBack;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by Administrator on 2018/8/3.
 */

public abstract class OkGoCallBack<T> implements ICallBack {
    @Override
    public void Success(String result) {
        Class<? extends T> aClass = getClassType(this);
        T t=  new Gson().fromJson(result, aClass);
      success(t);
    }

    public Class<? extends T> getClassType(Object object) {
        Type classType = object.getClass().getGenericSuperclass();
        return  (Class<? extends T>)((ParameterizedType)classType).getActualTypeArguments()[0];
    }
    public abstract void  success(T t);
}
