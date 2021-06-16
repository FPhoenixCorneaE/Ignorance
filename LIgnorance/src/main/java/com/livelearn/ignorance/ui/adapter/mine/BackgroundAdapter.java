package com.livelearn.ignorance.ui.adapter.mine;

import android.view.View;

import androidx.collection.ArrayMap;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.model.bean.mine.BackgroundBean;
import com.livelearn.ignorance.utils.GlideUtils;
import com.livelearn.ignorance.widget.animcheckbox.AnimCheckBox;
import com.livelearn.ignorance.widget.labelview.LabelImageView;
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter;
import com.xiaochao.lcrapiddeveloplibrary.BaseViewHolder;

import java.util.List;

/**
 * Created on 2017/8/8.
 */

public class BackgroundAdapter extends BaseQuickAdapter<BackgroundBean> {

    private ArrayMap<Integer, BackgroundBean> selectedBackground = new ArrayMap<>(1);

    public BackgroundAdapter(List<BackgroundBean> data) {
        super(R.layout.adapter_background, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, BackgroundBean item) {
        GlideUtils.setupImage(mContext, ((LabelImageView) holder.getView(R.id.liv_label_image)), item.getBackgroundUrl(), R.drawable.shape_solidlightgray);
        ((LabelImageView) holder.getView(R.id.liv_label_image)).setLabelVisible(true);
        if (item.isVip()) {
            ((LabelImageView) holder.getView(R.id.liv_label_image)).setTextTitle("VIP");
            if (item.isOriginal()) {
                ((LabelImageView) holder.getView(R.id.liv_label_image)).setTextContent("原 创");
            } else {
                ((LabelImageView) holder.getView(R.id.liv_label_image)).setTextContent(null);
            }
        } else if (item.isNew()) {
            ((LabelImageView) holder.getView(R.id.liv_label_image)).setTextTitle("NEW");
            if (item.isOriginal()) {
                ((LabelImageView) holder.getView(R.id.liv_label_image)).setTextContent("原 创");
            } else {
                ((LabelImageView) holder.getView(R.id.liv_label_image)).setTextContent(null);
            }
        } else {
            ((LabelImageView) holder.getView(R.id.liv_label_image)).setTextTitle(null);
            if (item.isOriginal()) {
                ((LabelImageView) holder.getView(R.id.liv_label_image)).setTextContent("原 创");
            } else {
                ((LabelImageView) holder.getView(R.id.liv_label_image)).setLabelVisible(false);
            }
        }
        if (item.isInUse()) {
            holder.getView(R.id.cb_anim).setVisibility(View.VISIBLE);
            ((AnimCheckBox) holder.getView(R.id.cb_anim)).setChecked(true);
            selectedBackground.put(0, item);
        } else {
            holder.getView(R.id.cb_anim).setVisibility(View.INVISIBLE);
        }
    }

    public void setSelectedBackground(BackgroundBean selectedBackground) {
        this.selectedBackground.put(0, selectedBackground);
    }

    public BackgroundBean getSelectedBackground() {
        return selectedBackground.get(0);
    }
}
