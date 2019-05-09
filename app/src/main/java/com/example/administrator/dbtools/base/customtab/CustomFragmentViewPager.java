package com.example.administrator.dbtools.base.customtab;

import android.content.Context;
import android.graphics.Color;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.administrator.dbtools.R;
import com.example.administrator.dbtools.base.BaseFragment;

import java.util.List;

/**
 * 自定义ViewPager父控件，增加Tab标签
 * 
 *  用法：不能直接引用，需继承该控件，实现initViewPagerFragments()方法
 * 
 */
public abstract class CustomFragmentViewPager extends FrameLayout {

//	private ImageView add_image_shadow;
	private LinearLayout llAddImage;
	private ViewPager mPager;// 页卡内容

	private LinearLayout newsTitle;

	/**
	 * PagerSlidingTabStrip的实例
	 */
	private CustomViewPagerTab tabs;
	/**
	 * 获取当前屏幕的密度
	 */
	private DisplayMetrics dm;
	private List<BaseFragment> baseFragments;
	private CustomViewPagerAdapter pageAdapter;

	/**
	 * 初始化左侧栏，设置默认样式，设置选中的监听动作
	 */
	public CustomFragmentViewPager(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		LayoutInflater.from(context).inflate(R.layout.viewpager_widget_toolbar, this);

		dm = getResources().getDisplayMetrics();
		tabs = (CustomViewPagerTab) findViewById(R.id.tabs);
		llAddImage=(LinearLayout)findViewById(R.id.ll_add_image);
		newsTitle= (LinearLayout) findViewById(R.id.news_ll_title);

		// 初始化子页面等相关内容
		initViewPager();
	}

	public CustomFragmentViewPager(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CustomFragmentViewPager(Context context) {
		this(context, null, 0);
	}

	public  void setOffscreenPageLimit(int count){
		mPager.setOffscreenPageLimit(count);
	}
	/**
	 * 初始化ViewPager
	 */
	private void initViewPager() {
		// 初始化子页面布局
		baseFragments = initViewPagerFragments();
		pageAdapter = new CustomViewPagerAdapter(
				initFragmentManager(), baseFragments);
		mPager = (ViewPager) findViewById(R.id.vPager);
		mPager.setAdapter(pageAdapter);
		mPager.setCurrentItem(0);
//		mPager.setOnPageChangeListener(new MyOnPageChangeListener());

		// 设置表头
		tabs.setViewPager(mPager);
		setTabsValue();
	}

	public void setOnPageChangeListener(OnPageChangeListener listener) {
		tabs.setOnPageChangeListener(listener);
	}

	/**
	 * page 当前页码
	 */
	public void setCurrentPage(int page) {
		try {
			mPager.setCurrentItem(page);
		} catch (Exception E) {
			mPager.setCurrentItem(0);
		}
	}
	
	/**
	 * 获取 当前页码
	 */
	public int getCurrentPage() {
			return mPager.getCurrentItem();
	}

	/*
	 * 需要初始化的Fragments,在继承的子控件中实现该方法：
	 */
	public abstract List<BaseFragment> initViewPagerFragments();

	protected abstract FragmentManager initFragmentManager();

	/**
	 * 对PagerSlidingTabStrip的各项属性进行赋值。
	 */
	private void setTabsValue() {
		// 设置Tab是自动填充满屏幕的
		tabs.setShouldExpand(true);
		// 设置Tab的分割线是透明的
		tabs.setDividerColor(Color.TRANSPARENT);
		// 设置Tab底部线的高度
		tabs.setUnderlineHeight((int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 1, dm));
		// 设置Tab Indicator的高度
		tabs.setIndicatorHeight((int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 2, dm));
		// 设置Tab标题文字的大小
		tabs.setTextSize((int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_SP, 16, dm));

		// 设置Tab Indicator的颜色
		tabs.setIndicatorColorResource(R.color.home_myapplys_indicatior_line);
		// 设置选中Tab文字的颜色 (这是我自定义的一个方法)
		tabs.setSelectedTextColorResource(R.color.home_myapplys_indicatior_line);
		// 取消点击Tab时的背景色
		tabs.setTabBackground(0);
		// 设置 Tab Icon图片大小
		tabs.setIcocLayoutHeight(25);
		tabs.setIcocLayoutWidth(25);
		tabs.setTextColorResource(R.color.home_myapplys_tab_text_color);
		// 设置下划线的颜色
		tabs.setUnderlineColorResource(R.color.home_myapplys_tab_under_line);
	}

	/**
	 * ViewPager适配器
	 */
	public class CustomViewPagerAdapter extends FragmentStatePagerAdapter {
		private List<BaseFragment> fragments = null;

		public CustomViewPagerAdapter(FragmentManager fm,
				List<BaseFragment> fragmentList) {
			super(fm);
			fragments = fragmentList;
		}

		@Override
		public BaseFragment getItem(int position) {
			return fragments.get(position);
		}

		@Override
		public int getCount() {
			if (fragments == null) {
				return 0;
			}
			return fragments.size();
		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public int getItemPosition(Object object) {
			return POSITION_NONE;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return fragments.get(position).getMyTitle();
		}
	}

	protected void update(List<BaseFragment> baseFragments){
		int newCurrentPage = getNewCurrentPage(baseFragments);
		this.baseFragments.clear();
		this.baseFragments.addAll(baseFragments);
		pageAdapter.notifyDataSetChanged();
		// 设置表头
		tabs.setViewPager(mPager);
		setCurrentPage(newCurrentPage);
		if (newCurrentPage == 0){
			tabs.setSelectedPosition(0);
			tabs.updateTabStyles();
		}
	}

	public BaseFragment getCurrentFragment(){

		return baseFragments.get(getCurrentPage());
	}

	private int getNewCurrentPage(List<BaseFragment> baseFragments){
		int newCurrent = 0;
		String currentTitle = this.baseFragments.get(getCurrentPage()).getMyTitle();
		for (int i = 0,size = baseFragments.size();i<size;i++){
			if(baseFragments.get(i).getMyTitle().equals(currentTitle)){
				newCurrent = i;
				break;
			}
		}
		return newCurrent;
	}
//  public void setLinkHide(){
//	  view_link.setVisibility(View.GONE);
//  }
	public void setUnderlineColor(int color){
		tabs.setUnderlineColor(color);
	}
	public void setTextColor(int textColor,int selectTextColor){
		// 设置Tab Indicator的颜色
		tabs.setIndicatorColorResource(R.color.home_myapplys_tab_under_line2);
		// 设置选中Tab文字的颜色 (这是我自定义的一个方法)
		tabs.setSelectedTextColorResource(selectTextColor);
		tabs.setTextColorResource(textColor);
		// 设置下划线的颜色
		tabs.setUnderlineColorResource(R.color.home_myapplys_tab_under_line);
		tabs.setIndicatorHeight((int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 3, dm));
	}
	public LinearLayout getllAddImage() {
		return llAddImage;
	}

	public void setTitleBackground(int resId){
		newsTitle.setBackgroundResource(resId);
	}
}
