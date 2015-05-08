package com.example.procedurepal;

import com.example.myfirstapp.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
/**
 * 
 * @author Dylan Chu
 * 
 * This class displays a message
 *
 */
public class DisplayMessageActivity extends Activity {
	
	TextView textView;
	
	/**
	 * onCreate
	 * 
	 * sets layout and message
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_multiple_choice);
		 // Get the message from the intent
	    Intent intent = getIntent();
	    String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
	 // Create the text view
	    setText(message);
	    
	    
	    
	}
	
	/**
	 * setText
	 * @param str: text to display
	 * sets the textView to the input
	 */
	public void setText(String str){
		textView = (TextView) findViewById(R.id.score);
		textView.setTextSize(40);
	    textView.setText(str);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.multiple_choice, menu);
		return true;
	}
	
	/**
	 * viewData
	 * @param view: the view selected
	 * starts the data activity
	 */
	public void viewData (View view){
    	Intent intent = new Intent(this, DataActivity.class);
    	startActivity(intent);
    }

}
