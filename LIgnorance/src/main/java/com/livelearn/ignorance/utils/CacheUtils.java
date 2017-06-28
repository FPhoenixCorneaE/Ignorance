package com.livelearn.ignorance.utils;

import com.apkfuns.logutils.LogUtils;
import com.livelearn.ignorance.common.FilesDirectory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * 序列化缓存List<bean>
 */

public class CacheUtils {

    private static final File filesDir = FilesDirectory.FILES_DIR;
    public static final String CACHE_MOVIE = "DouBan_Movie";
    public static final String CACHE_BOOK = "DouBan_Book";
    public static final String CACHE_BOOK_DETAILS = "图书详情";
    public static final String CACHE_BOOK_REVIEW_DETAILS = "书评详情";

    /**
     * 序列化Bean
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static <T> void saveBean(T bean, String cacheType, String name, String start) {
        File nameDir;
        File file;
        if (null != cacheType && !cacheType.isEmpty()) {
            File typeDir = new File(filesDir, cacheType);
            if (!typeDir.exists()) {
                typeDir.mkdir();
            }
            nameDir = new File(typeDir, name);
            if (!nameDir.exists()) {
                nameDir.mkdir();
            }
        } else {
            nameDir = new File(filesDir, name);
            if (!nameDir.exists()) {
                nameDir.mkdir();
            }
        }
        file = new File(nameDir, start);
        if (file.exists()) {
            file.delete();
        }
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(bean);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            LogUtils.e(e);
        }
    }

    /**
     * 序列化List
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static <T> void saveListBean(List<T> list, String cacheType, String name) {
        File file;
        if (!cacheType.isEmpty()) {
            File fileDir = new File(filesDir, cacheType);
            if (!fileDir.exists() || !fileDir.isDirectory()) {
                fileDir.mkdir();
            }
            file = new File(fileDir, name);
        } else {
            file = new File(filesDir, name);
        }
        if (file.exists()) {
            file.delete();
        }
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(list);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            LogUtils.e(e);
        }
    }

    /**
     * 反序列化
     */
    @SuppressWarnings({"ResultOfMethodCallIgnored", "unchecked"})
    public static <E> E readBean(String cacheType, String name, String start) {
        if (name == null || start == null) {
            return null;
        } else {
            File nameDir;
            File file;
            if (!cacheType.isEmpty()) {
                File typeDir = new File(filesDir, cacheType);
                if (!typeDir.exists() || !typeDir.isDirectory()) {
                    typeDir.mkdir();
                }
                nameDir = new File(typeDir, name);
                if (!nameDir.exists()) {
                    nameDir.mkdir();
                }
            } else {
                nameDir = new File(filesDir, name);
                if (!nameDir.exists()) {
                    nameDir.mkdir();
                }
            }
            file = new File(nameDir, start);
            try {
                if (!file.exists()) return null;
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                E list = (E) ois.readObject();
                ois.close();
                return list;
            } catch (Exception e) {
                LogUtils.e(e);
                return null;
            }
        }
    }

    /**
     * 反序列化
     */
    @SuppressWarnings({"ResultOfMethodCallIgnored", "unchecked"})
    public static <E> List<E> readListBean(String cacheType, String name) {
        if (name == null) {
            return null;
        } else {
            File file;
            if (!cacheType.isEmpty()) {
                File fileDir = new File(filesDir, cacheType);
                if (!fileDir.exists() || !fileDir.isDirectory()) {
                    fileDir.mkdir();
                }

                file = new File(fileDir, name);

            } else {
                file = new File(filesDir, name);
            }
            try {
                if (!file.exists()) return null;
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                List<E> list = (List<E>) ois.readObject();
                ois.close();
                return list;
            } catch (Exception e) {
                LogUtils.e(e);
                return null;
            }
        }
    }
}