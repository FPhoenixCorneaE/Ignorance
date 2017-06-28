package com.livelearn.ignorance.test.frescohelper.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.fresco.helper.ImageLoader;
import com.facebook.fresco.helper.Phoenix;
import com.facebook.fresco.helper.utils.MLog;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.widget.TitleBar;

/**
 * </>
 * Created by android_ls on 16/11/10.
 */

public class TestFrescoBaseUseActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_fresco_base_use;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        ((TitleBar) findViewById(R.id.tb_title)).setTitleText(className);

        String url = "http://ww3.sinaimg.cn/large/610dc034jw1f6m4aj83g9j20zk1hcww3.jpg";

        // 从网络加载一张图片
        Phoenix.with((SimpleDraweeView)findViewById(R.id.sdv_1)).load(url);
//        ImageLoader.loadImage((SimpleDraweeView)findViewById(R.id.sdv_1), url);

        // 网络加载一张图片，并以圆形显示
        ImageLoader.loadImage((SimpleDraweeView)findViewById(R.id.sdv_2), url);

        // 网络加载一张图片，并以圆形加边框显示
        ImageLoader.loadImage((SimpleDraweeView)findViewById(R.id.sdv_3), url);

        ImageLoader.loadImage((SimpleDraweeView)findViewById(R.id.sdv_4), url);

        ImageLoader.loadImage((SimpleDraweeView)findViewById(R.id.sdv_5), url);

        ImageLoader.loadImage((SimpleDraweeView)findViewById(R.id.sdv_6), url);

        ImageLoader.loadImage((SimpleDraweeView)findViewById(R.id.sdv_7), "");

        ImageLoader.loadImage((SimpleDraweeView)findViewById(R.id.sdv_8), url);

        ImageLoader.loadImage((SimpleDraweeView)findViewById(R.id.sdv_9), url);
        findViewById(R.id.sdv_9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MLog.i("按下效果");

            }
        });

        ImageLoader.loadImage((SimpleDraweeView)findViewById(R.id.sdv_10), url);

        String url2 = "http://www.baidu.com";
        ImageLoader.loadImage((SimpleDraweeView)findViewById(R.id.sdv_11), url2);
    }

    @Override
    public void setListeners() {

    }

}
