package com.livelearn.ignorance.ui.view.book.doubanbook;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.transition.Explode;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionSet;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.common.Constant;
import com.livelearn.ignorance.model.bean.book.douban.DouBanBookDetailsBean;
import com.livelearn.ignorance.model.db.dbentity.DouBanBookCollection;
import com.livelearn.ignorance.model.db.dbhelper.DouBanBookCollectionDBHelper;
import com.livelearn.ignorance.presenter.contract.book.doubanbook.DouBanBookDetailsContract;
import com.livelearn.ignorance.ui.activity.book.doubanbook.DouBanBookDetailsActivity;
import com.livelearn.ignorance.ui.adapter.book.doubanbook.BookReviewAdapter;
import com.livelearn.ignorance.ui.adapter.book.doubanbook.LikeBookAdapter;
import com.livelearn.ignorance.ui.adapter.book.doubanbook.ShortCommentAdapter;
import com.livelearn.ignorance.utils.GlideUtils;
import com.livelearn.ignorance.utils.IntentUtils;
import com.livelearn.ignorance.utils.PaletteUtils;
import com.livelearn.ignorance.utils.ResourceUtils;
import com.livelearn.ignorance.utils.SnackbarUtils;
import com.livelearn.ignorance.utils.StringUtils;
import com.livelearn.ignorance.widget.RootView;
import com.livelearn.ignorance.widget.cbprogressBar.CBProgressBar;
import com.livelearn.ignorance.widget.flowlayout.TagCloudView;
import com.ms.expandable.ExpandableTextView;
import com.xiaochao.lcrapiddeveloplibrary.viewtype.ProgressActivity;

import org.simple.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created on 2017/5/10.
 */

public class DouBanBookDetailsView extends RootView implements DouBanBookDetailsContract.View, AppBarLayout.OnOffsetChangedListener {

    private static final String TYPE_AUTHOR = "作者简介";
    private static final String TYPE_DIR_LIST = "目录";

    @BindView(R.id.iv_book_cover)
    ImageView ivBookCover;

    @BindView(R.id.tb_title)
    Toolbar tbTitle;

    @BindView(R.id.ctl_book_details)
    CollapsingToolbarLayout ctlBookDetails;

    @BindView(R.id.abl_book_details)
    AppBarLayout ablBookDetails;

    @BindView(R.id.tv_book_name)
    TextView tvBookName;

    @BindView(R.id.tv_book_author)
    TextView tvBookAuthor;

    @BindView(R.id.tv_book_publisher)
    TextView tvBookPublisher;

    @BindView(R.id.tv_book_pubtime)
    TextView tvBookPubtime;

    @BindView(R.id.tv_book_pages)
    TextView tvBookPages;

    @BindView(R.id.tv_book_pricing)
    TextView tvBookPricing;

    @BindView(R.id.tv_book_binding)
    TextView tvBookBinding;

    @BindView(R.id.tv_rating_number)
    TextView tvRatingNumber;

    @BindView(R.id.rb_rating_number)
    RatingBar rbRatingNumber;

    @BindView(R.id.tv_rating_sum)
    TextView tvRatingSum;

    @BindView(R.id.tv_content_intro_title)
    TextView tvContentIntroTitle;

    @BindView(R.id.tv_short_content_intro)
    ExpandableTextView tvShortContentIntro;

    @BindView(R.id.tv_book_author_intro_title)
    TextView tvBookAuthorIntroTitle;

    @BindView(R.id.tv_book_author_intro)
    TextView tvBookAuthorIntro;

    @BindView(R.id.ll_book_author_intro)
    LinearLayout llBookAuthorIntro;

    @BindView(R.id.tv_book_dir_title)
    TextView tvBookDirTitle;

    @BindView(R.id.tv_book_short_dir)
    TextView tvBookShortDir;

    @BindView(R.id.ll_book_dir)
    LinearLayout llBookDir;

    @BindView(R.id.tv_like_book_title)
    TextView tvLikeBookTitle;

    @BindView(R.id.rv_like_book)
    RecyclerView rvLikeBook;

    @BindView(R.id.ll_book_info)
    LinearLayout llBookInfo;

    @BindView(R.id.nsv_book_nested)
    NestedScrollView nsvBookNested;

    @BindView(R.id.tv_intro_title)
    TextView tvIntroTitle;

    @BindView(R.id.iv_close)
    ImageView ivClose;

    @BindView(R.id.tv_full_intro)
    TextView tvFullIntro;

