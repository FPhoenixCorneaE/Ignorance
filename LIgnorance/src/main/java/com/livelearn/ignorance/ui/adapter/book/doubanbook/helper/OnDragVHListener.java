package com.livelearn.ignorance.ui.adapter.book.doubanbook.helper;

/**
 * Created by ChinaLHR on 2016/12/23.
 * Email:13435500980@163.com
 * ViewHolder 标签被选中 以及 拖拽释放 触发监听器
 */

public interface OnDragVHListener {
    //Item选中时触发
    void onItemSelected();

    //Item拖拽结束后触发
    void onItemFinished();
}
