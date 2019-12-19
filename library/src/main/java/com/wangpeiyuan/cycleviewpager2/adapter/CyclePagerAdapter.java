package com.wangpeiyuan.cycleviewpager2.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wangpeiyuan.cycleviewpager2.util.CyclePositionUtil;

import java.util.List;

import static androidx.recyclerview.widget.RecyclerView.NO_ID;

/**
 * Created by wangpeiyuan on 2019-12-02.
 */
public abstract class CyclePagerAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    @Override
    public final int getItemCount() {
        return getRealItemCount() > 1 ? getRealItemCount() + 2 : getRealItemCount();
    }

    @Override
    public final void onBindViewHolder(@NonNull VH holder, int position) {
        onBindRealViewHolder(holder, CyclePositionUtil.getRealPosition(position, getRealItemCount()));
    }

    @Override
    public final void onBindViewHolder(@NonNull VH holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, CyclePositionUtil.getRealPosition(position, getRealItemCount()), payloads);
    }


    @Override
    public final int getItemViewType(int position) {
        return getRealItemViewType(CyclePositionUtil.getRealPosition(position, getRealItemCount()));
    }

    @Override
    public final long getItemId(int position) {
        return getRealItemId(CyclePositionUtil.getRealPosition(position, getRealItemCount()));
    }

    public abstract int getRealItemCount();

    public abstract void onBindRealViewHolder(@NonNull VH holder, int position);

    public int getRealItemViewType(int position) {
        return 0;
    }

    public long getRealItemId(int position) {
        return NO_ID;
    }

}
