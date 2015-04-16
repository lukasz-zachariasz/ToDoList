package com.example.todolist;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class ListActiv extends Activity {
	private String Name;
	private String Date;
	private String Time;
	private String Info;
	private static List<Event> EventsList = new ArrayList<Event>();
	private ListView list;
	private ArrayAdapter<Event> adapter ; 
	private static FileInputStream fis;
	private static ObjectInputStream is;
	
	
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try {
			fis = openFileInput("date.bin");
			is = new ObjectInputStream(fis);
			EventsList = new ArrayList<Event>();
			while(true) EventsList.add(((Event)is.readObject()));
		} catch (StreamCorruptedException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		try {
			is.close();
			fis.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}
		catch(NullPointerException e2){
			//NoKurwa
		}
		if(getIntent().getExtras()!=null)
		{
			if(getIntent().getBooleanExtra("Deletion", false)){
				EventsList.remove(getIntent().getIntExtra("Position", 0));
			}
			else{
				Name = getIntent().getStringExtra("Name");
				Date = getIntent().getStringExtra("Date");
				Time = getIntent().getStringExtra("Time");
				Info = getIntent().getStringExtra("Info");
				if(getIntent().getExtras().getInt("Position",-1)!=-1){
					EventsList.get(getIntent().getExtras().getInt("Position")).setDate(Date);
					EventsList.get(getIntent().getExtras().getInt("Position")).setName(Name);
					EventsList.get(getIntent().getExtras().getInt("Position")).setTime(Time);
					EventsList.get(getIntent().getExtras().getInt("Position")).setInfo(Info);
				}
				else{
					EventsList.add(new Event(Name, Date, Time, Info));				
				}
			}

		}
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		list = (ListView) findViewById(R.id.listView1);
        adapter = new Adaptinho(this, R.layout.extelement, EventsList);
        
        list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener(){



			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {				
					Event item = adapter.getItem(arg2);
					
					Intent intent = new Intent(ListActiv.this, Show.class);
					intent.putExtra("Name", ListActiv.EventsList.get(arg2).getName());
					intent.putExtra("Date", ListActiv.EventsList.get(arg2).getDate());
					intent.putExtra("Time", ListActiv.EventsList.get(arg2).getTime());
					intent.putExtra("Info", ListActiv.EventsList.get(arg2).getInfo());
					intent.putExtra("Position", arg2);
					//based on item add info to intent
					startActivity(intent);
				// TODO Auto-generated method stub
				
			}
			
			
			});
	}
	
	protected void onStop(){
		super.onStop();
		try {
			FileOutputStream fos = openFileOutput("date.bin", Context.MODE_PRIVATE);
			ObjectOutputStream os = new ObjectOutputStream(fos);
			for(int i =0;i<EventsList.size();i++)
			{
				os.writeObject(EventsList.get(i));
			}
			os.close();
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list, menu);
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
}