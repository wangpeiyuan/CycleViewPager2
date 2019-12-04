package com.wangpeiyuan.cycleviewpager2.util;

import android.content.res.Resources;
import android.util.TypedValue;

/**
 * Created by wangpeiyuan on 2019-12-04.
 */
public class DisplayUtil {

    public static float dp2px(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }
}
