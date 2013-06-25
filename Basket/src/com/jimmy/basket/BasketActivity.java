package com.jimmy.basket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.SlidingDrawer.OnDrawerOpenListener;
import android.widget.SlidingDrawer.OnDrawerScrollListener;
import android.widget.TextView;

public class BasketActivity extends Activity implements OnDrawerOpenListener, OnDrawerCloseListener {

	private SlidingDrawer basketSlidingDrawer = null;
	private ImageView basketHandle = null;
	private RelativeLayout basketContent = null;
	ListView basketlist = null;
	private LayoutInflater mInflater = null;
	
	private List<Map<String, Object>> basketData = new ArrayList<Map<String, Object>>();
	
	BasketAdapter adapter;
	
	//business info
	ImageView logo = null;
	TextView business = null;
	TextView phone = null;
	
	BaseApplication baseApplication = null;
	
	public void initBasketActivity() {
		basketSlidingDrawer = (SlidingDrawer) findViewById(R.id.sd_basket_slidingDrawer);
		basketHandle = (ImageView) findViewById(R.id.iv_basket_basketHandle);
		basketContent = (RelativeLayout) findViewById(R.id.rl_basket_basketContent);
		logo = (ImageView) findViewById(R.id.iv_basket_logo);
		business = (TextView) findViewById(R.id.tv_basket_business);
		phone = (TextView) findViewById(R.id.tv_basket_phone);
		basketSlidingDrawer.setOnDrawerOpenListener(this);
		basketSlidingDrawer.setOnDrawerCloseListener(this);
		baseApplication = (BaseApplication) getApplication();
		mInflater = LayoutInflater.from(this);
		
		basketlist = (ListView) findViewById(R.id.lv_basket_list);
		
        adapter =  new BasketAdapter();
        basketlist.setAdapter(adapter);
        
        initBusinessInfo();
	}

	public void initBasketData(List<Map<String, Object>> ItemsDate,List<Map<String, Object>> miscellaneousDate,
			List<Map<String, Object>> discountItemDate,Map<String, Object> discountTransationDate){
		basketData.clear();
		double countPrice = 0;
		//add item
		if(null != ItemsDate && ItemsDate.size()>0){
			for(Map<String, Object> d : ItemsDate){
				String name = (String) d.get("name");
				int count = (Integer) d.get("count");
				double price = (Double) d.get("price");
				countPrice += price;
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("name", name);
				map.put("count", count);
				map.put("price", "$"+price);
				map.put("type", BasketEntity.DATA_TYPE_ITEMS);
				basketData.add(map);
			}
		}
		//add miscellaneousPrice
		if(null != miscellaneousDate && miscellaneousDate.size()>0){
			for(Map<String, Object> d : miscellaneousDate){
				String name = (String) d.get("name");
				double price = (Double) d.get("price");
				Map<String, Object> map = new HashMap<String, Object>();
				countPrice += price;
				map.put("name", name);
				map.put("price", "$"+price);
				map.put("type", BasketEntity.DATA_TYPE_MISCELLANEOUS);
				basketData.add(map);
			}
		}
		
		// add discount an items
		if(null != discountItemDate && discountItemDate.size()>0){
			for(Map<String, Object> d : discountItemDate){
				String off = (String) d.get("off");
				String name = (String) d.get("name");
				int count = (Integer) d.get("count");
				double price = (Double) d.get("price");
				countPrice += price;
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("off",off);
				map.put("name", name);
				map.put("count", count);
				map.put("price", "$"+price);
				map.put("type", BasketEntity.DATA_TYPE_DISCOUNT_ITEM);
				basketData.add(map);
			}
		}
		//add discount a transation
		if(null != discountTransationDate && !discountTransationDate.isEmpty()){
			String off = (String) discountTransationDate.get("off");
			double price = (Double) discountTransationDate.get("price");
			countPrice += price;
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("off",off);
			map.put("price", "$"+price);
			map.put("type", BasketEntity.DATA_TYPE_DISCOUNT_ITEM);
			basketData.add(map);
		}
		//add tax
		String taxFlg = "yes";
		if("yes".equals(taxFlg)){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", "50% Tax");
			map.put("price", "$"+getTax(countPrice));
			map.put("type", BasketEntity.DATA_TYPE_TAX);
			basketData.add(map);
		}
	}

