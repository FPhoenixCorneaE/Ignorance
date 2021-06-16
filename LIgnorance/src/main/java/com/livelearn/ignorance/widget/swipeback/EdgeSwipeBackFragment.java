package com.livelearn.ignorance.widget.swipeback;

import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.livelearn.ignorance.R;


/**
 * EdgeSwipeBackFragment
 * Created by YoKeyword on 16/4/19.
 */
public class EdgeSwipeBackFragment extends Fragment {
    private static final String SWIPEBACKFRAGMENT_STATE_SAVE_IS_HIDDEN = "SWIPEBACKFRAGMENT_STATE_SAVE_IS_HIDDEN";
    private EdgeSwipeBackLayout mEdgeSwipeBackLayout;
    private Animation mNoAnim;
    boolean mLocking = false;

    protected EdgeSwipeBackActivity _mActivity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof EdgeSwipeBackActivity) {
            _mActivity = (EdgeSwipeBackActivity) activity;
        } else {
            throw new RuntimeException(activity.toString() + " must extends EdgeSwipeBackActivity");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            boolean isSupportHidden = savedInstanceState.getBoolean(SWIPEBACKFRAGMENT_STATE_SAVE_IS_HIDDEN);

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (isSupportHidden) {
                ft.hide(this);
            } else {
                ft.show(this);
            }
            ft.commit();
        }

        mNoAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.no_anim);
        onFragmentCreate();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SWIPEBACKFRAGMENT_STATE_SAVE_IS_HIDDEN, isHidden());
    }

    private void onFragmentCreate() {
        mEdgeSwipeBackLayout = new EdgeSwipeBackLayout(getActivity());
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mEdgeSwipeBackLayout.setLayoutParams(params);
        mEdgeSwipeBackLayout.setBackgroundColor(Color.TRANSPARENT);
    }

    protected View attachToSwipeBack(View view) {
        mEdgeSwipeBackLayout.attachToFragment(this, view);
        return mEdgeSwipeBackLayout;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden && mEdgeSwipeBackLayout != null) {
            mEdgeSwipeBackLayout.hiddenFragment();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View view = getView();
        initFragmentBackground(view);
        if (view != null) {
            view.setClickable(true);
        }
    }

    private void initFragmentBackground(View view) {
        if (view instanceof EdgeSwipeBackLayout) {
            View childView = ((EdgeSwipeBackLayout) view).getChildAt(0);
            setBackground(childView);
        } else {
            setBackground(view);
        }
    }

    private void setBackground(View view) {
        if (view != null && view.getBackground() == null) {
            int defaultBg = _mActivity.getDefaultFragmentBackground();
            if (defaultBg == 0) {
                int background = getWindowBackground();
                view.setBackgroundResource(background);
            } else {
                view.setBackgroundResource(defaultBg);
            }
        }
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        if (mLocking) {
            return mNoAnim;
        }
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    protected int getWindowBackground() {
        TypedArray a = getActivity().getTheme().obtainStyledAttributes(new int[]{
                android.R.attr.windowBackground
        });
        int background = a.getResourceId(0, 0);
        a.recycle();
        return background;
    }

    public EdgeSwipeBackLayout getSwipeBackLayout() {
        return mEdgeSwipeBackLayout;
    }

    public void setSwipeBackEnable(boolean enable) {
        mEdgeSwipeBackLayout.setEnableGesture(enable);
    }
}
