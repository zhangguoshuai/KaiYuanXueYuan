package com.kaiyuanxueyuan.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.dmplayer.childfragment.MyMusicFragment;
import com.kaiyuanxueyuan.R;
import com.kaiyuanxueyuan.fragment.liveFragment.LiveFragment;
import com.kaiyuanxueyuan.fragment.mainFragment.NewsFragment;
import com.kaiyuanxueyuan.fragment.mainFragment.PersonalFragment;
import com.kaiyuanxueyuan.fragment.mainFragment.StudyFragment;
import com.kaiyuanxueyuan.ui.QRCode.CaptureActivity;
import com.kaiyuanxueyuan.utils.ShowInfo;
import com.kaiyuanxueyuan.view.CustomViewPager;
import com.rey.material.app.ThemeManager;
import com.rey.material.app.ToolbarManager;
import com.rey.material.drawable.ThemeDrawable;
import com.rey.material.util.ThemeUtil;
import com.rey.material.util.ViewUtil;
import com.rey.material.widget.Button;
import com.rey.material.widget.SnackBar;
import com.rey.material.widget.TabIndicatorView;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * 最重要的activity，加载五个模块
 */
public class MainActivity extends AppCompatActivity implements ToolbarManager.OnToolbarGroupChangedListener {

    private static final int REQUEST_CODE_SCAN = 0x0000;
    private static final String DECODED_CONTENT_KEY = "codedContent";
    private static final String DECODED_BITMAP_KEY = "codedBitmap";
	private DrawerLayout drawerLayout;
	private FrameLayout frameLayout;
	private ListView listView;
	private CustomViewPager customViewPager;
	private TabIndicatorView tabIndicatorView;
	private DrawerAdapter mDrawerAdapter;
	private PagerAdapter mPagerAdapter;
	private Toolbar mToolbar;
    private ToolbarManager mToolbarManager;
    private SnackBar mSnackBar;

	private Tab[] mItems = new Tab[]{Tab.NEWS, Tab.LIVE, Tab.MUSIC, Tab.UNIVERSITY,Tab.MY};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main_activity);

        drawerLayout = (DrawerLayout)findViewById(R.id.main_drawerLayout);
        frameLayout = (FrameLayout)findViewById(R.id.main_frameLayout);
        listView = (ListView)findViewById(R.id.main_listView);
		mToolbar = (Toolbar)findViewById(R.id.main_toolbar);
        customViewPager = (CustomViewPager)findViewById(R.id.main_customViewPager);
        tabIndicatorView = (TabIndicatorView)findViewById(R.id.main_tabIndicatorView);
        mSnackBar = (SnackBar)findViewById(R.id.main_SnackBar);



        setSupportActionBar(mToolbar);

        mToolbarManager = new ToolbarManager(getDelegate(), mToolbar, R.id.tb_group_main, R.style.ToolbarRippleStyle, R.anim.abc_fade_in, R.anim.abc_fade_out);
        mToolbarManager.setNavigationManager(new ToolbarManager.ThemableNavigationManager(R.array.navigation_drawer, getSupportFragmentManager(), mToolbar, drawerLayout) {
            @Override
            public void onNavigationClick() {
//                if (mToolbarManager.getCurrentGroup() != R.id.tb_group_main)
//                    mToolbarManager.setCurrentGroup(R.id.tb_group_main);
//                else
//                    drawerLayout.openDrawer(GravityCompat.START);

                if(mToolbarManager.isNavigationVisisble()){

                    if(drawerLayout.isDrawerOpen(frameLayout)){
                        drawerLayout.closeDrawer(frameLayout);
                    }else{
                        drawerLayout.openDrawer(frameLayout);
                    }

                   // mToolbarManager.setNavigationVisisble(false, true);
                }else{

                   // mToolbarManager.setNavigationVisisble(true, true);
                }
            }

            @Override
            public boolean isBackState() {
                return super.isBackState() || mToolbarManager.getCurrentGroup() != R.id.tb_group_main;
            }

            @Override
            protected boolean shouldSyncDrawerSlidingProgress() {
                return super.shouldSyncDrawerSlidingProgress() && mToolbarManager.getCurrentGroup() == R.id.tb_group_main;
            }

        });
        mToolbarManager.registerOnToolbarGroupChangedListener(this);
		
		mDrawerAdapter = new DrawerAdapter(this);
        listView.setAdapter(mDrawerAdapter);
		
		mPagerAdapter = new PagerAdapter(getSupportFragmentManager(), mItems);
        customViewPager.setAdapter(mPagerAdapter);
        tabIndicatorView.setTabIndicatorFactory(new TabIndicatorView.ViewPagerIndicatorFactory(customViewPager));
        tabIndicatorView.setDrawLine(false);
        customViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                mDrawerAdapter.setSelected(mItems[position]);
                mSnackBar.dismiss();
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }

        });

        mDrawerAdapter.setSelected(Tab.NEWS);
        customViewPager.setCurrentItem(0);

        ViewUtil.setBackground(getWindow().getDecorView(), new ThemeDrawable(R.array.bg_window));
        ViewUtil.setBackground(mToolbar, new ThemeDrawable(R.array.bg_toolbar));
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
        mToolbarManager.createMenu(R.menu.menu_main);
		return true;
	}

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        mToolbarManager.onPrepareMenu();
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.tb_switch:
                mToolbarManager.setCurrentGroup(R.id.tb_group_contextual);
                break;
            case R.id.tb_done:
            case R.id.tb_done_all:
                mToolbarManager.setCurrentGroup(R.id.tb_group_main);
                break;
            case R.id.tb_theme:
                Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SCAN);
