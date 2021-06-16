package com.livelearn.ignorance.test.assembleessay.adpter;


import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by rth on 16-11-16.
 */

public class DragItemTouchCallBack extends ItemTouchHelper.Callback {

    private final ItemTouchHelperAdapter touchHelperAdapter;

    public DragItemTouchCallBack(ItemTouchHelperAdapter touchHelperAdapter) {
        this.touchHelperAdapter = touchHelperAdapter;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN
                | ItemTouchHelper.START | ItemTouchHelper.END;
        return makeFlag(ItemTouchHelper.ACTION_STATE_DRAG, dragFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        touchHelperAdapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

}
