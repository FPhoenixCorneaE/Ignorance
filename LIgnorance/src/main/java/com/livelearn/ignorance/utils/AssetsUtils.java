package com.livelearn.ignorance.utils;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class AssetsUtils {

    /**
     * 读取 assets 文件夹下 Json 文件
     *
     * @param fileName 文件名称
     * @return Json    String
     */
    public static String readFileFromAssets(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            AssetManager assetManager = context.getAssets();
            bufferedReader = new BufferedReader(new InputStreamReader(assetManager.open(fileName), Charset.defaultCharset()));
            String line;
            while (bufferedReader.readLine() != null) {
                line = bufferedReader.readLine();
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                // do nothing
            }
        }
        return stringBuilder.toString();
    }
}
