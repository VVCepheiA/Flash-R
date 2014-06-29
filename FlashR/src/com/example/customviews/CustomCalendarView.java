package com.example.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.CalendarView;

import com.example.flashr.R;

public class CustomCalendarView extends CalendarView{
	public CustomCalendarView(Context context, AttributeSet attrs){
		super(context,attrs);
		TypedArray a = context.getTheme().obtainStyledAttributes(attrs,R.styleable.CustomCalendarView
				,0,0);
			
	}
	
	
	
	@Override
	protected void onDraw(Canvas canvas){
		super.onDraw(canvas);
	}
}
