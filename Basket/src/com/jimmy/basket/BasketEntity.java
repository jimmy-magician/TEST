package com.jimmy.basket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasketEntity {
	public static final int DATA_TYPE_ITEMS = 0;
	public static final int DATA_TYPE_MISCELLANEOUS = 1;
	public static final int DATA_TYPE_DISCOUNT_ITEM = 2;
	public static final int DATA_TYPE_DISCOUNT_TRANSATION = 3;
	public static final int DATA_TYPE_TAX = 4;
	
	List<Map<String, Object>> ItemsDate = new ArrayList<Map<String,Object>>();
	List<Map<String, Object>> miscellaneousDate = new ArrayList<Map<String,Object>>();
	List<Map<String, Object>> discountItemDate  = new ArrayList<Map<String,Object>>();
	Map<String, Object> discountTransationDate  = new HashMap<String, Object>();


	public void addItemsDate(String itemName,int count,double price) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", itemName);
		map.put("count", count);
		map.put("price", price);
		map.put("type", DATA_TYPE_ITEMS);
		ItemsDate.add(map);
	}
	
	public List<Map<String, Object>> getItemsDate() {
		return ItemsDate;
	}
	
	public void chearItemsDate(){
		ItemsDate.clear();
	}
	
	/**
	 * if no miscellaneousName ,set null
	 */
	public void addMiscellaneousDate(String miscellaneousName,double miscellaneousPrice) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(null != miscellaneousName && !"".equals(miscellaneousName.trim())){
			map.put("name", miscellaneousName);
		}else{
			map.put("name", "MiscellaneousPrice");
		}
		map.put("price", miscellaneousPrice);
		map.put("type", DATA_TYPE_MISCELLANEOUS);
		miscellaneousDate.add(map);
	}
	
	public List<Map<String, Object>> getMiscellaneousDate() {
		return miscellaneousDate;
	}
	
	public void clearMiscellaneousDate(){
		miscellaneousDate.clear();
	}
	
	/**
	 * @param off  like $5.32 or 2%
	 * @param itemsName
	 * @param count
	 * @param price
	 */
	public void addDiscountItemDate(String off,String itemsName,int count,double price) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("off",off);
		map.put("name", itemsName);
		map.put("count", count);
		map.put("price", price);
		map.put("type", DATA_TYPE_DISCOUNT_ITEM);
		discountItemDate.add(map);
	}
	
	public List<Map<String, Object>> getDiscountItemDate() {
		return discountItemDate;
	}
	
	public void clearDiscountItemDate(){
		discountItemDate.clear();
	}
	
	/**
	 * off  <like $5.32 or 2%>
	 */
	public void addDiscountTransationDate(String off,double price) {
		discountTransationDate.put("off",off);
		discountTransationDate.put("price", price);
		discountTransationDate.put("type", DATA_TYPE_DISCOUNT_ITEM);
	}
	
	public Map<String, Object> getDiscountTransationDate() {
		return discountTransationDate;
	}
	
	public void clearDiscountTransationDate(){
		discountTransationDate.clear();
	}
	
	public void chearTransation(){
		ItemsDate.clear();
		miscellaneousDate.clear();
		discountItemDate.clear();
		discountTransationDate.clear();

	}
	
	

}
