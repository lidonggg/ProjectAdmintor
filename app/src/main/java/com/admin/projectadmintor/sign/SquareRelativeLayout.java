package com.admin.projectadmintor.sign;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by apple on 2018/1/4.
 */

public class SquareRelativeLayout extends RelativeLayout {
    public SquareRelativeLayout(Context context){
        super(context);
    }

    public SquareRelativeLayout(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
    }

    public SquareRelativeLayout(Context context,AttributeSet attributeSet,int defStyleAttr){
        super(context,attributeSet,defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getDefaultSize(0,widthMeasureSpec),getDefaultSize(0,heightMeasureSpec));
        int childWidthSize=getMeasuredWidth();
        widthMeasureSpec=heightMeasureSpec=MeasureSpec.makeMeasureSpec(childWidthSize,MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
