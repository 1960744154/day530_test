package com.lhr.frame;



import com.lhr.data.BaseInfo;
import com.lhr.data.InfoBean;
import com.lhr.data.MainAdEntity;
import com.lhr.data.SpecialtyChooseEntity;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiService {


    @GET(".")
    Observable<InfoBean> getInfoBean(@QueryMap Map<String, String> map, @Query("page_id") int id);

    @GET("ad/getAd")
    Observable<BaseInfo<MainAdEntity>> getAdvert(@QueryMap Map<String,Object> pMap);

    @GET("lesson/getAllspecialty")
    Observable<BaseInfo<List<SpecialtyChooseEntity>>> getSubjectList();
}
