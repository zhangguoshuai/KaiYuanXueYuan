package com.kaiyuanxueyuan.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kaiyuanxueyuan.R;

import java.util.List;

// 标签页滑动布局
public class ViewPagerIndicator extends LinearLayout {

	private Paint mPaint;

	private Path mPath;

	private int mTriangleWidth;

	@SuppressWarnings("unused")
	private int mTriangleHeight;

	private static final float RADIO_TRIANGLE_WIDTH = 1 / 6F;
	/**
	 * 三角形底边最大宽度
	 */
	private final int DIMENSION_TRANGLE_WIDTH_MAX = (int) (getScreenWidth() / 3 * RADIO_TRIANGLE_WIDTH);

	private int mInitTranslationX;

	private int mTranslationX;

	private int mTabVisibleCout;

	private static final int COUNT_DEFAULT_TAB = 4;

	private List<String> mTitles;
	
	private boolean triangle = true ;

	public ViewPagerIndicator(Context context) {
		this(context, null);
	}

	public ViewPagerIndicator(Context context, AttributeSet attrs) {
		super(context, attrs);

		// 获取可见tab的数量
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ViewPagerIndicator);

		mTabVisibleCout = a.getInt(R.styleable.ViewPagerIndicator_visible_tab_count, COUNT_DEFAULT_TAB);
		triangle = a.getBoolean(R.styleable.ViewPagerIndicator_visible_online, true);
		if (mTabVisibleCout < 0) {
			mTabVisibleCout = COUNT_DEFAULT_TAB;
		}

