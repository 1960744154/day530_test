package com.lhr.day530_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.lhr.data.BaseInfo;
import com.lhr.data.SpecialtyChooseEntity;
import com.lhr.day530_test.adapter.SubjectAdapter;
import com.lhr.day530_test.base.BaseMvpActivity;
import com.lhr.frame.ApiConfig;
import com.lhr.frame.FrameApplication;
import com.lhr.frame.constants.ConstantKey2;
import com.lhr.utils.newAdd.SharedPrefrenceUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SubjectActivity extends BaseMvpActivity<LauchModel> {

    private List<SpecialtyChooseEntity> mListData = new ArrayList<>();
    @BindView(R.id.title_content)
    TextView titleContent;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private SubjectAdapter mAdapter;
    private FrameApplication mApplication;

    @Override
    protected void onStop() {
        super.onStop();
        SharedPrefrenceUtils.putObject(this, ConstantKey2.SUBJECT_SELECT,mApplication.getSelectedInfo());
    }



    @Override
    protected void initData() {
        if (SharedPrefrenceUtils.getSerializableList(this, ConstantKey2.SUBJECT_LIST) != null) {
            mListData.addAll(SharedPrefrenceUtils.<SpecialtyChooseEntity>getSerializableList(this, ConstantKey2.SUBJECT_LIST));
            mAdapter.notifyDataSetChanged();
        } else
            presenter.getData(ApiConfig.SUBJECT);
    }

    @Override
    protected void initView() {
        titleContent.setText(getString(R.string.select_subject));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new SubjectAdapter(mListData,this);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected LauchModel initModule() {
        return  new LauchModel();
    }

    @Override
    protected int getLayout() {
         return R.layout.activity_subject;
    }

    @Override
    protected void netSuccess(int whichApi, Object[] pV) {
        switch (whichApi) {
            case ApiConfig.SUBJECT:
                BaseInfo<List<SpecialtyChooseEntity>> info = (BaseInfo<List<SpecialtyChooseEntity>>) pV[0];
                mListData.addAll(info.result);
                mAdapter.notifyDataSetChanged();
                SharedPrefrenceUtils.putSerializableList(this,ConstantKey2.SUBJECT_LIST,mListData);
                break;
        }
    }







    @OnClick(R.id.back_image)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onSuccess(int whichApi, Object[] pV) {

    }

    @Override
    public void onFailed(int whichApi, Throwable throwable) {

    }
}
