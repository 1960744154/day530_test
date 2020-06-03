package com.lhr.day530_test.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.lhr.data.Device;
import com.lhr.day530_test.R;
import com.lhr.frame.FrameApplication;
import com.lhr.frame.secret.SystemUtils;
import com.lhr.utils.NetworkUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public abstract class BaseSplashActivity extends BaseMvpActivity {
    @BindView(R.id.advert_image)
    public ImageView advertImage;
    @BindView(R.id.time_view)
    public TextView timeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayout() {
        return new R.layout().activity_splash2;
    }

    public void initDevice() {
        Device device = new Device();
        device.setScreenWidth(SystemUtils.getSize(this).x);
        device.setScreenHeight(SystemUtils.getSize(this).y);
        device.setDeviceName(SystemUtils.getDeviceName());
        device.setSystem(SystemUtils.getSystem(this));
        device.setVersion(SystemUtils.getVersion(this));
        device.setDeviceId(SystemUtils.getDeviceId(this));
        device.setLocalIp(NetworkUtils.getLocalIpAddress());
        FrameApplication.getFrameApplication().setDeviceInfo(device);
    }
}
