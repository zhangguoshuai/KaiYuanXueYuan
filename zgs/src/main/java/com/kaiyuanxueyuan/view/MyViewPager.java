package com.kaiyuanxueyuan.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * 自定义滑动标签页禁止滑动设置（目前没用到）
 * @author Administrator
 *
 */
public class MyViewPager extends ViewPager {
	  
    private boolean isCanScroll = true;  
  
    public MyViewPager(Context context) {
        super(context);  
    }  
  
    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);  
    }  
  
  
    @Override
    public void scrollTo(int x, int y){  
        if (isCanScroll){  
            super.scrollTo(x, y);  
        }  
    }  
   
    public void setScanScroll(boolean isCanScroll){  
        this.isCanScroll = isCanScroll;  
    }
   
    
} 