package com.livelearn.ignorance.model.bean.book;

import java.util.List;

/**
 * Created by Administrator on 2016/12/26.
 */

public class BookRecommendModel {

    private List<BannerModel> banner;
    private List<BookListModel> hotBook;
    private List<BookListModel> newBook;

    public List<BannerModel> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerModel> banner) {
        this.banner = banner;
    }

    public List<BookListModel> getHotBook() {
        return hotBook;
    }

    public void setHotBook(List<BookListModel> hotBook) {
        this.hotBook = hotBook;
    }

    public List<BookListModel> getNewBook() {
        return newBook;
    }

    public void setNewBook(List<BookListModel> newBook) {
        this.newBook = newBook;
    }

    @Override
    public String toString() {
        return "BookRecommendModel{" +
                "banner=" + banner +
                ", hotBook=" + hotBook +
                ", newBook=" + newBook +
                '}';
    }
}
