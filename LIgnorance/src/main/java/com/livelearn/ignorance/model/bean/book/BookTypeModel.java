package com.livelearn.ignorance.model.bean.book;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 变量区分大小写，必须与返回数据的key一致，否则做不了缓存
 * Created on 2016/12/26.
 */

public class BookTypeModel implements Parcelable{

    private String BookTypeUrl;
    private String BookTypeName;
    private String BookTypeImageUrl;
    private int StartPage;
    private int EndPage;
    private int pageLength;

    public BookTypeModel(String bookTypeImageUrl, String bookTypeName, String bookTypeUrl, int endPage, int pageLength, int startPage) {
        this.BookTypeImageUrl = bookTypeImageUrl;
        this.BookTypeName = bookTypeName;
        this.BookTypeUrl = bookTypeUrl;
        this.EndPage = endPage;
        this.pageLength = pageLength;
        this.StartPage = startPage;
    }

    protected BookTypeModel(Parcel in) {
        BookTypeUrl = in.readString();
        BookTypeName = in.readString();
        BookTypeImageUrl = in.readString();
        StartPage = in.readInt();
        EndPage = in.readInt();
        pageLength = in.readInt();
    }

    public static final Creator<BookTypeModel> CREATOR = new Creator<BookTypeModel>() {
        @Override
        public BookTypeModel createFromParcel(Parcel in) {
            return new BookTypeModel(in);
        }

        @Override
        public BookTypeModel[] newArray(int size) {
            return new BookTypeModel[size];
        }
    };

    public String getBookTypeImageUrl() {
        return BookTypeImageUrl;
    }

    public void setBookTypeImageUrl(String bookTypeImageUrl) {
        this.BookTypeImageUrl = bookTypeImageUrl;
    }

    public String getBookTypeName() {
        return BookTypeName;
    }

    public void setBookTypeName(String bookTypeName) {
        this.BookTypeName = bookTypeName;
    }

    public String getBookTypeUrl() {
        return BookTypeUrl;
    }

    public void setBookTypeUrl(String bookTypeUrl) {
        this.BookTypeUrl = bookTypeUrl;
    }

    public int getEndPage() {
        return EndPage;
    }

    public void setEndPage(int endPage) {
        this.EndPage = endPage;
    }

    public int getPageLength() {
        return pageLength;
    }

    public void setPageLength(int pageLength) {
        this.pageLength = pageLength;
    }

    public int getStartPage() {
        return StartPage;
    }

    public void setStartPage(int startPage) {
        this.StartPage = startPage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(BookTypeUrl);
        parcel.writeString(BookTypeName);
        parcel.writeString(BookTypeImageUrl);
        parcel.writeInt(StartPage);
        parcel.writeInt(EndPage);
        parcel.writeInt(pageLength);
    }

    @Override
    public String toString() {
        return "BookTypeModel{" +
                "bookTypeImageUrl='" + BookTypeImageUrl + '\'' +
                ", bookTypeUrl='" + BookTypeUrl + '\'' +
                ", bookTypeName='" + BookTypeName + '\'' +
                ", startPage=" + StartPage +
                ", endPage=" + EndPage +
                ", pageLength=" + pageLength +
                '}';
    }
}
