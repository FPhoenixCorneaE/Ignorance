package com.livelearn.ignorance.test.suspensionindexbar.activity;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.test.suspensionindexbar.adapter.CommonAdapter;
import com.livelearn.ignorance.test.suspensionindexbar.model.MeiTuanCity;
import com.livelearn.ignorance.test.suspensionindexbar.adapter.MeiTuanCityAdapter;
import com.livelearn.ignorance.test.suspensionindexbar.model.MeiTuanCurrentCity;
import com.livelearn.ignorance.test.suspensionindexbar.model.MeiTuanHeaderCity;
import com.livelearn.ignorance.test.suspensionindexbar.adapter.RecyclerHeaderFooterWrapperAdapter;
import com.livelearn.ignorance.test.suspensionindexbar.adapter.ViewHolder;
import com.livelearn.ignorance.utils.DisplayUtils;
import com.livelearn.ignorance.utils.ResourceUtils;
import com.livelearn.ignorance.utils.ToastUtils;
import com.livelearn.ignorance.widget.TitleBar;
import com.mcxtzhang.indexbar.IndexBar;
import com.mcxtzhang.indexbar.bean.BaseIndexPinyinBean;
import com.mcxtzhang.suspension.SuspensionDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class TestMeiTuanCityListActivity extends BaseActivity {

    @BindView(R.id.tb_title)
    TitleBar tbTitle;

    @BindView(R.id.rv_mei_tuan_city)
    RecyclerView rvMeiTuanCity;

    @BindView(R.id.ib_index_bar)
    IndexBar ibIndexBar;

    @BindView(R.id.tv_side_bar_hint)
    TextView tvSideBarHint;

    @BindView(R.id.btn_update_datas)
    Button btnUpdateDatas;

    //设置给InexBar、ItemDecoration的完整数据集
    private List<BaseIndexPinyinBean> mSourceDatas;
    //头部数据源
    private List<MeiTuanHeaderCity> mHeaderDatas;
    //主体部分数据源（城市数据）
    private List<MeiTuanCity> mBodyDatas;
    private MeiTuanCityAdapter mMeiTuanCityAdapter;
    private RecyclerHeaderFooterWrapperAdapter mHeaderAdapter;
    private SuspensionDecoration mDecoration;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_mei_tuan_city_list;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        tbTitle.setTitleText(className);

        mHeaderDatas = new ArrayList<>();
        mSourceDatas = new ArrayList<>();

        mHeaderDatas.add(new MeiTuanHeaderCity().setCityList(new ArrayList<String>()).setSuspensionTag("定位城市").setIndexTag("定"));
        mHeaderDatas.add(new MeiTuanHeaderCity().setCityList(new ArrayList<String>()).setSuspensionTag("最近访问城市").setIndexTag("近"));
        mHeaderDatas.add(new MeiTuanHeaderCity().setCityList(new ArrayList<String>()).setSuspensionTag("热门城市").setIndexTag("热"));
        mSourceDatas.addAll(mHeaderDatas);

        mMeiTuanCityAdapter = new MeiTuanCityAdapter(mContext, mBodyDatas);

        mHeaderAdapter = new RecyclerHeaderFooterWrapperAdapter(mMeiTuanCityAdapter) {
            @Override
            protected void onBindHeaderHolder(ViewHolder holder, int headerPos, int layoutId, Object o) {
                switch (layoutId) {
                    case R.layout.header_test_meituan_current_city:
                        MeiTuanCurrentCity currentCity = (MeiTuanCurrentCity) o;
                        holder.setText(R.id.tv_current, currentCity.getCity());
                        break;
                    case R.layout.header_test_meituan_city_list:
                        final MeiTuanHeaderCity headerCity = (MeiTuanHeaderCity) o;
                        //网格
                        RecyclerView recyclerView = holder.getView(R.id.rv_city);
                        recyclerView.setAdapter(
                                new CommonAdapter<String>(mContext, R.layout.adapter_test_meituan_header_city, headerCity.getCityList()) {
                                    @Override
                                    public void convert(ViewHolder holder, final String cityName) {
                                        holder.setText(R.id.tv_city, cityName);
                                        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                ToastUtils.showToast("CityName:" + cityName);
                                            }
                                        });
                                    }
                                });
                        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
                        break;
                    default:
                }
            }
        };

        //头部
        mHeaderAdapter.setHeaderView(0, R.layout.header_test_meituan_current_city, new MeiTuanCurrentCity().setCity("当前：上海"));
        mHeaderAdapter.setHeaderView(1, R.layout.header_test_meituan_city_list, mHeaderDatas.get(0));
        mHeaderAdapter.setHeaderView(2, R.layout.header_test_meituan_city_list, mHeaderDatas.get(1));
        mHeaderAdapter.setHeaderView(3, R.layout.header_test_meituan_city_list, mHeaderDatas.get(2));

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        rvMeiTuanCity.setLayoutManager(mLayoutManager);
        rvMeiTuanCity.setAdapter(mHeaderAdapter);

        rvMeiTuanCity.addItemDecoration(mDecoration = new SuspensionDecoration(this, mSourceDatas)
                .setmTitleHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 35, getResources().getDisplayMetrics()))
                .setColorTitleBg(0xffefefef)
                .setColorTitleFont(ResourceUtils.getColor(R.color.black))
                .setTitleFontSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()))
                .setHeaderViewCount(mHeaderAdapter.getHeaderViewCount() - mHeaderDatas.size()));

        //如果add两个，那么按照先后顺序，依次渲染
        DividerDecoration itemDecoration = new DividerDecoration(ResourceUtils.getColor(R.color.divider), 1, DisplayUtils.dp2px(16), DisplayUtils.dp2px(24));
        itemDecoration.setDrawLastItem(false);
        rvMeiTuanCity.addItemDecoration(itemDecoration);

        //indexbar初始化
        ibIndexBar.setmPressedShowTextView(tvSideBarHint)//设置HintTextView
                .setNeedRealIndex(true)//设置需要真实的索引
                .setmLayoutManager(mLayoutManager)//设置RecyclerView的LayoutManager
                .setHeaderViewCount(mHeaderAdapter.getHeaderViewCount() - mHeaderDatas.size());

        //模拟线上加载数据
        initDatas();
    }

    private void initDatas() {
        //延迟一秒 模拟加载数据中....
        getWindow().getDecorView().postDelayed(new Runnable() {
            @SuppressWarnings("unchecked")
            @Override
            public void run() {
                String[] cities = ResourceUtils.getStringArray(R.array.AddressBook_City);
                mBodyDatas = new ArrayList<>();
                for (String city : cities) {
                    mBodyDatas.add(new MeiTuanCity().setCity(city));
                }
                //先排序
                ibIndexBar.getDataHelper().sortSourceDatas(mBodyDatas);

                mMeiTuanCityAdapter.setDatas(mBodyDatas);
                mHeaderAdapter.notifyDataSetChanged();
                mSourceDatas.addAll(mBodyDatas);

                ibIndexBar.setSourceDatasAlreadySorted(true)
                        .setmSourceDatas(mSourceDatas)//设置数据
                        .invalidate();
                mDecoration.setmDatas(mSourceDatas);

                btnUpdateDatas.setVisibility(View.VISIBLE);
            }
        }, 1000);

        //延迟两秒加载头部
        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                MeiTuanHeaderCity header1 = mHeaderDatas.get(0);
                header1.getCityList().clear();
                header1.getCityList().add("上海");

                MeiTuanHeaderCity header2 = mHeaderDatas.get(1);
                List<String> recentCitys = new ArrayList<>();
                recentCitys.add("日本");
                recentCitys.add("爱尔兰");
                header2.setCityList(recentCitys);

                MeiTuanHeaderCity header3 = mHeaderDatas.get(2);
                List<String> hotCitys = new ArrayList<>();
                hotCitys.add("上海");
                hotCitys.add("北京");
                hotCitys.add("杭州");
                hotCitys.add("广州");
                header3.setCityList(hotCitys);

                mHeaderAdapter.notifyItemRangeChanged(1, 3);
            }
        }, 2000);
    }

    @Override
    public void setListeners() {

    }

    @SuppressWarnings("unchecked")
    @OnClick(R.id.btn_update_datas)
    public void onViewClicked() {
        for (int i = 0; i < 2; i++) {
            mBodyDatas.add(new MeiTuanCity().setCity("东京"));
            mBodyDatas.add(new MeiTuanCity().setCity("伦敦"));
            mBodyDatas.add(new MeiTuanCity().setCity("梵蒂冈"));
        }
        //先排序
        ibIndexBar.getDataHelper().sortSourceDatas(mBodyDatas);

        mSourceDatas.clear();
        mSourceDatas.addAll(mHeaderDatas);
        mSourceDatas.addAll(mBodyDatas);

        mHeaderAdapter.notifyDataSetChanged();
        ibIndexBar.setSourceDatasAlreadySorted(true)
                .setmSourceDatas(mSourceDatas)//设置数据
                .invalidate();
    }
}
