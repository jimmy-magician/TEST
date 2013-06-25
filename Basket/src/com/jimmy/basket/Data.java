package com.jimmy.basket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Data {
	
	private Map<String, Object> setData(String title,String describe,int price) {
		Map<String,Object> map = new HashMap<String, Object>();
        map.put("title", title);
        map.put("describe", describe);
        map.put("price", price);
		return map;
	}
	
	List<Map<String, Object>> allData() {
		List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();
        data.add(setData("zhang san","我是描述1",11));
        data.add(setData("abs cc","我是描述2",22));
        data.add(setData("werfe fd","我是描述3",33));
        data.add(setData("sdfsdf","我是描述4",44));
        data.add(setData("ghjhg","我是描述5",55));
        data.add(setData("wrttyu","我是描述6",66));
        data.add(setData("nmj","我是描述7",77));
        data.add(setData("yyh","我是描述8",88));
        data.add(setData("uhg","我是描述9",99));
        data.add(setData("vb","我是描述23",23));
        data.add(setData("effe","我是描述24",24));
        data.add(setData("dvcx","我是描述25",25));
        data.add(setData("vd","我是描述26",26));
        data.add(setData("df","我是描述27",27));
        data.add(setData("hg","我是描述28",28));
        data.add(setData("wtyu","我是描述29",29));
        data.add(setData("nj","我是描述35",35));
        data.add(setData("yh","我是描述36",36));
        data.add(setData("ug","我是描述37",37));
        data.add(setData("b","我是描述38",38));
		return data;
	}
	
	
	private Map<String, Object> setBasketData(String name,int count ,double price) {
		Map<String,Object> map = new HashMap<String, Object>();
        map.put("name", name);
        map.put("count", count);
        map.put("price", price);
		return map;
	}
	
	List<Map<String, Object>> allBasketData() {
		List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();
        data.add(setBasketData("zhang san",1,11));
        data.add(setBasketData("abs cc",2,22));
        data.add(setBasketData("werfe fd",3,33));
        data.add(setBasketData("sdfsdf",4,44));
        data.add(setBasketData("ghjhg",5,55));
        data.add(setBasketData("wrttyu",6,66));
        data.add(setBasketData("nmj",7,77));
        data.add(setBasketData("yyh",8,88));
        data.add(setBasketData("uhg",9,99));
		return data;
	}

}
