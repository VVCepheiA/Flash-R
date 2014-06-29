package com.example.customviews;

import com.example.flashr.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

public class CompoundCalendarView extends RelativeLayout{
	
	public CompoundCalendarView(Context context, AttributeSet attrs){
		super(context, attrs);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.compound_calendar, this);
	}
	
	
}
