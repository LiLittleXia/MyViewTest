package com.lz.myviewtest;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2019/2/26.
 */

public class MyCircularView extends View {

    private int color ;

    public MyCircularView(Context context) {
        super(context);
    }

    public MyCircularView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.MyViewColor);
        color = a.getColor(R.styleable.MyViewColor_circularColor,Color.parseColor("#468aff"));
        //最后记得将TypedArray对象回收
        a.recycle();

    }

    public MyCircularView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //正方形
        int w = getMySize(100,widthMeasureSpec);
        int h = getMySize(100,heightMeasureSpec);

        if(w>h){
            w = h;
        }else {
            h = w;
        }
        setMeasuredDimension(w,h);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        //父类实现了基本的而绘制功能，比如绘制背景颜色、背景图片等
        super.onDraw(canvas);

        int r = getMeasuredWidth()/2;
        //圆心横坐标，左边距+半径
        int centerX = r;
        //圆心纵坐标，上边距+半径
        int centerY = r;



        Paint p = new Paint();
        p.setColor(color);
        //画圆
        Log.d("lz", "onDraw: ---坐标（"+(getLeft()+r)+","+(getTop() +r)+")");
        canvas.drawCircle(centerX,centerY,r,p);

    }

    private int getMySize(int defaultSize, int measureSpec){
        //先设置成默认值
        int mySize = defaultSize;
        //获取模式和大小
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        //根据mode获取size
        switch (mode){
            case MeasureSpec.UNSPECIFIED://没指定取默认值
                mySize = defaultSize;
                break;
            case MeasureSpec.AT_MOST://获取了最大尺寸用最大尺寸
                mySize = size;
                break;
            case MeasureSpec.EXACTLY://指定尺寸用指定尺寸
                mySize = size;
            break;
        }
        return mySize;
    }
}
