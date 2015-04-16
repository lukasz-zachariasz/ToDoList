package com.example.todolist;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class Adaptinho extends ArrayAdapter<Event> {
	private Context context;
    private int layoutResourceId;   
    private int textID;
    private List<Event> InputList; // = new ArrayList<String>();

	
    public Adaptinho(Context context, int resource, List<Event> objects) {
		super(context, resource, objects);
		this.context=context;
		this.layoutResourceId=resource;
		this.InputList=objects;
		// TODO Auto-generated constructor stub
	} 


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		Holder holder=null;
		if(row == null)
        {
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);
			
			holder = new Holder();
			holder.name = (TextView)row.findViewById(R.id.textView2);
			holder.date = (TextView)row.findViewById(R.id.textView1);
			row.setTag(holder);
        }
		else{
			holder = (Holder)row.getTag();
		}
		Event object = InputList.get(position);
        holder.name.setText(object.getName());
        holder.date.setText(object.getDate());
		
		// TODO Auto-generated method stub
		return row;
	}
    
    static class Holder
    {
    	TextView name;
    	TextView date;
    }
}
