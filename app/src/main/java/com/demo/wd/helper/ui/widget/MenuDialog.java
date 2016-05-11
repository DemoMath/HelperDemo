package com.demo.wd.helper.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager.LayoutParams;

import com.demo.wd.helper.utils.CommonUtils;


/**
 * 这是首页显示工具的Dialog
 * Created by Administrator on 2016/4/27.
 */
public class MenuDialog extends Dialog{

    public MenuDialog(Context context) {
        super(context);
    }

    public MenuDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected MenuDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        LayoutParams attributes = window.getAttributes();
        attributes.gravity = Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL;
        attributes.width = CommonUtils.dip2px(200);
        attributes.height = CommonUtils.dip2px(100);
    }
}
