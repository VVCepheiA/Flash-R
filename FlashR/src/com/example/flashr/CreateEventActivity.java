package com.example.flashr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.database.DatabaseHandler;
import com.example.database.Event;

public class CreateEventActivity extends ActionBarActivity {
	
	public String dateTime;
	TimePicker notifyTime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_event);
		
		String createOrEdit = "create";
		
		if (createOrEdit.equals("create")){
			TextView create = (TextView)findViewById(R.id.createOrEdit);
            create.setText("Create");
		}else{
			TextView edit = (TextView)findViewById(R.id.createOrEdit);
            edit.setText("Edit");
		}
		
		notifyTime = ((TimePicker) findViewById(R.id.eventTime));
	    notifyTime.clearFocus();

	    int hour = notifyTime.getCurrentHour();
	    int minute = notifyTime.getCurrentMinute();
	    
	    dateTime = "20140629"+ Integer.toString(hour) + Integer.toString(minute);
				
	}
	
	public void onCreateClick(View view){
		DatabaseHandler db = new DatabaseHandler(this);
		db.addEvent(new Event(dateTime,"Content"));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_event, menu);
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
	
	private void toCalendarActivity(){
		Intent intent = new Intent(this, CalendarActivity.class);
		startActivity(intent);	
	}

	public void onCancelClick(View view){
		toCalendarActivity();	
	}
	
	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		TimePicker notifyTime;
		

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_create_event,
					container, false);		
			return rootView;
		}
		
		
	}
	
	
}
