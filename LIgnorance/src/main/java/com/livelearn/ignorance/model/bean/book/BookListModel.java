package com.livelearn.ignorance.model.bean.book;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created on 2016/12/26.
 */

public class BookListModel implements Parcelable {

    private String imageUrl;
    private String bookName;
    private String author;
    private String introduction;
    private String codeId;

    public BookListModel() {
    }

    public BookListModel(String imageUrl, String bookName, String author, String introduction, String codeId) {
        this.imageUrl = imageUrl;
        this.bookName = bookName;
        this.author = author;
        this.introduction = introduction;
        this.codeId = codeId;
    }

    protected BookListModel(Parcel in) {
        imageUrl = in.readString();
        bookName = in.readString();
        author = in.readString();
        introduction = in.readString();
        codeId = in.readString();
    }

    public static final Creator<BookListModel> CREATOR = new Creator<BookListModel>() {
        @Override
        public BookListModel createFromParcel(Parcel in) {
            return new BookListModel(in);
        }

        @Override
        public BookListModel[] newArray(int size) {
            return new BookListModel[size];
        }
    };

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getCodeId() {
        return codeId;
    }

    public void setCodeId(String codeId) {
        this.codeId = codeId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(imageUrl);
        parcel.writeString(bookName);
        parcel.writeString(author);
        parcel.writeString(introduction);
        parcel.writeString(codeId);
    }

    @Override
    public String toString() {
        return "BookListModel{" +
                "author='" + author + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", bookName='" + bookName + '\'' +
                ", introduction='" + introduction + '\'' +
                ", codeId='" + codeId + '\'' +
                '}';
    }
}
