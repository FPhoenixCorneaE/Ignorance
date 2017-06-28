package com.livelearn.ignorance.ui.adapter.book.doubanbook;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.common.Constant;
import com.livelearn.ignorance.ui.adapter.book.doubanbook.helper.OnDragVHListener;
import com.livelearn.ignorance.ui.adapter.book.doubanbook.helper.OnItemMoveListener;
import com.livelearn.ignorance.utils.SharedPreferencesUtils;
import com.livelearn.ignorance.utils.ToastUtils;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ChinaLHR on 2016/12/23.
 * Email:13435500980@163.com
 */

public class BookLabelAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements OnItemMoveListener {
    // 我的标签 标题
    public static final int TYPE_MY_LABEL_HEADER = 0;
    // 我的标签
    public static final int TYPE_MY_LABEL = 1;
    // 其他标签 标题
    public static final int TYPE_OTHER_LABEL_HEADER = 2;
    // 其他标签
    public static final int TYPE_OTHER_LABEL = 3;

    private String currentTheme;

    // 我的标签之前的header数量  该demo中 即标题部分 为 1
    private static final int COUNT_PRE_MY_HEADER = 1;
    // 其他标签之前的header数量  该demo中 即标题部分 为 COUNT_PRE_MY_HEADER + 1
    private static final int COUNT_PRE_OTHER_HEADER = COUNT_PRE_MY_HEADER + 1;

    private static final long ANIM_TIME = 360L;

    // touch 点击开始时间
    private long startTime;
    // touch 间隔时间  用于分辨是否是 "点击"
    private static final long SPACE_TIME = 100;
    private Context mContext;
    private LayoutInflater mInflater;
    private ItemTouchHelper mItemTouchHelper;

    // 是否为编辑模式
    private boolean isEditMode;

    private List<String> mMyLabel, mOtherLabel;

    public String KEY;
    // 我的标签点击事件
    private OnMyLabelItemClickListener mMyLabelItemClickListener;

    public interface OnMyLabelItemClickListener {
        void onItemClick(View v, int position);
    }

    public void setOnMyLabelItemClickListener(OnMyLabelItemClickListener listener) {
        this.mMyLabelItemClickListener = listener;
    }

