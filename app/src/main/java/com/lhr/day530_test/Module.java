package com.lhr.day530_test;



import com.lhr.data.InfoBean;
import com.lhr.frame.ApiConfig;
import com.lhr.frame.CommonModule;
import com.lhr.frame.CommonPresenter;
import com.lhr.frame.NetWork;

import java.util.Map;

public class Module  implements CommonModule {

    NetWork netWork=NetWork.getInstance();
    @Override
    public void getData(CommonPresenter commonPresenter, int whichApi, Object[] m) {

        if(ApiConfig.TEST==1){
            int o = (int) m[0];
            Map<String,String> o1 = (Map) m[1];
            int o2 = (int) m[2];

            netWork.getRetrofit(netWork.getApiService().getInfoBean(o1, o2),commonPresenter,whichApi, o);
        }

    }
}
