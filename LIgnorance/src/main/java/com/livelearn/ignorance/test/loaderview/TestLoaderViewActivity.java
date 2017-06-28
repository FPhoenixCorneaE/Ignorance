package com.livelearn.ignorance.test.loaderview;

import android.os.AsyncTask;
import android.os.Bundle;

import com.apkfuns.logutils.LogUtils;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.utils.GlideUtils;
import com.livelearn.ignorance.widget.TitleBar;
import com.livelearn.ignorance.widget.loaderview.LoaderImageView;
import com.livelearn.ignorance.widget.loaderview.LoaderTextView;

import butterknife.BindView;

public class TestLoaderViewActivity extends BaseActivity {

    @BindView(R.id.tb_title)
    TitleBar tbTitle;

    @BindView(R.id.liv_loader_image)
    LoaderImageView livLoaderImage;

    @BindView(R.id.ltv_name)
    LoaderTextView ltvName;

    @BindView(R.id.ltv_title)
    LoaderTextView ltvTitle;

    @BindView(R.id.ltv_phone)
    LoaderTextView ltvPhone;

    @BindView(R.id.ltv_email)
    LoaderTextView ltvEmail;

    private DummyWait dummyWait;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_loader_view;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        tbTitle.setTitleText(className);
        dummyWait = new DummyWait();
        dummyWait.execute();
    }

    @Override
    public void setListeners() {

    }

    private void postLoadData() {
        ltvName.setText(String.valueOf("Mr. Donald Trump"));
        ltvTitle.setText(String.valueOf("Presidency Candidate of United State"));
        ltvPhone.setText(String.valueOf("+001 2345 6789"));
        ltvEmail.setText(String.valueOf("donald.trump@donaldtrump.com"));
        GlideUtils.setupImage(mContext, livLoaderImage, R.mipmap.pic_guide_1);
    }

    class DummyWait extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                LogUtils.e(e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            postLoadData();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dummyWait.cancel(true);
    }
}
