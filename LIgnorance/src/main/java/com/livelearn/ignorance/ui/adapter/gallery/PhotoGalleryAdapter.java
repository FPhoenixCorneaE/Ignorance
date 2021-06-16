package com.livelearn.ignorance.ui.adapter.gallery;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.widget.photoview.PhotoView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 15/6/21.
 */
public class PhotoGalleryAdapter extends PagerAdapter {

    private List<String> paths = new ArrayList<>();
    private RequestManager mGlide;

    public PhotoGalleryAdapter(RequestManager glide, List<String> paths) {
        this.mGlide = glide;
        this.paths = paths;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final Context context = container.getContext();
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.adapter_photo_gallery, container, false);

        final PhotoView imageView = (PhotoView) itemView.findViewById(R.id.pv_gallery);
        imageView.enable();
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

        final String path = paths.get(position);
        final Uri uri;
        if (path.startsWith("http")) {
            uri = Uri.parse(path);
        } else {
            uri = Uri.fromFile(new File(path));
        }

        mGlide.load(uri)
                .dontAnimate()
                .dontTransform()
                .into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (context instanceof Activity) {
                    if (!((Activity) context).isFinishing()) {
                        ((Activity) context).onBackPressed();
                    }
                }
            }
        });

        container.addView(itemView);

        return itemView;
    }

    @Override
    public int getCount() {
        return paths.size();
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        Glide.clear((View) object);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

}
