package com.livelearn.ignorance.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.livelearn.ignorance.R;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Glide加载图片工具类
 * 不要在非主线程里面使用Glide加载图片，如果真的使用了，请把context参数换成getApplicationContext.
 * 创建Glide的主要目的有两个，一个是实现平滑的图片列表滚动效果，另一个是支持远程图片的获取、大小调整和展示。
 * Glide特点:
 * 1.使用简单
 * 2.可配置度高，自适应程度高
 * 3.支持常见图片格式 Jpg png gif webp
 * 4.支持多种数据源  网络、本地、资源、Assets 等
 * 5.高效缓存策略    支持Memory和Disk图片缓存 默认Bitmap格式采用RGB_565内存使用至少减少一半
 * 6.生命周期集成   根据Activity/Fragment生命周期自动管理请求
 * 7.高效处理Bitmap  使用Bitmap Pool使Bitmap复用，主动调用recycle回收需要回收的Bitmap，减小系统回收压力
 */
public class GlideUtils {

    /**
     * 设置图片
     *
     * @param context   绑定Context/Activity/FragmentActivity/Fragment
     * @param imageView 展示图片ImageView或ImageView子类
     * @param picurl    图片路径,可以为一个文件路径、uri或者url,也可以为本地图片，Uri类型图片，资源图片，byte[]类型图片，甚至可以是自定义类型图片
     */
    public static void setupImage(Context context, @NonNull ImageView imageView, Object picurl) {
        setupImage(context, imageView, picurl, -1);
    }

    /**
     * 设置图片
     *
     * @param context    绑定Context/Activity/FragmentActivity/Fragment
     * @param imageView  展示图片ImageView或ImageView子类
     * @param picurl     图片路径
     * @param errorResId 加载错误资源图片id
     */
    public static void setupImage(Context context, @NonNull ImageView imageView, Object picurl, int errorResId) {
        setupImage(context, imageView, picurl, -1, false, true, true, errorResId);
    }

    /**
     * 设置图片
     *
     * @param context       绑定Context/Activity/FragmentActivity/Fragment
     * @param imageView     展示图片ImageView或ImageView子类
     * @param picurl        图片路径
     * @param errorDrawable 加载错误图片
     */
    public static void setupImage(Context context, @NonNull ImageView imageView, Object picurl, Drawable errorDrawable) {
        setupImage(context, imageView, picurl, -1, false, true, true, errorDrawable);
    }

