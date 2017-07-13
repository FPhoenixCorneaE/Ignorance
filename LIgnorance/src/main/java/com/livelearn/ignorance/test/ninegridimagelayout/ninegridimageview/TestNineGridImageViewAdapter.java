package com.livelearn.ignorance.test.ninegridimagelayout.ninegridimageview;

import android.content.Context;
import android.widget.ImageView;

import com.apkfuns.logutils.LogUtils;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.utils.GlideUtils;
import com.livelearn.ignorance.utils.ToastUtils;
import com.livelearn.ignorance.widget.ninegridimagelayout.ninegridimage.ItemImageClickListener;
import com.livelearn.ignorance.widget.ninegridimagelayout.ninegridimage.NineGridImageView;
import com.livelearn.ignorance.widget.ninegridimagelayout.ninegridimage.NineGridImageViewAdapter;
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter;
import com.xiaochao.lcrapiddeveloplibrary.BaseViewHolder;

import java.util.List;

/**
 * Created on 2017/7/11.
 */

@SuppressWarnings("unchecked")
public class TestNineGridImageViewAdapter extends BaseQuickAdapter<String> {

    private List<String> mDatas;
    private int mShowStyle;

    public TestNineGridImageViewAdapter(List<String> data, int showStyle) {
        super(R.layout.adapter_test_nine_grid_image_view, data);
        this.mDatas = data;
        this.mShowStyle = showStyle;
    }

    @Override
    protected void convert(BaseViewHolder holder, String item) {
        ((NineGridImageView<String>) holder.getView(R.id.ngiv_picture)).setShowStyle(mShowStyle);

        //这里setAdapter，setImagesData顺序调换的话，数据会显示不出来。
        ((NineGridImageView<String>) holder.getView(R.id.ngiv_picture)).setAdapter(new NineGridImageViewAdapter<String>() {
            @Override
            protected void onDisplayImage(Context context, ImageView imageView, String picUrl) {
                GlideUtils.setupImage(context, imageView, picUrl, R.drawable.shape_solidlightgray);
            }

            @Override
            protected void onItemImageClick(Context context, ImageView imageView, int index, List<String> mDatas) {
                ToastUtils.showToast("image position is " + index);
            }

            @Override
            protected ImageView generateImageView(Context context) {
                return new ImageView(context);
            }
        });
        if (NineGridImageView.STYLE_FILL == mShowStyle) {
            switch (holder.getAdapterPosition()) {
                case 0://单张
                case 1://2张
                case 2://3张,不跨行不跨列
                    ((NineGridImageView<String>) holder.getView(R.id.ngiv_picture)).setImagesData(mDatas.subList(0, holder.getAdapterPosition() + 1), NineGridImageView.NOSPAN);
                    break;
                case 3://3张,首行跨列
                    ((NineGridImageView<String>) holder.getView(R.id.ngiv_picture)).setImagesData(mDatas.subList(0, 3), NineGridImageView.TOPCOLSPAN);
                    break;
                case 4://3张,末行跨列
                    ((NineGridImageView<String>) holder.getView(R.id.ngiv_picture)).setImagesData(mDatas.subList(0, 3), NineGridImageView.BOTTOMCOLSPAN);
                    break;
                case 5://3张,首列跨行
                    ((NineGridImageView<String>) holder.getView(R.id.ngiv_picture)).setImagesData(mDatas.subList(0, 3), NineGridImageView.LEFTROWSPAN);
                    break;
                case 6://4张,不跨行不跨列
                    ((NineGridImageView<String>) holder.getView(R.id.ngiv_picture)).setImagesData(mDatas.subList(0, 4), NineGridImageView.NOSPAN);
                    break;
                case 7://4张,首行跨列
                    ((NineGridImageView<String>) holder.getView(R.id.ngiv_picture)).setImagesData(mDatas.subList(0, 4), NineGridImageView.TOPCOLSPAN);
                    break;
                case 8://4张,末行跨列
                    ((NineGridImageView<String>) holder.getView(R.id.ngiv_picture)).setImagesData(mDatas.subList(0, 4), NineGridImageView.BOTTOMCOLSPAN);
                    break;
                case 9://4张,首列跨行
                    ((NineGridImageView<String>) holder.getView(R.id.ngiv_picture)).setImagesData(mDatas.subList(0, 4), NineGridImageView.LEFTROWSPAN);
                    break;
                case 10://5张,不跨行不跨列
                    ((NineGridImageView<String>) holder.getView(R.id.ngiv_picture)).setImagesData(mDatas.subList(0, 5), NineGridImageView.NOSPAN);
                    break;
                case 11://5张,首行跨列
                    ((NineGridImageView<String>) holder.getView(R.id.ngiv_picture)).setImagesData(mDatas.subList(0, 5), NineGridImageView.TOPCOLSPAN);
                    break;
                case 12://5张,末行跨列
                    ((NineGridImageView<String>) holder.getView(R.id.ngiv_picture)).setImagesData(mDatas.subList(0, 5), NineGridImageView.BOTTOMCOLSPAN);
                    break;
                case 13://5张,首列跨行
                    ((NineGridImageView<String>) holder.getView(R.id.ngiv_picture)).setImagesData(mDatas.subList(0, 5), NineGridImageView.LEFTROWSPAN);
                    break;
                case 14://6张,不跨行不跨列
                    ((NineGridImageView<String>) holder.getView(R.id.ngiv_picture)).setImagesData(mDatas.subList(0, 6), NineGridImageView.NOSPAN);
                    break;
                case 15://6张,首行跨列
                    ((NineGridImageView<String>) holder.getView(R.id.ngiv_picture)).setImagesData(mDatas.subList(0, 6), NineGridImageView.TOPCOLSPAN);
                    break;
                case 16://6张,末行跨列
                    ((NineGridImageView<String>) holder.getView(R.id.ngiv_picture)).setImagesData(mDatas.subList(0, 6), NineGridImageView.BOTTOMCOLSPAN);
                    break;
                case 17://6张,首列跨行
                    ((NineGridImageView<String>) holder.getView(R.id.ngiv_picture)).setImagesData(mDatas.subList(0, 6), NineGridImageView.LEFTROWSPAN);
                    break;
            }
        } else {
            ((NineGridImageView<String>) holder.getView(R.id.ngiv_picture)).setSingleImgSize(500);
            ((NineGridImageView<String>) holder.getView(R.id.ngiv_picture)).setImagesData(mDatas.subList(0, holder.getAdapterPosition() + 1));
        }


        ((NineGridImageView<String>) holder.getView(R.id.ngiv_picture)).setItemImageClickListener(new ItemImageClickListener<String>() {
            @Override
            public void onItemImageClick(Context context, ImageView imageView, int index, List<String> mDatas) {
                LogUtils.e(mDatas.get(index));
            }
        });
    }
}
