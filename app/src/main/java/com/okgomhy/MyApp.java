package com.okgomhy;

import android.app.Application;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.SPCookieStore;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import java.util.logging.Level;
import okhttp3.OkHttpClient;


/**
 * @author ChayChan
 * @description: Application类
 * @date 2017/6/10  15:44
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //**************************************相关第三方SDK的初始化等操作*************************************************
        initOkGo();
    }

    private void initOkGo() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //使用sp保持cookie，如果cookie不过期，则一直有效
        builder.cookieJar(new CookieJarImpl(new SPCookieStore(this)));
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        //log颜色级别，决定了log在控制台显示的颜色
        loggingInterceptor.setColorLevel(Level.INFO);
        builder.addInterceptor(loggingInterceptor);
        // 拦截器

        OkGo.getInstance()
                .init(this)
                .setOkHttpClient(builder.build()) //设置OkHttpClient，不设置将使用默认的
                .setCacheMode(CacheMode.NO_CACHE)
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)
                .setRetryCount(3)
                ;


    }

}
