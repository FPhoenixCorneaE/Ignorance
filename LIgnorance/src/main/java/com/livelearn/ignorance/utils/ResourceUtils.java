package com.livelearn.ignorance.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 资源操作
 */
public class ResourceUtils {

    @SuppressLint("StaticFieldLeak")
    private static Context mContext;

    /**
     * Init method, always by invoked in Application
     */
    public static void init(Context context) {
        if (null == context) {
            throw new IllegalArgumentException("context cannot be null.");
        }
        mContext = context.getApplicationContext();
    }

    /**
     * Return a resource identifier for the given resource name. A fully qualified resource name is of the form
     * "package:type/entry". The first two components (package and type) are optional if defType and defPackage,
     * respectively, are specified here.
     */
    public static int getResourceByName(String drawableName, String defType) {
        return mContext.getResources().getIdentifier(drawableName, defType, AppUtils.getPackageName(mContext));
    }

    /**
     * Get raw file, ui/raw/file
     */
    public static InputStream getRaw(int id) {
        return mContext.getResources().openRawResource(id);
    }

    /**
     * Get raw file descriptor, ui/raw/file. This function only works for resources that are stored in the package as
     * uncompressed data, which typically includes things like mp3 files and png images.
     */
    public static AssetFileDescriptor getRawFd(int id) {
        return mContext.getResources().openRawResourceFd(id);
    }

    /**
     * Get raw text file, ui/raw/text
     */
    public String getRawText(int id) {
        try {
            InputStreamReader inputReader = new InputStreamReader(getRaw(id));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            StringBuilder result = new StringBuilder();
            while ((line = bufReader.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get xml file, ui/xml/file
     */
    public static XmlResourceParser getXml(int id) {
        return mContext.getResources().getXml(id);
    }

    /**
     * Get drawable, ui/drawable/file
     */
    public static Drawable getDrawable(int id) {
        return ContextCompat.getDrawable(mContext, id);
    }

    /**
     * Get string, ui/values/strings.xml
     */
    public static String getString(int id) {
        return mContext.getResources().getString(id);
    }

    /**
     * Get string array, ui/values/strings.xml
     */
    public static String[] getStringArray(int id) {
        return mContext.getResources().getStringArray(id);
    }

    /**
     * Get int array, ui/values/strings.xml
     */
    public static int[] getIntArray(int id) {
        return mContext.getResources().getIntArray(id);
    }

    /**
     * Get color, ui/values/colors.xml
     */
    public static int getColor(int id) {
        return ContextCompat.getColor(mContext, id);
    }

    /**
     * Get color state list, ui/values/colors.xml
     */
    public static ColorStateList getColorStateList(int id) {
        return ContextCompat.getColorStateList(mContext, id);
    }

    /**
     * Get dimension, ui/values/dimens.xml
     *
     * @return View dimension value multiplied by the appropriate metric.
     * 获取某个dimen的值,如果是dp或sp的单位,将其乘以density,如果是px,则不乘   返回float
     */
    public static float getDimension(int id) {
        return mContext.getResources().getDimension(id);
    }

    /**
     * Get dimension, ui/values/dimens.xml
     *
     * @return View dimension value multiplied by the appropriate metric and truncated to integer pixels.
     * 获取某个dimen的值,如果是dp或sp的单位,将其乘以density,如果是px,则不乘  返回int
     */
    public static int getDimensionPixelOffset(int id) {
        return mContext.getResources().getDimensionPixelOffset(id);
    }

    /**
     * Get dimension, ui/values/dimens.xml
     *
     * @return View dimension value multiplied by the appropriate metric and truncated to integer pixels.
     * 不管写的是dp还是sp还是px,都会乘以density.
     */
    public static int getDimensionPixelSize(int id) {
        return mContext.getResources().getDimensionPixelSize(id);
    }

}
