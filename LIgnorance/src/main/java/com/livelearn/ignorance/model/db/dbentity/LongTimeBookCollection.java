package com.livelearn.ignorance.model.db.dbentity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created on 2017/7/17.
 */

@Entity
public class LongTimeBookCollection {

    @Id(autoincrement = true)
    private Long id;

    //书籍名称
    @NotNull
    private String book_name;
    //书籍封面
    @NotNull
    private String book_image_url;
    //书籍作者
    private String book_author;
    //书籍类型
    private String book_type;
    //书籍字数
    private String book_length;
    //书籍进度
    private String book_progress;
    //最后更新时间
    private String book_update_time;
    //书籍下载地址
    @NotNull
    private String book_download;
    //书籍简介
    private String book_introduction;
    //书籍在线阅读地址
    @NotNull
    private String book_read_url;
    //收藏时间
    private String collected_time;
    @Generated(hash = 648213357)
    public LongTimeBookCollection(Long id, @NotNull String book_name,
            @NotNull String book_image_url, String book_author, String book_type,
            String book_length, String book_progress, String book_update_time,
            @NotNull String book_download, String book_introduction,
            @NotNull String book_read_url, String collected_time) {
        this.id = id;
        this.book_name = book_name;
        this.book_image_url = book_image_url;
        this.book_author = book_author;
        this.book_type = book_type;
        this.book_length = book_length;
        this.book_progress = book_progress;
        this.book_update_time = book_update_time;
        this.book_download = book_download;
        this.book_introduction = book_introduction;
        this.book_read_url = book_read_url;
        this.collected_time = collected_time;
    }
    @Generated(hash = 1473279721)
    public LongTimeBookCollection() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getBook_name() {
        return this.book_name;
    }
    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }
    public String getBook_image_url() {
        return this.book_image_url;
    }
    public void setBook_image_url(String book_image_url) {
        this.book_image_url = book_image_url;
    }
    public String getBook_author() {
        return this.book_author;
    }
    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }
    public String getBook_type() {
        return this.book_type;
    }
    public void setBook_type(String book_type) {
        this.book_type = book_type;
    }
    public String getBook_length() {
        return this.book_length;
    }
    public void setBook_length(String book_length) {
        this.book_length = book_length;
    }
    public String getBook_progress() {
        return this.book_progress;
    }
    public void setBook_progress(String book_progress) {
        this.book_progress = book_progress;
    }
    public String getBook_update_time() {
        return this.book_update_time;
    }
    public void setBook_update_time(String book_update_time) {
        this.book_update_time = book_update_time;
    }
    public String getBook_download() {
        return this.book_download;
    }
    public void setBook_download(String book_download) {
        this.book_download = book_download;
    }
    public String getBook_introduction() {
        return this.book_introduction;
    }
    public void setBook_introduction(String book_introduction) {
        this.book_introduction = book_introduction;
    }
    public String getBook_read_url() {
        return this.book_read_url;
    }
    public void setBook_read_url(String book_read_url) {
        this.book_read_url = book_read_url;
    }
    public String getCollected_time() {
        return this.collected_time;
    }
    public void setCollected_time(String collected_time) {
        this.collected_time = collected_time;
    }
}
