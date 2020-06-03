package com.lhr.day530_test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class HomeActivity extends AppCompatActivity {

    private ImageView animate_imgae;
    int i=3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
    }

    private void initView() {
        animate_imgae = (ImageView) findViewById(R.id.animate_imgae);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        i--;
                        if(i==0){
                            Intent intent = new Intent(HomeActivity.this, SubjectActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
            }
        },0,1000);
    }
}
