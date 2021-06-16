package com.livelearn.ignorance.test.viewpager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.viewpager.widget.PagerAdapter;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.utils.ToastUtils;
import com.livelearn.ignorance.widget.TitleBar;
import com.livelearn.ignorance.widget.indicatorbox.ViewPagerIndicator;
import com.livelearn.ignorance.widget.tabstrip.OvalTabStrip;
import com.livelearn.ignorance.widget.viewpager.jazzyviewpager.JazzyViewPager;
import com.livelearn.ignorance.widget.viewpager.jazzyviewpager.OutlineContainer;

import butterknife.BindView;

public class TestJazzyViewPagerActivity extends BaseActivity {

    @BindView(R.id.ll_root)
    LinearLayout llRoot;

    @BindView(R.id.tb_title)
    TitleBar tbTitle;

    @BindView(R.id.vp_jazzy)
    JazzyViewPager vpJazzy;

    @BindView(R.id.vpi_page)
    ViewPagerIndicator vpiPage;

    @BindView(R.id.ots_tab_strip)
    OvalTabStrip otsTabStrip;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_jazzy_view_pager;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        tbTitle.setTitleText(className)
                .setRightIcon(R.mipmap.ic_nav_moreset)
                .showRightIcon()
                .setOnClickRightListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        final String[] jazzyEffects = ResourceUtils.getStringArray(R.array.Jazzy_Effects);
//                        final ActionSheetDialog sheetDialog = new ActionSheetDialog(jazzyEffects, llRoot);
//                        sheetDialog.title("切换方式")
//                                .titleTextColor(ResourceUtils.getColor(R.color.purple))
//                                .titleBgColor(ResourceUtils.getColor(R.color.base_background))
//                                .show();
//                        sheetDialog.setOnOperItemClickL(new OnOperItemClickL() {
//                            @Override
//                            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
//                                JazzyViewPager.TransitionEffect effect = JazzyViewPager.TransitionEffect.valueOf(jazzyEffects[position]);
//                                setupJazziness(effect);
//                                sheetDialog.dismiss();
//                            }
//                        });

                        startActivityForResult(new Intent(mContext, TestDialogTopRightActivity.class), 1);
                    }
                });


        setupJazziness(JazzyViewPager.TransitionEffect.Tablet);
    }

    @Override
    public void setListeners() {

    }

    private void setupJazziness(JazzyViewPager.TransitionEffect effect) {
        vpJazzy.setTransitionEffect(effect);
        vpJazzy.setAdapter(new MainAdapter());
        vpJazzy.setPageMargin(30);

        vpiPage.setViewPager(vpJazzy);

        otsTabStrip.bindViewPager(vpJazzy);
    }

    private class MainAdapter extends PagerAdapter {
        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            TextView text = new TextView(mContext);
            text.setGravity(Gravity.CENTER);
            text.setTextSize(30);
            text.setTextColor(Color.WHITE);
            text.setText(String.valueOf("Page " + (position + 1)));
            text.setPadding(30, 30, 30, 30);
            int bg = Color.rgb((int) Math.floor(Math.random() * 128) + 64,
                    (int) Math.floor(Math.random() * 128) + 64,
                    (int) Math.floor(Math.random() * 128) + 64);
            text.setBackgroundColor(bg);
            text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    vpJazzy.setFadeEnabled(!vpJazzy.getFadeEnabled());
                    ToastUtils.showToast(String.valueOf("FadeEnabled:" + vpJazzy.getFadeEnabled()));
                }
            });
            container.addView(text, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            vpJazzy.setObjectForPosition(text, position);
            return text;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object obj) {
            container.removeView(vpJazzy.findViewFromObject(position));
        }

        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            if (view instanceof OutlineContainer) {
                return ((OutlineContainer) view).getChildAt(0) == obj;
            } else {
                return view == obj;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Title" + (position + 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (data == null) {
                return;
            }
            JazzyViewPager.TransitionEffect effect = JazzyViewPager.TransitionEffect.valueOf(data.getStringExtra("JazzyEffect"));
            setupJazziness(effect);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
