package com.example.todolist;

import java.util.Calendar;
import java.util.Timer;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class Form extends Activity {
	private EditText NameField;
	private EditText DateField;
	private EditText TimeField;
	private EditText InfoField;
	private String Name;
	private String Date;
	private String Time;
	private String Info;
	private Button Del;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_form);
		NameField = (EditText) findViewById(R.id.editText1);
		DateField = (EditText) findViewById(R.id.editText2);
		TimeField = (EditText) findViewById(R.id.editText4);
		InfoField = (EditText) findViewById(R.id.editText3);
		Del 	  = (Button)   findViewById(R.id.button3);
		Del.setClickable(false);
		if(getIntent().getExtras()!=null)
		{
			Del.setClickable(true);
			NameField.setText(getIntent().getStringExtra("Name"));
			DateField.setText(getIntent().getStringExtra("Date"));
			TimeField.setText(getIntent().getStringExtra("Time"));
			InfoField.setText(getIntent().getStringExtra("Info"));
		}
		
		
		
	}
	public void selectDate(View view) {
		DialogFragment newFragment = new SelectDateFragment();
		newFragment.show(getFragmentManager(), "DatePicker");
		}
		public void populateSetDate(int year, int month, int day) {
		DateField.setText(month+"/"+day+"/"+year);
		}
		public class SelectDateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
		final Calendar calendar = Calendar.getInstance();
		int yy = calendar.get(Calendar.YEAR);
		int mm = calendar.get(Calendar.MONTH);
		int dd = calendar.get(Calendar.DAY_OF_MONTH);
		return new DatePickerDialog(getActivity(), this, yy, mm, dd);
		}

		public void onDateSet(DatePicker view, int yy, int mm, int dd) {
		populateSetDate(yy, mm+1, dd);
		}
		}
		
	public void selectTime(View view){
		DialogFragment newFragment = new SelectTimeFragment();
		newFragment.show(getFragmentManager(), "TimePicker");
	}
	public void populateSetTime(int h, int m){
		TimeField.setText(h+":"+m);
	}
	public class SelectTimeFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

		public Dialog onCreateDialog(Bundle savedInstanceState) {
	        final Calendar c = Calendar.getInstance();
	        int hour = c.get(Calendar.HOUR_OF_DAY);
	        int minute = c.get(Calendar.MINUTE);
	        return new TimePickerDialog(getActivity(), this, hour, minute,
	        		DateFormat.is24HourFormat(getActivity()));
		}
		@Override
		public void onTimeSet(TimePicker arg0, int arg1, int arg2) {
			populateSetTime(arg1,arg2);
			// TODO Auto-generated method stub
			
		}
		
		
	}
		
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.form, menu);
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
	
	public boolean Set(View v)
	{
		Name = NameField.getText().toString();
		Date = DateField.getText().toString();
		Time = TimeField.getText().toString();
		Info = InfoField.getText().toString();
		if(Name.matches("")||Name==null||Date.matches("")||Date==null)
		{
			Context cont = getApplicationContext();
			Toast.makeText(cont, "Set name and date of event", Toast.LENGTH_LONG).show();
			return true;
		}
		if(getIntent().getExtras()!=null)
		{
			Intent SendToList=new Intent(this, ListActiv.class).putExtra("Name", Name);
			SendToList.putExtra("Date", Date);
			SendToList.putExtra("Time", Time);
			SendToList.putExtra("Info", Info);			
			SendToList.putExtra("Position", getIntent().getExtras().getInt("Position"));
			startActivity(SendToList);
		}
		else{
		Intent SendToList=new Intent(this, ListActiv.class).putExtra("Name", Name);
		SendToList.putExtra("Date", Date);
		SendToList.putExtra("Time", Time);
		SendToList.putExtra("Info", Info);
		SendToList.putExtra("Position", -1);
		startActivity(SendToList);
		}
		return false;
	}
	public void Del(View v)
	{
		Intent SendToList=new Intent(this, ListActiv.class).putExtra("Deletion", true);
		SendToList.putExtra("Position", getIntent().getExtras().getInt("Position"));
		startActivity(SendToList);
		
	}
}
