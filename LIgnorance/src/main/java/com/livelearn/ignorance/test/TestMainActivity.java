package com.livelearn.ignorance.test;

import android.os.Bundle;
import android.widget.ListView;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.test.alphatabview.TestAlphaTabViewActivity;
import com.livelearn.ignorance.test.assembleessay.activity.TestAssembleEssayActivity;
import com.livelearn.ignorance.test.baseitemlayout.TestBaseItemLayoutActivity;
import com.livelearn.ignorance.test.bigimageviewer.TestBigImageViewerMainActivity;
import com.livelearn.ignorance.test.circlerefreshlayout.TestCircleRefreshLayoutActivity;
import com.livelearn.ignorance.test.colortrackview.TestColorTrackViewActivity;
import com.livelearn.ignorance.test.database.TestQueryDatabaseTypeActivity;
import com.livelearn.ignorance.test.dragfooterview.TestDragFooterActivity;
import com.livelearn.ignorance.test.easyrecyclerview.TestEasyRecyclerViewActivity;
import com.livelearn.ignorance.test.frescohelper.activity.TestFrescoHelperActivity;
import com.livelearn.ignorance.test.indicatorbox.TestFlashBorderViewActivity;
import com.livelearn.ignorance.test.indicatorbox.TestShrinkButtonActivity;
import com.livelearn.ignorance.test.linkageanimation.TestLinkageAnimationActivity;
import com.livelearn.ignorance.test.loaderview.TestLoaderViewActivity;
import com.livelearn.ignorance.test.loadingview.TestLoadingViewActivity;
import com.livelearn.ignorance.test.movingimageview.TestMovingImageViewActivity;
import com.livelearn.ignorance.test.ninegridlayout.TestMultiViewActivity;
import com.livelearn.ignorance.test.ninegridlayout.TestNiceNineImageLayoutActivity;
import com.livelearn.ignorance.test.ninegridlayout.TestNineGridLayoutActivity;
import com.livelearn.ignorance.test.observablescrollview.TestObservableScrollViewMainActivity;
import com.livelearn.ignorance.test.pulltorefreshwithloadmore.activity.TestPullToRefreshWithLoadMoreHomeActivity;
import com.livelearn.ignorance.test.searchdialog.TestSearchDialogActivity;
import com.livelearn.ignorance.test.smoothcompoundbutton.TestSmoothCompoundButtonActivity;
import com.livelearn.ignorance.test.smoothprogressbar.TestSmoothProgressBarMainActivity;
import com.livelearn.ignorance.test.statebutton.TestStateButtonActivity;
import com.livelearn.ignorance.test.suspensionindexbar.activity.TestSuspensionIndexBarMainActivity;
import com.livelearn.ignorance.test.topsnackbar.TestTopSnackbarActivity;
import com.livelearn.ignorance.test.transferee.TestTransfereeActivity;
import com.livelearn.ignorance.test.uploadimage.TestUploadMultipleImageActivity;
import com.livelearn.ignorance.test.uploadimage.TestUploadSingleImageActivity;
import com.livelearn.ignorance.test.verticalbannerview.TestVerticalBannerViewActivity;
import com.livelearn.ignorance.test.viewpager.TestAnimationViewPagerActivity;
import com.livelearn.ignorance.test.viewpager.TestJazzyViewPagerActivity;
import com.livelearn.ignorance.test.viewpager.TestThreeDimensionViewPagerActivity;
import com.livelearn.ignorance.utils.IntentUtils;
import com.livelearn.ignorance.utils.ResourceUtils;
import com.livelearn.ignorance.widget.TitleBar;

import butterknife.BindView;

/**
 * 测试主界面
 */
public class TestMainActivity extends BaseActivity implements TestMainAdapter.OnItemClickListener {

    @BindView(R.id.tb_title)
    TitleBar tbTitle;

