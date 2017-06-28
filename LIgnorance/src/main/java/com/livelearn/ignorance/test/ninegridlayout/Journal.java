package com.livelearn.ignorance.test.ninegridlayout;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

/**
 * Created on 2017/3/8.
 */

public class Journal implements Parcelable {


    /**
     * friendName : 小溪
     * friendAvatarUrl : http://
     * journalDescription : 今天天气真好，好开心！！~~~
     * pictureList : http://
     * releaseLocation : 广州
     * releaseTime : 2017/03/08
     */

    private String friendName;
    private String friendAvatarUrl;
    private String journalDescription;
    private String pictureList;
    private String releaseLocation;
    private String releaseTime;

    public Journal() {
    }

    protected Journal(Parcel in) {
        friendName = in.readString();
        friendAvatarUrl = in.readString();
        journalDescription = in.readString();
        pictureList = in.readString();
        releaseLocation = in.readString();
        releaseTime = in.readString();
    }

    public static final Creator<Journal> CREATOR = new Creator<Journal>() {
        @Override
        public Journal createFromParcel(Parcel in) {
            return new Journal(in);
        }

        @Override
        public Journal[] newArray(int size) {
            return new Journal[size];
        }
    };

    public static Journal objectFromData(String str) {

        return new Gson().fromJson(str, Journal.class);
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public String getFriendAvatarUrl() {
        return friendAvatarUrl;
    }

    public void setFriendAvatarUrl(String friendAvatarUrl) {
        this.friendAvatarUrl = friendAvatarUrl;
    }

    public String getJournalDescription() {
        return journalDescription;
    }

    public void setJournalDescription(String journalDescription) {
        this.journalDescription = journalDescription;
    }

    public String getPictureList() {
        return pictureList;
    }

    public void setPictureList(String pictureList) {
        this.pictureList = pictureList;
    }

    public String getReleaseLocation() {
        return releaseLocation;
    }

    public void setReleaseLocation(String releaseLocation) {
        this.releaseLocation = releaseLocation;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    @Override
    public String toString() {
        return "Journal{" +
                "friendName='" + friendName + '\'' +
                ", friendAvatarUrl='" + friendAvatarUrl + '\'' +
                ", journalDescription='" + journalDescription + '\'' +
                ", pictureList='" + pictureList + '\'' +
                ", releaseLocation='" + releaseLocation + '\'' +
                ", releaseTime='" + releaseTime + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(friendName);
        parcel.writeString(friendAvatarUrl);
        parcel.writeString(journalDescription);
        parcel.writeString(pictureList);
        parcel.writeString(releaseLocation);
        parcel.writeString(releaseTime);
    }
}