    public BookLabelAdapter(Context context, ItemTouchHelper helper, List<String> mMyLabel, List<String> mOtherLabel, String key) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mItemTouchHelper = helper;
        this.mMyLabel = mMyLabel;
        this.mOtherLabel = mOtherLabel;
        this.KEY = key;
        this.currentTheme = SharedPreferencesUtils.getString(Constant.USER_INFO, Constant.CURRENT_THEME);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_MY_LABEL_HEADER;
        } else if (position > 0 && position < mMyLabel.size() + 1) {
            return TYPE_MY_LABEL;
        } else if (position == mMyLabel.size() + 1) {
            return TYPE_OTHER_LABEL_HEADER;
        } else {
            return TYPE_OTHER_LABEL;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        final View view;
        switch (viewType) {
            case TYPE_MY_LABEL_HEADER:
                view = mInflater.inflate(R.layout.header_book_label_added, parent, false);
                final AddedBookLabelHeaderViewHolder holder = new AddedBookLabelHeaderViewHolder(view);
                holder.btnEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //我的频道Header点击事件，开启关闭编辑Mode
                        if (!isEditMode) {
                            startEditMode((RecyclerView) parent);
                            holder.btnEdit.setText("完成");
                        } else {
                            cancelEditMode((RecyclerView) parent);
                            holder.btnEdit.setText("编辑");
                            changeSp();

                        }
                    }
                });
                return holder;
            case TYPE_MY_LABEL:
                view = mInflater.inflate(R.layout.adapter_book_label_added, parent, false);
                final AddedBookLabelViewHolder myHolder = new AddedBookLabelViewHolder(view);

                myHolder.tvBookLabel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        int position = myHolder.getAdapterPosition();
                        //开启编辑模式
                        if (isEditMode) {
                            if (mMyLabel.size() > 2) {
                                RecyclerView recyclerView = ((RecyclerView) parent);
                                View targetView = recyclerView.getLayoutManager().findViewByPosition(mMyLabel.size() + COUNT_PRE_OTHER_HEADER);
                                View currentView = recyclerView.getLayoutManager().findViewByPosition(position);
                                // 如果targetView不在屏幕内,则indexOfChild为-1  此时不需要添加动画,因为此时notifyItemMoved自带一个向目标移动的动画
                                // 如果在屏幕内,则添加一个位移动画
                                if (recyclerView.indexOfChild(targetView) >= 0) {
                                    int targetX, targetY;

                                    RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
                                    int spanCount = ((GridLayoutManager) manager).getSpanCount();

                                    // 移动后 高度将变化 (我的频道Grid 最后一个item在新的一行第一个)
                                    if ((mMyLabel.size() - COUNT_PRE_MY_HEADER) % spanCount == 0) {
                                        View preTargetView = recyclerView.getLayoutManager().findViewByPosition(mMyLabel.size() + COUNT_PRE_OTHER_HEADER - 1);
                                        targetX = preTargetView.getLeft();
                                        targetY = preTargetView.getTop();
                                    } else {
                                        targetX = targetView.getLeft();
                                        targetY = targetView.getTop();
                                    }

                                    moveMyToOther(myHolder);
                                    startAnimation(recyclerView, currentView, targetX, targetY);

                                } else {
                                    moveMyToOther(myHolder);
                                }
                            } else {
                                ToastUtils.showToast("标签不可以少于两个");
                            }
                        } else {
                            mMyLabelItemClickListener.onItemClick(v, position - COUNT_PRE_MY_HEADER);
                        }
                    }
                });

                myHolder.tvBookLabel.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(final View v) {
                        if (!isEditMode) {
                            RecyclerView recyclerView = ((RecyclerView) parent);
                            startEditMode(recyclerView);

                            // header 按钮文字 改成 "完成"
                            View view = recyclerView.getChildAt(0);
                            if (view == recyclerView.getLayoutManager().findViewByPosition(0)) {
                                TextView tvBtnEdit = (TextView) view.findViewById(R.id.btn_edit);
                                tvBtnEdit.setText("完成");
                            }
                        }

                        mItemTouchHelper.startDrag(myHolder);
                        return true;
                    }
                });

                myHolder.tvBookLabel.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (isEditMode) {
                            switch (MotionEventCompat.getActionMasked(event)) {
                                case MotionEvent.ACTION_DOWN:
                                    startTime = System.currentTimeMillis();
                                    break;
                                case MotionEvent.ACTION_MOVE:
                                    if (System.currentTimeMillis() - startTime > SPACE_TIME) {
                                        mItemTouchHelper.startDrag(myHolder);
                                    }
                                    break;
                                case MotionEvent.ACTION_CANCEL:
                                case MotionEvent.ACTION_UP:
                                    startTime = 0;
                                    break;
                            }

                        }
                        return false;
                    }
                });
                return myHolder;

            case TYPE_OTHER_LABEL_HEADER:
                view = mInflater.inflate(R.layout.header_book_label_other, parent, false);
                return new RecyclerView.ViewHolder(view) {
                };

            case TYPE_OTHER_LABEL:
                view = mInflater.inflate(R.layout.adapter_book_label_other, parent, false);
                final OtherBookLabelViewHolder otherHolder = new OtherBookLabelViewHolder(view);

                otherHolder.tvBookLabel.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if (isEditMode) {
                            RecyclerView recyclerView = ((RecyclerView) parent);
                            RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
                            int currentPiosition = otherHolder.getAdapterPosition();
                            // 如果RecyclerView滑动到底部,移动的目标位置的y轴 - height
                            View currentView = manager.findViewByPosition(currentPiosition);
                            // 目标位置的前一个item  即当前MyChannel的最后一个
                            View preTargetView = manager.findViewByPosition(mMyLabel.size() - 1 + COUNT_PRE_MY_HEADER);

                            // 如果targetView不在屏幕内,则为-1  此时不需要添加动画,因为此时notifyItemMoved自带一个向目标移动的动画
                            // 如果在屏幕内,则添加一个位移动画
                            if (recyclerView.indexOfChild(preTargetView) >= 0) {
                                int targetX = preTargetView.getLeft();
                                int targetY = preTargetView.getTop();

                                int targetPosition = mMyLabel.size() - 1 + COUNT_PRE_OTHER_HEADER;

                                GridLayoutManager gridLayoutManager = ((GridLayoutManager) manager);
                                int spanCount = gridLayoutManager.getSpanCount();
                                // target 在最后一行第一个
                                if ((targetPosition - COUNT_PRE_MY_HEADER) % spanCount == 0) {
                                    View targetView = manager.findViewByPosition(targetPosition);
                                    targetX = targetView.getLeft();
                                    targetY = targetView.getTop();
                                } else {
                                    targetX += preTargetView.getWidth();

                                    // 最后一个item可见
                                    if (gridLayoutManager.findLastVisibleItemPosition() == getItemCount() - 1) {
                                        // 最后的item在最后一行第一个位置
                                        if ((getItemCount() - 1 - mMyLabel.size() - COUNT_PRE_OTHER_HEADER) % spanCount == 0) {
                                            // RecyclerView实际高度 > 屏幕高度 && RecyclerView实际高度 < 屏幕高度 + item.height
                                            int firstVisiblePostion = gridLayoutManager.findFirstVisibleItemPosition();
                                            if (firstVisiblePostion == 0) {
                                                // FirstCompletelyVisibleItemPosition == 0 即 内容不满一屏幕 , targetY值不需要变化
                                                // // FirstCompletelyVisibleItemPosition != 0 即 内容满一屏幕 并且 可滑动 , targetY值 + firstItem.getTop
                                                if (gridLayoutManager.findFirstCompletelyVisibleItemPosition() != 0) {
                                                    int offset = (-recyclerView.getChildAt(0).getTop()) - recyclerView.getPaddingTop();
                                                    targetY += offset;
                                                }
                                            } else { // 在这种情况下 并且 RecyclerView高度变化时(即可见第一个item的 position != 0),
                                                // 移动后, targetY值  + 一个item的高度
                                                targetY += preTargetView.getHeight();
                                            }
                                        }
                                    } else {
                                        System.out.println("current--No");
                                    }
                                }

                                // 如果当前位置是otherChannel可见的最后一个
                                // 并且 当前位置不在grid的第一个位置
                                // 并且 目标位置不在grid的第一个位置
                                // 则 需要延迟250秒 notifyItemMove , 这是因为这种情况 , 并不触发ItemAnimator , 会直接刷新界面
                                // 导致我们的位移动画刚开始,就已经notify完毕,引起不同步问题
                                if (currentPiosition == gridLayoutManager.findLastVisibleItemPosition()
                                        && (currentPiosition - mMyLabel.size() - COUNT_PRE_OTHER_HEADER) % spanCount != 0
                                        && (targetPosition - COUNT_PRE_MY_HEADER) % spanCount != 0) {
                                    moveOtherToMyWithDelay(otherHolder);
                                } else {
                                    moveOtherToMy(otherHolder);
                                }
                                startAnimation(recyclerView, currentView, targetX, targetY);

                            } else {
                                moveOtherToMy(otherHolder);
                            }
                        }
                    }
                });
                return otherHolder;
        }
        return null;
    }

    /**
     * 更改偏好储存
     */
    private void changeSp() {
        if (KEY.equals(Constant.BOOK_LABEL_ADDED)) {
            String[] strs = new String[mMyLabel.size()];
            for (int i = 0; i < mMyLabel.size(); i++) {
                strs[i] = mMyLabel.get(i);
            }
            String addedBookLabel = Arrays.toString(strs);
            SharedPreferencesUtils.put(Constant.USER_INFO, Constant.BOOK_LABEL_ADDED, addedBookLabel.substring(1, addedBookLabel.length() - 1));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof AddedBookLabelViewHolder) {

            AddedBookLabelViewHolder myHolder = (AddedBookLabelViewHolder) holder;
            myHolder.tvBookLabel.setText(mMyLabel.get(position - COUNT_PRE_MY_HEADER));
            if (isEditMode) {
                myHolder.ivDelete.setVisibility(View.VISIBLE);
            } else {
                myHolder.ivDelete.setVisibility(View.INVISIBLE);
            }

        } else if (holder instanceof OtherBookLabelViewHolder) {

            ((OtherBookLabelViewHolder) holder).tvBookLabel.setText(mOtherLabel.get(position - mMyLabel.size() - COUNT_PRE_OTHER_HEADER));

        } else if (holder instanceof AddedBookLabelHeaderViewHolder) {

            AddedBookLabelHeaderViewHolder headerHolder = (AddedBookLabelHeaderViewHolder) holder;
            if (isEditMode) {
                headerHolder.btnEdit.setText("完成");
            } else {
                headerHolder.btnEdit.setText("编辑");
            }
        }
    }

    @Override
    public int getItemCount() {
        return mMyLabel.size() + mOtherLabel.size() + COUNT_PRE_OTHER_HEADER;
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        String item = mMyLabel.get(fromPosition - COUNT_PRE_MY_HEADER);
        mMyLabel.remove(fromPosition - COUNT_PRE_MY_HEADER);
        mMyLabel.add(toPosition - COUNT_PRE_MY_HEADER, item);
        notifyItemMoved(fromPosition, toPosition);
    }

    /**
     * 开始增删动画
     */
    private void startAnimation(RecyclerView recyclerView, final View currentView, float targetX, float targetY) {
        final ViewGroup viewGroup = (ViewGroup) recyclerView.getParent();
        final ImageView mirrorView = addMirrorView(viewGroup, recyclerView, currentView);

        Animation animation = getTranslateAnimator(
                targetX - currentView.getLeft(), targetY - currentView.getTop());
        currentView.setVisibility(View.INVISIBLE);
        mirrorView.startAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                viewGroup.removeView(mirrorView);
                if (currentView.getVisibility() == View.INVISIBLE) {
                    currentView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    /**
     * 我的标签 移动到 其他标签
     */
    private void moveMyToOther(AddedBookLabelViewHolder myHolder) {
        int position = myHolder.getAdapterPosition();

        int startPosition = position - COUNT_PRE_MY_HEADER;
        if (startPosition > mMyLabel.size() - 1) {
            return;
        }
        String item = mMyLabel.get(startPosition);
        mMyLabel.remove(startPosition);
        mOtherLabel.add(0, item);

        notifyItemMoved(position, mMyLabel.size() + COUNT_PRE_OTHER_HEADER);
    }

    /**
     * 其他标签 移动到 我的标签
     */
    private void moveOtherToMy(OtherBookLabelViewHolder otherHolder) {
        int position = processItemRemoveAdd(otherHolder);
        if (position == -1) {
            return;
        }
        notifyItemMoved(position, mMyLabel.size() - 1 + COUNT_PRE_MY_HEADER);
    }

    /**
     * 其他标签 移动到 我的标签 伴随延迟
     */
    private void moveOtherToMyWithDelay(OtherBookLabelViewHolder otherHolder) {
        final int position = processItemRemoveAdd(otherHolder);
        if (position == -1) {
            return;
        }
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                notifyItemMoved(position, mMyLabel.size() - 1 + COUNT_PRE_MY_HEADER);
            }
        }, ANIM_TIME);
    }

    private Handler delayHandler = new Handler();

    private int processItemRemoveAdd(OtherBookLabelViewHolder otherHolder) {
        int position = otherHolder.getAdapterPosition();

        int startPosition = position - mMyLabel.size() - COUNT_PRE_OTHER_HEADER;
        if (startPosition > mOtherLabel.size() - 1) {
            return -1;
        }
        String item = mOtherLabel.get(startPosition);
        mOtherLabel.remove(startPosition);
        mMyLabel.add(item);
        return position;
    }


    /**
     * 添加需要移动的 镜像View
     * <p>
     * 我们要获取cache首先要通过setDrawingCacheEnable方法开启cache，然后再调用getDrawingCache方法就可以获得view的cache图片了。
     * buildDrawingCache方法可以不用调用，因为调用getDrawingCache方法时，若果cache没有建立，系统会自动调用buildDrawingCache方法生成cache。
     * 若想更新cache, 必须要调用destoryDrawingCache方法把旧的cache销毁，才能建立新的。
     * 当调用setDrawingCacheEnabled方法设置为false, 系统也会自动把原来的cache销毁。
     */
    private ImageView addMirrorView(ViewGroup parent, RecyclerView recyclerView, View view) {
        view.destroyDrawingCache();
        view.setDrawingCacheEnabled(true);
        final ImageView mirrorView = new ImageView(recyclerView.getContext());
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        mirrorView.setImageBitmap(bitmap);
        view.setDrawingCacheEnabled(false);
        int[] locations = new int[2];
        view.getLocationOnScreen(locations);
        int[] parenLocations = new int[2];
        recyclerView.getLocationOnScreen(parenLocations);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(bitmap.getWidth(), bitmap.getHeight());
        params.setMargins(locations[0], locations[1] - parenLocations[1], 0, 0);
        parent.addView(mirrorView, params);
        return mirrorView;
    }

    /**
     * 我的标签
     */
    private class AddedBookLabelViewHolder extends RecyclerView.ViewHolder implements OnDragVHListener {
        private TextView tvBookLabel;
        private ImageView ivDelete;

        AddedBookLabelViewHolder(View itemView) {
            super(itemView);
            tvBookLabel = (TextView) itemView.findViewById(R.id.tv_book_label);
            ivDelete = (ImageView) itemView.findViewById(R.id.iv_delete);

            if ("2".equals(currentTheme)) {
                tvBookLabel.setTextColor(Color.BLACK);
            }
        }

        /**
         * item 被选中时
         */
        @Override
        public void onItemSelected() {
            tvBookLabel.setBackgroundResource(R.drawable.selector_solid_normaldarkpale_pressedlightgray_selectedlightgray_corners_five);
        }

        /**
         * item 取消选中时
         */
        @Override
        public void onItemFinished() {
            tvBookLabel.setBackgroundResource(R.drawable.selector_solid_normaldarkpale_pressedlightgray_selectedlightgray_corners_five);
        }
    }

    /**
     * 其他标签
     */
    private class OtherBookLabelViewHolder extends RecyclerView.ViewHolder {
        private TextView tvBookLabel;

        OtherBookLabelViewHolder(View itemView) {
            super(itemView);
            tvBookLabel = (TextView) itemView.findViewById(R.id.tv_book_label);
            if ("2".equals(currentTheme)) {
                tvBookLabel.setTextColor(Color.BLACK);
            }
        }
    }

    /**
     * 开启编辑模式
     */
    private void startEditMode(RecyclerView parent) {
        isEditMode = true;

        int visibleChildCount = parent.getChildCount();
        for (int i = 0; i < visibleChildCount; i++) {
            View view = parent.getChildAt(i);
            ImageView ivDelete = (ImageView) view.findViewById(R.id.iv_delete);
            if (ivDelete != null) {
                ivDelete.setVisibility(View.VISIBLE);
            }
        }
    }

    /**
     * 完成编辑模式
     */
    private void cancelEditMode(RecyclerView parent) {
        isEditMode = false;

        int visibleChildCount = parent.getChildCount();
        for (int i = 0; i < visibleChildCount; i++) {
            View view = parent.getChildAt(i);
            ImageView ivDelete = (ImageView) view.findViewById(R.id.iv_delete);
            if (ivDelete != null) {
                ivDelete.setVisibility(View.INVISIBLE);
            }
        }
    }

    /**
     * 获取位移动画
     */
    private TranslateAnimation getTranslateAnimator(float targetX, float targetY) {
        TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.ABSOLUTE, targetX,
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.ABSOLUTE, targetY);
        // RecyclerView默认移动动画250ms 这里设置360ms 是为了防止在位移动画结束后 remove(view)过早 导致闪烁
        translateAnimation.setDuration(ANIM_TIME);
        translateAnimation.setFillAfter(true);
        return translateAnimation;
    }


    /**
     * 我的标签  标题部分
     */
    private class AddedBookLabelHeaderViewHolder extends RecyclerView.ViewHolder {
        private Button btnEdit;

        AddedBookLabelHeaderViewHolder(View itemView) {
            super(itemView);
            btnEdit = (Button) itemView.findViewById(R.id.btn_edit);
        }
    }

}
