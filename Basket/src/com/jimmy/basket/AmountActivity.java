package com.jimmy.basket;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class AmountActivity extends BasketActivity {
	
	 private ViewPager viewPager;// 页卡内容 
	 private List<View> listViews; // Tab页面列表 
	 View numberView ;
	 View itemlistView;
	 
	EditText search = null;
	ListView listView = null;
	ItemsAdapter adapter;
	
	Button flipperPoint1;
	Button flipperPoint2;
	  public void onCreate(Bundle savedInstanceState) {
		  
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.amount);
	        LayoutInflater mInflater = getLayoutInflater(); 
	        numberView = mInflater.inflate(R.layout.number, null);
	        itemlistView = mInflater.inflate(R.layout.itemlist, null); 
	        flipperPoint1 =(Button) findViewById(R.id.flipperPoint1); 
	        flipperPoint2 = (Button) findViewById(R.id.flipperPoint2);  
	        
	        initBasketActivity();
	        InitViewPager();
	        
	        listView = (ListView) itemlistView.findViewById(R.id.list);
	        search = (EditText) itemlistView.findViewById(R.id.search);
	        search.addTextChangedListener(textWatcher);
	        List<Map<String, Object>> data = new Data().allData();
	        adapter =  new ItemsAdapter(this, data, R.layout.items,
	        new String[] {"title","describe","price"}, new int[]{R.id.title,R.id.describe,R.id.price});
	        listView.setAdapter(adapter);
	        
	        //next
	        Button next = (Button) findViewById(R.id.next);
	        next.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					startActivity(new Intent(AmountActivity.this, SecondActivity.class));
					
				}
			});
	        
	        //add basket data
	        Button ItemsDate = (Button) findViewById(R.id.add_ItemsDate);
	        Button miscellaneousDate = (Button) findViewById(R.id.add_miscellaneousDate);
	        Button discountItemDate = (Button) findViewById(R.id.add_discountItemDate);
	        Button discountTransationDate = (Button) findViewById(R.id.add_discountTransationDate);
	        ItemsDate.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					baseApplication.basketEntity.addItemsDate("苹果", 3, 30);
					baseApplication.basketEntity.addItemsDate("橘子", 1, 10);
					baseApplication.basketEntity.addItemsDate("炸弹", 100, 1);
					flushBasketData();
				}
			});
	        miscellaneousDate.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					baseApplication.basketEntity.addMiscellaneousDate(null, 100);
					flushBasketData();
				}
			});
	        discountItemDate.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					baseApplication.basketEntity.addDiscountItemDate("$5.5", "苹果", 2, 5.5);
					baseApplication.basketEntity.addDiscountItemDate("3%", "炸弹", 50, 70);
					flushBasketData();
				}
			});
	        discountTransationDate.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					baseApplication.basketEntity.addDiscountTransationDate("$100", 100);
					baseApplication.basketEntity.addDiscountTransationDate("10%", 100);
					flushBasketData();
				}
			});
	  }
	  
	    private TextWatcher textWatcher = new TextWatcher() {
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				Log.d("LH", "onTextChanged");
			}
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				Log.d("LH", "beforeTextChanged");
			}
			
			public void afterTextChanged(Editable s) {
				Log.d("LH", "afterTextChanged"+"-"+s);
				adapter.getFilter().filter(s.toString());
			}
		};
	  
	    /**
	     * 初始化ViewPager
	     */ 
	    private void InitViewPager() { 
	    	viewPager = (ViewPager) findViewById(R.id.viewPager); 
	        listViews = new ArrayList<View>(); 
	        listViews.add(numberView); 
	        listViews.add(itemlistView); 
	        viewPager.setAdapter(new MyPagerAdapter(listViews)); 
	        viewPager.setCurrentItem(0); 
	        viewPager.setOnPageChangeListener(new MyOnPageChangeListener()); 
	    } 

	    /**
	     * ViewPager适配器
	     */ 
	    public class MyPagerAdapter extends PagerAdapter { 
	        public List<View> mListViews; 
	 
	        public MyPagerAdapter(List<View> mListViews) { 
	            this.mListViews = mListViews; 
	        } 
	 
	        @Override 
	        public void destroyItem(View arg0, int arg1, Object arg2) { 
	            ((ViewPager) arg0).removeView(mListViews.get(arg1)); 
	        } 
	 
	        @Override 
	        public void finishUpdate(View arg0) { 
	        } 
	 
	        @Override 
	        public int getCount() { 
	            return mListViews.size(); 
	        } 
	 
	        @Override 
	        public Object instantiateItem(View arg0, int arg1) { 
	            ((ViewPager) arg0).addView(mListViews.get(arg1), 0); 
	            return mListViews.get(arg1); 
	        } 
	 
	        @Override 
	        public boolean isViewFromObject(View arg0, Object arg1) { 
	            return arg0 == (arg1); 
	        } 
	 
	        @Override 
	        public void restoreState(Parcelable arg0, ClassLoader arg1) { 
	        } 
	 
	        @Override 
	        public Parcelable saveState() { 
	            return null; 
	        } 
	 
	        @Override 
	        public void startUpdate(View arg0) { 
	        } 
	    } 
	    
	    /**
	     * 页卡切换监听
	     */ 
	    public class MyOnPageChangeListener implements OnPageChangeListener { 
	        @Override 
	        public void onPageSelected(int arg0) { 
	        	 switch (arg0) { 
	             case 0: 
	            	 flipperPoint1.setBackgroundResource(R.drawable.flipper_btn1);
	            	 flipperPoint2.setBackgroundResource(R.drawable.flipper_btn2);
	                 break; 
	             case 1: 
	            	 flipperPoint1.setBackgroundResource(R.drawable.flipper_btn2);
	            	 flipperPoint2.setBackgroundResource(R.drawable.flipper_btn1);
	                 break; 
	        	 }
	        } 
	 
	        @Override 
	        public void onPageScrolled(int arg0, float arg1, int arg2) { 
	        } 
	 
	        @Override 
	        public void onPageScrollStateChanged(int arg0) { 
	        } 
	    } 
}
