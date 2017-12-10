package com.nemanja.userapp.util;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by Vladan on 7/7/2017.
 */

public class ItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private OnSwipedListener onSwipedListener;

    public ItemTouchHelperCallback(OnSwipedListener onSwipedListener) {
        this.onSwipedListener = onSwipedListener;
}

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
             int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
             int swipeFlags = 0;
            return makeMovementFlags(dragFlags, swipeFlags);
        } else {
            //单列的RecyclerView支持上下拖动和左右侧滑
             int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
             int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
            return makeMovementFlags(dragFlags, swipeFlags);
        }
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;


    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        onSwipedListener.onItemDismiss(viewHolder.getAdapterPosition());
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return false;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }
}
