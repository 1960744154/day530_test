package com.lhr.frame;



import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetWork {

    public static NetWork netWork;

    private NetWork() {
    }

    public static NetWork getInstance(){
        if(netWork==null){
            synchronized (NetWork.class){
                if(netWork==null){
                    netWork=new NetWork();
                }
            }
        }
        return netWork;
    }

    public <T>  ApiService  getApiService(T...t){
        String baseUrl=ServiceConfig.BASEURL;
        if(t.length!=0&&t!=null){
            baseUrl= (String) t[0];
        }
      return   new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(ApiService.class);
    }


    public <T,O> void getRetrofit(Observable<T> localTestInfo, final CommonPresenter pPresenter, final int whichApi, final O... o){

        localTestInfo.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObservable() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        super.onSubscribe(d);
                        pPresenter.addDisposeable(d);
                    }

                    @Override
                    public void Success(Object value) {
                        pPresenter.onSuccess(whichApi,value,o);
                    }



                    @Override
                    public void onFailed(Throwable e) {
                        pPresenter.onFailed(whichApi,e);
                    }

                });
    }
//
//    public <T, O> void netWork(Observable<T> localTestInfo, final CommonPresenter pPresenter, final int whichApi, O... o) {
//        localTestInfo.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe();
//    }

}
