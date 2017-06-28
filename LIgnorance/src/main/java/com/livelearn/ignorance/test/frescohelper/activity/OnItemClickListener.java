package com.livelearn.ignorance.test.frescohelper.activity;

import java.util.ArrayList;

/**
 * Created by android_ls on 16/11/7.
 */

public interface OnItemClickListener<T> {

    void onItemClick(ArrayList<T> photos, int position);

}
