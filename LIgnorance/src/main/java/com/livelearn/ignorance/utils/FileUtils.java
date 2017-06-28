/*
 * Copyright (C) 2016 venshine.cn@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.livelearn.ignorance.utils;

import com.apkfuns.logutils.LogUtils;
import com.livelearn.ignorance.common.FilesDirectory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import cn.finalteam.toolsfinal.StringUtils;

/**
 * 文件操作
 *
 * @author venshine
 */
public class FileUtils {

    private static final String FILE_SUFFIX_SEPARATOR = ".";
    private static final File externalCacheDir = FilesDirectory.EXTERNAL_CACHE_DIR;

    /**
     * @return OkHttp3Cache缓存目录
     */
    public static File getOkHttpCacheDirectory() {
        return getCacheDirectory(externalCacheDir, "OkHttp3Cache");
    }

    /**
     * @return 创建缓存目录
     */
    public static File getCacheDirectory(File parentDirectory, String childDirectory) {
        File file = new File(parentDirectory, childDirectory);
        if (!file.exists()) {
            boolean b = file.mkdirs();
            LogUtils.e(childDirectory + "缓存文件夹不存在，创建" + (b ? "成功" : "失败"));
        } else {
            LogUtils.e(childDirectory + "缓存文件夹已存在");
        }
        return file;
    }

    /**
     * Read file
     */
    public static StringBuilder readFile(String filePath, String charsetName) {
        File file = new File(filePath);
        StringBuilder fileContent = new StringBuilder("");
        if (!file.isFile()) {
            return null;
        }

        BufferedReader reader = null;
        try {
            InputStreamReader is = new InputStreamReader(new FileInputStream(file), charsetName);
            reader = new BufferedReader(is);
            String line;
            while ((line = reader.readLine()) != null) {
                if (!fileContent.toString().equals("")) {
                    fileContent.append("\r\n");
                }
                fileContent.append(line);
            }
            return fileContent;
        } catch (IOException e) {
            throw new RuntimeException("IOException", e);
        } finally {
            IOUtils.close(reader);
        }
    }

    /**
     * Write file
     */
    public static boolean writeFile(String filePath, String content, boolean append) {
        if (StringUtils.isEmpty(content)) {
            return false;
        }
        FileWriter fileWriter = null;
        try {
            makeDirs(filePath);
            fileWriter = new FileWriter(filePath, append);
            fileWriter.write(content);
            return true;
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred. ", e);
        } finally {
            IOUtils.close(fileWriter);
        }
    }

    /**
     * write file, the string will be written to the begin of the file
     */
    public static boolean writeFile(String filePath, String content) {
        return writeFile(filePath, content, false);
    }

    /**
     * Write file
     */
    public static boolean writeFile(String filePath, InputStream is) {
        return writeFile(filePath, is, false);
    }

    /**
     * Write file
     */
    public static boolean writeFile(String filePath, InputStream is, boolean append) {
        return writeFile(filePath != null ? new File(filePath) : null, is, append);
    }

    /**
     * Write file
     */
    public static boolean writeFile(File file, InputStream is) {
        return writeFile(file, is, false);
    }

    /**
     * Write file
     */
    public static boolean writeFile(File file, InputStream is, boolean append) {
        OutputStream o = null;
        try {
            makeDirs(file.getAbsolutePath());
            o = new FileOutputStream(file, append);
            byte data[] = new byte[1024];
            int length = -1;
            while ((length = is.read(data)) != -1) {
                o.write(data, 0, length);
            }
            o.flush();
            return true;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("FileNotFoundException", e);
        } catch (IOException e) {
            throw new RuntimeException("IOException", e);
        } finally {
            IOUtils.close(o);
            IOUtils.close(is);
        }
    }

    /**
     * Move file
     */
    public static void moveFile(String srcFilePath, String destFilePath) throws FileNotFoundException {
        if (StringUtils.isEmpty(srcFilePath) || StringUtils.isEmpty(destFilePath)) {
            throw new RuntimeException("Both srcFilePath and destFilePath cannot be null.");
        }
        moveFile(new File(srcFilePath), new File(destFilePath));
    }

    /**
     * Move file
     */
    public static void moveFile(File srcFile, File destFile) throws FileNotFoundException {
        boolean rename = srcFile.renameTo(destFile);
        if (!rename) {
            copyFile(srcFile.getAbsolutePath(), destFile.getAbsolutePath());
            deleteFile(srcFile.getAbsolutePath());
        }
    }