    /**
     * 设置图片
     * <p>
     * 问题：有时候使用placeholder(int Drawble)图片会显示不出来。
     * 解决：可以使用 .dontAnimate()使得图片在有占位图的同时下载并且实时显示出图片
     * 原理： .dontAnimate()只是一个巧妙的角度，归根结底是由于Glide它会为每种大小的ImageView缓存 一次。
     * 尽管一张图片已经缓存了一次，但是假如你要在另外一个地方再次以不同尺寸显示，需要重新下载，调整成新尺寸的大小，然后将这个尺寸的也缓存起来。
     * 所以不同于Picasso，ImageView只要变化尺寸，图片就需要重新下载，所以会有图片不显示的错觉。
     * 所以可以使用.diskCacheStrategy(DiskCacheStrategy.ALL)来缓存所有不同形状的图片，解决问题。
     *
     * @param context               绑定Context/Activity/FragmentActivity/Fragment
     * @param imageView             展示图片ImageView或ImageView子类
     * @param picurl                图片路径
     * @param cornerRadius          圆角大小
     * @param isCircle              是否是圆形
     * @param isCenterCrop          是否中心剪裁
     * @param isCrossFade           是否淡入淡出
     * @param errorResId            加载错误资源图片id
     * @param bitmapTransformations 位图转换，可以转换一种或多种
     */
    @SafeVarargs
    public static void setupImage(Context context, @NonNull ImageView imageView, Object picurl, int cornerRadius, boolean isCircle, boolean isCenterCrop, boolean isCrossFade, int errorResId, Transformation<Bitmap>... bitmapTransformations) {
        DrawableTypeRequest drawableTypeRequest = getDrawableTypeRequest(context, picurl);
        if (isCenterCrop)
            drawableTypeRequest.centerCrop();

        if (isCrossFade)
            drawableTypeRequest.crossFade();

        if (bitmapTransformations != null && bitmapTransformations.length > 0)
            drawableTypeRequest.bitmapTransform(bitmapTransformations);

        if (cornerRadius == -1) {
            if (isCircle) {
                RoundedBitmapDrawable errorDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), BitmapFactory.decodeResource(context.getResources(), errorResId));
                errorDrawable.setCircular(true);
                drawableTypeRequest.dontAnimate()
                        .placeholder(errorDrawable)
                        .error(errorDrawable);
            } else {
                Drawable errorDrawable;
                if (errorResId == -1) {
                    errorResId = R.color.color_light_gray;
                    errorDrawable = new ColorDrawable(ContextCompat.getColor(context, errorResId));
                } else {
                    errorDrawable = ContextCompat.getDrawable(context, errorResId);
                }
                drawableTypeRequest.dontAnimate()
                        .placeholder(errorDrawable)
                        .error(errorDrawable);
            }
        } else {
            RoundedBitmapDrawable errorDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), BitmapFactory.decodeResource(context.getResources(), errorResId));
            errorDrawable.setCornerRadius(cornerRadius);
            drawableTypeRequest.dontAnimate()
                    .placeholder(errorDrawable)
                    .error(errorDrawable);
        }
        drawableTypeRequest.into(imageView);
    }

    /**
     * 设置图片
     *
     * @param context               绑定Context/Activity/FragmentActivity/Fragment
     * @param imageView             展示图片ImageView或ImageView子类
     * @param picurl                图片路径
     * @param cornerRadius          圆角大小
     * @param isCircle              是否是圆形
     * @param isCenterCrop          是否中心剪裁
     * @param isCrossFade           是否淡入淡出
     * @param errorDrawable         加载错误图片
     * @param bitmapTransformations 位图转换，可以转换一种或多种
     */
    @SafeVarargs
    public static void setupImage(Context context, @NonNull ImageView imageView, Object picurl, int cornerRadius, boolean isCircle, boolean isCenterCrop, boolean isCrossFade, Drawable errorDrawable, Transformation<Bitmap>... bitmapTransformations) {
        DrawableTypeRequest drawableTypeRequest = getDrawableTypeRequest(context, picurl);
        if (isCenterCrop) {
            drawableTypeRequest.centerCrop();
        }
        if (isCrossFade) {
            drawableTypeRequest.crossFade();
        }
        if (bitmapTransformations != null && bitmapTransformations.length > 0)
            drawableTypeRequest.bitmapTransform(bitmapTransformations);

        if (errorDrawable == null)
            errorDrawable = new ColorDrawable(ContextCompat.getColor(context, R.color.color_light_gray));

        if (cornerRadius == -1) {
            if (isCircle) {
                if (errorDrawable instanceof BitmapDrawable) {
                    RoundedBitmapDrawable errorRoundedDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), BitmapUtils.drawableToBitmap(errorDrawable));
                    errorRoundedDrawable.setCircular(true);
                    drawableTypeRequest.dontAnimate()
                            .placeholder(errorRoundedDrawable)
                            .error(errorRoundedDrawable);
                } else {
                    drawableTypeRequest.dontAnimate()
                            .placeholder(errorDrawable)
                            .error(errorDrawable);
                }
            } else {
                drawableTypeRequest.dontAnimate()
                        .placeholder(errorDrawable)
                        .error(errorDrawable);
            }
        } else {
            if (errorDrawable instanceof BitmapDrawable) {
                RoundedBitmapDrawable errorRoundedDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), BitmapUtils.drawableToBitmap(errorDrawable));
                errorRoundedDrawable.setCornerRadius(cornerRadius);
                drawableTypeRequest.dontAnimate()
                        .placeholder(errorRoundedDrawable)
                        .error(errorRoundedDrawable);
            } else {
                drawableTypeRequest.dontAnimate()
                        .placeholder(errorDrawable)
                        .error(errorDrawable);
            }
        }
        drawableTypeRequest.into(imageView);
    }

    /**
     * DecodeFormat 图像质量，默认情况下Glide使用RGB_565,因为它只需要两个字节,比使用高质量和系统默认ARGB_8888节省一半的内存。然而RGB_565在某些图像条带会有问题，也不支持透明度
     * setMemoryCache 设置最大的内存缓存，单位为bytes
     * setBitmapPool 设置位图的大小或实现池，单位为bytes
     * thumbnail 缩略图支持:这样会先加载缩略图，然后在加载全图，参数为缩略比例
     * DiskCacheStrategy 策略解说：all:缓存源资源和转换后的资源 none:不作任何磁盘缓存 source:缓存源资源 result：缓存转换后的资源
     *
     * @param context 绑定Context/Activity/FragmentActivity/Fragment
     * @param picurl  图片路径
     * @return DrawableTypeRequest 图片类型请求
     */
    @NonNull
    private static DrawableTypeRequest getDrawableTypeRequest(Context context, Object picurl) {
        new GlideBuilder(context)
                .setBitmapPool(new LruBitmapPool(2 * 1024 * 1024))
                .setMemoryCache(new LruResourceCache(100 * 1024 * 1024))
                .setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
        RequestManager requestManager = Glide.with(context);
        DrawableTypeRequest drawableTypeRequest = requestManager.load(picurl);
        drawableTypeRequest.thumbnail(0.5f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(false);
        return drawableTypeRequest;
    }

    /**
     * 设置圆角图片
     *
     * @param context      绑定Context/Activity/FragmentActivity/Fragment
     * @param imageView    展示图片ImageView或ImageView子类
     * @param picurl       图片路径
     * @param cornerRadius 圆角大小
     */
    public static void setupRoundedImage(Context context, @NonNull ImageView imageView, Object picurl, int cornerRadius) {
        setupRoundedImage(context, imageView, picurl, cornerRadius, -1);
    }

    /**
     * 设置圆角图片
     *
     * @param context      绑定Context/Activity/FragmentActivity/Fragment
     * @param imageView    展示图片ImageView或ImageView子类
     * @param picurl       图片路径
     * @param cornerRadius 圆角大小
     * @param errorResId   加载错误资源图片id
     */
    public static void setupRoundedImage(Context context, @NonNull ImageView imageView, Object picurl, int cornerRadius, int errorResId) {
        setupRoundedImage(context, imageView, picurl, cornerRadius, errorResId, true, true);
    }

    /**
     * 设置圆角图片
     *
     * @param context       绑定Context/Activity/FragmentActivity/Fragment
     * @param imageView     展示图片ImageView或ImageView子类
     * @param picurl        图片路径
     * @param cornerRadius  圆角大小
     * @param errorDrawable 加载错误图片
     */
    public static void setupRoundedImage(Context context, @NonNull ImageView imageView, Object picurl, int cornerRadius, Drawable errorDrawable) {
        setupRoundedImage(context, imageView, picurl, cornerRadius, true, true, errorDrawable);
    }

    /**
     * 设置圆角图片
     *
     * @param context      绑定Context/Activity/FragmentActivity/Fragment
     * @param imageView    展示图片ImageView或ImageView子类
     * @param picurl       图片路径
     * @param cornerRadius 圆角大小
     * @param isCenterCrop 是否中心剪裁
     * @param isCrossFade  是否淡入淡出
     * @param errorResId   加载错误资源图片id
     */
    public static void setupRoundedImage(Context context, @NonNull ImageView imageView, Object picurl, int cornerRadius, int errorResId, boolean isCenterCrop, boolean isCrossFade) {
        setupImage(context, imageView, picurl, cornerRadius, false, isCenterCrop, isCrossFade, errorResId, new RoundedCornersTransformation(context, cornerRadius, 0, RoundedCornersTransformation.CornerType.ALL));
    }

    /**
     * 设置圆角图片
     *
     * @param context       绑定Context/Activity/FragmentActivity/Fragment
     * @param imageView     展示图片ImageView或ImageView子类
     * @param picurl        图片路径
     * @param cornerRadius  圆角大小
     * @param isCenterCrop  是否中心剪裁
     * @param isCrossFade   是否淡入淡出
     * @param errorDrawable 加载错误图片
     */
    public static void setupRoundedImage(Context context, @NonNull ImageView imageView, Object picurl, int cornerRadius, boolean isCenterCrop, boolean isCrossFade, Drawable errorDrawable) {
        setupImage(context, imageView, picurl, cornerRadius, false, isCenterCrop, isCrossFade, errorDrawable, new RoundedCornersTransformation(context, cornerRadius, 0, RoundedCornersTransformation.CornerType.ALL));
    }

    /**
     * 设置圆形图片
     *
     * @param context   绑定Context/Activity/FragmentActivity/Fragment
     * @param imageView 展示图片ImageView或ImageView子类
     * @param picurl    图片路径
     */
    public static void setupCircleImage(Context context, @NonNull ImageView imageView, Object picurl) {
        setupCircleImage(context, imageView, picurl, -1);
    }

    /**
     * 设置圆形图片
     *
     * @param context    绑定Context/Activity/FragmentActivity/Fragment
     * @param imageView  展示图片ImageView或ImageView子类
     * @param picurl     图片路径
     * @param errorResId 加载错误资源图片id
     */
    public static void setupCircleImage(Context context, @NonNull ImageView imageView, Object picurl, int errorResId) {
        setupCircleImage(context, imageView, picurl, true, true, errorResId);
    }

    /**
     * 设置圆形图片
     *
     * @param context       绑定Context/Activity/FragmentActivity/Fragment
     * @param imageView     展示图片ImageView或ImageView子类
     * @param picurl        图片路径
     * @param errorDrawable 加载错误图片
     */
    public static void setupCircleImage(Context context, @NonNull ImageView imageView, Object picurl, Drawable errorDrawable) {
        setupCircleImage(context, imageView, picurl, true, true, errorDrawable);
    }

    /**
     * 设置圆形图片
     *
     * @param context      绑定Context/Activity/FragmentActivity/Fragment
     * @param imageView    展示图片ImageView或ImageView子类
     * @param picurl       图片路径
     * @param isCenterCrop 是否中心剪裁
     * @param isCrossFade  是否淡入淡出
     * @param errorResId   加载错误资源图片id
     */
    public static void setupCircleImage(Context context, @NonNull ImageView imageView, Object picurl, boolean isCenterCrop, boolean isCrossFade, int errorResId) {
        setupImage(context, imageView, picurl, -1, true, isCenterCrop, isCrossFade, errorResId, new CropCircleTransformation(context));
    }

    /**
     * 设置圆形图片
     *
     * @param context       绑定Context/Activity/FragmentActivity/Fragment
     * @param imageView     展示图片ImageView或ImageView子类
     * @param picurl        图片路径
     * @param isCenterCrop  是否中心剪裁
     * @param isCrossFade   是否淡入淡出
     * @param errorDrawable 加载错误图片
     */
    public static void setupCircleImage(Context context, @NonNull ImageView imageView, Object picurl, boolean isCenterCrop, boolean isCrossFade, Drawable errorDrawable) {
        setupImage(context, imageView, picurl, -1, true, isCenterCrop, isCrossFade, errorDrawable, new CropCircleTransformation(context));
    }

    /**
     * 设置灰度图片,默认圆形
     *
     * @param context   绑定Context/Activity/FragmentActivity/Fragment
     * @param imageView 展示图片ImageView或ImageView子类
     * @param picurl    图片路径
     */
    public static void setupGrayscaleImage(Context context, @NonNull ImageView imageView, Object picurl) {
        setupGrayscaleImage(context, imageView, picurl, -1);
    }

    /**
     * 设置灰度图片,默认圆形
     *
     * @param context    绑定Context/Activity/FragmentActivity/Fragment
     * @param imageView  展示图片ImageView或ImageView子类
     * @param picurl     图片路径
     * @param errorResId 加载错误资源图片id
     */
    public static void setupGrayscaleImage(Context context, @NonNull ImageView imageView, Object picurl, int errorResId) {
        setupGrayscaleCircleImage(context, imageView, picurl, true, true, errorResId);
    }

    /**
     * 设置灰度图片,默认圆形
     *
     * @param context       绑定Context/Activity/FragmentActivity/Fragment
     * @param imageView     展示图片ImageView或ImageView子类
     * @param picurl        图片路径
     * @param errorDrawable 加载错误图片
     */
    public static void setupGrayscaleImage(Context context, @NonNull ImageView imageView, Object picurl, Drawable errorDrawable) {
        setupGrayscaleCircleImage(context, imageView, picurl, true, true, errorDrawable);
    }

    /**
     * 设置灰度圆形图片
     *
     * @param context      绑定Context/Activity/FragmentActivity/Fragment
     * @param imageView    展示图片ImageView或ImageView子类
     * @param picurl       图片路径
     * @param isCenterCrop 是否中心剪裁
     * @param isCrossFade  是否淡入淡出
     * @param errorResId   加载错误资源图片id
     */
    public static void setupGrayscaleCircleImage(Context context, @NonNull ImageView imageView, Object picurl, boolean isCenterCrop, boolean isCrossFade, int errorResId) {
        setupImage(context, imageView, picurl, -1, true, isCenterCrop, isCrossFade, errorResId, new GrayscaleTransformation(context));
    }

    /**
     * 设置灰度圆形图片
     *
     * @param context       绑定Context/Activity/FragmentActivity/Fragment
     * @param imageView     展示图片ImageView或ImageView子类
     * @param picurl        图片路径
     * @param isCenterCrop  是否中心剪裁
     * @param isCrossFade   是否淡入淡出
     * @param errorDrawable 加载错误图片
     */
    public static void setupGrayscaleCircleImage(Context context, @NonNull ImageView imageView, Object picurl, boolean isCenterCrop, boolean isCrossFade, Drawable errorDrawable) {
        setupImage(context, imageView, picurl, -1, true, isCenterCrop, isCrossFade, errorDrawable, new GrayscaleTransformation(context));
    }

    /**
     * 设置灰度圆角图片
     *
     * @param context      绑定Context/Activity/FragmentActivity/Fragment
     * @param imageView    展示图片ImageView或ImageView子类
     * @param picurl       图片路径
     * @param cornerRadius 圆角大小
     * @param isCenterCrop 是否中心剪裁
     * @param isCrossFade  是否淡入淡出
     * @param errorResId   加载错误资源图片id
     */
    public static void setupGrayscaleRoundedImage(Context context, @NonNull ImageView imageView, Object picurl, int cornerRadius, boolean isCenterCrop, boolean isCrossFade, int errorResId) {
        setupImage(context, imageView, picurl, cornerRadius, false, isCenterCrop, isCrossFade, errorResId, new GrayscaleTransformation(context));
    }

    /**
     * 设置灰度圆角图片
     *
     * @param context       绑定Context/Activity/FragmentActivity/Fragment
     * @param imageView     展示图片ImageView或ImageView子类
     * @param picurl        图片路径
     * @param cornerRadius  圆角大小
     * @param isCenterCrop  是否中心剪裁
     * @param isCrossFade   是否淡入淡出
     * @param errorDrawable 加载错误图片
     */
    public static void setupGrayscaleRoundedImage(Context context, @NonNull ImageView imageView, Object picurl, int cornerRadius, boolean isCenterCrop, boolean isCrossFade, Drawable errorDrawable) {
        setupImage(context, imageView, picurl, cornerRadius, true, isCenterCrop, isCrossFade, errorDrawable, new GrayscaleTransformation(context));
    }
}
