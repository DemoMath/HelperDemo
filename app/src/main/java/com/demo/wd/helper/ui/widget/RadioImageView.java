package com.demo.wd.helper.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.demo.wd.helper.R;


/**
 * Created by Administrator on 2016/4/15.
 */
public class RadioImageView extends ImageView {

    private float radio;

    public RadioImageView(Context context) {
        this(context,null);
    }

    public RadioImageView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RadioImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RadioImageView);
        radio = typedArray.getFloat(R.styleable.RadioImageView_radio, 0);
        //TypedArray要记得recycle
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMeasureSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthMeasureSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthSrcMeasureSpecSize = widthMeasureSpecSize - getPaddingLeft() - getPaddingRight();

        int heightMeasureSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightMeasureSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightSrcMeasureSpecSize = heightMeasureSpecSize - getPaddingTop() - getPaddingBottom();

        if (widthMeasureSpecMode == MeasureSpec.EXACTLY && heightMeasureSpecMode != MeasureSpec.EXACTLY && radio != 0 ){
            int height = (int)(widthSrcMeasureSpecSize / radio + 0.5) + getPaddingTop() + getPaddingBottom();
            heightMeasureSpec =  MeasureSpec.makeMeasureSpec(height,MeasureSpec.EXACTLY);
        }else if (heightMeasureSpecMode == MeasureSpec.EXACTLY && widthMeasureSpecMode != MeasureSpec.EXACTLY && radio != 0 ) {
            int weight = (int)(heightSrcMeasureSpecSize * radio + 0.5) + getPaddingTop() + getPaddingBottom();
            widthMeasureSpec =  MeasureSpec.makeMeasureSpec(weight,MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
