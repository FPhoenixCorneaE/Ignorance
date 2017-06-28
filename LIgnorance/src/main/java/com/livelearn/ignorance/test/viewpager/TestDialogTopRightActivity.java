package com.livelearn.ignorance.test.viewpager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.utils.ResourceUtils;
import com.livelearn.ignorance.utils.ToastUtils;
import com.yalantis.ucrop.util.StatusBarCompat;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestDialogTopRightActivity extends Activity {

    @BindView(R.id.ll_list)
    LinearLayout llList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_dialog_top_right);
        ButterKnife.bind(this);

        StatusBarCompat.compat(this, ResourceUtils.getColor(R.color.status_bar));

        initLayout();
    }

    private void initLayout() {
        llList.removeAllViews();

        String[] jazzyEffects = ResourceUtils.getStringArray(R.array.Jazzy_Effects);
        for (int i = 0; i < jazzyEffects.length; i++) {
            TextView child = getTextView();
            if (i == jazzyEffects.length - 1) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) child.getLayoutParams();
                layoutParams.setMargins(0, ResourceUtils.getDimensionPixelOffset(R.dimen.margin_10dp), 0, ResourceUtils.getDimensionPixelOffset(R.dimen.margin_10dp));
            }
            child.setText(jazzyEffects[i]);
            llList.addView(child);
        }
    }

    @NonNull
    private TextView getTextView() {
        LinearLayout.LayoutParams textViewParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textViewParams.setMargins(0, ResourceUtils.getDimensionPixelOffset(R.dimen.margin_10dp), 0, 0);
        TextView textView = new TextView(this);
        textView.setLayoutParams(textViewParams);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        textView.setTextColor(ResourceUtils.getColor(R.color.white));
        textView.setPadding(ResourceUtils.getDimensionPixelOffset(R.dimen.margin_10dp), 0, ResourceUtils.getDimensionPixelOffset(R.dimen.margin_10dp), 0);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showToast(((TextView) view).getText().toString());
                Intent intent = new Intent();
                intent.putExtra("JazzyEffect", ((TextView) view).getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        return textView;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return true;
    }
}
