package com.admin.projectadmintor.sign;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by apple on 2018/1/4.
 */

public class SquareGridView extends GridView {
    public SquareGridView(Context context){
        super(context);
    }

    public SquareGridView(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
    }

    public SquareGridView(Context context,AttributeSet attributeSet,int defStyleAttr){
        super(context,attributeSet,defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec=MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
