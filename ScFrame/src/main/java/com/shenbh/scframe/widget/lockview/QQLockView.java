package com.shenbh.scframe.widget.lockview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.blankj.utilcode.util.SizeUtils;

/**
 * 作者：shenbh
 * 创建时间：2017/11/20
 * 邮箱：93234929@qq.com
 * 说明：
 * 仿QQ手势解锁view
 */
public class QQLockView extends View implements ILockView {

    private Paint mPaint;
    private int mCurrentState = NO_FINGER;
    private float mOuterRadius;
    private float mInnerRadius;

    public QQLockView(Context context) {
        this(context, null);
    }

    public QQLockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        width = width > height ? height : width;
        setMeasuredDimension(width, width);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float space = 10;
        float x = getWidth() / 2;
        float y = getHeight() / 2;
        canvas.translate(x, y);
        mOuterRadius = x - space;
        mInnerRadius = (x - space) / 3;
        switch (mCurrentState) {
            case NO_FINGER:
                drawNoFinger(canvas);
                break;
            case FINGER_TOUCH:
                drawFingerTouch(canvas);
                break;
            case FINGER_UP_MATCHED:
                drawFingerUpMatched(canvas);
                break;
            case FINGER_UP_UN_MATCHED:
                drawFingerUpUnmatched(canvas);
                break;
        }
    }

    /**
     * 画无手指触摸状态
     *
     * @param canvas
     */
    private void drawNoFinger(Canvas canvas) {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.parseColor("#838383"));
        mPaint.setStrokeWidth(SizeUtils.dp2px(2));
        canvas.drawCircle(0, 0, mOuterRadius, mPaint);
    }

    /**
     * 画手指触摸状态
     *
     * @param canvas
     */
    private void drawFingerTouch(Canvas canvas) {
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.parseColor("#ADD5E6"));
        canvas.drawCircle(0, 0, mOuterRadius, mPaint);
        mPaint.setColor(Color.parseColor("#01A0E5"));
        canvas.drawCircle(0, 0, mInnerRadius, mPaint);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.parseColor("#01A0E5"));
        mPaint.setStrokeWidth(SizeUtils.dp2px(1));
        canvas.drawCircle(0, 0, mOuterRadius, mPaint);
    }

    /**
     * 画手指抬起，匹配状态
     *
     * @param canvas
     */
    private void drawFingerUpMatched(Canvas canvas) {
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.parseColor("#ADD5E6"));
        canvas.drawCircle(0, 0, mOuterRadius, mPaint);
        mPaint.setColor(Color.parseColor("#01A0E5"));
        canvas.drawCircle(0, 0, mInnerRadius, mPaint);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.parseColor("#838383"));
        mPaint.setStrokeWidth(SizeUtils.dp2px(2));
        canvas.drawCircle(0, 0, mOuterRadius, mPaint);
    }

    /**
     * 画手指抬起，不匹配状态
     *
     * @param canvas
     */
    private void drawFingerUpUnmatched(Canvas canvas) {
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.parseColor("#EDACA7"));
        canvas.drawCircle(0, 0, mOuterRadius, mPaint);
        mPaint.setColor(Color.parseColor("#F7564A"));
        canvas.drawCircle(0, 0, mInnerRadius, mPaint);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.parseColor("#F7564A"));
        mPaint.setStrokeWidth(SizeUtils.dp2px(1));
        canvas.drawCircle(0, 0, mOuterRadius, mPaint);
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public View newInstance(Context context) {
        return new QQLockView(context);
    }

    @Override
    public void onNoFinger() {
        mCurrentState = NO_FINGER;
        postInvalidate();
    }

    @Override
    public void onFingerTouch() {
        mCurrentState = FINGER_TOUCH;
        postInvalidate();
    }

    @Override
    public void onFingerUpMatched() {
        mCurrentState = FINGER_UP_MATCHED;
        postInvalidate();
    }

    @Override
    public void onFingerUpUnmatched() {
        mCurrentState = FINGER_UP_UN_MATCHED;
        postInvalidate();
    }

    public void setCurrentState(int state) {
        this.mCurrentState = state;
        postInvalidate();
    }

}