    /**
     * Copy file
     */
    public static boolean copyFile(String srcFilePath, String destFilePath) throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(srcFilePath);
        return writeFile(destFilePath, inputStream);
    }

    /**
     * rename file
     */
    public static boolean renameFile(File file, String newFileName) {
        File newFile = null;
        if (file.isDirectory()) {
            newFile = new File(file.getParentFile(), newFileName);
        } else {
            String temp = newFileName
                    + file.getName().substring(
                    file.getName().lastIndexOf('.'));
            newFile = new File(file.getParentFile(), temp);
        }
        return file.renameTo(newFile);
    }

    /**
     * Get file name without suffix
     */
    public static String getFileNameWithoutSuffix(String filePath) {
        if (StringUtils.isEmpty(filePath)) {
            return filePath;
        }
        int suffix = filePath.lastIndexOf(FILE_SUFFIX_SEPARATOR);
        int fp = filePath.lastIndexOf(File.separator);
        if (fp == -1) {
            return (suffix == -1 ? filePath : filePath.substring(0, suffix));
        }
        if (suffix == -1) {
            return filePath.substring(fp + 1);
        }
        return (fp < suffix ? filePath.substring(fp + 1, suffix) : filePath.substring(fp + 1));
    }

    /**
     * Get file name
     */
    public static String getFileName(String filePath) {
        if (StringUtils.isEmpty(filePath)) {
            return filePath;
        }
        int fp = filePath.lastIndexOf(File.separator);
        return (fp == -1) ? filePath : filePath.substring(fp + 1);
    }

    /**
     * Get folder name
     */
    public static String getFolderName(String filePath) {
        if (StringUtils.isEmpty(filePath)) {
            return filePath;
        }
        int fp = filePath.lastIndexOf(File.separator);
        return (fp == -1) ? "" : filePath.substring(0, fp);
    }

    /**
     * Get suffix of file
     */
    public static String getFileSuffix(String filePath) {
        if (StringUtils.isEmpty(filePath)) {
            return filePath;
        }
        int suffix = filePath.lastIndexOf(FILE_SUFFIX_SEPARATOR);
        int fp = filePath.lastIndexOf(File.separator);
        if (suffix == -1) {
            return "";
        }
        return (fp >= suffix) ? "" : filePath.substring(suffix + 1);
    }

    /**
     * Create the directory
     */
    public static boolean makeDirs(String filePath) {
        String folderName = getFolderName(filePath);
        if (StringUtils.isEmpty(folderName)) {
            return false;
        }
        File folder = new File(folderName);
        return (folder.exists() && folder.isDirectory()) || folder.mkdirs();
    }

    /**
     * Judge whether a file is exist
     */
    public static boolean isFileExist(String filePath) {
        if (StringUtils.isEmpty(filePath)) {
            return false;
        }
        File file = new File(filePath);
        return (file.exists() && file.isFile());
    }

    /**
     * Judge whether a folder is exist
     */
    public static boolean isFolderExist(String directoryPath) {
        if (StringUtils.isEmpty(directoryPath)) {
            return false;
        }
        File dire = new File(directoryPath);
        return (dire.exists() && dire.isDirectory());
    }

    /**
     * Delete file or folder
     */
    public static boolean deleteFile(String path) {
        if (StringUtils.isEmpty(path)) {
            return true;
        }

        File file = new File(path);
        if (!file.exists()) {
            return true;
        }
        if (file.isFile()) {
            return file.delete();
        }
        if (!file.isDirectory()) {
            return false;
        }
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                if (f.isFile()) {
                    f.delete();
                } else if (f.isDirectory()) {
                    deleteFile(f.getAbsolutePath());
                }
            }
        }
        return file.delete();
    }

    /**
     * Delete file or folder
     */
    public static boolean deleteFile(File file) {
        if (!file.exists()) {
            return true;
        }
        if (file.isFile()) {
            return file.delete();
        }
        if (!file.isDirectory()) {
            return false;
        }
        if (file.isDirectory()) {
            File[] childFile = file.listFiles();
            if (childFile == null || childFile.length == 0) {
                return file.delete();
            }
            for (File f : childFile) {
                deleteFile(f);
            }
        }
        return file.delete();
    }

    /**
     * Get file size
     */
    public static long getFileSize(String path) {
        if (StringUtils.isEmpty(path)) {
            return -1;
        }
        File file = new File(path);
        return (file.exists() && file.isFile() ? file.length() : -1);
    }

    /**
     * Get folder size
     */
    public static long getFolderSize(File file) throws Exception {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (File aFileList : fileList) {
                if (aFileList.isDirectory()) {
                    size = size + getFolderSize(aFileList);
                } else {
                    size = size + aFileList.length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }
}
