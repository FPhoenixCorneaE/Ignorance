package com.livelearn.ignorance.ui.view.mine;

import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.livelearn.ignorance.base.IBaseView;

import java.util.List;

/**
 * Created on 2017/8/10.
 */

public interface IEditDataView extends IBaseView {

    String getSavedPhotoWallString();

    List<String> getPhotoWallList();

    EasyRecyclerView getRecyclerView();

    TextView getPhotoWallCountTextView();
}
