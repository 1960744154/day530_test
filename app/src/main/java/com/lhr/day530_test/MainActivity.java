package com.lhr.day530_test;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lhr.day530_test.Fragment.KeChengFragment;
import com.lhr.day530_test.Fragment.VIPFragment;
import com.lhr.day530_test.Fragment.WoDeFragment;
import com.lhr.day530_test.Fragment.ZhuYeFragment;
import com.lhr.day530_test.Fragment.ZiLiaoFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager main_viewpager;
    private TabLayout main_tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        main_viewpager = (ViewPager) findViewById(R.id.main_viewpager);
        main_tab = (TabLayout) findViewById(R.id.main_tab);
        List<Fragment> list=new ArrayList<>();
        list.add(new ZhuYeFragment());
        list.add(new KeChengFragment());
        list.add(new VIPFragment());
        list.add(new ZiLiaoFragment());
        list.add(new WoDeFragment());
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(),list);
        main_viewpager.setAdapter(fragmentAdapter);
        main_tab.setupWithViewPager(main_viewpager);
        View zhu = getTab(R.drawable.zhuye, "主页");
        View ke=getTab(R.drawable.kecheng,"课程");
        View vip=getTab(R.drawable.vippp,"VIP");
        View ziliao=getTab(R.drawable.ziliao,"资料");
        View wode=getTab(R.drawable.wode,"我的");
        main_tab.getTabAt(0).setCustomView(zhu);
        main_tab.getTabAt(1).setCustomView(ke);
        main_tab.getTabAt(2).setCustomView(vip);
        main_tab.getTabAt(3).setCustomView(ziliao);
        main_tab.getTabAt(4).setCustomView(wode);

    }
    public View getTab(int posi,String name){
        View root= LayoutInflater.from(this).inflate(R.layout.tab,null);
        ImageView imageView = root.findViewById(R.id.tab_image);
        TextView textView = root.findViewById(R.id.tab_text);
        imageView.setImageResource(posi);
        textView.setText(name);
        return root;
    }
}
