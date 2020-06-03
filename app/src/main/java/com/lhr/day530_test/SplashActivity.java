package com.lhr.day530_test;

import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;

import com.lhr.data.BaseInfo;
import com.lhr.data.MainAdEntity;
import com.lhr.data.SpecialtyChooseEntity;
import com.lhr.day530_test.base.BaseSplashActivity;
import com.lhr.frame.ApiConfig;
import com.lhr.frame.CommonModule;
import com.lhr.frame.FrameApplication;
import com.lhr.frame.constants.ConstantKey2;
import com.lhr.frame.secret.SystemUtils;
import com.lhr.utils.newAdd.GlideUtil;
import com.lhr.utils.newAdd.SharedPrefrenceUtils;

import java.util.concurrent.TimeUnit;

import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SplashActivity extends BaseSplashActivity {
    private BaseInfo<MainAdEntity> mInfo;
    private Disposable mSubscribe;
    private SpecialtyChooseEntity.DataBean mSelectedInfo;
    private FrameApplication mApplication;

    @Override
    protected void initData() {
        mSelectedInfo = SharedPrefrenceUtils.getObject(this, ConstantKey2.SUBJECT_SELECT);
        String specialtyId = "";
        if (mSelectedInfo != null && !TextUtils.isEmpty(mSelectedInfo.getSpecialty_id())) {
            mApplication.setSelectedInfo(mSelectedInfo);
            specialtyId = mSelectedInfo.getSpecialty_id();
        }
        Point realSize = SystemUtils.getRealSize(this);
        presenter.getData(ApiConfig.ADVERT, specialtyId, realSize.x, realSize.y);
    }

    @Override
    protected void initView() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initDevice();
    }

    @Override
    protected CommonModule initModule() {
        return new LauchModel();
    }

    @Override
    protected void netSuccess(int whichApi,  Object[] pV) {
        mInfo = (BaseInfo<MainAdEntity>) pV[0];
        GlideUtil.loadImage(advertImage, mInfo.result.getInfo_url());
        timeView.setVisibility(View.VISIBLE);
        goTime();
    }

    private int preTime = 4;
    private void goTime() {
        mSubscribe = Observable.interval(1, TimeUnit.SECONDS).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        if (preTime - aLong > 0) timeView.setText(preTime - aLong + "s");
                        else jump();
                    }
                });
    }

    private void jump() {
        mSubscribe.dispose();
        startActivity(new Intent(this, mSelectedInfo != null && !TextUtils.isEmpty(mSelectedInfo.getSpecialty_id()) ? mApplication.isLogin() ? HomeActivity.class : LoginActivity.class : SubjectActivity.class));
        finish();
    }

    @Override
    public void onSuccess(int whichApi, Object[] pV) {
        if (mSubscribe != null && !mSubscribe.isDisposed()) mSubscribe.dispose();
    }

    @OnClick({R.id.advert_image, R.id.time_view})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.advert_image:
                if (mInfo != null) {
//                    mInfo.result.getJump_url();
                }
                break;
            case R.id.time_view:
                jump();
                break;
        }
    }



    @Override
    public void onFailed(int whichApi, Throwable throwable) {

    }
}