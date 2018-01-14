package com.haibin.calendarview.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.WeekView;

/**
 * 简单周视图
 * Created by huanghaibin on 2017/11/29.
 */

public class SimpleWeekView extends WeekView{
    private int mRadius;

    private Paint mTextPaint = new Paint();
    private Paint mSchemeBasicPaint = new Paint();
    private float mRadio;
    private int mPadding;
    private float mSchemeBaseLine;

    public SimpleWeekView(Context context) {
        super(context);
    }

    @Override
    protected void onPreviewHook() {
        mRadius = Math.min(mItemWidth, mItemHeight) / 5 * 2;
        mSchemePaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDrawSelected(Canvas canvas, Calendar calendar, int x, boolean hasScheme) {
        int cx = x + mItemWidth / 2;
        int cy =  mItemHeight / 2;
        canvas.drawCircle(cx, cy, mRadius, mSelectedPaint);
    }

    @Override
    protected void onDrawScheme(Canvas canvas, Calendar calendar, int x) {
        /*int cx = x + mItemWidth / 2;
        int cy = mItemHeight / 2;
        canvas.drawCircle(cx, cy, mRadius, mSchemePaint);*/

       /* mSchemeBasicPaint.setAntiAlias(true);
        //mSchemeBasicPaint.setStyle(Paint.Style.FILL);
        mSchemeBasicPaint.setTextAlign(Paint.Align.CENTER);
        mSchemeBasicPaint.setColor(0xffed5353);
        mSchemeBasicPaint.setFakeBoldText(true);*/
        mRadio = dipToPx(getContext(), 7);
        mPadding = dipToPx(getContext(), 4);
        Paint.FontMetrics metrics = mSchemeBasicPaint.getFontMetrics();
        mSchemeBaseLine = mRadio - metrics.descent + (metrics.bottom - metrics.top) / 2 + dipToPx(getContext(), 1);
    }

    @Override
    protected void onDrawText(Canvas canvas, Calendar calendar, int x, boolean hasScheme, boolean isSelected) {
        /*float baselineY = mTextBaseLine ;
        int cx = x + mItemWidth / 2;
        if (hasScheme) {
            canvas.drawText(String.valueOf(calendar.getDay()),
                    cx,
                    baselineY,
                    calendar.isCurrentDay() ? mCurDayTextPaint :
                            calendar.isCurrentMonth() ? mSchemeTextPaint : mSchemeTextPaint);

        } else {
            canvas.drawText(String.valueOf(calendar.getDay()), cx, baselineY,
                    calendar.isCurrentDay() ? mCurDayTextPaint :
                            calendar.isCurrentMonth() ? mCurMonthTextPaint : mCurMonthTextPaint);
        }*/

        int cx = x + mItemWidth / 2;
        int top = 0;
        if (hasScheme) {
            //mSchemeTextPaint.setColor(calendar.getSchemeColor());
            //mCurMonthLunarTextPaint.setColor(mCurMonthLunarTextColor);
            canvas.drawText(String.valueOf(calendar.getDay()), cx, mTextBaseLine + top,
                    calendar.isCurrentDay() ? mCurDayTextPaint :
                            calendar.isCurrentMonth() ? mSchemeTextPaint : mSchemeTextPaint);

            //canvas.drawText(calendar.getLunar(), cx, mTextBaseLine + mItemHeight / 10, mCurMonthLunarTextPaint);

            mTextPaint.setColor( Color.WHITE);
            mSchemeBasicPaint.setColor(calendar.getSchemeColor());

            canvas.drawCircle(x + mItemWidth - mPadding - mRadio / 2, mPadding + mRadio, mRadio, mSchemeBasicPaint);

            canvas.drawText(calendar.getScheme(), x + mItemWidth - mPadding - mRadio, mPadding + mSchemeBaseLine, mTextPaint);

        } else {
            canvas.drawText(String.valueOf(calendar.getDay()), cx, mTextBaseLine + top,

                            calendar.isCurrentMonth() ? mCurMonthTextPaint : mCurMonthTextPaint);
            //calendar.isCurrentDay() ? mCurDayTextPaint :

            if(calendar.isCurrentDay()){
                //canvas.drawCircle(x + mItemWidth - mPadding - mRadio / 2, y + mPadding + mRadio, mRadio, mSchemeBasicPaint);
                canvas.drawCircle(x + mItemWidth / 2, mItemHeight / 2, mRadius, mSchemePaint);
            }
            //canvas.drawText(calendar.getLunar(), cx, mTextBaseLine + mItemHeight / 10, mCurMonthLunarTextPaint);
        }
    }

    /**
     * dp转px
     *
     * @param context context
     * @param dpValue dp
     * @return px
     */
    private static int dipToPx(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
