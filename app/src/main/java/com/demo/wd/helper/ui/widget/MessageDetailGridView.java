package com.demo.wd.helper.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * @author jiangyue
 *
 */
public class MessageDetailGridView extends GridView{
	

	public MessageDetailGridView(Context context) {
		super(context);
	}

	public MessageDetailGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec); 
	}

}
