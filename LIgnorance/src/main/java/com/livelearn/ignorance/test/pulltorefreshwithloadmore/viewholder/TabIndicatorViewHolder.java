package com.livelearn.ignorance.test.pulltorefreshwithloadmore.viewholder;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.TextView;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.test.pulltorefreshwithloadmore.viewpager.TabPageIndicator;
import com.livelearn.ignorance.utils.DisplayUtils;

/**
 * Created on 2017/3/21.
 */

public class TabIndicatorViewHolder extends TabPageIndicator.ViewHolderBase {

    private TextView mNameView;
    private View mTagView;

    @Override
    public View createView(LayoutInflater layoutInflater, int position) {
        View view = layoutInflater.inflate(R.layout.item_tab_page_indicator, null);
        view.setLayoutParams(new AbsListView.LayoutParams(DisplayUtils.dp2px(40), -1));
        mNameView = (TextView) view.findViewById(R.id.tv_tab_name);
        mTagView = view.findViewById(R.id.tv_current_tab);
        return view;
    }

    @Override
    public void updateView(int position, boolean isCurrent) {
        mNameView.setText(String.valueOf(position));
        if (isCurrent) {
            mTagView.setVisibility(View.VISIBLE);
        } else {
            mTagView.setVisibility(View.INVISIBLE);
        }
    }
}
