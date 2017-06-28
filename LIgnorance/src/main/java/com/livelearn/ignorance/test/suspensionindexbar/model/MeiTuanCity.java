package com.livelearn.ignorance.test.suspensionindexbar.model;

import com.mcxtzhang.indexbar.bean.BaseIndexPinyinBean;

/**
 * Created on 2017/5/3.
 */

public class MeiTuanCity extends BaseIndexPinyinBean{

    private String city;

    public String getCity() {
        return city;
    }

    public MeiTuanCity setCity(String city) {
        this.city = city;
        return this;
    }

    @Override
    public String getTarget() {
        return city;
    }
}
