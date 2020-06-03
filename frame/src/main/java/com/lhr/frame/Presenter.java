package com.lhr.frame;

import android.app.Activity;
import android.util.Log;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class Presenter<V extends CommonView ,M extends CommonModule> implements CommonPresenter{
    private SoftReference<V> MView;
    private SoftReference<M> Mmodule;
    private final List<Disposable> objects;

    public Presenter(V view,M module) {
        MView=new SoftReference<>(view);
        Mmodule=new SoftReference<>(module);
        objects = new ArrayList<>();
    }

    @Override
    public void getData(int whichApi, Object... pP) {
        if(Mmodule!=null&&Mmodule.get()!=null) Mmodule.get().getData(this, whichApi, pP);
    }

    @Override
    public void addDisposeable(Disposable disposable) {
        objects.add(disposable);
    }




    public void clear(){

        for (int i = 0; i < objects.size(); i++) {
            if(objects.get(i)!=null){
                objects.get(i).dispose();
            }
        }

        if(MView!=null){
            MView.clear();
            MView=null;
        }

        if(Mmodule!=null){
            Mmodule.clear();
            Mmodule=null;
        }
    }

    @Override
    public void onSuccess(int whichApi, Object[] pV) {

        if(MView!=null&&MView.get()!=null) MView.get().onSuccess(whichApi,pV);

    }

    @Override
    public void onFailed(int whichApi, Throwable throwable) {
        if(MView!=null&&MView.get()!=null) MView.get().onFailed(whichApi,throwable);
    }
}
