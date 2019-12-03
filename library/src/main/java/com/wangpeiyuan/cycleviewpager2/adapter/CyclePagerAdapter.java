package com.wangpeiyuan.cycleviewpager2.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by wangpeiyuan on 2019-12-02.
 */
public abstract class CyclePagerAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    @Override
    public int getItemCount() {
        return getRealItemCount() > 1 ? getRealItemCount() + 2 : getRealItemCount();
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        onRealBindViewHolder(holder, getFixPosition(position));
    }

    protected int getFixPosition(int position) {
        int fixPosition;
        int realItemCount = getRealItemCount();
        if (position == 0) {
            fixPosition = realItemCount - 1;
        } else if (position == realItemCount + 1) {
            fixPosition = 0;
        } else {
            fixPosition = position - 1;
        }
        return fixPosition;
    }

    public abstract int getRealItemCount();

    public abstract void onRealBindViewHolder(@NonNull VH holder, int position);
}
