package com.livelearn.ignorance.model.bean.book;

import android.os.Parcel;
import android.os.Parcelable;

public class BookDetailsModel implements Parcelable {

    //图片地址
    private String bookImageUrl;
    //书籍名称
    private String bookName;
    //书籍作者
    private String bookAuthor;
    //书籍类型
    private String bookType;
    //书籍字数
    private String bookLength;
    //书籍进度
    private String bookProgress;
    //最后更新时间
    private String bookUpdateTime;
    //书籍下载地址
    private String bookDownload;
    //书籍简介
    private String bookIntroduction;
    //书籍在线阅读地址
    private String bookReadUrl;

    public BookDetailsModel() {
    }

    protected BookDetailsModel(Parcel in) {
        bookImageUrl = in.readString();
        bookName = in.readString();
        bookAuthor = in.readString();
        bookType = in.readString();
        bookLength = in.readString();
        bookProgress = in.readString();
        bookUpdateTime = in.readString();
        bookDownload = in.readString();
        bookIntroduction = in.readString();
        bookReadUrl = in.readString();
    }

    public static final Creator<BookDetailsModel> CREATOR = new Creator<BookDetailsModel>() {
        @Override
        public BookDetailsModel createFromParcel(Parcel in) {
            return new BookDetailsModel(in);
        }

        @Override
        public BookDetailsModel[] newArray(int size) {
            return new BookDetailsModel[size];
        }
    };

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookDownload() {
        return bookDownload;
    }

    public void setBookDownload(String bookDownload) {
        this.bookDownload = bookDownload;
    }

    public String getBookImageUrl() {
        return bookImageUrl;
    }

    public void setBookImageUrl(String bookImageUrl) {
        this.bookImageUrl = bookImageUrl;
    }

    public String getBookIntroduction() {
        return bookIntroduction;
    }

    public void setBookIntroduction(String bookIntroduction) {
        this.bookIntroduction = bookIntroduction;
    }

    public String getBookLength() {
        return bookLength;
    }

    public void setBookLength(String bookLength) {
        this.bookLength = bookLength;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookProgress() {
        return bookProgress;
    }

    public void setBookProgress(String bookProgress) {
        this.bookProgress = bookProgress;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public String getBookUpdateTime() {
        return bookUpdateTime;
    }

    public void setBookUpdateTime(String bookUpdateTime) {
        this.bookUpdateTime = bookUpdateTime;
    }

    public String getBookReadUrl() {
        return bookReadUrl;
    }

    public void setBookReadUrl(String bookReadUrl) {
        this.bookReadUrl = bookReadUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(bookImageUrl);
        parcel.writeString(bookName);
        parcel.writeString(bookAuthor);
        parcel.writeString(bookType);
        parcel.writeString(bookLength);
        parcel.writeString(bookProgress);
        parcel.writeString(bookUpdateTime);
        parcel.writeString(bookDownload);
        parcel.writeString(bookIntroduction);
        parcel.writeString(bookReadUrl);
    }

    @Override
    public String toString() {
        return "BookDetailsModel{" +
                "bookImageUrl='" + bookImageUrl + '\'' +
                ", bookName='" + bookName + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", bookType='" + bookType + '\'' +
                ", bookLength='" + bookLength + '\'' +
                ", bookProgress='" + bookProgress + '\'' +
                ", bookUpdateTime='" + bookUpdateTime + '\'' +
                ", bookDownload='" + bookDownload + '\'' +
                ", bookIntroduction='" + bookIntroduction + '\'' +
                ", bookReadUrl='" + bookReadUrl + '\'' +
                '}';
    }
}