    @BindView(R.id.nsv_full_intro)
    NestedScrollView nsvFullIntro;

    @BindView(R.id.fab_book_collection)
    FloatingActionButton fabBookCollection;

    @BindView(R.id.cl_book_details)
    CoordinatorLayout clBookDetails;

    @BindView(R.id.srl_book_details)
    SwipeRefreshLayout srlBookDetails;

    @BindView(R.id.pa_progress)
    ProgressActivity paProgress;

    @BindView(R.id.tv_book_series)
    TextView tvBookSeries;

    @BindView(R.id.tv_book_isbn)
    TextView tvBookIsbn;

    @BindView(R.id.pb_stars5)
    CBProgressBar pbStars5;

    @BindView(R.id.tv_stars5_percent)
    TextView tvStars5Percent;

    @BindView(R.id.pb_stars4)
    CBProgressBar pbStars4;

    @BindView(R.id.tv_stars4_percent)
    TextView tvStars4Percent;

    @BindView(R.id.pb_stars3)
    CBProgressBar pbStars3;

    @BindView(R.id.tv_stars3_percent)
    TextView tvStars3Percent;

    @BindView(R.id.pb_stars2)
    CBProgressBar pbStars2;

    @BindView(R.id.tv_stars2_percent)
    TextView tvStars2Percent;

    @BindView(R.id.pb_stars1)
    CBProgressBar pbStars1;

    @BindView(R.id.tv_stars1_percent)
    TextView tvStars1Percent;

    @BindView(R.id.ll_stars_percent)
    LinearLayout llStarsPercent;

    @BindView(R.id.tv_common_tag_title)
    TextView tvCommonTagTitle;

    @BindView(R.id.tcv_common_tag)
    TagCloudView tcvCommonTag;

    @BindView(R.id.ll_common_tag)
    LinearLayout llCommonTag;

    @BindView(R.id.tv_series_info)
    TextView tvSeriesInfo;

    @BindView(R.id.ll_series_info)
    LinearLayout llSeriesInfo;

    @BindView(R.id.tv_short_comment_title)
    TextView tvShortCommentTitle;

    @BindView(R.id.tv_hot_short_comment)
    TextView tvHotShortComment;

    @BindView(R.id.tv_new_short_comment)
    TextView tvNewShortComment;

    @BindView(R.id.rv_short_comment)
    RecyclerView rvShortComment;

    @BindView(R.id.ll_short_comment)
    LinearLayout llShortComment;

    @BindView(R.id.tv_more_short_comment)
    TextView tvMoreShortComment;

    @BindView(R.id.tv_book_review_title)
    TextView tvBookReviewTitle;

    @BindView(R.id.rv_book_review)
    RecyclerView rvBookReview;

    @BindView(R.id.tv_more_book_review)
    TextView tvMoreBookReview;

    @BindView(R.id.ll_book_review)
    LinearLayout llBookReview;

    private String bookId;
    private String bookName;
    private DouBanBookDetailsContract.Presenter mPresenter;
    private BottomSheetBehavior bottomSheetBehavior;
    private DouBanBookCollectionDBHelper bookCollectionDBHelper;
    private DouBanBookDetailsBean mBookDetails;
    private boolean isCollection;
    private ShortCommentAdapter mShortCommentAdapter;

    public DouBanBookDetailsView(Context context) {
        super(context);
    }

