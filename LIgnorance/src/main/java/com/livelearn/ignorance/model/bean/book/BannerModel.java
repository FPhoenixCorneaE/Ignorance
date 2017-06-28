package com.livelearn.ignorance.model.bean.book;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created on 2016/12/26.
 */

public class BannerModel implements Parcelable{

    private String imageUrl;
    private String bannerTitle;
    private String Url;

    public BannerModel(String bannerTitle, String imageUrl, String Url) {
        this.bannerTitle = bannerTitle;
        this.imageUrl = imageUrl;
        this.Url = Url;
    }

    protected BannerModel(Parcel in) {
        imageUrl = in.readString();
        bannerTitle = in.readString();
        Url = in.readString();
    }

    public static final Creator<BannerModel> CREATOR = new Creator<BannerModel>() {
        @Override
        public BannerModel createFromParcel(Parcel in) {
            return new BannerModel(in);
        }

        @Override
        public BannerModel[] newArray(int size) {
            return new BannerModel[size];
        }
    };

    public String getBannerTitle() {
        return bannerTitle;
    }

    public void setBannerTitle(String bannerTitle) {
        this.bannerTitle = bannerTitle;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String Url) {
        this.Url = Url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(imageUrl);
        parcel.writeString(bannerTitle);
        parcel.writeString(Url);
    }

    @Override
    public String toString() {
        return "BannerModel{" +
                "bannerTitle='" + bannerTitle + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", url='" + Url + '\'' +
                '}';
    }
}
