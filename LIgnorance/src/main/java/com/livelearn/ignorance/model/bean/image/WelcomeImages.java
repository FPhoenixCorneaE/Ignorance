package com.livelearn.ignorance.model.bean.image;

import android.content.Context;
import android.content.res.AssetManager;

import com.apkfuns.logutils.LogUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 启动页（欢迎界面）图片
 */
public class WelcomeImages {

    public List<String> getImgData() {
        List<String> imgs = new ArrayList<>();
        imgs.add("file:///android_asset/welcomeimage/pic_welcome_01.jpg");
        imgs.add("file:///android_asset/welcomeimage/pic_welcome_02.jpg");
        imgs.add("file:///android_asset/welcomeimage/pic_welcome_03.jpg");
        imgs.add("file:///android_asset/welcomeimage/pic_welcome_04.jpg");
        imgs.add("file:///android_asset/welcomeimage/pic_welcome_05.jpg");
        imgs.add("file:///android_asset/welcomeimage/pic_welcome_06.jpg");
        imgs.add("file:///android_asset/welcomeimage/pic_welcome_07.jpg");
        imgs.add("file:///android_asset/welcomeimage/pic_welcome_08.jpg");
        return imgs;
    }

    public List<String> getWelcomeImageData(Context mContext) {
        List<String> imgList = new ArrayList<>();
        AssetManager assetManager = mContext.getAssets();
        try {
            String directory = "file:///android_asset/welcomeimage/";
            String[] imgs = assetManager.list("welcomeimage");
            for (String imgPath : imgs) {
                imgList.add(String.valueOf(directory + imgPath));
            }
        } catch (IOException e) {
            LogUtils.e(e);
        }
        return imgList;
    }
}
