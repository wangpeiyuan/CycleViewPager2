package com.wangpeiyuan.cycleviewpager2.indicator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.ColorInt;
import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.wangpeiyuan.cycleviewpager2.util.DisplayUtil;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by wangpeiyuan on 2019-12-04.
 */
public class DotsIndicator extends View implements Indicator {

    private final Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float mRadius;
    private float mDotsPadding;
    @ColorInt
    private int mSelectedColor;
    @ColorInt
    private int mUnSelectedColor;

    private int mDotsCount;
    private int mCurrentSelectedPosition;

    private int mLeftMargin;
    private int mBottomMargin;
    private int mRightMargin;
    @Direction
    private int mDirection = Direction.CENTER;

    @IntDef({Direction.LEFT, Direction.CENTER, Direction.RIGHT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Direction {
        int LEFT = 0;
        int CENTER = 1;
        int RIGHT = 2;
    }

    public DotsIndicator(Context context) {
        super(context);
        initialize(context, null);
    }

    public DotsIndicator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize(context, attrs);
    }

    public DotsIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public DotsIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize(context, attrs);
    }

    private void initialize(@NonNull Context context, @Nullable AttributeSet attrs) {
        mRadius = DisplayUtil.dp2px(3);
        mDotsPadding = DisplayUtil.dp2px(3);
        mSelectedColor = Color.GRAY;
        mUnSelectedColor = Color.WHITE;

        mLeftMargin = mRightMargin = 0;
        mBottomMargin = (int) DisplayUtil.dp2px(6);
    }

    @NonNull
    @Override
    public View getIndicatorView() {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        switch (mDirection) {
            case Direction.LEFT:
                layoutParams.gravity = Gravity.BOTTOM | Gravity.START;
                break;
            case Direction.CENTER:
                layoutParams.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
                break;
            case Direction.RIGHT:
                layoutParams.gravity = Gravity.BOTTOM | Gravity.END;
                break;
        }
        layoutParams.leftMargin = mLeftMargin;
        layoutParams.bottomMargin = mBottomMargin;
        layoutParams.rightMargin = mRightMargin;
        setLayoutParams(layoutParams);
        return this;
    }

    @Override
    public void onChanged(int itemCount, int currentPosition) {
        mDotsCount = itemCount;
        mCurrentSelectedPosition = currentPosition;
        requestLayout();
        postInvalidate();
    }

    @Override
    public void onPageSelected(int position) {
        mCurrentSelectedPosition = position;
        postInvalidate();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //do nothing
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //do nothing
    }

    public void setRadius(float radius) {
        this.mRadius = radius;
    }

    public void setDotsPadding(float dotsPadding) {
        this.mDotsPadding = dotsPadding;
    }

    public void setSelectedColor(@ColorInt int selectedColor) {
        this.mSelectedColor = selectedColor;
    }

    public void setUnSelectedColor(@ColorInt int unSelectedColor) {
        this.mUnSelectedColor = unSelectedColor;
    }

    public void setLeftMargin(int leftMargin) {
        this.mLeftMargin = leftMargin;
    }

    public void setBottomMargin(int bottomMargin) {
        this.mBottomMargin = bottomMargin;
    }

    public void setRightMargin(int rightMargin) {
        this.mRightMargin = rightMargin;
    }

    public void setDirection(@Direction int direction) {
        this.mDirection = direction;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mDotsCount > 1) {
            int width = (int) (mDotsCount * mRadius * 2 + (mDotsCount - 1) * mDotsPadding);
            int height = (int) (mRadius * 2);
            setMeasuredDimension(width, height);
            return;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mDotsCount > 1) {
            float mTempTranslateX = mRadius;
            for (int i = 0; i < mDotsCount; i++) {
                mPaint.setColor(mCurrentSelectedPosition == i ? mSelectedColor : mUnSelectedColor);
                canvas.drawCircle(mTempTranslateX, mRadius, mRadius, mPaint);
                mTempTranslateX = mTempTranslateX + mRadius + mDotsPadding + mRadius;
            }
        }
    }
}
