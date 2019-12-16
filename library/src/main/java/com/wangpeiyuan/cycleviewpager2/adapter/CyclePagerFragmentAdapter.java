package com.wangpeiyuan.cycleviewpager2.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.adapter.FragmentViewHolder;

import java.util.List;

import static androidx.recyclerview.widget.RecyclerView.NO_ID;

/**
 * Created by wangpeiyuan on 2019-12-02.
 * Modified by NaJiPeng on 2019-12-16.
 */
public abstract class CyclePagerFragmentAdapter extends FragmentStateAdapter {

    public CyclePagerFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public CyclePagerFragmentAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public CyclePagerFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public final Fragment createFragment(int position) {
        return createRealFragment(getFixPosition(position));
    }

    @Override
    public final int getItemCount() {
        return getRealItemCount() > 1 ? getRealItemCount() + 2 : getRealItemCount();
    }

    @Override
    public final long getItemId(int position) {
        return getRealItemId(getFixPosition(position));
    }

    @Override
    public final void onBindViewHolder(@NonNull FragmentViewHolder holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, getFixPosition(position), payloads);
    }

    @Override
    public final int getItemViewType(int position) {
        return getRealItemViewType(getFixPosition(position));
    }

    private int getFixPosition(int position) {
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

    @NonNull
    public abstract Fragment createRealFragment(int position);

    public int getRealItemViewType(int position){
        return 0;
    }

    public long getRealItemId(int position) {
        return NO_ID;
    }

}
