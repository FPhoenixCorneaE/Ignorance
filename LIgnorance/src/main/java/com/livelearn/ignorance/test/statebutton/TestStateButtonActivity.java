package com.livelearn.ignorance.test.statebutton;

import android.os.Bundle;
import android.view.View;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.widget.StateButton;
import com.livelearn.ignorance.widget.TitleBar;

public class TestStateButtonActivity extends BaseActivity {

    StateButton text;

    StateButton background;

    StateButton radius;

    StateButton stroke;

    StateButton dash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_state_button;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        ((TitleBar) findViewById(R.id.tb_title)).setTitleText(className);

        //设置不同状态下文字变色
        text = (StateButton) findViewById(R.id.text_test);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text.setEnabled(false);
            }
        });

        //最常用的设置不同背景
        background = (StateButton) findViewById(R.id.background_test);
        background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                background.setEnabled(false);
            }
        });

        //设置四个角不同的圆角
        radius = (StateButton) findViewById(R.id.different_radius_test);
        radius.setRadius(new float[]{0, 0, 20, 20, 40, 40, 60, 60});


        //设置不同状态下边框颜色，宽度
        stroke = (StateButton) findViewById(R.id.stroke_test);
        stroke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stroke.setEnabled(false);
            }
        });

        //设置间断
        dash = (StateButton) findViewById(R.id.dash_test);
        dash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dash.setEnabled(false);
            }
        });
    }

    @Override
    public void setListeners() {

    }
}
