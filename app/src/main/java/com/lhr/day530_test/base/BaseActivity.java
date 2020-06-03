package com.lhr.day530_test.base;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


import com.lhr.day530_test.DataListener;
import com.lhr.frame.LoadTypeConfig;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void initRecyclerLayout(RecyclerView recyclerView, SmartRefreshLayout smartRefreshLayout, final DataListener dataListener){
        if(recyclerView!=null)recyclerView.setLayoutManager(new LinearLayoutManager(this));
        if(smartRefreshLayout!=null&&dataListener!=null){
            smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
                @Override
                public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                    dataListener.State(LoadTypeConfig.MORE);
                }

                @Override
                public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                    dataListener.State(LoadTypeConfig.REFRESH);
                }
            });
        }
    }


    public void showLog(String msg){
        Log.i("Tag", msg);
    }

}