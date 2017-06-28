package com.livelearn.ignorance.model.bean.image;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseFragment;
import com.livelearn.ignorance.common.Constant;
import com.livelearn.ignorance.utils.ResourceUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 引导页图片
 */
public class GuideModel implements Parcelable {

    private int resId;
    private String text;
    private String subText;

    public GuideModel() {
    }

    protected GuideModel(Parcel in) {
        resId = in.readInt();
        text = in.readString();
        subText = in.readString();
        images = in.createIntArray();
    }

    public static final Creator<GuideModel> CREATOR = new Creator<GuideModel>() {
        @Override
        public GuideModel createFromParcel(Parcel in) {
            return new GuideModel(in);
        }

        @Override
        public GuideModel[] newArray(int size) {
            return new GuideModel[size];
        }
    };

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getSubText() {
        return subText;
    }

    public void setSubText(String subText) {
        this.subText = subText;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "GuideModel{" +
                "resId=" + resId +
                ", text='" + text + '\'' +
                ", subText='" + subText + '\'' +
                '}';
    }

    private int[] images = {R.mipmap.pic_guide_1, R.mipmap.pic_guide_2, R.mipmap.pic_guide_3, R.mipmap.pic_guide_4};

    public List<GuideModel> getGuideData(Context context) {
        List<GuideModel> guideModels = new ArrayList<>();
        String[] texts = ResourceUtils.getStringArray(R.array.GuideText);
        String[] subTexts = ResourceUtils.getStringArray(R.array.GuideSubText);
        if (images.length == texts.length && images.length == subTexts.length) {
            for (int i = 0; i < images.length; i++) {
                GuideModel guideModel = new GuideModel();
                guideModel.setResId(images[i]);
                guideModel.setText(texts[i]);
                guideModel.setSubText(subTexts[i]);
                guideModels.add(guideModel);
            }
        }
        return guideModels;
    }

    public GuideModel getGuideData(BaseFragment fragment) {
        Bundle bundle = fragment.getArguments();
        return (GuideModel) bundle.getParcelable(Constant.ARG_SECTION_NUMBER);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(resId);
        parcel.writeString(text);
        parcel.writeString(subText);
        parcel.writeIntArray(images);
    }
}
