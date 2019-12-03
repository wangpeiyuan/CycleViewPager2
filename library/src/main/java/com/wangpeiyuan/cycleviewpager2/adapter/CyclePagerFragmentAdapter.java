package com.wangpeiyuan.cycleviewpager2.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

/**
 * Created by wangpeiyuan on 2019-12-02.
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
    public Fragment createFragment(int position) {
        return createRealFragment(getFixPosition(position));
    }

    @Override
    public int getItemCount() {
        return getRealItemCount() > 1 ? getRealItemCount() + 2 : getRealItemCount();
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

    @NonNull
    public abstract Fragment createRealFragment(int position);

}
