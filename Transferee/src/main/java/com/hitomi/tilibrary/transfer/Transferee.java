package com.hitomi.tilibrary.transfer;

import android.content.Context;

import com.hitomi.tilibrary.loader.ImageLoader;
import com.hitomi.tilibrary.loader.NoneImageLoader;
import com.hitomi.tilibrary.style.index.CircleIndexIndicator;
import com.hitomi.tilibrary.style.progress.ProgressBarIndicator;

/**
 * Main workflow: <br/>
 * 1、点击缩略图展示缩略图到 transferee 过渡动画 <br/>
 * 2、显示下载高清图片进度 <br/>
 * 3、加载完成显示高清图片 <br/>
 * 4、高清图支持手势缩放 <br/>
 * 5、关闭 transferee 展示 transferee 到原缩略图的过渡动画 <br/>
 * Created by hitomi on 2017/1/19.
 * <p>
 * email: 196425254@qq.com
 * <p>
 * <p>
 * transferee 支持两种模式：
 * 1.只有原图，就是说九宫格列表中的图片和全屏显示的大图其实来源于一张图片。详见 GlideNoThumActivity 和 UniversalNoThumActivity
 * 2.既有原图，又有缩略图，例如我司使用了阿里云的图片裁剪功能提供了缩略图来源，在列表页使用阿里云裁剪后的缩略图，优化列表数据流量和流畅度，同时又能在详情页或者图片查看器中显示大图。这种情况下也是 transferee 最适合的模式。详见UniversalNormalActivity
 * <p>
 * 在使用 transferee 组件的时候，还需要注意一些问题：
 * 由于不同 ImageLoader 的缓存和图片加载策略不同，所以在使用目前 transferee 中内置的 Glide 或者 Universal-Image-Loader 时，所支持的功能体系也是不一样的：
 * ·使用 Glide 作为 transferee 的图片加载器时，不支持设置 thumbnailImageList 属性，即只支持模式 1
 * ·使用 Glide 作为 transferee 的图片加载器时，如果您的项目中也是使用的 Glide 去加载图片，最好使用 ProgressBarIndicator 作为 transferee 的进度指示器；如果一定要显示出图片的百分比加载进度，即使用 ProgressPieIndicator 的话，那么在显示 transferee 时，应该暂停列表页当前图片的加载。详见 GlideNoThumActivity
 * ·使用 Universal-Image-Loader 作为 transferee 的图片加载器时，且只有原图的场景下，如果您的项目中也是使用的 Universal-Image-Loader 去加载图片，那么 transferee 中将无法显示出当前图片的百分比加载进度，只能使用 ProgressBarIndicator 作为 transferee 的进度指示器。详见 UniversalNoThumActivity
 * ·缩略图的 ScaleType 需要设置为 centerCrop (这个有点废话了...)
 */
public class Transferee {

    private TransDialog transDialog;
    private TransferConfig transConfig;

    /**
     * @param context 上下文环境
     */
    public Transferee(Context context) {
        this.transDialog = new TransDialog(context);
    }

    /**
     * 检查参数，如果必须参数缺少，就使用缺省参数或者抛出异常
     */
    private void checkConfig() {
        if (transConfig.isSourceEmpty())
            throw new IllegalArgumentException("the parameter sourceImageList can't be empty");

        transConfig.setNowThumbnailIndex(transConfig.getNowThumbnailIndex() < 0
                ? 0 : transConfig.getNowThumbnailIndex());

        transConfig.setOffscreenPageLimit(transConfig.getOffscreenPageLimit() <= 0
                ? 1 : transConfig.getOffscreenPageLimit());

        transConfig.setDuration(transConfig.getDuration() <= 0
                ? 300 : transConfig.getDuration());

        transConfig.setProgressIndicator(transConfig.getProgressIndicator() == null
                ? new ProgressBarIndicator() : transConfig.getProgressIndicator());

        transConfig.setIndexIndicator(transConfig.getIndexIndicator() == null
                ? new CircleIndexIndicator() : transConfig.getIndexIndicator());

        transConfig.setImageLoader(transConfig.getImageLoader() == null
                ? new NoneImageLoader() : transConfig.getImageLoader());
    }

    /**
     * 配置 transferee 参数对象
     *
     * @param config 参数对象
     * @return transferee
     */
    public Transferee apply(TransferConfig config) {
        if (!transDialog.isShown()) {
            transConfig = config;
            checkConfig();
            transDialog.applyTransferee(config);
        }
        return this;
    }

    /**
     * 显示 transferee
     */
    public void show() {
        transDialog.showTransferee();
    }

    /**
     * 显示 transferee, 并设置 OnTransfereeChangeListener
     *
     * @param listener {@link TransDialog.OnTransfereeStateChangeListener}
     */
    public void show(TransDialog.OnTransfereeStateChangeListener listener) {
        transDialog.showTransferee(listener);
    }

    /**
     * 关闭 transferee
     */
    public void dismiss() {
        transDialog.dismissTransferee(transConfig);
    }

    /**
     * 清除 transferee 缓存
     */
    public static void clear(ImageLoader imageLoader) {
        imageLoader.clearCache();
    }
}
