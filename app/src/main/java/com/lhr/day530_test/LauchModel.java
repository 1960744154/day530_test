package com.lhr.day530_test;

import android.content.Context;
import android.text.TextUtils;

import com.lhr.frame.ApiConfig;
import com.lhr.frame.CommonModule;
import com.lhr.frame.CommonPresenter;
import com.lhr.frame.NetWork;
import com.lhr.frame.ParamHashMap;


/**
 * Created by 任小龙 on 2020/6/2.
 */
public class LauchModel implements CommonModule {
    private NetWork mManger = NetWork.getInstance();
    private Context mContext = Application1907.get07ApplicationContext();


    @Override
    public void getData(CommonPresenter commonPresenter, int whichApi, Object[] m) {
        switch (whichApi) {
            case ApiConfig.ADVERT:
                ParamHashMap map = new ParamHashMap().add("w", m[1]).add("h", m[2]).add("positions_id", "APP_QD_01").add("is_show", 0);
                if (!TextUtils.isEmpty((String) m[0])) map.add("specialty_id", m[0]);
                mManger.getRetrofit(mManger.getApiService(mContext.getString(R.string.ad_openapi)).getAdvert(map), commonPresenter, whichApi);

                break;
            case ApiConfig.SUBJECT:
                mManger.getRetrofit(mManger.getApiService(mContext.getString(R.string.edu_openapi)).getSubjectList(), commonPresenter, whichApi);
                break;
        }
    }
}
