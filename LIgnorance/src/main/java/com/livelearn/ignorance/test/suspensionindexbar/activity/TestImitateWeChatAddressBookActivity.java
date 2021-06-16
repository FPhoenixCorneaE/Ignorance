package com.livelearn.ignorance.test.suspensionindexbar.activity;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.model.bean.UserInfo;
import com.livelearn.ignorance.test.dragfooterview.Url;
import com.livelearn.ignorance.ui.adapter.AddressBookAdapter;
import com.livelearn.ignorance.utils.DisplayUtils;
import com.livelearn.ignorance.utils.ResourceUtils;
import com.livelearn.ignorance.widget.TitleBar;
import com.mcxtzhang.indexbar.IndexBar;
import com.mcxtzhang.suspension.SuspensionDecoration;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;

public class TestImitateWeChatAddressBookActivity extends BaseActivity {

    private static final String INDEX_STRING_TOP = "↑";

    @BindView(R.id.tb_title)
    TitleBar tbTitle;

    @BindView(R.id.rv_address_book)
    EasyRecyclerView rvAddressBook;

    @BindView(R.id.ib_index_bar)
    IndexBar ibIndexBar;

    @BindView(R.id.tv_side_bar_hint)
    TextView tvSideBarHint;

    @BindView(R.id.btn_update_datas)
    Button btnUpdateDatas;

    private AddressBookAdapter mAddressBookAdapter;
    private List<UserInfo> mDatas = new ArrayList<>();
    private SuspensionDecoration mDecoration;
    private TextView tvFooter;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_imitate_we_chat_address_book;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        tbTitle.setTitleText(className);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        rvAddressBook.setLayoutManager(mLayoutManager);

        mAddressBookAdapter = new AddressBookAdapter(mContext);
        rvAddressBook.setAdapter(mAddressBookAdapter);
        rvAddressBook.addItemDecoration(mDecoration = new SuspensionDecoration(this, mDatas));
        //如果add两个，那么按照先后顺序，依次渲染
        DividerDecoration itemDecoration = new DividerDecoration(ResourceUtils.getColor(R.color.divider), 1, DisplayUtils.dp2px(16), DisplayUtils.dp2px(24));
        itemDecoration.setDrawLastItem(false);
        rvAddressBook.addItemDecoration(itemDecoration);

        //indexbar初始化
        ibIndexBar.setmPressedShowTextView(tvSideBarHint)//设置HintTextView
                .setNeedRealIndex(true)//设置需要真实的索引
                .setmLayoutManager(mLayoutManager);//设置RecyclerView的LayoutManager

        //模拟线上加载数据
        initDatas();
    }

    private void initDatas() {
        final String[] friends = ResourceUtils.getStringArray(R.array.AddressBook_Friends);
        //延迟零点五秒 模拟加载数据中....
        getWindow().getDecorView().postDelayed(new Runnable() {
            @SuppressWarnings("unchecked")
            @Override
            public void run() {
                // 微信的头部 也是可以右侧IndexBar导航索引的，
                // 但是它不需要被ItemDecoration设一个标题titile
                mDatas.add((UserInfo) new UserInfo().setUserNickname("新的朋友").setTop(true).setBaseIndexTag(INDEX_STRING_TOP));
                mDatas.add((UserInfo) new UserInfo().setUserNickname("群聊").setTop(true).setBaseIndexTag(INDEX_STRING_TOP));
                mDatas.add((UserInfo) new UserInfo().setUserNickname("标签").setTop(true).setBaseIndexTag(INDEX_STRING_TOP));
                mDatas.add((UserInfo) new UserInfo().setUserNickname("公众号").setTop(true).setBaseIndexTag(INDEX_STRING_TOP));
                for (int i = 0; i < friends.length; i++) {
                    mDatas.add(new UserInfo().setUserNickname(friends[i]).setUserAvatarPath(Url.urls[i % Url.urls.length]));
                }

                ibIndexBar.setmSourceDatas(mDatas)//设置数据
                        .invalidate();
                mAddressBookAdapter.addAll((List<UserInfo>) ibIndexBar.getmSourceDatas());
                mDecoration.setmDatas(mDatas);

                mAddressBookAdapter.addFooter(new RecyclerArrayAdapter.ItemView() {
                    @Override
                    public View onCreateView(ViewGroup parent) {
                        tvFooter = new TextView(mContext);
                        tvFooter.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DisplayUtils.dp2px(49F)));
                        tvFooter.setBackgroundColor(ResourceUtils.getColor(R.color.color_dark_pale));
                        tvFooter.setGravity(Gravity.CENTER);
                        tvFooter.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                        tvFooter.setTextColor(ResourceUtils.getColor(R.color.color_light_purple));
                        tvFooter.setText(String.format(Locale.getDefault(), "%d位联系人", mDatas.size() - 4));
                        return tvFooter;
                    }

                    @Override
                    public void onBindView(View headerView) {

                    }
                });

                btnUpdateDatas.setVisibility(View.VISIBLE);
            }
        }, 500);
    }

    @Override
    public void setListeners() {

    }

    @SuppressWarnings("unchecked")
    @OnClick(R.id.btn_update_datas)
    public void onViewClicked() {
        String[] nicknames = {"Alice", "Bob", "Candy", "Davy", "Eric", "Frank", "Gen", "Helen", "Ir",
                "Jac", "Koe", "Li", "Mei", "Nou", "Own", "Punk", "Queen", "Rose", "Sunshine", "Tony", "Uu", "Vv",
                "Wen", "Xv", "Yy", "Zero"};
        String[] numbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
        String[] colors = {"Black", "White", "Red", "Orange", "Yellow", "Green", "Indigo", "Blue", "Purple"};
        for (int i = 0; i < 10; i++) {
            String nickname;
            switch (new Random().nextInt(3)) {
                case 0:
                    nickname = nicknames[new Random().nextInt(nicknames.length)];
                    break;
                case 1:
                    nickname = numbers[new Random().nextInt(numbers.length)];
                    break;
                default:
                    nickname = colors[new Random().nextInt(colors.length)];
                    break;
            }
            mDatas.add(new UserInfo().setUserNickname(nickname)
                    .setUserAvatarPath(Url.urls[new Random().nextInt(Url.urls.length)]));
        }

        ibIndexBar.setNeedRealIndex(true)
                .setmSourceDatas(mDatas)
                .invalidate();
        mAddressBookAdapter.clear();
        mAddressBookAdapter.addAll((List<UserInfo>) ibIndexBar.getmSourceDatas());
        mDecoration.setmDatas(mDatas);

        tvFooter.setText(String.format(Locale.getDefault(), "%d位联系人", mDatas.size() - 4));
    }
}
