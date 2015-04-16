package com.example.todolist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Show extends Activity {
	private TextView NameField; //textView4
	private TextView DateField; //textView3
	private TextView TimeField; //textView6
	private TextView InfoField; //textView7
	private String Name;
	private String Date;
	private String Time;
	private String Info;
	private int    Position;

	private void startSettings(){
		NameField = (TextView) findViewById(R.id.textView4);
		DateField = (TextView) findViewById(R.id.textView3);
		TimeField = (TextView) findViewById(R.id.textView6);
		InfoField = (TextView) findViewById(R.id.textView7);
		Name	  = getIntent().getExtras().getString("Name");
		Date	  = getIntent().getExtras().getString("Date");
		Time	  = getIntent().getExtras().getString("Time");
		Info	  = getIntent().getExtras().getString("Info");
		Position  = getIntent().getExtras().getInt("Position");
		NameField.setText(Name);
		DateField.setText(Date);
		TimeField.setText(Time);
		InfoField.setText(Info);
	}
	
	public void List(View v){
		startActivity(new Intent(this, ListActiv.class));
	}
	
	public void Edit(View v){
		Intent Edit = new Intent(this, Form.class);
		Edit.putExtra("Name", Name);
		Edit.putExtra("Date", Date);
		Edit.putExtra("Time", Time);
		Edit.putExtra("Info", Info);
		Edit.putExtra("Position", Position);
		startActivity(Edit);
	}
	
	public void Delete(View v){
		Intent Delete=new Intent(this, ListActiv.class).putExtra("Deletion", true);
		Delete.putExtra("Position", Position);
		startActivity(Delete);
	}
	
	public void Menu(View v){
		startActivity(new Intent(this, MainActivity.class));
	}
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show);
		startSettings();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show, menu);
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
