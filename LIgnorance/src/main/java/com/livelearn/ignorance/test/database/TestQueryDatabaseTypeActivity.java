package com.livelearn.ignorance.test.database;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.utils.IntentUtils;
import com.livelearn.ignorance.widget.TitleBar;

import butterknife.BindView;
import butterknife.OnClick;

public class TestQueryDatabaseTypeActivity extends BaseActivity {

    @BindView(R.id.tb_title)
    TitleBar tbTitle;

    @BindView(R.id.btn_hospital)
    Button btnHospital;

    @BindView(R.id.btn_department)
    Button btnDepartment;

    @BindView(R.id.btn_disease)
    Button btnDisease;

    @BindView(R.id.tv_return_result)
    TextView tvReturnResult;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_query_database_type;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        tbTitle.setTitleText(className);
    }

    @Override
    public void setListeners() {

    }

    @OnClick({R.id.btn_hospital, R.id.btn_department, R.id.btn_disease})
    public void onViewClicked(View view) {
        Bundle bundle = new Bundle();
        bundle.putString(TestQueryDatabaseActivity.KEYWORD, tvReturnResult.getText().toString());
        switch (view.getId()) {
            case R.id.btn_hospital:
                bundle.putString(TestQueryDatabaseActivity.TYPE, TestQueryDatabaseActivity.HOSPITAL);
                IntentUtils.startActivityForResult(mContext, TestQueryDatabaseActivity.class, 1, bundle);
                break;
            case R.id.btn_department:
                bundle.putString(TestQueryDatabaseActivity.TYPE, TestQueryDatabaseActivity.DEPARTMENT);
                IntentUtils.startActivityForResult(mContext, TestQueryDatabaseActivity.class, 2, bundle);
                break;
            case R.id.btn_disease:
                bundle.putString(TestQueryDatabaseActivity.TYPE, TestQueryDatabaseActivity.DISEASE);
                IntentUtils.startActivityForResult(mContext, TestQueryDatabaseActivity.class, 3, bundle);
                break;
            default:
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK == resultCode && data != null) {
            switch (requestCode) {
                case 1:
                    tvReturnResult.setText(data.getStringExtra(TestQueryDatabaseActivity.HOSPITAL));
                    break;
                case 2:
                    tvReturnResult.setText(data.getStringExtra(TestQueryDatabaseActivity.DEPARTMENT));
                    break;
                case 3:
                    tvReturnResult.setText(String.valueOf(data.getStringExtra(TestQueryDatabaseActivity.DISEASE) + data.getStringExtra("disease_id")));
                    break;
                default:
            }
        }
    }
}
