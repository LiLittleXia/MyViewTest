package com.lz.myviewtest;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2019/2/26.
 */

public class MyViewGroup extends ViewGroup {


    public MyViewGroup(Context context) {
        super(context);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //将所有的子View进行测量，这会触发每个子View的onMeasure函数
        //注意要与measureChild区分，measureChild是对单个view进行测量
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int pl = getPaddingLeft();//获取padding

        MarginLayoutParams lp = (MarginLayoutParams)getLayoutParams();
        int ml = Math.max(0,lp.leftMargin);//获取margin

        //获取子view数量
        int childCount = getChildCount();
        if (0 == childCount) {//没view0，0
            setMeasuredDimension(0, 0);
        } else {
            //如果宽高都是包裹内容
            if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
                //高度取所有view相加，高度取所有view的最大值
                int w = getMaxChildWidth();
                int h = getTotleHeight();
                setMeasuredDimension(w+pl, h);
            } else if (heightMode == MeasureSpec.AT_MOST) {//如果只有高度是包裹内容
                setMeasuredDimension(widthSize, getTotleHeight());
            } else if (widthMode == MeasureSpec.AT_MOST) {
                setMeasuredDimension(getMaxChildWidth(), heightSize);
            }
        }

    }

    /**
     * 当这个view和其子view被分配一个大小和位置时，被layout调用。
     * @param changed 当前View的大小和位置改变了
     * @param left 左部位置（相对于父视图）
     * @param top 顶部位置（相对于父视图）
     * @param right 右部位置（相对于父视图）
     * @param bottom 底部位置（相对于父视图）
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int cound = getChildCount();
        int curHight = top;
        for (int i = 0; i < cound; i++) {
            View childView = getChildAt(i);
            int h = childView.getMeasuredHeight();
            int w = childView.getMeasuredWidth();

            int pl = childView.getPaddingLeft();//获取padding

            MarginLayoutParams lp = (MarginLayoutParams) childView.getLayoutParams();
            int ml = Math.max(0,lp.leftMargin);//获取margin
            //摆放子View，参数分别是子View矩形区域的左、上、右、下边
            childView.layout(left,curHight,left+w,curHight+h);
            curHight+=h;
        }

    }

    public int getMaxChildWidth() {
        int maxChildWidth = 0;
        int cound = getChildCount();
        for (int i = 0; i < cound; i++) {
            View child = getChildAt(i);
            if (child.getMeasuredWidth() > maxChildWidth) {
                maxChildWidth = child.getMeasuredWidth();
            }
        }
        return maxChildWidth;
    }

    public int getTotleHeight() {
        int totleHeight = 0;
        int cound = getChildCount();
        for (int i = 0; i < cound; i++) {
            View child = getChildAt(i);
            totleHeight += child.getMeasuredHeight();
        }
        return totleHeight;
    }
}
