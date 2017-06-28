package com.livelearn.ignorance.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Json解析
 * Created on 2017/3/27.
 */

public class GsonUtils {

    public static <T> T getObjectFromJson(String json, Class<T> classOfT) {
        return new Gson().fromJson(json, classOfT);
    }

    public static <T> T getListFromJson(String json, TypeToken typeToken) {
        return new Gson().fromJson(json, typeToken.getType());
    }
}