		a.recycle();
		// 初始化画笔
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setColor(Color.parseColor("#4c90f9"));// 蓝色
		mPaint.setStyle(Style.FILL);
		mPaint.setPathEffect(new CornerPathEffect(3));
	}

	@Override
	protected void dispatchDraw(Canvas canvas) {
		
		if (triangle) {
			canvas.save();
			canvas.translate(mInitTranslationX + mTranslationX, getHeight());
			canvas.drawPath(mPath, mPaint);
			canvas.restore();
		}
		
		super.dispatchDraw(canvas);
	}

    @Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);

		mTriangleWidth = (int) (w / mTabVisibleCout * RADIO_TRIANGLE_WIDTH);
		mTriangleWidth = Math.min(DIMENSION_TRANGLE_WIDTH_MAX, mTriangleWidth);
		mInitTranslationX = w / mTabVisibleCout / 2 - mTriangleWidth / 2;
		
		if (triangle) {
			initTriangle();
		}
		
	}

	@Override
	protected void onFinishInflate() {

		super.onFinishInflate();

		int cCount = getChildCount();
		if (cCount == 0)
			return;
		for (int i = 0; i < cCount; i++) {
			View view = getChildAt(i);
			LayoutParams lp = (LayoutParams) view.getLayoutParams();
			lp.weight = 0;
			lp.width = getScreenWidth() / mTabVisibleCout;
			view.setLayoutParams(lp);
		}
		setItemClickEvent();
	}

	/**
	 * 获得屏幕的宽度
	 * 
	 * @return
	 */
	public int getScreenWidth() {
		WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		return outMetrics.widthPixels;
	}

	/**
	 * 是否显示下划线
	 * @param triangle
     */
	public void setTriangle(boolean triangle){
		this.triangle = triangle;
	}

	/**
	 * 初始化下划线
	 */
	private void initTriangle() {

		mTriangleHeight = mTriangleWidth / 2;
		mPath = new Path();
		mPath.moveTo(-100, 0);
		mPath.lineTo(130, 0);
		mPath.lineTo(130, -10);
		mPath.lineTo(-100, -10);
		// mPath.lineTo(mTriangleWidth, 0);
		// System.out.println("width:" + mTriangleWidth);
		// mPath.lineTo(mTriangleWidth / 2, -mTriangleHeight + 2);
		// mPath.lineTo(mTriangleWidth , -mTriangleHeight );
		// System.out.println("coor:" + mTriangleWidth / 2 + "," +
		// -mTriangleHeight + 2);
		mPath.close();
	}

	/**
	 * 指示器跟着手指进行滚动
	 * @param position
	 * @param offset
	 */
	public void scroll(int position, float offset) {
		int tabWidth = getWidth() / mTabVisibleCout;
		mTranslationX = (int) (tabWidth * (offset + position));

		// 容器移动，在tab处于移动至最后一个时
		if (position >= (mTabVisibleCout - 2) && offset > 0 && getChildCount() > mTabVisibleCout) {
			if (mTabVisibleCout != 1) {
				this.scrollTo((position - (mTabVisibleCout - 2)) * tabWidth + (int) (tabWidth * offset), 0);
			} else {
				this.scrollTo(position * tabWidth + (int) (tabWidth * offset), 0);
			}
		}
		invalidate();
	}

	/**
	 * 设置标题内容
	 * 
	 * @param titles
	 */
	public void setTabItemTitles(List<String> titles) {

		if (titles != null && titles.size() > 0) {
			this.removeAllViews();
			mTitles = titles;
			for (String title : mTitles) {
				addView(generateTextView(title));
			}
			setItemClickEvent();
		}
	}

	private  int COLOR_TEXT_NOMAL = 0x77000000;// 0x77FFFFFF
	private  int COLOR_TEXT_HIGHLIGHT = 0xFF000000;// 0xFFFFFFFF

	/**
	 * 设置可见tab数量
	 * @param count
	 */
	public void setVisibaleTabCount(int count) {
		mTabVisibleCout = count;
	}

    public void setTextColor(int color){
        COLOR_TEXT_HIGHLIGHT = color;
    }
	/**
	 * 根据title创建tab
	 * @param title
	 * @return
	 */
	private View generateTextView(String title) {

		TextView tv = new TextView(getContext());
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		lp.width = getScreenWidth() / mTabVisibleCout;
		tv.setText(title);
		tv.setGravity((Gravity.CENTER));
        tv.setPadding(5,5,5,5);
		tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
		/********************************************/
		tv.setTextColor(COLOR_TEXT_NOMAL);
		tv.setLayoutParams(lp);

		return tv;
	}

	ViewPager mViewPager;

	public interface PageOnchangeListen {

		void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);

		void onPageSelected(int position);

		void onPageScrollStateChanged(int state);
	}

	public PageOnchangeListen mListener;

	public void setOnPageChangeListener(PageOnchangeListen listener) {

		this.mListener = listener;
	}

	/**
	 * 设置关联的ViewPager
	 * 
	 * @param viewPager
	 * @param pos
	 */
	public void setViewPager(ViewPager viewPager, int pos) {

		mViewPager = viewPager;

		mViewPager.addOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {

				if (mListener != null) {
					mListener.onPageSelected(position);
				}

				highLightTextView(position);

			}

			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				// tabWidth*positopnOffset+position*tabWidth
				scroll(position, positionOffset);
				if (mListener != null) {
					mListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
				}

			}

			@Override
			public void onPageScrollStateChanged(int position) {

				if (mListener != null) {
					mListener.onPageScrollStateChanged(position);
				}

			}
		});

		mViewPager.setCurrentItem(pos);

		highLightTextView(pos);
	}

	/**
	 * 重置颜色
	 */
	private void resetTextColor() {
		for (int i = 0; i < getChildCount(); i++) {
			View view = getChildAt(i);
			if (view instanceof TextView) {
				((TextView) view).setTextColor(COLOR_TEXT_NOMAL);
			}
		}
	}

	/**
	 * 高亮某一文本
	 * 
	 * @param pos
	 */
	private void highLightTextView(int pos) {
		resetTextColor();
		View view = getChildAt(pos);
		if (view instanceof TextView) {
			((TextView) view).setTextColor(COLOR_TEXT_HIGHLIGHT);
		}
	}

	/**
	 * 设置tab的点击事件
	 */
	private void setItemClickEvent() {

		int cCount = getChildCount();

		for (int i = 0; i < cCount; i++) {

			final int j = i;

			View view = getChildAt(i);

			view.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					mViewPager.setCurrentItem(j);

				}
			});
		}
	}
}
