package com.livelearn.ignorance.test.easyrecyclerview;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.test.easyrecyclerview.collapsing.TestCollapsingActivity;
import com.livelearn.ignorance.test.easyrecyclerview.header.TestHeaderFooterActivity;
import com.livelearn.ignorance.test.easyrecyclerview.horizontal.TestHorizontalActivity;
import com.livelearn.ignorance.test.easyrecyclerview.loadmore.TestRefreshAndMoreActivity;
import com.livelearn.ignorance.test.easyrecyclerview.multistyle.TestMultiStyleActivity;
import com.livelearn.ignorance.test.easyrecyclerview.staggeredgrid.TestStaggeredGridActivity;
import com.livelearn.ignorance.test.easyrecyclerview.sticky.TestStickyHeaderActivity;
import com.livelearn.ignorance.utils.IntentUtils;
import com.livelearn.ignorance.widget.TitleBar;

/**
 * Created on 2016/1/6.
 */
public class TestEasyRecyclerViewActivity extends BaseActivity {
    Button refreshAndMore;
    Button multiStyle;
    Button headerAndFooter;
    Button collapsing;
    Button staggeredGrid;
    Button horizontal;
    Button stickyHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_easy_recycler_view;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        ((TitleBar) findViewById(R.id.tb_title)).setTitleText(className);

        EasyRecyclerView.DEBUG = true;
        refreshAndMore = (Button) findViewById(R.id.refresh_and_more);
        headerAndFooter = (Button) findViewById(R.id.header_footer);
        multiStyle = (Button) findViewById(R.id.multi_style);
        collapsing = (Button) findViewById(R.id.collapsing);
        staggeredGrid = (Button) findViewById(R.id.staggered_grid);
        horizontal = (Button) findViewById(R.id.horizontal);
        stickyHeader = (Button) findViewById(R.id.stiky_header);

    }

    @Override
    public void setListeners() {
        refreshAndMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.startActivity(mContext, TestRefreshAndMoreActivity.class);
            }
        });
        headerAndFooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.startActivity(mContext, TestHeaderFooterActivity.class);
            }
        });
        multiStyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.startActivity(mContext, TestMultiStyleActivity.class);
            }
        });
        collapsing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.startActivity(mContext, TestCollapsingActivity.class);
            }
        });
        staggeredGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.startActivity(mContext, TestStaggeredGridActivity.class);
            }
        });
        horizontal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.startActivity(mContext, TestHorizontalActivity.class);
            }
        });
        stickyHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.startActivity(mContext, TestStickyHeaderActivity.class);
            }
        });
    }
}