	private double getTax(double countPrice){
		return countPrice*0.5;
	}
	
	private void initBusinessInfo(){
//		logo .setImageResource(arg0);
		business.setText("黑商");
		phone.setText("999-999-9999");
	}
	
	@Override
	public void onDrawerOpened() {
		basketHandle.setImageResource(R.drawable.basket_up);
	}
	
	@Override
	public void onDrawerClosed() {
		basketHandle.setImageResource(R.drawable.basket_down);
	}
	
	public void flushBasketData() {
		BaseApplication baseApplication =(BaseApplication)getApplication();
		initBasketData(baseApplication.basketEntity.getItemsDate(), 
				baseApplication.basketEntity.getMiscellaneousDate(),
				baseApplication.basketEntity.getDiscountItemDate(),
				baseApplication.basketEntity.getDiscountTransationDate());
		adapter.notifyDataSetChanged();
	}

	class BasketAdapter extends BaseAdapter{
		  
		@Override
		public int getCount() {
			return basketData.size();
		}

		@Override
		public Object getItem(int position) {
			return basketData.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = mInflater.inflate(R.layout.basket_list, null);
				holder.off = (TextView) convertView.findViewById(R.id.tv_basketItem_off);
				holder.green = (ImageView) convertView.findViewById(R.id.iv_basketItem_green);
				holder.count = (TextView) convertView.findViewById(R.id.tv_basketItem_count);
				holder.name = (TextView) convertView.findViewById(R.id.tv_basketItem_name);
				holder.price = (TextView) convertView.findViewById(R.id.tv_basketItem_price);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			int type = (Integer) basketData.get(position).get("type");
			switch (type) {
			case BasketEntity.DATA_TYPE_ITEMS:
				holder.count.setText(""+basketData.get(position).get("count"));
				holder.name.setText(""+basketData.get(position).get("name"));
				holder.price.setText(""+basketData.get(position).get("price"));
				break;
			case BasketEntity.DATA_TYPE_MISCELLANEOUS:
				holder.name.setText(""+basketData.get(position).get("name"));
				holder.price.setText(""+basketData.get(position).get("price"));
				holder.green.setVisibility(View.GONE);
				holder.count.setVisibility(View.GONE);
				holder.name.setTextAppearance(BasketActivity.this, R.style.Helvetica_grey2_bold_14);
				break;
			case BasketEntity.DATA_TYPE_DISCOUNT_ITEM:
			    holder.off.setVisibility(View.VISIBLE);
			    holder.off.setText(""+basketData.get(position).get("off"));
				holder.count.setText(""+basketData.get(position).get("count"));
				holder.name.setText(""+basketData.get(position).get("name"));
				holder.price.setText(""+basketData.get(position).get("price"));
				holder.name.setTextAppearance(BasketActivity.this, R.style.Helvetica_grey2_bold_14);
				break;
			case BasketEntity.DATA_TYPE_DISCOUNT_TRANSATION:
			    holder.off.setVisibility(View.VISIBLE);
			    holder.off.setText(""+basketData.get(position).get("off"));
				holder.price.setText(""+basketData.get(position).get("price"));
				holder.name.setTextAppearance(BasketActivity.this, R.style.Helvetica_grey2_bold_14);
				break;
			case BasketEntity.DATA_TYPE_TAX:
				holder.name.setText(""+basketData.get(position).get("name"));
				holder.price.setText(""+basketData.get(position).get("price"));
				holder.name.setTextAppearance(BasketActivity.this, R.style.Helvetica_grey2_bold_14);
				break;
			}
			
			return convertView;
		}
		
	}
	
	static class ViewHolder {
		TextView off;
		ImageView green;
		TextView count;
		TextView name;
		TextView price;
	}


 
}
