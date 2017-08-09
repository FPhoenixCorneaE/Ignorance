package com.livelearn.ignorance.model.bean.mine;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created on 2017/8/8.
 */

public class BackgroundBean implements Parcelable {

    private boolean isVip;
    private boolean isOriginal;
    private boolean isNew;
    private boolean isInUse;
    private String backgroundUrl;

    public BackgroundBean() {
    }

    protected BackgroundBean(Parcel in) {
        isVip = in.readByte() != 0;
        isOriginal = in.readByte() != 0;
        isNew = in.readByte() != 0;
        isInUse = in.readByte() != 0;
        backgroundUrl = in.readString();
    }

    public static final Creator<BackgroundBean> CREATOR = new Creator<BackgroundBean>() {
        @Override
        public BackgroundBean createFromParcel(Parcel in) {
            return new BackgroundBean(in);
        }

        @Override
        public BackgroundBean[] newArray(int size) {
            return new BackgroundBean[size];
        }
    };

    public boolean isVip() {
        return isVip;
    }

    public BackgroundBean setVip(boolean vip) {
        isVip = vip;
        return this;
    }

    public boolean isOriginal() {
        return isOriginal;
    }

    public BackgroundBean setOriginal(boolean original) {
        isOriginal = original;
        return this;
    }

    public boolean isNew() {
        return isNew;
    }

    public BackgroundBean setNew(boolean aNew) {
        isNew = aNew;
        return this;
    }

    public boolean isInUse() {
        return isInUse;
    }

    public BackgroundBean setInUse(boolean inUse) {
        isInUse = inUse;
        return this;
    }

    public String getBackgroundUrl() {
        return backgroundUrl;
    }

    public BackgroundBean setBackgroundUrl(String backgroundUrl) {
        this.backgroundUrl = backgroundUrl;
        return this;
    }

    @Override
    public String toString() {
        return "BackgroundBean{" +
                "isVip=" + isVip +
                ", isOriginal=" + isOriginal +
                ", isNew=" + isNew +
                ", isInUse=" + isInUse +
                ", backgroundUrl='" + backgroundUrl + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (isVip ? 1 : 0));
        dest.writeByte((byte) (isOriginal ? 1 : 0));
        dest.writeByte((byte) (isNew ? 1 : 0));
        dest.writeByte((byte) (isInUse ? 1 : 0));
        dest.writeString(backgroundUrl);
    }
}
