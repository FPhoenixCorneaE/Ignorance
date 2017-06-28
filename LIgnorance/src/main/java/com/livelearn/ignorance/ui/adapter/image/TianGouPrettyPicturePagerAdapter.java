package com.livelearn.ignorance.ui.adapter.image;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.apkfuns.logutils.LogUtils;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.common.BaseUrl;
import com.livelearn.ignorance.model.bean.image.TianGouPrettyPictureModel;
import com.livelearn.ignorance.utils.DisplayUtils;
import com.livelearn.ignorance.utils.GlideUtils;
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter;
import com.xiaochao.lcrapiddeveloplibrary.BaseViewHolder;

import java.util.List;
import java.util.Locale;

public class TianGouPrettyPicturePagerAdapter extends BaseQuickAdapter<TianGouPrettyPictureModel.TngouBean> {

    private OnItemClickListener mOnItemClickListener;

    public TianGouPrettyPicturePagerAdapter(List<TianGouPrettyPictureModel.TngouBean> data) {
        super(R.layout.adapter_tian_gou_pretty_picture_pager, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder, final TianGouPrettyPictureModel.TngouBean item) {
        String picUrl = BaseUrl.TIANGOU_IMAGE_URL_PREFIX + item.getImg() + "_320x480.jpg";

        LogUtils.i(picUrl);

        ViewGroup.LayoutParams params = holder.getView(R.id.iv_ratio_image).getLayoutParams();
        int width = (DisplayUtils.getScreenWidth() - (5 * 2 + 5 * 2 * 2 + 5 * 2 * 2)) / 2;//宽度为屏幕宽度减去空隙后的一半
        params.height = 480 * width / 320;
        holder.getView(R.id.iv_ratio_image).setLayoutParams(params);
        GlideUtils.setupImage(mContext, (ImageView) holder.getView(R.id.iv_ratio_image), picUrl);

        holder.getView(R.id.iv_ratio_image).setContentDescription(item.getTitle());
        holder.setText(R.id.tv_title, item.getTitle());
        holder.setText(R.id.tv_count, String.format(Locale.getDefault(), "%d张", item.getSize()));
        holder.setText(R.id.tv_page_view, String.format(Locale.getDefault(), "浏览量：%d", item.getCount()));

        holder.getView(R.id.ll_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick((ImageView) holder.getView(R.id.iv_ratio_image), item, holder.getAdapterPosition());
                }
            }
        });
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(ImageView imageView, TianGouPrettyPictureModel.TngouBean prettyPicture, int position);
    }
}
