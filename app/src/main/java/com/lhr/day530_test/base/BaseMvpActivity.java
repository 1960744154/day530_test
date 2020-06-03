package com.lhr.day530_test.base;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.lhr.frame.CommonModule;
import com.lhr.frame.CommonView;
import com.lhr.frame.Presenter;


public abstract   class BaseMvpActivity<M extends CommonModule> extends BaseActivity implements CommonView {

        M module;
    protected Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        module=initModule();
        presenter = new Presenter(this, module);

        initView();
        initData();
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract M initModule();

    protected abstract int getLayout();

    protected abstract void netSuccess(int whichApi, Object[] pV);

    protected  void netFailed(int whichApi, Throwable throwable){

    }

    @Override
    public void onSuccess(int whichApi, Object[] pV) {

       netSuccess(whichApi,pV);
    }

    @Override
    public void onFailed(int whichApi, Throwable throwable) {
        showLog(whichApi+"-error-content"+throwable==null|| TextUtils.isEmpty(throwable.getMessage())?"不明错误类型":throwable.getMessage());
        netFailed(whichApi,throwable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.clear();
    }


}
