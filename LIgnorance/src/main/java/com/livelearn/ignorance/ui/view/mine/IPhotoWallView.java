package com.livelearn.ignorance.ui.view.mine;

import android.view.View;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.livelearn.ignorance.base.IBaseView;
import com.livelearn.ignorance.widget.TitleBar;

import java.util.List;

/**
 * Created on 2017/8/16.
 */

public interface IPhotoWallView extends IBaseView {

    void savePhotoWallData(String photoWallString);

    String getSavedPhotoWallString();

    List<String> getPhotoWallList();

    TitleBar getTitleBar();

    EasyRecyclerView getRecyclerView();

    View getDeletePhotoView();

    View getUploadPhotoView();
}
