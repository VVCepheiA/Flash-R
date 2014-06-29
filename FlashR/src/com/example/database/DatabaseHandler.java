package com.example.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper{
	// All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "eventManager";
 
    // Contacts table name
    private static final String TABLE_EVENTS = "events";
 
    // Contacts Table Columns names
    private static final String KEY_DATETIME = "dateTime";
    private static final String KEY_EVENTNAME = "event_name";
 
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_EVENT_TABLE = "CREATE TABLE " + TABLE_EVENTS + "("
                + KEY_DATETIME + " TEXT," + KEY_EVENTNAME + " TEXT" + ")";
        db.execSQL(CREATE_EVENT_TABLE);
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);
 
        // Create tables again
        onCreate(db);
    }
    
 
    public void addEvent(Event event) {
    	SQLiteDatabase db = this.getWritableDatabase();
    	 
        ContentValues values = new ContentValues();
        values.put(KEY_DATETIME, event.getDateTime());
        values.put(KEY_EVENTNAME, event.getEventName());         
     
        // Inserting Row
        db.insert(TABLE_EVENTS, null, values);
        db.close(); // Closing database connection
    }
     
    // Getting single contact
    public Event getEvent(String dateTime) {
    	SQLiteDatabase db = this.getReadableDatabase();
    	 
        Cursor cursor = db.query(TABLE_EVENTS, new String[] { KEY_DATETIME, KEY_EVENTNAME }, 
        		KEY_DATETIME + "=" + dateTime, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
     
        Event event = new Event(cursor.getString(0),cursor.getString(1));
        // return event
        return event;
    }
     
    // Getting All Contacts
    public List<Event> getDayEvents(String dateTime) {
    	SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_EVENTS, new String[] { KEY_DATETIME, KEY_EVENTNAME }, 
        		KEY_DATETIME + " LIKE " + dateTime, null, null, null, null);
        ArrayList<Event> eventList = new ArrayList<Event>();
        
        if (cursor != null)
        	cursor.moveToFirst();
                
        while(!cursor.isAfterLast()) {
            Event event = new Event(cursor.getString(0),cursor.getString(1));
            eventList.add(event);
            cursor.moveToNext();
        }
        
        return eventList;
    }
     
    // Getting contacts Count
    public int getEventCount(String dateTime) {
    	 String countQuery = "SELECT  * FROM " + TABLE_EVENTS + " WHERE " + KEY_DATETIME + "=" + dateTime;
         SQLiteDatabase db = this.getReadableDatabase();
         Cursor cursor = db.rawQuery(countQuery, null);
         cursor.close(); 
         // return count
         return cursor.getCount();
    }
    
    public List<Event> getAllContacts() {
        List<Event> eventList = new ArrayList<Event>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_EVENTS;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Event event = new Event();
                event.setDateTime(cursor.getString(0));
                event.setEventName(cursor.getString(1));
                // Adding contact to list
                eventList.add(event);
            } while (cursor.moveToNext());
        }
 
        // return contact list
        return eventList;
    }
    
    // Updating single contact
//    public int updateEvent(Event event) {
//    	
//    	
//    }
//     
//    // Deleting single contact
//    public void deleteEvent(Event event) {}
}
