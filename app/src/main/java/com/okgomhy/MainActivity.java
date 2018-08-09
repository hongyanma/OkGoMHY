package com.okgomhy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lzy.okgo.model.HttpParams;
import com.okgomhy.BasePositionUtils.BaseDataBean;
import com.okgomhy.BasePositionUtils.GetIdUtil;
import com.okgomhy.BasePositionUtils.LaLongUtitls;
import com.okgomhy.BasePositionUtils.LagLat;
import com.okgomhy.OkGointerface.GetRealPosition;
import com.okgomhy.okgoHttpRequest.OkGoCallBack;
import com.okgomhy.okgoHttpRequest.OkGoHttpRequrstPresent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private String cid = "";
    private String lac = "";
    private String mnc = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    //    GetIdUtil getIdUtil = new GetIdUtil(this);
      //  List<BaseDataBean> assa = getIdUtil.getBaseData(this);
        LagLat lagLat=new LagLat("121.581337","29.863742");
        LagLat lagLat2=new LagLat("121.578786","29.864890");
        List<LagLat> stringsa=new ArrayList<>();
        stringsa.add(lagLat);
        stringsa.add(lagLat2);
        LagLat lagLata=  GetRealPosition.GetPosition(stringsa);


        findViewById(R.id.tv_name).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpParams httpParams = new HttpParams();
                httpParams.put("cid", cid.substring(0, cid.length() - 1));
                httpParams.put("lac", lac.substring(0, lac.length() - 1));
                httpParams.put("mnc", mnc.substring(0, mnc.length() - 1));
                OkGoHttpRequrstPresent.getInstance().get("http://127.0.0.1:8999/zlapp/api/baseStation/queryBasiccoordinate", httpParams, new OkGoCallBack<PositionEntity>() {
                    @Override
                    public void success(PositionEntity positionEntity) {
                        if (positionEntity.getReturncode().equals("200")) {
                            LaLongUtitls laLongUtitls=new LaLongUtitls(positionEntity.getData().getLongitude(),positionEntity.getData().getLatitude());
                            List<LagLat> strings=  laLongUtitls.GetLalong();
                            LagLat lagLat=  GetRealPosition.GetPosition(strings);
                        }

                    }

                    @Override
                    public void OnFailse(int code, Exception e) {

                    }
                });
            }
        });

    }
}
