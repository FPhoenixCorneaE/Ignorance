package com.livelearn.ignorance.test.frescohelper.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;

import com.facebook.fresco.helper.photo.PictureBrowse;
import com.facebook.fresco.helper.photo.entity.PhotoInfo;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.widget.TitleBar;

import java.io.File;
import java.util.ArrayList;

/**
 * 显示加载本地相册
 *
 * Created by android_ls on 16/11/11.
 */
public class TestPhotoAlbumActivity extends BaseActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    private final String[] IMAGE_PROJECTION = {
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.DATE_ADDED,
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.MIME_TYPE};

    private ArrayList<PhotoInfo> mImageList = new ArrayList<>();
    private TestPhotoWallAdapter mPhotoWallAdapter;
    private GridLayoutManager mLayoutManager;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_photo_wall;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        ((TitleBar) findViewById(R.id.tb_title)).setTitleText(className);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.rv_photo_wall);
        mLayoutManager = new GridLayoutManager(this, 3);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mPhotoWallAdapter = new TestPhotoWallAdapter(mImageList, new OnItemClickListener<PhotoInfo>() {

            @Override
            public void onItemClick(ArrayList<PhotoInfo> photos, int position) {
//                MLog.i("position = " + position);
//                MLog.i("photos.get(position).thumbnailUrl = " + photos.get(position).thumbnailUrl);

                PictureBrowse.newBuilder(TestPhotoAlbumActivity.this)
                        .setLayoutManager(mLayoutManager)
                        .setCurrentPosition(position)
                        .setPhotoList(photos)
                        .enabledAnimation(false) // 关闭动画效果
                        .build()
                        .start();
            }
        });
        mRecyclerView.setAdapter(mPhotoWallAdapter);

        getSupportLoaderManager().initLoader(0, null, this);
    }

    @Override
    public void setListeners() {

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, IMAGE_PROJECTION,
                null, null, IMAGE_PROJECTION[2] + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if(cursor == null) {
            return;
        }

        try {
            while (cursor.moveToNext()) {
                String path = cursor.getString(cursor.getColumnIndex(IMAGE_PROJECTION[0]));
                if (!TextUtils.isEmpty(path)) {
                    File mFile = new File(path);
                    if (mFile.exists() && mFile.isFile()) {
                        String name = cursor.getString(cursor.getColumnIndex(IMAGE_PROJECTION[1]));
                        if (TextUtils.isEmpty(name)) {
                            continue;
                        }

                        PhotoInfo image = new PhotoInfo();
                        image.thumbnailUrl = path;
                        image.originalUrl = path;
                        mImageList.add(image);
                    }
                }
            }
        } finally {
            cursor.close();
        }

        mPhotoWallAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

}
