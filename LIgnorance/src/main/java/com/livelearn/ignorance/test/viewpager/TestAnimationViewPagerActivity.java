package com.livelearn.ignorance.test.viewpager;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalListDialog;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.utils.ImageDownloadUtils;
import com.livelearn.ignorance.utils.ResourceUtils;
import com.livelearn.ignorance.widget.TitleBar;
import com.livelearn.ignorance.widget.imageloadingprogress.CircleProgress;
import com.livelearn.ignorance.widget.imageloadingprogress.enums.CircleStyle;
import com.livelearn.ignorance.widget.imageloadingprogress.glide.ProgressModelLoader;
import com.livelearn.ignorance.widget.photoview.PhotoView;
import com.livelearn.ignorance.widget.viewpager.animationviewpager.AnimationViewPager;
import com.livelearn.ignorance.widget.viewpagertransformer.DepthPageTransformer;
import com.livelearn.ignorance.widget.viewpagertransformer.RotationAlphaTransformer;
import com.livelearn.ignorance.widget.viewpagertransformer.RotationTransformer;
import com.livelearn.ignorance.widget.viewpagertransformer.ZoomOutPageTransformer;
import com.zhl.cbdialog.CBDialogBuilder;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

public class TestAnimationViewPagerActivity extends BaseActivity {

    @BindView(R.id.tb_title)
    TitleBar tbTitle;

    @BindView(R.id.vp_animation)
    AnimationViewPager vpAnimation;

    private int curPosition;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_animation_view_pager;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        tbTitle.setTitleText(className);

        final List<String> imageList = Arrays.asList("http://sjbz.fd.zol-img.com.cn/t_s208x312c/g5/M00/00/01/ChMkJ1fJUYiIEtDDAAGcif4js8oAAU9egJmm74AAZyh409.jpg",
                "http://sjbz.fd.zol-img.com.cn/t_s208x312c/g5/M00/00/01/ChMkJlfJUVqIG9E4AAXem303o8UAAU9dgMjCU4ABd6z036.jpg",
                "http://sjbz.fd.zol-img.com.cn/t_s208x312c/g5/M00/00/01/ChMkJlfJUTeIKL3LAAefkYj1Ju0AAU9cwOiAJYAB5-p242.jpg",
                "http://sjbz.fd.zol-img.com.cn/t_s208x312c/g5/M00/00/01/ChMkJ1fJUTaIGQK6AAHvReuOQUkAAU9cwNWTfoAAe9d468.jpg",
                "http://sjbz.fd.zol-img.com.cn/t_s208x312c/g5/M00/00/01/ChMkJlfJUQWIfCz_AAeU25AxkMMAAU9bwAvrToAB5Tz853.jpg",
                "http://sjbz.fd.zol-img.com.cn/t_s208x312c/g5/M00/00/01/ChMkJlfJUMOIAR3HAAQcsgvxeScAAU9aAHAXRIABBzK941.jpg");

        vpAnimation.setPageMargin((int) (getResources().getDisplayMetrics().density * 20));
        vpAnimation.setPageTransformer(true, new ZoomOutPageTransformer());
        vpAnimation.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return imageList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, final int position) {
                PhotoView view = new PhotoView(mContext);
                //启用缩放功能
                view.enable();
                view.setScaleType(ImageView.ScaleType.FIT_CENTER);
                //Glide加载setTag不能用setTag(Object tag)这个方法
                view.setTag(R.id.v_tag, imageList.get(position));

                final CircleProgress circleProgress = generateProgress();
                circleProgress.inject(view);

                final String imageUrl = imageList.get(position);

                Glide.with(mContext)
                        .using(new ProgressModelLoader(
                                new Handler(Looper.getMainLooper()) {
                                    @Override
                                    public void handleMessage(Message msg) {
                                        circleProgress.setLevel(msg.arg1);
                                        circleProgress.setMaxValue(msg.arg2);
                                    }
                                }))
                        .load(imageUrl)
                        .crossFade()
                        .into(view);

                view.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        NormalListDialog listDialog = new NormalListDialog(mContext, new String[]{"保存"});
                        listDialog.isTitleShow(false)
                                .widthScale(0.6f)
                                .show();
                        listDialog.setOnOperItemClickL(new OnOperItemClickL() {
                            @Override
                            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                                switch (position) {
                                    case 0:
                                        ImageDownloadUtils.saveGlideImageToLocal(mContext, imageUrl);
                                        break;
                                }
                            }
                        });
                        return false;
                    }
                });

                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }

            public int getItemPosition(Object object) {
                return POSITION_NONE;
            }
        });
    }

    /**
     * 生成扇形进度
     */
    private CircleProgress generateProgress() {
        CircleProgress.Builder builder = new CircleProgress.Builder();
        return builder.setStyle(CircleStyle.FAN)
                .setBottomColorRes(R.color.white, mContext)
                .setProgressColorRes(R.color.color_light_gray, mContext)
                .setTextColorRes(R.color.color_light_purple, mContext)
                .build();
    }

    @Override
    public void setListeners() {
        curPosition = 3;
        tbTitle.setRightIcon(R.mipmap.ic_nav_moreset).setOnClickRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CBDialogBuilder(mContext)
                        .setTouchOutSideCancelable(true)
                        .showConfirmButton(false)
                        .setTitle(null)
                        .setDialoglocation(CBDialogBuilder.DIALOG_LOCATION_CENTER)
                        .setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_SLID_RIGHT)
                        .setItemTextColorStateList(ResourceUtils.getColorStateList(R.color.selector_pressedlightpurple_normalgray))
                        .setItemSelectedTextColor(ResourceUtils.getColor(R.color.color_light_purple))
                        .setItemBackgroundRes(R.drawable.selector_stroke_normaltransparent_pressedlightpurple)
                        .setItemSelectedBackgroundRes(R.drawable.shape_strokelightpurple)
                        .setItems(new String[]{"DepthPage", "RotationAlpha", "Rotation", "ZoomOutPage"},
                                new CBDialogBuilder.OnDialogItemClickListener() {
                                    @Override
                                    public void onDialogItemClick(CBDialogBuilder.DialogItemAdapter itemAdapter, Context context, CBDialogBuilder dialogbuilder, Dialog dialog, int position) {
                                        switch (position) {
                                            case 0:
                                                vpAnimation.setPageTransformer(true, new DepthPageTransformer());
                                                break;
                                            case 1:
                                                vpAnimation.setPageTransformer(true, new RotationAlphaTransformer());
                                                break;
                                            case 2:
                                                vpAnimation.setPageTransformer(true, new RotationTransformer());
                                                break;
                                            case 3:
                                                vpAnimation.setPageTransformer(true, new ZoomOutPageTransformer());
                                                break;
                                        }
                                        curPosition = position;
                                        dialog.dismiss();
                                    }
                                }, curPosition)
                        .create().show();
            }
        });
    }
}
