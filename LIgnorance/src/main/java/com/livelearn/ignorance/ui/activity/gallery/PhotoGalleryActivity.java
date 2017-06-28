package com.livelearn.ignorance.ui.activity.gallery;

import android.content.Context;
import android.content.Intent;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.ui.adapter.gallery.PhotoGalleryAdapter;
import com.livelearn.ignorance.utils.ResourceUtils;
import com.livelearn.ignorance.widget.indicatorbox.PointIndicator;
import com.livelearn.ignorance.widget.viewpagertransformer.RotationAlphaTransformer;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * 图片画廊
 */
public class PhotoGalleryActivity extends BaseActivity {

    private final static long ANIM_DURATION = 200L;
    private final static String PHOTO_PATHS = "PHOTO_PATHS";
    private final static String CURRENT_ITEM = "CURRENT_ITEM";
    private final static String THUMBNAIL_TOP = "THUMBNAIL_TOP";
    private final static String THUMBNAIL_LEFT = "THUMBNAIL_LEFT";
    private final static String THUMBNAIL_WIDTH = "THUMBNAIL_WIDTH";
    private final static String THUMBNAIL_HEIGHT = "THUMBNAIL_HEIGHT";
    private final static String HAS_ANIM = "HAS_ANIM";

    @BindView(R.id.vp_photo_gallery)
    ViewPager vpPhotoGallery;

    @BindView(R.id.tv_current_item)
    TextView tvCurrentItem;

    @BindView(R.id.pi_circle_point)
    PointIndicator piCirclePoint;

    private int thumbnailTop = 0;
    private int thumbnailLeft = 0;
    private int thumbnailWidth = 0;
    private int thumbnailHeight = 0;

    private boolean hasAnim = false;
    private final ColorMatrix colorizerMatrix = new ColorMatrix();
    private int currentItem = 0;
    private ArrayList<String> paths;

    public static void start(Context context, List<String> paths, int currentItem, int[] screenLocation, int thumbnailWidth, int thumbnailHeight) {
        Bundle args = new Bundle();
        args.putStringArray(PHOTO_PATHS, paths.toArray(new String[paths.size()]));
        args.putInt(CURRENT_ITEM, currentItem);
        args.putInt(THUMBNAIL_LEFT, screenLocation[0]);
        args.putInt(THUMBNAIL_TOP, screenLocation[1]);
        args.putInt(THUMBNAIL_WIDTH, thumbnailWidth);
        args.putInt(THUMBNAIL_HEIGHT, thumbnailHeight);
        args.putBoolean(HAS_ANIM, true);

        Intent intent = new Intent(context, PhotoGalleryActivity.class);
        intent.putExtras(args);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_photo_gallery;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        //不能滑动返回
        setSwipeBackEnable(false);

        paths = new ArrayList<>();

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            String[] pathArr = bundle.getStringArray(PHOTO_PATHS);
            paths.clear();
            if (pathArr != null) {

                paths = new ArrayList<>(Arrays.asList(pathArr));
            }

            hasAnim = bundle.getBoolean(HAS_ANIM);
            currentItem = bundle.getInt(CURRENT_ITEM);
            thumbnailTop = bundle.getInt(THUMBNAIL_TOP);
            thumbnailLeft = bundle.getInt(THUMBNAIL_LEFT);
            thumbnailWidth = bundle.getInt(THUMBNAIL_WIDTH);
            thumbnailHeight = bundle.getInt(THUMBNAIL_HEIGHT);
        }

        PhotoGalleryAdapter mPagerAdapter = new PhotoGalleryAdapter(Glide.with(this), paths);

        vpPhotoGallery.setPageTransformer(true, new RotationAlphaTransformer());
        vpPhotoGallery.setOffscreenPageLimit(3);
        vpPhotoGallery.setAdapter(mPagerAdapter);
        vpPhotoGallery.setCurrentItem(currentItem);
        vpPhotoGallery.setPageMargin((int) (getResources().getDisplayMetrics().density * 30));

        piCirclePoint.setViewPager(vpPhotoGallery);
        piCirclePoint.setIndicatorNum(mPagerAdapter.getCount());

        setCurrentItem(currentItem);

