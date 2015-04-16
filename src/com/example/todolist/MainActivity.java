package com.example.todolist;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Intent Intencja = new Intent(this, About.class);
        Button about = (Button) findViewById(R.id.button3);
        about.setOnClickListener(new View.OnClickListener(){
        	public void onClick(View view){
        		startActivity(Intencja);
        	}
        });
    }

public void addNew(View v)
{
	final Intent Intencja2 = new Intent(this, Form.class);
	startActivity(Intencja2);
}
public void Show(View v)
{
	final Intent Intencja3 = new Intent(this, ListActiv.class);
	startActivity(Intencja3);
}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.main, menu);
       // getMenuInflater().inflate(R.id.about, menu)
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
