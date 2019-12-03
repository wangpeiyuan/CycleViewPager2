package com.wangpeiyuan.cycleviewpager2.util;

import android.util.Log;

/**
 * Created by wangpeiyuan on 2019-12-03.
 */
public class Logger {
    private static final String TAG = "CycleViewPager2";

    private static boolean isDebug = false;

    private Logger() {

    }

    public static void setIsDebug(boolean isDebug) {
        Logger.isDebug = isDebug;
    }

    public static void d(String msg) {
        if (isDebug) {
            Log.d(TAG, msg);
        }
    }

    public static void e(String msg) {
        if (isDebug) {
            Log.e(TAG, msg);
        }
    }

}
