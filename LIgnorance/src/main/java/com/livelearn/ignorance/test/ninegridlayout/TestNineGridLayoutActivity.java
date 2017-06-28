package com.livelearn.ignorance.test.ninegridlayout;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.LinearLayout;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.utils.ResourceUtils;
import com.livelearn.ignorance.widget.TitleBar;

import java.util.ArrayList;

import butterknife.BindView;

public class TestNineGridLayoutActivity extends BaseActivity {

    @BindView(R.id.tb_title)
    TitleBar tbTitle;

    @BindView(R.id.rv_nine_grid)
    EasyRecyclerView rvNineGrid;

    private TestNineGridLayoutAdapter testNineGridLayoutAdapter;
    private Handler handler = new Handler();
    private int page;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_nine_grid_layout;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        tbTitle.setTitleText(className)
                .setTitleTextColor(ResourceUtils.getColor(R.color.color_pale));

        rvNineGrid.setLayoutManager(new LinearLayoutManager(mContext, LinearLayout.VERTICAL, false));
        DividerDecoration divider = new DividerDecoration(ResourceUtils.getColor(R.color.divider), 1);
        divider.setDrawHeaderFooter(true);
        divider.setDrawLastItem(true);
        rvNineGrid.addItemDecoration(divider);

        rvNineGrid.setAdapterWithProgress(testNineGridLayoutAdapter = new TestNineGridLayoutAdapter(mContext));

        testNineGridLayoutAdapter.addAll(getJournal(page));
    }

    private ArrayList<Journal> getJournal(int page) {
        ArrayList<Journal> journals = new ArrayList<>();
        if (page > 4) return journals;
        String[] friendNameArray = ResourceUtils.getStringArray(R.array.Journal_Name);
        String[] friendAvatarUrlArray = ResourceUtils.getStringArray(R.array.Journal_AvatarUrl);
        String[] journalDescriptionArray = ResourceUtils.getStringArray(R.array.Journal_Description);
        String[] pictureArray = ResourceUtils.getStringArray(R.array.Journal_Pictures);
        String[] releaseLocationArray = ResourceUtils.getStringArray(R.array.Journal_Location);
        String[] releaseTimeArray = ResourceUtils.getStringArray(R.array.Journal_Time);
        for (int i = 0; i < friendNameArray.length; i++) {
            Journal journal = new Journal();
            journal.setFriendName(friendNameArray[i]);
            journal.setFriendAvatarUrl(friendAvatarUrlArray[i]);
            journal.setJournalDescription(journalDescriptionArray[i]);
            journal.setPictureList(pictureArray[i]);
            journal.setReleaseLocation(releaseLocationArray[i]);
            journal.setReleaseTime(releaseTimeArray[i]);
            journals.add(journal);
        }
        return journals;
    }

    @Override
    public void setListeners() {
        rvNineGrid.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!isConnected) {
                    testNineGridLayoutAdapter.pauseMore();
                    return;
                }
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        testNineGridLayoutAdapter.clear();
                        testNineGridLayoutAdapter.addAll(getJournal(page = 0));
                    }
                }, 2000);
            }
        });
        testNineGridLayoutAdapter.setMore(R.layout.custom_view_footer_loadmore_easy_recycler_view, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                if (!isConnected) {
                    testNineGridLayoutAdapter.pauseMore();
                    return;
                }
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        testNineGridLayoutAdapter.addAll(getJournal(page++));
                    }
                }, 2000);
            }

            @Override
            public void onMoreClick() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        testNineGridLayoutAdapter.addAll(getJournal(page++));
                    }
                }, 2000);
            }
        });
        testNineGridLayoutAdapter.setNoMore(R.layout.custom_view_footer_nomore_easy_recycler_view, new RecyclerArrayAdapter.OnNoMoreListener() {
            @Override
            public void onNoMoreShow() {
                testNineGridLayoutAdapter.resumeMore();
            }

            @Override
            public void onNoMoreClick() {
                testNineGridLayoutAdapter.resumeMore();
            }
        });
        testNineGridLayoutAdapter.setError(R.layout.custom_view_footer_error_easy_recycler_view, new RecyclerArrayAdapter.OnErrorListener() {
            @Override
            public void onErrorShow() {
                testNineGridLayoutAdapter.resumeMore();
            }

            @Override
            public void onErrorClick() {
                testNineGridLayoutAdapter.resumeMore();
            }
        });
    }
}