    @BindView(R.id.lv_test)
    ListView lvTest;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_main;
    }

    public void initLayout(Bundle savedInstanceState) {
        tbTitle.setTitleText(ResourceUtils.getString(R.string.test_main_title));

        String[] contentList = ResourceUtils.getStringArray(R.array.TestMainItem);
        TestMainAdapter testMainAdapter = new TestMainAdapter(mContext, contentList);
        lvTest.setAdapter(testMainAdapter);
        testMainAdapter.setOnItemClickListener(this);
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void onItemClick(int position, String content) {
        switch (content) {
            case "AlphaTabView 微信底部滑动渐变标签栏":
                IntentUtils.startActivity(mContext, TestAlphaTabViewActivity.class);
                break;
            case "AnimationViewPager 动画多面控件":
                IntentUtils.startActivity(mContext, TestAnimationViewPagerActivity.class);
                break;
            case "BaseItemLayout 通用item布局":
                IntentUtils.startActivity(mContext, TestBaseItemLayoutActivity.class);
                break;
            case "BigImageViewer 大图查看器":
                IntentUtils.startActivity(mContext, TestBigImageViewerMainActivity.class);
                break;
            case "CircleRefreshLayout 圆圈刷新布局":
                IntentUtils.startActivity(mContext, TestCircleRefreshLayoutActivity.class);
                break;
            case "ColorTrackView 字体颜色渐变控件":
                IntentUtils.startActivity(mContext, TestColorTrackViewActivity.class);
                break;
            case "Database 数据库":
                IntentUtils.startActivity(mContext, TestQueryDatabaseTypeActivity.class);
                break;
            case "DragContainer 拖拽容器":
                IntentUtils.startActivity(mContext, TestDragFooterActivity.class);
                break;
            case "EasyRecyclerView 取代GridView和ListView":
                IntentUtils.startActivity(mContext, TestEasyRecyclerViewActivity.class);
                break;
            case "FlashBorderView 闪现边框":
                IntentUtils.startActivity(mContext, TestFlashBorderViewActivity.class);
                break;
            case "FlowDragLayout 可拖拽流式布局":
                IntentUtils.startActivity(mContext, TestAssembleEssayActivity.class);
                break;
            case "FrescoHelper Fresco的使用帮助":
                IntentUtils.startActivity(mContext, TestFrescoHelperActivity.class);
                break;
            case "JazzyViewPager 花哨多面控件":
                IntentUtils.startActivity(mContext, TestJazzyViewPagerActivity.class);
                break;
            case "LinkageAnimation 联动动画":
                IntentUtils.startActivity(mContext, TestLinkageAnimationActivity.class);
                break;
            case "LoaderView 载入控件":
                IntentUtils.startActivity(mContext, TestLoaderViewActivity.class);
                break;
            case "LoadingView 加载中控件":
                IntentUtils.startActivity(mContext, TestLoadingViewActivity.class);
                break;
            case "MovingImageView 滑动背景图":
                IntentUtils.startActivity(mContext, TestMovingImageViewActivity.class);
                break;
            case "MultiView QQ空间九宫格视图":
                IntentUtils.startActivity(mContext, TestMultiViewActivity.class);
                break;
            case "NiceNineImageLayout Nice首页9图样式及拖拽效果":
                IntentUtils.startActivity(mContext, TestNiceNineImageLayoutActivity.class);
                break;
            case "NineGridLayout 微信九宫格视图":
                IntentUtils.startActivity(mContext, TestNineGridLayoutActivity.class);
                break;
            case "ObservableScrollView 可观察的滚动控件":
                IntentUtils.startActivity(mContext, TestObservableScrollViewMainActivity.class);
                break;
            case "PullToRefreshWithLoadMore 下拉刷新加载更多组件":
                IntentUtils.startActivity(mContext, TestPullToRefreshWithLoadMoreHomeActivity.class);
                break;
            case "SearchDialog 仿哔哩哔哩搜索框效果":
                IntentUtils.startActivity(mContext, TestSearchDialogActivity.class);
                break;
            case "ShrinkButton 可收缩成进度条的按钮":
                IntentUtils.startActivity(mContext, TestShrinkButtonActivity.class);
                break;
            case "SmoothCompoundButton 平滑混合按钮":
                IntentUtils.startActivity(mContext, TestSmoothCompoundButtonActivity.class);
                break;
            case "SmoothProgressBar 平滑进度条":
                IntentUtils.startActivity(mContext, TestSmoothProgressBarMainActivity.class);
                break;
            case "StateButton 状态按钮":
                IntentUtils.startActivity(mContext, TestStateButtonActivity.class);
                break;
            case "SuspensionIndexBar 分组悬停索引右侧边栏":
                IntentUtils.startActivity(mContext, TestSuspensionIndexBarMainActivity.class);
                break;
            case "ThreeDimensionViewPager 3D效果多面控件":
                IntentUtils.startActivity(mContext, TestThreeDimensionViewPagerActivity.class);
                break;
            case "TopSnackbar 顶部快餐店":
                IntentUtils.startActivity(mContext, TestTopSnackbarActivity.class);
                break;
            case "Transferee 九宫格以及图片画廊":
                IntentUtils.startActivity(mContext, TestTransfereeActivity.class);
                break;
            case "UploadMultipleImage 上传多张图片":
                IntentUtils.startActivity(mContext, TestUploadMultipleImageActivity.class);
                break;
            case "UploadSingleImage 上传单张图片":
                IntentUtils.startActivity(mContext, TestUploadSingleImageActivity.class);
                break;
            case "VerticalBannerView 悬垂幕广告":
                IntentUtils.startActivity(mContext, TestVerticalBannerViewActivity.class);
                break;
        }
    }
}
