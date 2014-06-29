package com.example.customadaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.flashr.R;

public class CustomGridViewAdaptor extends BaseAdapter{
	private Context mContext;
	private int[] mItem;
	LayoutInflater inflater;
	public CustomGridViewAdaptor(Context context, int[] item){
		super();
		this.mContext = context;
		this.mItem = item;
		inflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mItem.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mItem[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		convertView = inflater.inflate(R.layout.cell, null);
		TextView tv = (TextView)convertView.findViewById(R.id.cell_grid);
		tv.setText(Integer.toString(mItem[position]));
		return convertView;
	}
}
