package com.livelearn.ignorance.utils;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;

/**
 * Square公司的图形缓存库Picasso工具类
 * Picasso特点：
 * 1.在Adapter中取消了不在视图范围内的ImageView的资源加载,因为可能会产生图片错位;
 * 2.使用复杂的图片转换技术降低内存的使用
 * 3.自带内存和硬盘的二级缓存机制
 * <p>
 * 缺点：
 * 1.功能较为简单-图片加载；
 * 2.性能（加载速度等等）较其他图片加载库（Glide、Fresco）较差；
 * 3.自身无实现“本地缓存”，而是交给了 Square 的另外一个网络库 okhttp 去实现，
 * 这样的好处是可以通过请求 Response Header 中的 Cache-Control 及 Expired 控制图片的过期时间。
 * <p>
 * 如果项目已经使用了 Square 公司的其他开源库（如 Retrofit 或者 OkHttp），在满足需求的前提下建议使用Picasso
 * Created on 2017/4/10.
 */

public class PicassoUtils {

    /**
     * @param context         上下文
     * @param imageUrl        图片网络加载地址
     * @param targetImageView 想进行图片展示的ImageView
     */
    public static void loadImage(Context context, String imageUrl, ImageView targetImageView) {
        Picasso.with(context).load(imageUrl).into(targetImageView);
    }

    /**
     * @param context         上下文
     * @param resourceId      项目资源
     * @param targetImageView 想进行图片展示的ImageView
     */
    public static void loadImage(Context context, int resourceId, ImageView targetImageView) {
        Picasso.with(context).load(resourceId).into(targetImageView);
    }

    /**
     * @param context         上下文
     * @param uri             URI地址,支持任意的URI地址
     * @param targetImageView 想进行图片展示的ImageView
     */
    public static void loadImage(Context context, Uri uri, ImageView targetImageView) {
        Picasso.with(context).load(uri).into(targetImageView);
    }

    /**
     * @param context         上下文
     * @param file            本地文件,这个file并不一定非得是在你的设备中,可以是任意的路径,只要是File路径即可
     * @param targetImageView 想进行图片展示的ImageView
     */
    public static void loadImage(Context context, File file, ImageView targetImageView) {
        Picasso.with(context).load(file).into(targetImageView);
    }

    /**
     * @param context         上下文
     * @param imageUrl        图片网络加载地址
     * @param targetWidth     图片宽度
     * @param targetHeight    图片高度
     * @param targetImageView 想进行图片展示的ImageView
     */
    public static void loadImageResize(Context context, String imageUrl, int targetWidth, int targetHeight, ImageView targetImageView) {
        Picasso.with(context)
                .load(imageUrl)
                //裁剪图片尺寸
                .resize(targetWidth, targetHeight)
                //设置图片圆角
                .centerCrop()
                .into(targetImageView);
    }

    /**
     * @param context         上下文
     * @param imageUrl        图片网络加载地址
     * @param targetImageView 想进行图片展示的ImageView
     */
    public static void loadImage(Context context, String imageUrl, ImageView targetImageView, int placeholderResId, int errorResId) {
        Picasso.with(context)
                .load(imageUrl)
                //加载过程中的图片显示
                .placeholder(placeholderResId)
                //加载失败中的图片显示
                //如果重试3次（下载源代码可以根据需要修改）还是无法成功加载图片，则用错误占位符图片显示。
                .error(errorResId)
                .into(targetImageView);
    }
}
