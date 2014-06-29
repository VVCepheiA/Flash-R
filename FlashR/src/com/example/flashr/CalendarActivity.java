package com.example.flashr;

import java.util.Calendar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.customadaptor.CustomGridViewAdaptor;
import com.example.customadaptor.CustomListViewAdapter;

public class CalendarActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calendar);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.calendar, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		private int[][] month = new int[12][42];
		private int[] totalMonthDays = new int[]{
			31,	28,31,30,31,30,31,31,30,31,30,31
		};
		private String[] yearDateList = new String[]{
				"2014 - January",
				"2014 - February",
				"2014 - March",
				"2014 - April",
				"2014 - May",
				"2014 - June",
				"2014 - July",
				"2014 - August",
				"2014 - September",
				"2014 - October",
				"2014 - November",
				"2014 - December",
		};
		private String[] listViewInfoItems = new String[]{
				"First event",
				"Second Event",
				"Thrid Event",
				"Fourth Event"
		};
		private String[] listViewDateItems = new String[]{
				"2014",
				"2014",
				"2014",
				"2014"
		};
		private int defaultMonth = 6;
		private int curMonth = defaultMonth;
		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			final View rootView = inflater.inflate(R.layout.fragment_calendar,
					container, false);
			populateDays();
			TextView tv = (TextView)rootView.findViewById(R.id.yearMonthDisplay);
			
			tv.setText(yearDateList[defaultMonth]);
			//int curMonth = getCurMonth(tv);
			/*GridView gv = (GridView)rootView.findViewById(R.id.gridView1);
			ListView lv = (ListView)rootView.findViewById(R.id.listView1);
			
			
			CustomGridViewAdaptor gridAdapter = new CustomGridViewAdaptor(getActivity(),month[curMonth]);
			CustomListViewAdapter listAdapter = new CustomListViewAdapter(getActivity(),listViewDateItems,listViewInfoItems);
			gv.setAdapter(gridAdapter);
			lv.setAdapter(listAdapter);*/
			GridView gv = (GridView)rootView.findViewById(R.id.gridView1);
			ListView lv = (ListView)rootView.findViewById(R.id.listView1);
			Button leftBtn = (Button)rootView.findViewById(R.id.leftBtn);
			Button rightBtn = (Button)rootView.findViewById(R.id.rightBtn);
			leftBtn.setOnClickListener(new OnClickListener() {
			    public void onClick(View v)
			    {
			        moveDateLeft(rootView);
			    } 
			});
			rightBtn.setOnClickListener(new OnClickListener() {
			    public void onClick(View v)
			    {
			        moveDateRight(rootView);
			    } 
			});
			
			
			
			setValues(rootView);
			return rootView;
		}
		private void setValues(final View view){
			TextView tv = (TextView)view.findViewById(R.id.yearMonthDisplay);
			GridView gv = (GridView)view.findViewById(R.id.gridView1);
			ListView lv = (ListView)view.findViewById(R.id.listView1);
			
			tv.setText(yearDateList[curMonth]);
			CustomGridViewAdaptor gridAdapter = new CustomGridViewAdaptor(getActivity(),month[curMonth]);
			CustomListViewAdapter listAdapter = new CustomListViewAdapter(getActivity(),listViewDateItems,listViewInfoItems);
			gv.setAdapter(gridAdapter);
			lv.setAdapter(listAdapter);
			gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	            @Override
	            public void onItemClick(AdapterView<?> parent, View view2, int position, long id) {
	            	Bundle b = new Bundle();
	            	Intent intent = new Intent(getActivity(),CreateEventActivity.class);
	            	
	            	TextView tv = (TextView)view.findViewById(R.id.yearMonthDisplay);
	            	//TextView tv = (TextView)parent.getItemAtPosition(position);
	            	String message = parent.getItemAtPosition(position).toString();
	            	String yearMonth = tv.getText().toString();
	            	String year = yearMonth.substring(0,4);
	            	String month = yearMonth.substring(7);
	            	String[] data = new String[]{year,month,message};
	            	b.putStringArray("dateData", data);
	        		//intent.putExtra(EXTRA_MESSAGE,message);
	            	intent.putExtras(b);
	        		
	        		Log.i("Message",yearMonth);
	        		startActivity(intent);
	            }
	        });
		}
		private void populateDays(){
			Calendar c = Calendar.getInstance();
			c.set(Calendar.YEAR, 2014);
			c.set(Calendar.MONTH, 1);
			c.set(Calendar.DAY_OF_YEAR,0);
			for(int i=0;i<12;i++){
				c.set(Calendar.YEAR, 2014);
				c.set(Calendar.MONTH, i+1);
				c.set(Calendar.DAY_OF_YEAR,0);
				int minDay = c.get(Calendar.DAY_OF_WEEK);
				int beginDay = 1;
				int totalDays = totalMonthDays[curMonth];
				for(int d=minDay;d<=totalDays+minDay-1;d++){
					month[i][d] = beginDay;
					beginDay++;
				}
			}
			/*if(beginDay == 0){
				beginDay++;
			}
			for(int curMonth = 0;curMonth<12;curMonth++){
				int totalDays = totalMonthDays[curMonth]+1;
				for(int i=0;i<35;i++){
					month[curMonth][i] = beginDay;
					beginDay = (beginDay+1)%totalDays;
					if(beginDay == 0){
						beginDay++;
					}
				}
				beginDay = (beginDay-7+totalDays-1)%totalDays;
				if(beginDay == 0){
					beginDay++;
				}
			}*/
		}
		
		private int getCurMonth(TextView tv){
			
			String month = tv.getText().toString().substring(7);
			if(month.equalsIgnoreCase("January")){
				return 0;
			}else if(month.equalsIgnoreCase("February")){
				return 1;
			}else if(month.equalsIgnoreCase("March")){
				return 2;
			}else if(month.equalsIgnoreCase("April")){
				return 3;
			}else if(month.equalsIgnoreCase("May")){
				return 4;
			}else if(month.equalsIgnoreCase("June")){
				return 5;
			}else if(month.equalsIgnoreCase("July")){
				return 6;
			}else if(month.equalsIgnoreCase("August")){
				return 7;
			}else if(month.equalsIgnoreCase("September")){
				return 8;
			}else if(month.equalsIgnoreCase("October")){
				return 9;
			}else if(month.equalsIgnoreCase("November")){
				return 10;
			}else{
				return 11;
			}
		}
		public void moveDateLeft(View view){
			curMonth--;
			setValues(view);
		}
		public void moveDateRight(View view){
			curMonth++;
			setValues(view);
		}
	}
	

}
