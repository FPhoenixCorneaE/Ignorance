package com.livelearn.ignorance.common;

import com.livelearn.ignorance.model.bean.UserInfo;

/**
 * 常量
 */
public class Constant {

    /**
     * ViewPager选中
     */
    public static final String ARG_SECTION_NUMBER = "section_number";
    /**
     * 用户信息
     */
    public static final String USER_INFO = "user_info";
    /**
     * 导航状态
     */
    public static final String GUIDE_STATE = "guide_state";
    /**
     * 用户id
     */
    public static final String USER_ID = "user_id";
    /**
     * 用户账号
     */
    public static final String USER_ACCOUNT = "user_account";
    /**
     * 用户昵称
     */
    public static final String USER_NICKNAME = "user_nickname";
    /**
     * 用户头像
     */
    public static final String USER_AVATAR = "user_avatar";
    /**
     * 用户密码
     */
    public static final String USER_PASSWORD = "user_password";
    /**
     * 自动登录
     */
    public static final String AUTO_LOGIN = "auto_login";
    /**
     * 登录状态
     */
    public static final String LOGIN_STATE = "login_state";
    /**
     * 是否记住密码
     */
    public static final String REMEMBER_PASSWORD = "remember_password";
    /**
     * 当前主题
     */
    public static final String CURRENT_THEME = "current_theme";
    /**
     * 数据库版本号
     */
    public static final String DB_VERSION = "dbVersion";
    /**
     * 文件缓存版本号
     */
    public static final String FILE_CACHE_BEAN_VERSION = "FileCacheBeanVersion";
    /**
     * 用户信息实体类
     */
    private volatile static UserInfo userInfo;
    /**
     * Activity退出动画
     */
    public static final String BACK_ANIMATION = "back_animation";
    /**
     * 图片类型
     */
    public static final String PHOTO_BUCKET_FACE = "dd-face";
    public static final String PHOTO_BUCKET_FEED = "dd-feed";
    /**
     * 文件字节数组
     */
    public static final String FILE_BYTE_ARRAY = "file_byte_array";
    /**
     * 位置
     */
    public static final String POSITION = "position";
    /**
     * 上传图片广播
     */
    public static final String BROADCAST_ACTION_UPLOAD_IMAGE = "com.livelearn.ignore.action.UPLOAD_IMAGE";
    /**
     * 上传结果
     */
    public static final String RESULT_UPLOAD_IMAGE = "result_upload_image";
    public static final int RESULT_OK = -1;
    public static final int RESULT_ERROR = 0;
    /**
     * 网络请求
     */
    public static final String ERROR_TITLE = "网络请求失败";
    public static final String ERROR_CONTEXT = "是网络先动的手不怪我";
    public static final String ERROR_BUTTON = "重试";
    //返回空数据
    public static final String EMPTY_BOOKS_TITLE = "这个分类还没书籍";
    public static final String EMPTY_BOOKS_CONTEXT = "宝宝已经在快速整理中了";
    public static final String EMPTY_SEARCH_BOOK_TITLE = "没有找到您想要的小说";
    public static final String EMPTY_SEARCH_BOOK_CONTEXT = "宝宝心里苦!但是宝宝不说";
    /**
     * 书库
     */
    public static final String BOOK_ID = "book_id";
    public static final String BOOK_URL = "book_url";
    public static final String BOOK_NAME = "book_name";
    public static final String BOOK_TYPE = "book_type";
    public static final String BOOK_CLASS = "book_class";
    public static final String BOOK_COLLECTION = "book_collection";
    public static final String BOOK_COLLECTION_CANCEL = "book_collection_cancel";
    /**
     * 图片地址
     */
    public static final String IMAGE_URL = "image_url";
    /**
     * 图片类型
     */
    public static final String IMAGE_TYPE = "image_type";
    /**
     * 已添加的图书标签
     */
    public static final String BOOK_LABEL_ADDED = "book_label_added";

    /**
     * 视频
     */
    public static final String VIDEO_TITLE = "video_title";
    public static final String VIDEO_CATALOG_ID = "video_catalog_id";
    public static final String VIDEO_MEDIA_ID = "video_media_id";


    public static UserInfo getUserInfo() {
        return userInfo;
    }

    public static void setUserInfo(UserInfo userInfo) {
        Constant.userInfo = userInfo;
    }
}