//                if(mToolbarManager.isNavigationVisisble())
//                    mToolbarManager.setNavigationVisisble(false, true);
//                else
//                    mToolbarManager.setNavigationVisisble(true, true);

//                int theme = (ThemeManager.getInstance().getCurrentTheme() + 1) % ThemeManager.getInstance().getThemeCount();
//                ThemeManager.getInstance().setCurrentTheme(theme);
//                Toast.makeText(this, "Current theme: " + theme, Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    @Override
    public void onToolbarGroupChanged(int oldGroupId, int groupId) {
        mToolbarManager.notifyNavigationStateChanged();
    }

    public SnackBar getSnackBar(){
        return mSnackBar;
    }

    public enum Tab {
        UNIVERSITY ("学府"),
        MUSIC ("音乐"),
        LIVE ("直播"),
        NEWS ("新闻"),
        MY ("我的");
	    private final String name;       

	    Tab(String s) {
	        name = s;
	    }

	    public boolean equalsName(String otherName){
	        return (otherName != null) && name.equals(otherName);
	    }

	    public String toString(){
	       return name;
	    }

	}
	
	class DrawerAdapter extends BaseAdapter implements View.OnClickListener, ThemeManager.OnThemeChangedListener {

		private Tab mSelectedTab;
		private int mTextColorLight;
        private int mTextColorDark;
        private int mBackgroundColorLight;
        private int mBackgroundColorDark;

        public DrawerAdapter(Context context){
            mTextColorLight = context.getResources().getColor(R.color.abc_primary_text_material_light);
            mTextColorDark = context.getResources().getColor(R.color.abc_primary_text_material_dark);
            mBackgroundColorLight = ThemeUtil.colorPrimary(context, 0);
            mBackgroundColorDark = ThemeUtil.colorAccent(context, 0);

            ThemeManager.getInstance().registerOnThemeChangedListener(this);
        }

        @Override
        public void onThemeChanged(ThemeManager.OnThemeChangedEvent event) {
            notifyDataSetInvalidated();
        }

        public void setSelected(Tab tab){
			if(tab != mSelectedTab){
				mSelectedTab = tab;
				notifyDataSetInvalidated();
			}
		}
		
		public Tab getSelectedTab(){
			return mSelectedTab;
		}
		
		@Override
		public int getCount() {
			return mItems.length;
		}

		@Override
		public Object getItem(int position) {
			return mItems[position];
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView;
			if(v == null) {
                v = LayoutInflater.from(MainActivity.this).inflate(R.layout.main_drawer, null);
                v.setOnClickListener(this);
            }

            v.setTag(position);
			Tab tab = (Tab)getItem(position);
			((TextView)v).setText(tab.toString());
			
			if(tab == mSelectedTab) {
                v.setBackgroundColor(ThemeManager.getInstance().getCurrentTheme() == 0 ? mBackgroundColorLight : mBackgroundColorDark);
                ((TextView)v).setTextColor(0xFFFFFFFF);
            }
			else {
                v.setBackgroundResource(0);
                ((TextView)v).setTextColor(ThemeManager.getInstance().getCurrentTheme() == 0 ? mTextColorLight : mTextColorDark);
            }
			
			return v;
		}

        @Override
        public void onClick(View v) {
            int position = (Integer)v.getTag();
            customViewPager.setCurrentItem(position);
            drawerLayout.closeDrawer(frameLayout);
        }
    }
	
	private static class PagerAdapter extends FragmentStatePagerAdapter {
		
		Fragment[] mFragments;
		Tab[] mTabs; 
				
		private static final Field sActiveField;
		
		static {
			Field f = null;
			try {
				Class<?> c = Class.forName("android.support.v4.app.FragmentManagerImpl");
				f = c.getDeclaredField("mActive");
				f.setAccessible(true);   
			} catch (Exception e) {}
			
			sActiveField = f;
		}
		
        public PagerAdapter(FragmentManager fm, Tab[] tabs) {
            super(fm);    
            mTabs = tabs;
            mFragments = new Fragment[mTabs.length];
       
            
            //dirty way to get reference of cached fragment
            try{
    			ArrayList<Fragment> mActive = (ArrayList<Fragment>)sActiveField.get(fm);
    			if(mActive != null){
    				for(Fragment fragment : mActive){
    					if(fragment instanceof NewsFragment)
    						setFragment(Tab.NEWS, fragment);
    					else if(fragment instanceof LiveFragment)
    						setFragment(Tab.LIVE, fragment);
                        else if(fragment instanceof MyMusicFragment)
                            setFragment(Tab.MUSIC, fragment);
    					else if(fragment instanceof StudyFragment)
    						setFragment(Tab.UNIVERSITY, fragment);
                        else if(fragment instanceof PersonalFragment)
                            setFragment(Tab.MY, fragment);
    				}
    			}
    		}
    		catch(Exception e){}
        }
        
        private void setFragment(Tab tab, Fragment f){
        	for(int i = 0; i < mTabs.length; i++)
        		if(mTabs[i] == tab){
        			mFragments[i] = f;
        			break;
        		}
        }
        
		@Override
        public Fragment getItem(int position) {
			if(mFragments[position] == null){
	        	switch (mTabs[position]) {
                    case NEWS:
						mFragments[position] = NewsFragment.newInstance();
						break;
                    case LIVE:
						mFragments[position] = LiveFragment.newInstance();
						break;
                    case MUSIC:
                        mFragments[position] = MyMusicFragment.newInstance();
                        break;
                    case UNIVERSITY:
						mFragments[position] = StudyFragment.newInstance();
						break;
                    case MY:
                        mFragments[position] = PersonalFragment.newInstance();
                        break;
				}
			}
						
			return mFragments[position];		
        }
				
		@Override
		public CharSequence getPageTitle(int position) {
			return mTabs[position].toString().toUpperCase();
		}

		@Override
        public int getCount() {
            return mFragments.length;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {

                String content = data.getStringExtra(DECODED_CONTENT_KEY);
                Bitmap bitmap = data.getParcelableExtra(DECODED_BITMAP_KEY);
                ShowInfo.Toast(this,content);
                // qrCodeImage.setImageBitmap(bitmap);
            }
        }
    }

}
