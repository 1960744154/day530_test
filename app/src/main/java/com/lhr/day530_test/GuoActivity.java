package com.lhr.day530_test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.Timer;
import java.util.TimerTask;

public class GuoActivity extends AppCompatActivity {
    private ImageView advertising_image;
    private Button advertising_skip;
    int i = 5;
    int port = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guo);
        initView();
    }

    private void initView() {
        advertising_image = (ImageView) findViewById(R.id.advertising_image);
        advertising_skip = (Button) findViewById(R.id.advertising_skip);

        Glide.with(this).load("https://newoss.zhulong.com/ad/202004/13/41/100541b4f5xqxtiyrgsgqz.jpg").into(advertising_image);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(i>0&&port!=200){
                            advertising_skip.setText("跳过 "+i);
                        }
                        if(i==0&&port!=200){
                            Intent intent = new Intent(GuoActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        i--;
                    }
                });
            }
        },0,1000);

        advertising_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                port = 200;
                Intent intent = new Intent(GuoActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
