package com.livelearn.ignorance.ui.activity.book;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.common.Constant;
import com.livelearn.ignorance.model.db.dbhelper.LongTimeBookCollectionDBHelper;
import com.livelearn.ignorance.presenter.book.BookDetailsPresenter;
import com.livelearn.ignorance.ui.view.book.BookDetailsView;
import com.livelearn.ignorance.utils.ResourceUtils;
import com.livelearn.ignorance.utils.SnackbarUtils;
import com.livelearn.ignorance.utils.ToastUtils;
import com.livelearn.ignorance.widget.TitleBar;

import org.simple.eventbus.Subscriber;
import org.simple.eventbus.ThreadMode;

import butterknife.BindView;

/**
 * 久久图书书籍详情
 */
public class LongTimeBookDetailsActivity extends BaseActivity {

    @BindView(R.id.tb_title)
    TitleBar tbTitle;

    @BindView(R.id.bdv_book_details)
    BookDetailsView bdvBookDetails;

    @BindView(R.id.cl_book_details)
    CoordinatorLayout clBookDetails;

    private Animation animation;
    private LongTimeBookCollectionDBHelper mBookCollectionDBHelper;
    private boolean isCollected;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_long_time_book_details;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        mBookCollectionDBHelper = LongTimeBookCollectionDBHelper.getInstance();
        animation = AnimationUtils.loadAnimation(mContext, R.anim.set_play_pendulum);

        tbTitle.setTitleText(ResourceUtils.getString(R.string.book_details_title));

        String bookUrl = getIntent().getStringExtra(Constant.BOOK_URL);
        String bookName = getIntent().getStringExtra(Constant.BOOK_NAME);

        if (mBookCollectionDBHelper.queryBookHasCollected(bookName)) {
            isCollected = true;
            tbTitle.setRightIcon(R.mipmap.ic_collection_true);
        } else {
            isCollected = false;
            tbTitle.setRightIcon(R.mipmap.ic_collection_false);
        }
        mPresenter = new BookDetailsPresenter(bdvBookDetails, bookUrl, bookName);
    }

    @Override
    public void setListeners() {
        tbTitle.setOnClickRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getAnimation() == null) {
                    v.startAnimation(animation);
                    if (isCollected) {
                        bdvBookDetails.cancelBookCollection(mBookCollectionDBHelper);
                    } else {
                        bdvBookDetails.collectionBook(mBookCollectionDBHelper);
                    }
                } else {
                    ToastUtils.showToast("你点击得太频繁了,先休息一下吧");
                }
            }
        });
    }

    /**
     * 图书收藏
     *
     * @param isCollectedSuccess 收藏成功与否
     */
    @Subscriber(tag = Constant.BOOK_COLLECTION_LONG_TIME, mode = ThreadMode.MAIN)
    public void onCollection(boolean isCollectedSuccess) {
        if (isCollectedSuccess) {
            tbTitle.setRightIcon(R.mipmap.ic_collection_true);
            isCollected = true;
            showSnackbar("收藏成功");
        } else {
            showSnackbar("收藏失败");
        }
    }

    /**
     * 取消图书收藏
     *
     * @param isCancelCollectedSuccess 取消收藏成功与否
     */
    @Subscriber(tag = Constant.BOOK_COLLECTION_CANCEL_LONG_TIME, mode = ThreadMode.MAIN)
    public void onCancelCollection(boolean isCancelCollectedSuccess) {
        if (isCancelCollectedSuccess) {
            tbTitle.setRightIcon(R.mipmap.ic_collection_false);
            isCollected = false;
            showSnackbar("取消收藏成功");
        } else {
            showSnackbar("取消收藏失败");
        }
    }

    /**
     * 快餐店提示
     */
    private void showSnackbar(@NonNull CharSequence text) {
        SnackbarUtils.getInstance().makeShortSnackbar(clBookDetails, text)
                .setSnackbarBackgroundColor(ResourceUtils.getColor(R.color.color_light_purple))
                .setSnackbarAlpha(0.95f)
                .setTextColor(ResourceUtils.getColor(R.color.color_pale))
                .setTextSize(16)
                .show();
    }
}