        // Only run the animation if we're coming from the parent activity, not if
        // we're recreated automatically by the window manager (e.g., device rotation)
        if (savedInstanceState == null && hasAnim) {
            ViewTreeObserver observer = vpPhotoGallery.getViewTreeObserver();
            observer.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {

                    vpPhotoGallery.getViewTreeObserver().removeOnPreDrawListener(this);

                    // Figure out where the thumbnail and full size versions are, relative
                    // to the screen and each other
                    int[] screenLocation = new int[2];
                    vpPhotoGallery.getLocationOnScreen(screenLocation);
                    thumbnailLeft = thumbnailLeft - screenLocation[0];
                    thumbnailTop = thumbnailTop - screenLocation[1];

                    runEnterAnimation();

                    return true;
                }
            });
        }
    }

    private void setCurrentItem(int position) {
        if (!paths.isEmpty()) {
            String currentItem = "<big><font color=\"" + ResourceUtils.getColor(R.color.color_light_purple) + "\">" + (position + 1) + "</font></big>" + "/" + paths.size();
            tvCurrentItem.setText(Html.fromHtml(currentItem));
        }
    }

    /**
     * The enter animation scales the picture in from its previous thumbnail
     * size/location, colorizing it in parallel. In parallel, the background of the
     * activity is fading in. When the pictue is in place, the text description
     * drops down.
     */
    private void runEnterAnimation() {
        final long duration = ANIM_DURATION;

        // Set starting values for properties we're going to animate. These
        // values scale and position the full size version down to the thumbnail
        // size/location, from which we'll animate it back up
        ViewHelper.setPivotX(vpPhotoGallery, 0);
        ViewHelper.setPivotY(vpPhotoGallery, 0);
        ViewHelper.setScaleX(vpPhotoGallery, (float) thumbnailWidth / vpPhotoGallery.getWidth());
        ViewHelper.setScaleY(vpPhotoGallery, (float) thumbnailHeight / vpPhotoGallery.getHeight());
        ViewHelper.setTranslationX(vpPhotoGallery, thumbnailLeft);
        ViewHelper.setTranslationY(vpPhotoGallery, thumbnailTop);

        // Animate scale and translation to go from thumbnail to full size
        ViewPropertyAnimator.animate(vpPhotoGallery)
                .setDuration(duration)
                .scaleX(1)
                .scaleY(1)
                .translationX(0)
                .translationY(0)
                .setInterpolator(new DecelerateInterpolator());

        // Fade in the black background
        ObjectAnimator bgAnim = ObjectAnimator.ofInt(vpPhotoGallery.getBackground(), "alpha", 0, 255);
        bgAnim.setDuration(duration);
        bgAnim.start();

        // Animate a color filter to take the image from grayscale to full color.
        // This happens in parallel with the image scaling and moving into place.
        ObjectAnimator colorizer = ObjectAnimator.ofFloat(mContext, "saturation", 0, 1);
        colorizer.setDuration(duration);
        colorizer.start();

    }


    /**
     * The exit animation is basically a reverse of the enter animation, except that if
     * the orientation has changed we simply scale the picture back into the center of
     * the screen.
     *
     * @param endAction This action gets run after the animation completes (this is
     *                  when we actually switch activities)
     */
    public void runExitAnimation(final Runnable endAction) {

        if (!hasAnim) {
            endAction.run();
            return;
        }

        final long duration = ANIM_DURATION;

        // Animate image back to thumbnail size/location
        ViewPropertyAnimator.animate(vpPhotoGallery)
                .setDuration(duration)
                .setInterpolator(new AccelerateInterpolator())
                .scaleX((float) thumbnailWidth / vpPhotoGallery.getWidth())
                .scaleY((float) thumbnailHeight / vpPhotoGallery.getHeight())
                .translationX(thumbnailLeft)
                .translationY(thumbnailTop)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        endAction.run();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                    }
                });

        // Fade out background
        ObjectAnimator bgAnim = ObjectAnimator.ofInt(vpPhotoGallery.getBackground(), "alpha", 0);
        bgAnim.setDuration(duration);
        bgAnim.start();

        // Animate a color filter to take the image back to grayscale,
        // in parallel with the image scaling and moving into place.
        ObjectAnimator colorizer = ObjectAnimator.ofFloat(mContext, "saturation", 1, 0);
        colorizer.setDuration(duration);
        colorizer.start();
    }

    /**
     * This is called by the colorizing animator. It sets a saturation factor that is then
     * passed onto a filter on the picture's drawable.
     *
     * @param value saturation
     */
    public void setSaturation(float value) {
        colorizerMatrix.setSaturation(value);
        ColorMatrixColorFilter colorizerFilter = new ColorMatrixColorFilter(colorizerMatrix);
        vpPhotoGallery.getBackground().setColorFilter(colorizerFilter);
    }


    public ViewPager getViewPager() {
        return vpPhotoGallery;
    }


    public ArrayList<String> getPaths() {
        return paths;
    }


    public int getCurrentItem() {
        return vpPhotoGallery.getCurrentItem();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        paths.clear();
        paths = null;

        if (vpPhotoGallery != null) {
            vpPhotoGallery.setAdapter(null);
        }
    }

    /**
     * Overriding this method allows us to run our exit animation first, then exiting
     * the activity when it complete.
     */
    @Override
    public void onBackPressed() {
        if (!isFinishing()) {
            runExitAnimation(new Runnable() {
                public void run() {
                    finish();
                }
            });
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void setListeners() {
        vpPhotoGallery.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                hasAnim = currentItem == position;
                setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
