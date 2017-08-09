package com.livelearn.ignorance.ui.activity.mine;

import android.os.Bundle;
import android.view.View;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.common.Constant;
import com.livelearn.ignorance.utils.SharedPreferencesUtils;
import com.livelearn.ignorance.widget.TitleBar;

import org.simple.eventbus.EventBus;

import butterknife.BindView;

/**
 * 收藏
 * Created on 2017/7/18.
 */

public class MyCollectionActivity extends BaseActivity {

    @BindView(R.id.tb_title)
    TitleBar tbTitle;

    private String bookCollectionArrangementMode;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_my_collection;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        tbTitle.setTitleText("收藏");
        bookCollectionArrangementMode = SharedPreferencesUtils.getString(Constant.USER_INFO, Constant.BOOK_COLLECTION_ARRANGEMENT_MODE);
        if (Constant.BOOK_COLLECTION_ARRANGEMENT_MODE_LIST.equals(bookCollectionArrangementMode)) {
            tbTitle.setRightText("九宫格");
        } else {
            tbTitle.setRightText("列表");
        }
    }

    @Override
    public void setListeners() {
        tbTitle.setOnClickLeftListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackPressed();
                    }
                })
                .setOnClickRightListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Constant.BOOK_COLLECTION_ARRANGEMENT_MODE_LIST.equals(bookCollectionArrangementMode)) {
                            bookCollectionArrangementMode = Constant.BOOK_COLLECTION_ARRANGEMENT_MODE_GRID;
                            tbTitle.setRightText("列表");
                        } else {
                            bookCollectionArrangementMode = Constant.BOOK_COLLECTION_ARRANGEMENT_MODE_LIST;
                            tbTitle.setRightText("九宫格");
                        }
                        SharedPreferencesUtils.put(Constant.USER_INFO, Constant.BOOK_COLLECTION_ARRANGEMENT_MODE, bookCollectionArrangementMode);
                        EventBus.getDefault().post(bookCollectionArrangementMode, Constant.BOOK_COLLECTION_ARRANGEMENT_MODE);
                    }
                });
    }
}
