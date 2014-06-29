package com.example.customadaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.flashr.R;

public class CustomListViewAdapter extends BaseAdapter{
	private Context mContext;
	private String[] mDateItem;
	private String[] mInfoItem;
	LayoutInflater inflater;
	public CustomListViewAdapter(Context context, String[] dateItem, String[] infoItem){
		super();
		this.mContext = context;
		this.mDateItem = dateItem;
		this.mInfoItem = infoItem;
		inflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mDateItem.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mDateItem[position]+mInfoItem[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		convertView = inflater.inflate(R.layout.listrow, null);
		TextView dateTv = (TextView)convertView.findViewById(R.id.dateText);
		TextView infoTv = (TextView)convertView.findViewById(R.id.infoText);
		dateTv.setText(mDateItem[position]);
		infoTv.setText(mInfoItem[position]);
		return convertView;
	}
}
