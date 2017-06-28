package com.livelearn.ignorance.test.suspensionindexbar.model;

import com.mcxtzhang.indexbar.bean.BaseIndexPinyinBean;

import java.util.List;

/**
 * Created on 2017/5/2.
 */

public class MeiTuanHeaderCity extends BaseIndexPinyinBean {

    private List<String> cityList;
    private String suspensionTag;

    public List<String> getCityList() {
        return cityList;
    }

    public MeiTuanHeaderCity setCityList(List<String> cityList) {
        this.cityList = cityList;
        return this;
    }

    public MeiTuanHeaderCity setSuspensionTag(String suspensionTag) {
        this.suspensionTag = suspensionTag;
        return this;
    }

    public MeiTuanHeaderCity setIndexTag(String indexTag) {
        setBaseIndexTag(indexTag);
        return this;
    }

    @Override
    public String getTarget() {
        return null;
    }

    @Override
    public boolean isNeedToPinyin() {
        return false;
    }

    @Override
    public String getSuspensionTag() {
        return suspensionTag;
    }
}
