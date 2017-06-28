package com.livelearn.ignorance.ui.fragment.home;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseFragment;
import com.livelearn.ignorance.ui.fragment.book.LongTimeBookFragment;
import com.livelearn.ignorance.ui.fragment.book.doubanbook.DouBanBookFragment;
import com.livelearn.ignorance.ui.view.book.doubanbook.DouBanBookView;
import com.livelearn.ignorance.utils.FragmentUtils;
import com.livelearn.ignorance.widget.arclayout.AnimatorUtils;
import com.livelearn.ignorance.widget.arclayout.ArcLayout;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 书库
 */
public class BookFragment extends BaseFragment implements DouBanBookView.OnPanelStateChangedListener {

    @BindView(R.id.fl_container)
    FrameLayout flContainer;

    @BindView(R.id.al_arc_layout)
    ArcLayout alArcLayout;

    @BindView(R.id.fl_translucent_background)
    FrameLayout flTranslucentBackground;

    @BindView(R.id.btn_book_source)
    Button btnBookSource;

    @BindView(R.id.btn_dou_ban_book)
    Button btnDouBanBook;

    @BindView(R.id.btn_long_time_book)
    Button btnLongTimeBook;

    private LongTimeBookFragment longTimeBookFragment;
    private DouBanBookFragment douBanBookFragment;

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_book;
    }

    @Override
    public void initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        longTimeBookFragment = new LongTimeBookFragment();
        douBanBookFragment = new DouBanBookFragment();
        FragmentUtils.addChildFragment(this, R.id.fl_container, longTimeBookFragment, null, false);
    }

    @Override
    public void setListeners() {
        douBanBookFragment.setOnPanelStateChangedListener(this);
        flTranslucentBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideMenu();
            }
        });
    }

    @Override
    public void lazyFetchData() {

    }

    @OnClick(R.id.btn_book_source)
    public void onViewClicked() {
        if (btnBookSource.isSelected()) {
            hideMenu();
        } else {
            showMenu();
        }
        btnBookSource.setSelected(!btnBookSource.isSelected());
    }

    @OnClick({R.id.btn_dou_ban_book, R.id.btn_long_time_book})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_dou_ban_book://豆瓣图书
                //Fragment嵌套Fragment,用Fragment的getChildFragmentManager()代替FragmentActivity的getSupportFragmentManager()
                FragmentUtils.hideAndShowChildFragment(this, R.id.fl_container, longTimeBookFragment, douBanBookFragment, null, false);
                hideMenu();
                break;
            case R.id.btn_long_time_book://久久图书
                //Fragment嵌套Fragment,用Fragment的getChildFragmentManager()代替FragmentActivity的getSupportFragmentManager()
                FragmentUtils.hideAndShowChildFragment(this, R.id.fl_container, douBanBookFragment, longTimeBookFragment, null, false);
                hideMenu();
                break;
        }
    }

    @SuppressWarnings("NewApi")
    private void showMenu() {
        flTranslucentBackground.setVisibility(View.VISIBLE);

        List<Animator> animList = new ArrayList<>();

        for (int i = 0, len = alArcLayout.getChildCount(); i < len; i++) {
            animList.add(createShowItemAnimator(alArcLayout.getChildAt(i)));
        }

        AnimatorSet animSet = new AnimatorSet();
        animSet.setDuration(400);
        animSet.setInterpolator(new OvershootInterpolator());
        animSet.playTogether(animList);
        animSet.start();
    }

    @SuppressWarnings("NewApi")
    private void hideMenu() {

        List<Animator> animList = new ArrayList<>();

        for (int i = alArcLayout.getChildCount() - 1; i >= 0; i--) {
            animList.add(createHideItemAnimator(alArcLayout.getChildAt(i)));
        }

        AnimatorSet animSet = new AnimatorSet();
        animSet.setDuration(400);
        animSet.setInterpolator(new AnticipateInterpolator());
        animSet.playTogether(animList);
        animSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                flTranslucentBackground.setVisibility(View.INVISIBLE);
            }
        });
        animSet.start();
    }

    private Animator createShowItemAnimator(View item) {

        float dx = btnBookSource.getX() - item.getX();
        float dy = btnBookSource.getY() - item.getY();

        item.setRotation(0f);
        item.setTranslationX(dx);
        item.setTranslationY(dy);

        return ObjectAnimator.ofPropertyValuesHolder(
                item,
                AnimatorUtils.rotation(0f, 720f),
                AnimatorUtils.translationX(dx, 0f),
                AnimatorUtils.translationY(dy, 0f)
        );
    }

    private Animator createHideItemAnimator(final View item) {
        float dx = btnBookSource.getX() - item.getX();
        float dy = btnBookSource.getY() - item.getY();

        Animator anim = ObjectAnimator.ofPropertyValuesHolder(
                item,
                AnimatorUtils.rotation(720f, 0f),
                AnimatorUtils.translationX(0f, dx),
                AnimatorUtils.translationY(0f, dy)
        );

        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                item.setTranslationX(0f);
                item.setTranslationY(0f);
            }
        });

        return anim;
    }

    @Override
    public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
        if (SlidingUpPanelLayout.PanelState.ANCHORED == newState
                || SlidingUpPanelLayout.PanelState.EXPANDED == newState) {
            btnBookSource.setVisibility(View.INVISIBLE);
        } else if (SlidingUpPanelLayout.PanelState.COLLAPSED == newState
                || SlidingUpPanelLayout.PanelState.HIDDEN == newState) {
            btnBookSource.setVisibility(View.VISIBLE);
        }
    }
}
