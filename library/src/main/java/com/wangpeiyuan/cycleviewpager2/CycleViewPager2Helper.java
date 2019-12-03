package com.wangpeiyuan.cycleviewpager2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.wangpeiyuan.cycleviewpager2.itemdecoration.MarginItemDecoration;
import com.wangpeiyuan.cycleviewpager2.transformer.MultiplePagerScaleInTransformer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangpeiyuan on 2019-12-03.
 */
public class CycleViewPager2Helper {
    private CycleViewPager2 cycleViewPager2;
    private RecyclerView.Adapter adapter;
    @ViewPager2.Orientation
    private int orientation = ViewPager2.ORIENTATION_HORIZONTAL;
    @ViewPager2.OffscreenPageLimit
    private int limit = 1;
    private CompositePageTransformer compositePageTransformer;
    private List<RecyclerView.ItemDecoration> itemDecorations;
    private List<ViewPager2.OnPageChangeCallback> pageChangeCallbacks;

    private long autoTurningTime;

    public CycleViewPager2Helper(@NonNull CycleViewPager2 cycleViewPager2) {
        this.cycleViewPager2 = cycleViewPager2;
    }

    public CycleViewPager2Helper setAdapter(@Nullable RecyclerView.Adapter adapter) {
        this.adapter = adapter;
        return this;
    }

    public CycleViewPager2Helper setOrientation(@ViewPager2.Orientation int orientation) {
        this.orientation = orientation;
        return this;
    }

    public CycleViewPager2Helper setOffscreenPageLimit(@ViewPager2.OffscreenPageLimit int limit) {
        this.limit = limit;
        return this;
    }

    public CycleViewPager2Helper addPageTransformer(@NonNull ViewPager2.PageTransformer pageTransformer) {
        if (compositePageTransformer == null) {
            compositePageTransformer = new CompositePageTransformer();
        }
        compositePageTransformer.addTransformer(pageTransformer);
        return this;
    }

    private CycleViewPager2Helper addItemDecoration(@NonNull RecyclerView.ItemDecoration itemDecoration) {
        if (itemDecorations == null) {
            itemDecorations = new ArrayList<>();
        }
        itemDecorations.add(itemDecoration);
        return this;
    }

    public CycleViewPager2Helper registerOnPageChangeCallback(@NonNull ViewPager2.OnPageChangeCallback callback) {
        if (pageChangeCallbacks == null) {
            pageChangeCallbacks = new ArrayList<>();
        }
        pageChangeCallbacks.add(callback);
        return this;
    }

    public CycleViewPager2Helper setMultiplePagerScaleInTransformer(int nextItemVisiblePx,
                                                                    int currentItemHorizontalMarginPx,
                                                                    float scale) {
        addItemDecoration(new MarginItemDecoration(currentItemHorizontalMarginPx));
        addPageTransformer(new MultiplePagerScaleInTransformer(nextItemVisiblePx + currentItemHorizontalMarginPx, scale));
        return this;
    }

    public CycleViewPager2Helper setAutoTurning(long autoTurningTime) {
        this.autoTurningTime = autoTurningTime;
        return this;
    }

    public void build() {
        cycleViewPager2.setOrientation(orientation);
        cycleViewPager2.setOffscreenPageLimit(limit);

        if (adapter != null) {
            cycleViewPager2.setAdapter(adapter);
        }
        if (itemDecorations != null && !itemDecorations.isEmpty()) {
            for (RecyclerView.ItemDecoration itemDecoration : itemDecorations) {
                cycleViewPager2.addItemDecoration(itemDecoration);
            }
        }
        if (compositePageTransformer != null) {
            cycleViewPager2.setPageTransformer(compositePageTransformer);
        }
        if (pageChangeCallbacks != null && !pageChangeCallbacks.isEmpty()) {
            for (ViewPager2.OnPageChangeCallback pageChangeCallback : pageChangeCallbacks) {
                cycleViewPager2.registerOnPageChangeCallback(pageChangeCallback);
            }
        }
        if (autoTurningTime > 0) {
            cycleViewPager2.setAutoTurning(autoTurningTime);
        }
    }
}
