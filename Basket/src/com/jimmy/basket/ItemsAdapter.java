package com.jimmy.basket;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class ItemsAdapter extends SimpleAdapter {
	
	private LayoutInflater mInflater = null;
	List<? extends Map<String, ?>> data;
	int resource;
	private String[] from;
	private int[] to;
	private Context context;
	
	ArrayFilter  filter;
	
	public ItemsAdapter(Context context, List<? extends Map<String, ?>> data,
			int resource, String[] from, int[] to) {
		super(context, data, resource, from, to);
		mInflater = LayoutInflater.from(context);
		this.data = data;
		this.resource = resource;
		this.from = from;
		this.to = to;
		this.context = context;
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return  data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
//		Log.d("LH", ""+super.getCount()+getCount()+"-"+position);
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(resource, null);
			holder.title = (TextView) convertView.findViewById(R.id.title);
			holder.describe = (TextView) convertView.findViewById(R.id.describe);
			holder.price = (TextView) convertView.findViewById(R.id.price);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.title.setText(""+data.get(position).get("title"));
		holder.describe.setText(""+data.get(position).get("describe"));
		holder.price.setText(""+data.get(position).get("price"));
		
		return convertView;
	}
 
	static class ViewHolder {
		public TextView title;
		public TextView describe;
		public TextView price;
	}

	public Filter getFilter() {
		if (filter == null) {
			filter = new ArrayFilter();
		}
			return filter;
	}
	
	
	class ArrayFilter extends Filter {

		protected FilterResults performFiltering(CharSequence constraint) {
			FilterResults results = new FilterResults();
			List<Map<String,Object>> orign=new Data().allData();
			
			if(null ==constraint || constraint.length() ==0){
				results.values = orign;
				results.count = orign.size();
			}else{
				List<Map<String,Object>> newArray = new ArrayList<Map<String,Object>>();
				int count = orign.size();
				for(int i = 0;i< count;i++){
					String title = (String) orign.get(i).get("title");
					if(title.indexOf(""+constraint)!=-1){
						newArray.add((Map<String, Object>) orign.get(i));
					}
				}
				results.values = newArray;
				results.count = newArray.size();
			}
			return results;
		}

		protected void publishResults(CharSequence constraint,
				FilterResults results) {
			data = (List<? extends Map<String, ?>>) results.values;
			if(results.count > 0){
				notifyDataSetChanged();
			}else{
				notifyDataSetInvalidated();
			}
		}

	}
 
	
	
}
