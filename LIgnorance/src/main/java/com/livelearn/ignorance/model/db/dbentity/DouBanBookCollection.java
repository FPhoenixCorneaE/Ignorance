package com.livelearn.ignorance.model.db.dbentity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created on 2017/5/9.
 */
@Entity
public class DouBanBookCollection {

    @Id(autoincrement = true)
    private Long id;

    @NotNull
    private String book_id;

    @NotNull
    private String book_name;

    @NotNull
    private String book_cover_url;

    private String rating;

    private String author;

    private String publisher;

    private String time;

    @Generated(hash = 859707216)
    public DouBanBookCollection(Long id, @NotNull String book_id,
            @NotNull String book_name, @NotNull String book_cover_url,
            String rating, String author, String publisher, String time) {
        this.id = id;
        this.book_id = book_id;
        this.book_name = book_name;
        this.book_cover_url = book_cover_url;
        this.rating = rating;
        this.author = author;
        this.publisher = publisher;
        this.time = time;
    }

    @Generated(hash = 681218264)
    public DouBanBookCollection() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBook_id() {
        return this.book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getBook_name() {
        return this.book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getBook_cover_url() {
        return this.book_cover_url;
    }

    public void setBook_cover_url(String book_cover_url) {
        this.book_cover_url = book_cover_url;
    }

    public String getRating() {
        return this.rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return this.publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
