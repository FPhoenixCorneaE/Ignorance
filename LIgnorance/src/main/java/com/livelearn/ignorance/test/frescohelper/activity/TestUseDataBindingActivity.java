package com.livelearn.ignorance.test.frescohelper.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.databinding.ActivityTestFrescoUseDatabindingBinding;
import com.livelearn.ignorance.widget.TitleBar;


/**
 * </>
 * Created by android_ls on 16/11/11.
 */

public class TestUseDataBindingActivity extends BaseActivity {

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_fresco_use_databinding;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        ViewDataBinding databindingBinding = DataBindingUtil.inflate(LayoutInflater.from(this),
                R.layout.activity_test_fresco_use_databinding, null, false);
        setContentView(databindingBinding.getRoot());

        ((TitleBar) findViewById(R.id.tb_title)).setTitleText(className);

        String url = "http://g.hiphotos.baidu.com/imgad/pic/item/c75c10385343fbf22c362d2fb77eca8065388fa0.jpg";
        //这个Binding类是根据XML名字生成的
        ((ActivityTestFrescoUseDatabindingBinding) databindingBinding).setUrl(url);
    }

    @Override
    public void setListeners() {

    }
}
