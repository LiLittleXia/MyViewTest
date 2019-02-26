package com.lz.myviewtest;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2019/2/26.
 */

public class MyView extends View {
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
