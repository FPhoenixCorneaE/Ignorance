package com.livelearn.ignorance.test.easyrecyclerview.sticky;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.jude.easyrecyclerview.decoration.StickyHeaderDecoration;
import com.jude.rollviewpager.Util;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.test.easyrecyclerview.DataProvider;
import com.livelearn.ignorance.test.easyrecyclerview.entites.Person;
import com.livelearn.ignorance.test.easyrecyclerview.viewholder.PersonViewHolder;
import com.livelearn.ignorance.widget.TitleBar;


public class TestStickyHeaderActivity extends BaseActivity implements RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
    private EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<Person> adapter;
    private Handler handler = new Handler();

    private int page = 0;
    private boolean hasNetWork = true;
    private FloatingActionMenu fabMenu;
    private FloatingActionButton fabTop;
    private FloatingActionButton fabConnect;
    private FloatingActionButton fabDisconnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_load_more;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        ((TitleBar) findViewById(R.id.tb_title)).setTitleText(className);

        fabMenu = (FloatingActionMenu) findViewById(R.id.fab_menu);
        fabTop = (FloatingActionButton) findViewById(R.id.fab_top);
        fabConnect = (FloatingActionButton) findViewById(R.id.fab_connect);
        fabDisconnect = (FloatingActionButton) findViewById(R.id.fab_disconnect);
        recyclerView = (EasyRecyclerView) findViewById(R.id.recyclerView);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerDecoration itemDecoration = new DividerDecoration(Color.GRAY, Util.dip2px(this, 0.5f), Util.dip2px(this, 72), 0);
        itemDecoration.setDrawLastItem(false);
        recyclerView.addItemDecoration(itemDecoration);

        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<Person>(this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                return new PersonViewHolder(parent);
            }
        });

        // StickyHeader
        StickyHeaderDecoration decoration = new StickyHeaderDecoration(new TestStickyHeaderAdapter(this));
        decoration.setIncludeHeader(false);
        recyclerView.addItemDecoration(decoration);
        onRefresh();

        fabMenu.hideMenu(false);
        fabMenu.setIconAnimated(true);
        fabMenu.setClosedOnTouchOutside(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                fabMenu.showMenu(true);
                fabMenu.setMenuButtonShowAnimation(AnimationUtils.loadAnimation(mContext, R.anim.show_from_bottom));
                fabMenu.setMenuButtonHideAnimation(AnimationUtils.loadAnimation(mContext, R.anim.hide_to_bottom));
            }
        }, 500);

    }

    @Override
    public void setListeners() {
        adapter.setMore(R.layout.custom_view_footer_loadmore_easy_recycler_view, this);
        adapter.setNoMore(R.layout.custom_view_footer_nomore_easy_recycler_view, new RecyclerArrayAdapter.OnNoMoreListener() {
            @Override
            public void onNoMoreShow() {
                adapter.resumeMore();
            }

            @Override
            public void onNoMoreClick() {
                adapter.resumeMore();
            }
        });
        adapter.setOnItemLongClickListener(new RecyclerArrayAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(int position) {
                adapter.remove(position);
                return true;
            }
        });
        adapter.setError(R.layout.custom_view_footer_error_easy_recycler_view, new RecyclerArrayAdapter.OnErrorListener() {
            @Override
            public void onErrorShow() {
                adapter.resumeMore();
            }

            @Override
            public void onErrorClick() {
                adapter.resumeMore();
            }
        });

        recyclerView.setRefreshListener(this);
        fabTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.scrollToPosition(0);
                fabMenu.hideMenu(true);
            }
        });
        fabConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hasNetWork = true;
                fabConnect.setEnabled(false);
                fabDisconnect.setEnabled(true);
                fabMenu.hideMenu(true);
            }
        });
        fabDisconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hasNetWork = false;
                fabDisconnect.setEnabled(false);
                fabConnect.setEnabled(true);
                fabMenu.hideMenu(true);
            }
        });
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //如果 dx>0 则表示右滑 ， dx<0 表示左滑;dy<0 表示上滑， dy>0 表示下滑;通过这几个参数就可以监听滑动方向的状态。
                if (dy > 0) {
                    fabMenu.hideMenu(true);
                } else if (dy < 0) {
                    fabMenu.showMenu(true);
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
    }

    //第四页会返回空,意为数据加载结束
    @Override
    public void onLoadMore() {
        Log.i("EasyRecyclerView", "onLoadMore");
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //刷新
                if (!hasNetWork) {
                    adapter.pauseMore();
                    return;
                }
                adapter.addAll(DataProvider.getPersonList(page));
                page++;
            }
        }, 2000);
    }

    @Override
    public void onRefresh() {
        page = 0;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.clear();
                //刷新
                if (!hasNetWork) {
                    adapter.pauseMore();
                    return;
                }
                adapter.addAll(DataProvider.getPersonList(page));
                page = 1;
            }
        }, 2000);
    }
}
