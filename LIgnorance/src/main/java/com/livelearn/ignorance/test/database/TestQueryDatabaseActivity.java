package com.livelearn.ignorance.test.database;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.model.db.dbhelper.DepartmentDBHelper;
import com.livelearn.ignorance.model.db.dbhelper.DiseaseDBHelper;
import com.livelearn.ignorance.model.db.dbhelper.HospitalDBHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 查询医院/科室/疾病
 */
public class TestQueryDatabaseActivity extends BaseActivity {

    public static final String DISEASE = "disease";
    public static final String DEPARTMENT = "department";
    public static final String HOSPITAL = "hospital";
    public static final String TYPE = "type";
    public static final String KEYWORD = "keyword";

    @BindView(R.id.ll_back)
    LinearLayout llBack;

    @BindView(R.id.ll_delete)
    LinearLayout llDelete;

    @BindView(R.id.et_input)
    EditText etInput;

    @BindView(R.id.tv_confirm)
    TextView tvConfirm;

    @BindView(R.id.lv_data)
    ListView lvData;

    private String type;
    private List<String> dataList = new ArrayList<>();
    private TestQueryDatabaseAdapter dataAdapter;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_query_database;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        type = getIntent().getStringExtra(TYPE);
        String keyword = getIntent().getStringExtra(KEYWORD);
        if (null == type) {
            finish();
            return;
        }
        switch (type) {
            case DEPARTMENT:
                etInput.setHint("输入科室名称");
                break;
            case DISEASE:
                etInput.setHint("输入疾病名称");
                break;
            case HOSPITAL:
                etInput.setHint("输入医院名称");
                break;
        }
        if (keyword != null && !keyword.isEmpty()) {
            etInput.setText(keyword);
            etInput.setSelection(keyword.length());
            llDelete.setVisibility(View.VISIBLE);
        } else {
            etInput.setText("");
        }
    }

    @Override
    public void setListeners() {
        etInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence keyword, int start, int before, int count) {
                dataList.clear();
                if (keyword.length() != 0) {
                    llDelete.setVisibility(View.VISIBLE);
                    switch (type) {
                        case DEPARTMENT:
                            dataList = DepartmentDBHelper.getInstance().querySubDepartmentNameListByKeyword(keyword.toString());
                            break;
                        case DISEASE:
                            dataList = DiseaseDBHelper.getInstance().queryDiseaseNameListByKeyword(keyword.toString());
                            break;
                        case HOSPITAL:
                            dataList = HospitalDBHelper.getInstance().queryHospitalNameListByKeyword(keyword.toString());
                            break;
                    }
                } else {
                    llDelete.setVisibility(View.INVISIBLE);
                }
                if (!dataList.isEmpty()) {
                    lvData.setVisibility(View.VISIBLE);
                    if (dataAdapter != null) {
                        dataAdapter.resetData(dataList);
                        dataAdapter.notifyDataSetChanged();
                    } else {
                        dataAdapter = new TestQueryDatabaseAdapter(mContext, dataList);
                        lvData.setAdapter(dataAdapter);
                    }
                } else {
                    lvData.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        lvData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String data = dataList.get(position);
                etInput.setText(data);
                etInput.setSelection(data.length());
            }
        });
        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (type) {
                    case DEPARTMENT: {
                        Intent intent = new Intent();
                        intent.putExtra(DEPARTMENT, etInput.getText().toString());
                        setResult(RESULT_OK, intent);
                        finish();
                        break;
                    }
                    case DISEASE: {
                        Intent intent = new Intent();
                        intent.putExtra(DISEASE, etInput.getText().toString());
                        intent.putExtra("disease_id", DiseaseDBHelper.getInstance().queryDiseaseIdByDiseaseName(etInput.getText().toString()));
                        setResult(RESULT_OK, intent);
                        finish();
                        break;
                    }
                    case HOSPITAL: {
                        Intent intent = new Intent();
                        intent.putExtra(HOSPITAL, etInput.getText().toString());
                        setResult(RESULT_OK, intent);
                        finish();
                        break;
                    }
                }

            }
        });
        llDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etInput.setText("");
                lvData.setVisibility(View.INVISIBLE);
            }
        });
    }
}