    public DouBanBookDetailsView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DouBanBookDetailsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.view_dou_ban_book_details;
    }

    @Override
    public void init() {
        bookId = ((Activity) mContext).getIntent().getStringExtra(Constant.BOOK_ID);
        bookName = ((Activity) mContext).getIntent().getStringExtra(Constant.BOOK_NAME);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ((Activity) mContext).getWindow().setEnterTransition(makeTransition());
        }

        bottomSheetBehavior = BottomSheetBehavior.from(nsvFullIntro);

        bookCollectionDBHelper = DouBanBookCollectionDBHelper.getInstance();

        tbTitle.setNavigationIcon(R.drawable.ic_arrow_back_white);

        //图书是否已收藏
        if (bookCollectionDBHelper.queryBookHasCollected(bookId)) {
            GlideUtils.setupImage(mContext, fabBookCollection, R.mipmap.ic_collection_true);
            isCollection = true;
        } else {
            GlideUtils.setupImage(mContext, fabBookCollection, R.mipmap.ic_collection_false);
            isCollection = false;
        }
    }

    /**
     * 进入过渡动画
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private Transition makeTransition() {
        TransitionSet transition = new TransitionSet();
        transition.addTransition(new Explode());
        transition.addTransition(new Fade());
        transition.setDuration(400);
        return transition;
    }

    @Override
    public void setListeners() {
        tbTitle.setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity) mContext).onBackPressed();
            }
        });

        ablBookDetails.addOnOffsetChangedListener(this);

        //下拉刷新，直接从网络获取最新数据
        srlBookDetails.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getBookDetailsFromInternet(bookId, bookName);
            }
        });
    }

    /**
     * 收藏图书
     */
    private boolean collectionBook() {
        DouBanBookCollection bookCollection = new DouBanBookCollection();
        bookCollection.setBook_id(bookId);
        bookCollection.setBook_cover_url(mBookDetails.getBookCoverUrl());
        bookCollection.setBook_name(mBookDetails.getBookName());
        ArrayList<String> bookAuthorList = new ArrayList<>();
        for (DouBanBookDetailsBean.BookAuthorBean bookAuthor : mBookDetails.getBookAuthorList()) {
            bookAuthorList.add(bookAuthor.getAuthorName());
        }
        String author = StringUtils.spliceString(bookAuthorList);
        bookCollection.setAuthor(author);
        bookCollection.setPublisher(mBookDetails.getPublisher());
        bookCollection.setRating(mBookDetails.getRating().getRatingNum());
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault());
        String time = dateFormat.format(now);
        bookCollection.setTime(time);
        return bookCollectionDBHelper.addBookCollection(bookCollection);
    }

    @Override
    public void setPresenter(@NonNull DouBanBookDetailsContract.Presenter mPresenter) {
        this.mPresenter = mPresenter;
        showProgress();
        mPresenter.getDouBanBookDetailsByBookId(bookId, bookName);
    }

    @Override
    public void showProgress() {
        paProgress.showLoading();
    }

    @Override
    public void hideProgress() {
        if (srlBookDetails.isRefreshing()) {
            srlBookDetails.setRefreshing(false);
        }
        paProgress.showContent();
    }

    @Override
    public void onSuccess(DouBanBookDetailsBean mData) {
        if (mData != null) {
            this.mBookDetails = mData;
            hideProgress();
            updateUI();
        } else {
            onFailure(null);
        }
    }

    /**
     * 更新UI
     */
    private void updateUI() {
        //图书封面
        Glide.with(mContext)
                .load(mBookDetails.getBookCoverUrl())
                .asBitmap()
                .placeholder(R.mipmap.ic_nocover)
                .error(R.mipmap.ic_nocover)
                .listener(new RequestListener<String, Bitmap>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        int color = PaletteUtils.getColor(resource);
                        srlBookDetails.setBackgroundColor(color);
                        ctlBookDetails.setBackgroundColor(color);
                        ctlBookDetails.setContentScrimColor(color);
                        return false;
                    }
                })
                .into(ivBookCover);
        llBookInfo.setVisibility(VISIBLE);
        //豆瓣评分
        if (mBookDetails.getRating() != null) {
            if (!mBookDetails.getRating().getRatingNum().isEmpty()) {
                float rate = Float.parseFloat(mBookDetails.getRating().getRatingNum()) / 2;
                rbRatingNumber.setRating(rate);
                tvRatingNumber.setText(mBookDetails.getRating().getRatingNum());
                tvRatingSum.setText(mBookDetails.getRating().getRatingSum());
                pbStars5.setMax(100);
                pbStars5.setProgress(Float.valueOf(mBookDetails.getRating().getStars5RatingPer().replace("%", "")).intValue());
                tvStars5Percent.setText(mBookDetails.getRating().getStars5RatingPer());

                pbStars4.setMax(100);
                pbStars4.setProgress(Float.valueOf(mBookDetails.getRating().getStars4RatingPer().replace("%", "")).intValue());
                tvStars4Percent.setText(mBookDetails.getRating().getStars4RatingPer());

                pbStars3.setMax(100);
                pbStars3.setProgress(Float.valueOf(mBookDetails.getRating().getStars3RatingPer().replace("%", "")).intValue());
                tvStars3Percent.setText(mBookDetails.getRating().getStars3RatingPer());

                pbStars2.setMax(100);
                pbStars2.setProgress(Float.valueOf(mBookDetails.getRating().getStars2RatingPer().replace("%", "")).intValue());
                tvStars2Percent.setText(mBookDetails.getRating().getStars2RatingPer());

                pbStars1.setMax(100);
                pbStars1.setProgress(Float.valueOf(mBookDetails.getRating().getStars1RatingPer().replace("%", "")).intValue());
                tvStars1Percent.setText(mBookDetails.getRating().getStars1RatingPer());
            } else {
                rbRatingNumber.setRating(0f);
                tvRatingNumber.setVisibility(GONE);
                tvRatingSum.setText("目前无人评价");
                llStarsPercent.setVisibility(GONE);
            }
        }
        //图书名称
        tvBookName.setText(mBookDetails.getBookName());
        //图书作者
        ArrayList<String> bookAuthorList = new ArrayList<>();
        for (DouBanBookDetailsBean.BookAuthorBean bookAuthor : mBookDetails.getBookAuthorList()) {
            bookAuthorList.add(bookAuthor.getAuthorName());
        }
        tvBookAuthor.setText("作者：");
        StringUtils.addViewString(bookAuthorList, tvBookAuthor);
        //出版社
        if (!mBookDetails.getPublisher().isEmpty()) {
            tvBookPublisher.setText(String.format("出版社：%s", mBookDetails.getPublisher()));
        } else {
            tvBookPublisher.setVisibility(GONE);
        }
        //出版年
        if (!mBookDetails.getPubtime().isEmpty()) {
            tvBookPubtime.setText(String.format("出版年：%s", mBookDetails.getPubtime()));
        } else {
            tvBookPubtime.setVisibility(GONE);
        }
        //页数
        if (!mBookDetails.getPages().isEmpty()) {
            tvBookPages.setText(String.format("页数：%s", mBookDetails.getPages()));
        } else {
            tvBookPages.setVisibility(GONE);
        }
        //定价
        if (!mBookDetails.getPricing().isEmpty()) {
            tvBookPricing.setText(String.format("定价：%s", mBookDetails.getPricing()));
        } else {
            tvBookPricing.setVisibility(GONE);
        }
        //装帧
        if (!mBookDetails.getBinding().isEmpty()) {
            tvBookBinding.setText(String.format("装帧：%s", mBookDetails.getBinding()));
        } else {
            tvBookBinding.setVisibility(GONE);
        }
        //丛书
        if (!mBookDetails.getSeries().isEmpty()) {
            tvBookSeries.setText(String.format("丛书：%s", mBookDetails.getSeries()));
        } else {
            tvBookSeries.setVisibility(GONE);
        }
        //ISBN
        if (!mBookDetails.getIsbn().isEmpty()) {
            tvBookIsbn.setText(String.format("ISBN：%s", mBookDetails.getIsbn()));
        } else {
            tvBookIsbn.setVisibility(GONE);
        }
        //内容简介
        if (!mBookDetails.getFullContentIntro().isEmpty()) {
            tvContentIntroTitle.setText("内容简介");
            tvShortContentIntro.setText(mBookDetails.getFullContentIntro());
        } else {
            tvContentIntroTitle.setText("暂时没有内容简介...");
            tvShortContentIntro.setVisibility(GONE);
        }
        //豆瓣成员常用标签
        if (!mBookDetails.getCommonTagList().isEmpty()) {
            llCommonTag.setVisibility(VISIBLE);
            tvCommonTagTitle.setText(mBookDetails.getSumTagsNum());
            tcvCommonTag.setTags(mBookDetails.getCommonTagList());
        } else {
            llCommonTag.setVisibility(GONE);
        }
        //丛书信息
        if (!mBookDetails.getSeriesInfo().isEmpty()) {
            llSeriesInfo.setVisibility(VISIBLE);
            tvSeriesInfo.setText(mBookDetails.getSeriesInfo());
        } else {
            llSeriesInfo.setVisibility(GONE);
        }
        //作者简介
        if (!mBookDetails.getShortAuthorIntro().isEmpty()) {
            tvBookAuthorIntroTitle.setText("作者简介");
            tvBookAuthorIntro.setText(mBookDetails.getShortAuthorIntro());
        } else {
            tvBookAuthorIntroTitle.setText("作者简介");
            tvBookAuthorIntro.setText("暂时没有本书的作者简介");
        }
        //章节目录
        if (!mBookDetails.getShortDir().isEmpty()) {
            tvBookDirTitle.setText("目录");
            tvBookShortDir.setText(mBookDetails.getShortDir());
        } else {
            tvBookDirTitle.setText("目录");
            tvBookShortDir.setText("暂时没有本书的目录");
        }
        //短评
        if (!mBookDetails.getHotShortCommentList().isEmpty()) {
            llShortComment.setVisibility(VISIBLE);
            tvShortCommentTitle.setText("短评");
            //NestedScrollView嵌套RecyclerView时滑动不流畅问题的解决办法
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false);
            linearLayoutManager.setSmoothScrollbarEnabled(true);
            linearLayoutManager.setAutoMeasureEnabled(true);
            rvShortComment.setLayoutManager(linearLayoutManager);
            rvShortComment.setHasFixedSize(true);
            rvShortComment.setNestedScrollingEnabled(false);

            mShortCommentAdapter = new ShortCommentAdapter(mContext, mBookDetails.getHotShortCommentList());
            rvShortComment.setAdapter(mShortCommentAdapter);
        } else {
            llShortComment.setVisibility(GONE);
        }
        //书评
        if (!mBookDetails.getBookReviewList().isEmpty()) {
            llBookReview.setVisibility(VISIBLE);
            tvBookReviewTitle.setText("书评");
            //NestedScrollView嵌套RecyclerView时滑动不流畅问题的解决办法
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false);
            linearLayoutManager.setSmoothScrollbarEnabled(true);
            linearLayoutManager.setAutoMeasureEnabled(true);
            rvBookReview.setLayoutManager(linearLayoutManager);
            rvBookReview.setHasFixedSize(true);
            rvBookReview.setNestedScrollingEnabled(false);

            BookReviewAdapter mBookReviewAdapter = new BookReviewAdapter(mContext, mBookDetails.getBookReviewList());
            rvBookReview.setAdapter(mBookReviewAdapter);
            mBookReviewAdapter.setOnItemClickListener(new BookReviewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position, DouBanBookDetailsBean.BookReviewBean mData) {
                    mPresenter.getBookReviewDetails(mData.getBookReviewHref(), mBookDetails.getBookName() + "&" + mData.getBookReviewTitle());
                }
            });
        } else {
            llBookReview.setVisibility(GONE);
        }
        //喜欢读这本书的人也喜欢
        if (!mBookDetails.getLikeBookList().isEmpty()) {
            tvLikeBookTitle.setVisibility(VISIBLE);
            tvLikeBookTitle.setText("喜欢读这本书的人也喜欢...");
            rvLikeBook.setVisibility(VISIBLE);
            //NestedScrollView嵌套RecyclerView时滑动不流畅问题的解决办法
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false);
            linearLayoutManager.setSmoothScrollbarEnabled(true);
            linearLayoutManager.setAutoMeasureEnabled(true);
            rvLikeBook.setLayoutManager(linearLayoutManager);
            rvLikeBook.setHasFixedSize(true);
            rvLikeBook.setNestedScrollingEnabled(false);

            LikeBookAdapter mLikeBookAdapter = new LikeBookAdapter(mContext, mBookDetails.getLikeBookList());
            rvLikeBook.setAdapter(mLikeBookAdapter);

            mLikeBookAdapter.setOnItemClickListener(new LikeBookAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(String bookId, String bookName) {
                    if (bookId != null && !bookId.isEmpty() && bookName != null && !bookName.isEmpty()) {
                        Bundle bundle = new Bundle();
                        bundle.putString(Constant.BOOK_ID, bookId);
                        bundle.putString(Constant.BOOK_NAME, bookName);
                        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
                            Intent intent = new Intent(mContext, DouBanBookDetailsActivity.class);
                            intent.putExtras(bundle);
                            mContext.startActivity(intent,
                                    ActivityOptions.makeSceneTransitionAnimation((BaseActivity) mContext).toBundle());
                        } else {
                            IntentUtils.startActivity(mContext, DouBanBookDetailsActivity.class, bundle);
                        }
                    } else {
                        showSnackbar("数据出错了o(╯□╰)o");
                    }
                }
            });
        } else {
            tvLikeBookTitle.setVisibility(GONE);
            rvLikeBook.setVisibility(GONE);
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

    @Override
    public void onFailure(String message) {
        paProgress.showError(ResourceUtils.getDrawable(R.mipmap.pic_load_error), Constant.ERROR_TITLE, Constant.ERROR_CONTEXT, Constant.ERROR_BUTTON, new OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgress();
                //重试
                mPresenter.getDouBanBookDetailsByBookId(bookId, bookName);
            }
        });
        if (srlBookDetails.isRefreshing()) {
            srlBookDetails.setRefreshing(false);
        }
    }

    @OnClick({R.id.ll_book_author_intro, R.id.ll_book_dir, R.id.fab_book_collection, R.id.tv_hot_short_comment, R.id.tv_new_short_comment, R.id.tv_more_short_comment, R.id.tv_more_book_review})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_book_author_intro://作者简介
                showBottomSheetDialog(TYPE_AUTHOR);
                break;
            case R.id.ll_book_dir://目录
                showBottomSheetDialog(TYPE_DIR_LIST);
                break;
            case R.id.tv_hot_short_comment://热门短评
                if (mShortCommentAdapter != null) {
                    mShortCommentAdapter.setDatas(mBookDetails.getHotShortCommentList());
                    mShortCommentAdapter.notifyDataSetChanged();
                    tvHotShortComment.setTextColor(ResourceUtils.getColor(R.color.color_light_purple));
                    tvNewShortComment.setTextColor(ResourceUtils.getColor(R.color.color_gray));
                }
                break;
            case R.id.tv_new_short_comment://最新短评
                if (mShortCommentAdapter != null) {
                    mShortCommentAdapter.setDatas(mBookDetails.getNewShortCommentList());
                    mShortCommentAdapter.notifyDataSetChanged();
                    tvHotShortComment.setTextColor(ResourceUtils.getColor(R.color.color_gray));
                    tvNewShortComment.setTextColor(ResourceUtils.getColor(R.color.color_light_purple));
                }
                break;
            case R.id.tv_more_short_comment://更多短评
                break;
            case R.id.tv_more_book_review://更多书评
                break;
            case R.id.fab_book_collection://图书收藏
                if (isCollection) {
                    boolean isCollected = bookCollectionDBHelper.cancelBookCollection(bookId);
                    if (isCollected) {
                        GlideUtils.setupImage(mContext, fabBookCollection, R.mipmap.ic_collection_false);
                        isCollection = false;
                        showSnackbar("取消收藏成功");
                    } else {
                        showSnackbar("取消收藏失败");
                    }
                    EventBus.getDefault().post(isCollected, Constant.BOOK_COLLECTION_DOU_BAN);
                } else {
                    boolean isCancelCollected = collectionBook();
                    if (isCancelCollected) {
                        GlideUtils.setupImage(mContext, fabBookCollection, R.mipmap.ic_collection_true);
                        isCollection = true;
                        showSnackbar("收藏成功");
                    } else {
                        showSnackbar("收藏失败");
                    }
                    EventBus.getDefault().post(isCancelCollected, Constant.BOOK_COLLECTION_CANCEL_DOU_BAN);
                }
                break;
        }
    }

    /**
     * 显示BottomSheetDialog
     */
    private void showBottomSheetDialog(String type) {
        ablBookDetails.setExpanded(false);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        tvIntroTitle.setText(type);
        if (type.equals(TYPE_AUTHOR)) {
            if (!mBookDetails.getFullAuthortIntro().isEmpty()) {
                tvFullIntro.setText(mBookDetails.getFullAuthortIntro());
            } else {
                tvFullIntro.setText("暂时没有本书的作者简介");
            }
        } else if (type.equals(TYPE_DIR_LIST)) {
            if (!mBookDetails.getFullDir().isEmpty()) {
                tvFullIntro.setText(mBookDetails.getFullDir());
            } else {
                tvFullIntro.setText("暂时没有本书的目录");
            }
        }

        ivClose.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });
    }

    /**
     * 显示BottomSheetDialog
     * <p>
     * 书评详情
     */
    private void showBottomSheetDialog(String bookReviewTitle, String bookReviewFullContent) {
        if (bookReviewTitle.isEmpty()) {
            return;
        }
        ablBookDetails.setExpanded(false);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        tvIntroTitle.setText(bookReviewTitle);
        if (!bookReviewFullContent.isEmpty()) {
            tvFullIntro.setText(bookReviewFullContent);
        } else {
            tvFullIntro.setText("数据出错了o(╯□╰)o");
        }

        ivClose.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        //当Appbar完全显示时才启用SwipeRefreshLayout
        srlBookDetails.setEnabled(verticalOffset == 0);
    }

    /**
     * 返回键监听
     */
    public boolean onKeyDown(int keyCode, KeyEvent event, boolean superKeyDown) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                return false;
            } else {
                return superKeyDown;
            }
        } else {
            return superKeyDown;
        }
    }

    @Override
    public void showBookReviewDetails(String bookReviewTitle, String bookReviewFullContent) {
        showBottomSheetDialog(bookReviewTitle, bookReviewFullContent);
    }
}
