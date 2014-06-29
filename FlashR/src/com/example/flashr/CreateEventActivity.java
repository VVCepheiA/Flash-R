package com.example.flashr;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.database.DatabaseHandler;
import com.example.database.Event;

public class CreateEventActivity extends ActionBarActivity {
	
	private static String dateTime;
	private String date;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_event);
		
		String createOrEdit = "create";
		date = "testtesttest";
		
		if (createOrEdit.equals("create")){
			TextView create = (TextView)findViewById(R.id.createOrEdit);
            create.setText("Create");
		}else{
			TextView edit = (TextView)findViewById(R.id.createOrEdit);
            edit.setText("Edit");
		}
		
//	    Bundle b = this.getIntent().getExtras();
//	    String[] array = b.getStringArray("dateData");
		String[] array = {"1900","12","24"};
	    date = array[0]+array[1]+array[2];
				
	}
	
	public void onCreateClick(View view){
		EditText eNameTmp   = (EditText)findViewById(R.id.editText1);
		String eName = eNameTmp.getText().toString();
		
		
		TimePicker eventTime;
		eventTime = ((TimePicker) findViewById(R.id.eventTime));
	    eventTime.clearFocus();
	    
	    int hour = eventTime.getCurrentHour();
	    int minute = eventTime.getCurrentMinute();
	    String hourS = "";
	    String minuteS = "";
	    if (hour<10)
	    	hourS = "0" + Integer.toString(hour);
	    else
	    	hourS = Integer.toString(hour);
	    if (minute<10)
	    	minuteS = "0" + Integer.toString(minute);
	    else
	    	minuteS =  Integer.toString(minute);
	    	    
	    dateTime = date + hourS + minuteS;
		
		DatabaseHandler db = new DatabaseHandler(this);
		db.addEvent(new Event(dateTime,eName));
		Log.w("Reading: ", "Reading all contacts.."); 
        List<Event> event = db.getAllContacts();       
         
        for (Event cn : event) {
            String log = "DateTime: " + cn.getDateTime() + " ,EventName: " + cn.getEventName();
                // Writing Contacts to log
            Log.d("Name: ", log);
        }
        db.close();
        
        toCalendarActivity();
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
