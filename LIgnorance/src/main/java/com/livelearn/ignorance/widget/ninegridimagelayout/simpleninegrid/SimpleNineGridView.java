package com.livelearn.ignorance.widget.ninegridimagelayout.simpleninegrid;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hitomi.tilibrary.loader.glideloader.GlideImageLoader;
import com.hitomi.tilibrary.style.progress.ProgressPieIndicator;
import com.hitomi.tilibrary.transfer.TransDialog;
import com.hitomi.tilibrary.transfer.TransferConfig;
import com.hitomi.tilibrary.transfer.Transferee;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.utils.GlideUtils;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 九宫格展示图片，简单的做一个封装
 */
public class SimpleNineGridView extends GridView {

    private Context mContext;
    private OnTouchInvalidPositionListener mTouchInvalidPosListener;
    private Transferee transferee;

    public SimpleNineGridView(Context context) {
        this(context, null);
    }

    public SimpleNineGridView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleNineGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;

        init();
    }

    private void init() {
        setNumColumns(AUTO_FIT);
        setBackground(new ColorDrawable(Color.WHITE));
        setPadding(dp2px(8), 0, dp2px(8), 0);
        setHorizontalSpacing(dp2px(8));
        setVerticalSpacing(dp2px(8));
        setSelector(new ColorDrawable(Color.TRANSPARENT));
        setVerticalScrollBarEnabled(false);
        setStretchMode(STRETCH_COLUMN_WIDTH);
        setGravity(Gravity.CENTER);

        //实例化transferee
        transferee = new Transferee(mContext);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        //makeMeasureSpec函数中第一个函数决定布局空间的大小，第二个参数是布局模式
        //MeasureSpec.AT_MOST的意思就是子控件需要多大的控件就扩展到多大的空间
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    public void setOnTouchInvalidPositionListener(OnTouchInvalidPositionListener listener) {
        mTouchInvalidPosListener = listener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mTouchInvalidPosListener == null) {
            return super.onTouchEvent(ev);
        }
        if (!isEnabled()) {
            return isClickable() || isLongClickable();
        }
        final int motionPosition = pointToPosition((int) ev.getX(), (int) ev.getY());
        if (motionPosition == INVALID_POSITION) {
            super.onTouchEvent(ev);
            return mTouchInvalidPosListener.onTouchInvalidPosition(ev.getActionMasked());
        }
        return super.onTouchEvent(ev);
    }


    public interface OnTouchInvalidPositionListener {
        /**
         * motionEvent 可使用 MotionEvent.ACTION_DOWN 或者 MotionEvent.ACTION_UP等来按需要进行判断
         *
         * @return 是否要终止事件的路由
         */
        boolean onTouchInvalidPosition(int motionEvent);
    }

    public SimpleNineGridView setDatas(final List<String> mDatas) {
        if (mDatas == null) return this;
        if (mDatas.size() < 3) {
            setNumColumns(2);
        } else {
            setNumColumns(3);
        }

        setAdapter(new CommonAdapter<String>(mContext, R.layout.adapter_simple_nine_grid_view, mDatas) {
            @Override
            protected void convert(ViewHolder viewHolder, String item, final int position) {
                ImageView ivChild = viewHolder.getView(R.id.iv_child);
                GlideUtils.setupImage(mContext, ivChild, getItem(position), R.drawable.shape_solidpale);
                ivChild.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mOnSimpleItemClickListener != null) {
                            mOnSimpleItemClickListener.onClick(mContext, mDatas, position);
                        } else {
                            List<ImageView> originImageList = new ArrayList<>();
                            for (int i = 0; i < mDatas.size(); i++) {
                                originImageList.add((ImageView) ((ViewGroup) getChildAt(i)).getChildAt(0));
                            }
                            TransferConfig config = TransferConfig.build()
                                    .setNowThumbnailIndex(position)
                                    .setSourceImageList(mDatas)
                                    .setMissPlaceHolder(R.drawable.shape_solidpale)
                                    .setOriginImageList(originImageList)
                                    .setProgressIndicator(new ProgressPieIndicator())
//                                    .setIndexIndicator(new NumberIndexIndicator())
                                    .setImageLoader(GlideImageLoader.with(mContext.getApplicationContext()))
                                    .create();
                            transferee.apply(config).show(new TransDialog.OnTransfereeStateChangeListener() {
                                @Override
                                public void onShow() {
                                    Glide.with(mContext).pauseRequests();
                                }

                                @Override
                                public void onDismiss() {
                                    Glide.with(mContext).resumeRequests();
                                }
                            });
                        }
                    }
                });
            }
        });
        return this;
    }

    /**
     * 转换dip为px
     */
    private int dp2px(int dip) {
        float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f * (dip >= 0 ? 1 : -1));
    }

    private OnSimpleItemClickListener mOnSimpleItemClickListener;

    public SimpleNineGridView setOnSimpleItemClickListener(OnSimpleItemClickListener onSimpleItemClickListener) {
        this.mOnSimpleItemClickListener = onSimpleItemClickListener;
        return this;
    }

    public interface OnSimpleItemClickListener {
        void onClick(Context mContext, List<String> mDatas, int position);
    }
}

	

