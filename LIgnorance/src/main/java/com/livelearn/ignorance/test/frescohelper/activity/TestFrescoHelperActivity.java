package com.livelearn.ignorance.test.frescohelper.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.fresco.helper.ImageLoader;
import com.facebook.fresco.helper.Phoenix;
import com.facebook.fresco.helper.listener.DownloadImageResult;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.utils.IntentUtils;
import com.livelearn.ignorance.widget.TitleBar;

import static java.lang.System.currentTimeMillis;

public class TestFrescoHelperActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_fresco_helper;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        ((TitleBar) findViewById(R.id.tb_title)).setTitleText(className);

        long startTime = currentTimeMillis();
        Phoenix.init(this); // Context
        long result = System.currentTimeMillis() - startTime;
        ((TextView) findViewById(R.id.tv_init_count_time)).setText("初始化耗时：" + result + "ms");


        findViewById(R.id.btn_base_use).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.startActivity(mContext, TestFrescoBaseUseActivity.class);
            }
        });

        findViewById(R.id.btn_clear_memory).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fresco.getImagePipeline().clearMemoryCaches();
                System.gc();
            }
        });

        findViewById(R.id.btn_big).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.startActivity(mContext, TestBigImageActivity.class);
            }
        });

        findViewById(R.id.btn_small).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.startActivity(mContext, TestImageSizeActivity.class);
            }
        });

        findViewById(R.id.btn_gif).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.startActivity(mContext, TestGifActivity.class);
            }
        });

        findViewById(R.id.btn_meizi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.startActivity(mContext, TestMeiziActivity.class);
            }
        });

        findViewById(R.id.btn_meizi_webp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.startActivity(mContext, TestWebpActivity.class);
            }
        });

        findViewById(R.id.btn_use_databinding).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.startActivity(mContext, TestUseDataBindingActivity.class);
            }
        });

        findViewById(R.id.btn_blur).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.startActivity(mContext, TestBlurActivity.class);
            }
        });

        findViewById(R.id.btn_blur2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.startActivity(mContext, TestBlur2Activity.class);
            }
        });

        findViewById(R.id.btn_open_photo_wall).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.startActivity(mContext, TestPhotoWallActivity.class);
            }
        });

        findViewById(R.id.btn_open_photo_album).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.startActivity(mContext, TestPhotoAlbumActivity.class);
            }
        });
    }

    @Override
    public void setListeners() {

    }

    public void downloadImage(Context context) {
        String url = "http://feed.chujianapp.com/20161108/452ab5752287a99a1b5387e2cd849006.jpg@1080w";
        String filePath = "";
        ImageLoader.downloadImage(context, url, new DownloadImageResult(filePath) {

            @Override
            public void onResult(String filePath) {

            }
        });
    }

}
