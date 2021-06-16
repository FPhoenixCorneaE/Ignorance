package com.livelearn.ignorance.ui.adapter.gallery;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalListDialog;
import com.github.piasy.biv.indicator.ProgressPieIndicator;
import com.github.piasy.biv.view.BigImageView;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.utils.ImageDownloadUtils;
import com.livelearn.ignorance.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2017/6/14.
 */

public class PictureBrowsingAdapter extends PagerAdapter {

    private List<String> pictures;
    private Context mContext;

    public PictureBrowsingAdapter(List<String> pictures) {
        this.pictures = pictures == null ? new ArrayList<String>() : pictures;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        mContext = container.getContext();
        return showBigImage(container, position);
    }

    @NonNull
    private View showBigImage(ViewGroup container, final int position) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.adapter_picture_browsing, container, false);

        final BigImageView bigImageView = (BigImageView) itemView.findViewById(R.id.biv_big_image);

        final SubsamplingScaleImageView subsamplingScaleImageView = bigImageView.getSubsamplingScaleImageView();
        //最小每英寸点数
        subsamplingScaleImageView.setMinimumDpi(50);
        //最大放大比例
        subsamplingScaleImageView.setMaxScale(8F);
        //双击缩放风格，中心聚焦
        subsamplingScaleImageView.setDoubleTapZoomStyle(SubsamplingScaleImageView.ZOOM_FOCUS_CENTER);
        //双击缩放比例
        subsamplingScaleImageView.setDoubleTapZoomScale(3F);
        //可以被拖拽范围限制
        subsamplingScaleImageView.setPanLimit(SubsamplingScaleImageView.PAN_LIMIT_INSIDE);

        final String imageUri = pictures.get(position);
        final Uri uri = Uri.parse((imageUri.startsWith("http")) ? imageUri : (imageUri.startsWith("file://")) ? imageUri : "file://" + imageUri);


        bigImageView.setProgressIndicator(new ProgressPieIndicator());
        bigImageView.showImage(uri);

        bigImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mContext instanceof Activity) {
                    if (!((Activity) mContext).isFinishing()) {
                        ((Activity) mContext).onBackPressed();
                    }
                }
            }
        });

        bigImageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                NormalListDialog listDialog = new NormalListDialog(mContext, new String[]{"保存图片", "图片地址"});
                listDialog.isTitleShow(false)
                        .widthScale(0.6f)
                        .show();
                listDialog.setOnOperItemClickL(new OnOperItemClickL() {
                    @Override
                    public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                        switch (position) {
                            case 0:
                                ImageDownloadUtils.saveImageToLocal(mContext, imageUri);
                                break;
                            case 1:
                                ToastUtils.showToast(bigImageView.currentImageFile());
                                break;
                        }
                    }
                });
                return false;
            }
        });

//        if (imageUri.startsWith("http")) {
//            File file = FrescoHelper.getCache(mContext, uri);
//            if (file != null && file.exists()) {
//                subsamplingScaleImageView.setImage(ImageSource.uri(file.getAbsolutePath()));
//            } else {
//                FrescoHelper.getFrescoImg(mContext, imageUri, 0, 0, new LoadFrescoListener() {
//                    @Override
//                    public void onProgressUpdate(DataSource<CloseableReference<CloseableImage>> dataSource) {
//                        int progress = (int) (dataSource.getProgress() * 100);
//                    }
//
//                    @Override
//                    public void onSuccess(Bitmap bitmap) {
//                        handler.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                File file = FrescoHelper.getCache(mContext, uri);
//                                if (file != null && file.exists()) {
//                                    subsamplingScaleImageView.setImage(ImageSource.uri(file.getAbsolutePath()));
//                                }
//                            }
//                        });
//                    }
//
//                    @Override
//                    public void onFail() {
//
//                    }
//                });
//            }
//        } else {
//            subsamplingScaleImageView.setImage(ImageSource.uri(imageUri.replace("file://", "")));
//        }
//
//        final GestureDetector gestureDetector = new GestureDetector(mContext, new GestureDetector.SimpleOnGestureListener() {
//            @Override
//            public boolean onSingleTapConfirmed(MotionEvent e) {
//                if (mContext instanceof Activity) {
//                    if (!((Activity) mContext).isFinishing()) {
//                        ((Activity) mContext).finish();
//                        ((Activity) mContext).overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
//                    }
//                }
//                return true;
//            }
//
//            @Override
//            public void onLongPress(MotionEvent e) {
//                if (subsamplingScaleImageView.isReady()) {
//
//                }
//            }
//        });
//        subsamplingScaleImageView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                return gestureDetector.onTouchEvent(motionEvent);
//            }
//        });
        container.addView(itemView);
        return itemView;
    }

    @Override
    public int getCount() {
        return pictures.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
