package com.example.richeditortest.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.richeditortest.R;

public class SanJiaoView extends View {

    private final int TOP = 0;
    private final int BOTTOM = 1;
    private final int RIGHT = 2;
    private final int LEFT = 3;

    /**
     * 画三角的画笔
     */
    private Paint mPaint = new Paint();

    /**
     * 三角的颜色
     */
    private int color = 0xFF000000;

    /**
     * 三角的宽度
     */
    private int width = 50;

    /**
     * 三角的高度
     */
    private int height = 50;

    /**
     * 三角的方向
     */
    private int direction = TOP;

    public SanJiaoView(Context context) {
        super(context);
    }

    public SanJiaoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SanJiaoView, 0, 0);
        color = a.getColor(R.styleable.SanJiaoView_sjv_color, color);
        width = (int) a.getDimension(R.styleable.SanJiaoView_sjv_resolutionWidth, width);
        height = (int) a.getDimension(R.styleable.SanJiaoView_sjv_resolutionHeight, height);
        direction = a.getInt(R.styleable.SanJiaoView_sjv_direction, direction);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //设置画笔的颜色
        mPaint.setColor(color);
        //设置画笔抗锯齿
        mPaint.setAntiAlias(true);
        //设置画笔为实心的
        mPaint.setStyle(Paint.Style.FILL);
        //设置画笔的路径
        Path path = new Path();
        switch (direction) {
            case TOP:
                path.moveTo(0, width);
                path.lineTo(width, height);
                path.lineTo(width / 2, 0);
                break;
            case BOTTOM:
                path.moveTo(0, 0);
                path.lineTo(width / 2, height);
                path.lineTo(width, 0);
                break;
            case RIGHT:
                path.moveTo(0, 0);
                path.lineTo(0, height);
                path.lineTo(width, height / 2);
                break;
            case LEFT:
                path.moveTo(0, height / 2);
                path.lineTo(width, height);
                path.lineTo(width, 0);
                break;
        }

        path.close();
        canvas.drawPath(path, mPaint);
    }

}