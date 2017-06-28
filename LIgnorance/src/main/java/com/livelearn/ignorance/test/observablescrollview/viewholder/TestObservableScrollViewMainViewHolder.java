package com.livelearn.ignorance.test.observablescrollview.viewholder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.test.observablescrollview.TestParallaxTitleBarGridViewActivity;
import com.livelearn.ignorance.test.observablescrollview.TestParallaxTitleBarListViewActivity;
import com.livelearn.ignorance.test.observablescrollview.TestParallaxTitleBarScrollViewActivity;
import com.livelearn.ignorance.test.observablescrollview.TestTitleBarControlGridViewActivity;
import com.livelearn.ignorance.test.observablescrollview.TestTitleBarControlListViewActivity;
import com.livelearn.ignorance.test.observablescrollview.TestTitleBarControlRecyclerViewActivity;
import com.livelearn.ignorance.test.observablescrollview.TestTitleBarControlScrollViewActivity;
import com.livelearn.ignorance.test.observablescrollview.TestTitleBarControlWebViewActivity;
import com.livelearn.ignorance.utils.IntentUtils;

/**
 * Created on 2017/3/27.
 */

public class TestObservableScrollViewMainViewHolder extends BaseViewHolder<String> {

    private TextView tvText;

    public TestObservableScrollViewMainViewHolder(ViewGroup parent) {
        super(parent, R.layout.viewholder_test_observable_scroll_view_main);
        tvText = $(R.id.tv_text);
    }

    @Override
    public void setData(String data) {
        tvText.setText(data);
        tvText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (getLayoutPosition()) {
                    case 0:
                        IntentUtils.startActivity(getContext(), TestTitleBarControlGridViewActivity.class);
                        break;
                    case 1:
                        IntentUtils.startActivity(getContext(), TestTitleBarControlListViewActivity.class);
                        break;
                    case 2:
                        IntentUtils.startActivity(getContext(), TestTitleBarControlRecyclerViewActivity.class);
                        break;
                    case 3:
                        IntentUtils.startActivity(getContext(), TestTitleBarControlScrollViewActivity.class);
                        break;
                    case 4:
                        IntentUtils.startActivity(getContext(), TestTitleBarControlWebViewActivity.class);
                        break;
                    case 5:
                        IntentUtils.startActivity(getContext(), TestParallaxTitleBarGridViewActivity.class);
                        break;
                    case 6:
                        IntentUtils.startActivity(getContext(), TestParallaxTitleBarListViewActivity.class);
                        break;
                    case 7:
                        IntentUtils.startActivity(getContext(), TestParallaxTitleBarScrollViewActivity.class);
                        break;
                }
            }
        });
    }
}
