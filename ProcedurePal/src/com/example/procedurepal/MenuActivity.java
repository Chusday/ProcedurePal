package com.example.procedurepal;

import com.example.myfirstapp.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
/**
 * 
 * @author Dylan Chu
 * MenuActivity provides a secondary menu for users to select between instructions
 * and quizzes
 * 
 * Only takes into account the laundry instructions, but I'll add a drop-down menu for
 * different activities
 */
public class MenuActivity extends Activity {

	//constant for passing data to other screens
	public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
	
	//choice variable for instruction selection
	String choice;
	/**
	 * onCreate
	 * creates the layout
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//get what set of instructions to run
		Intent intent = this.getIntent();
        choice=intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
		setContentView(R.layout.activity_menu);
	}

	/**
	 * onCreateOptionsMenu
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}
	
	/**
	 * instructions
	 * @param view: The view selected
	 * Starts the instructions activity
	 */
	 public void instructions(View view){
	    	Intent intent = new Intent(this,Instructions.class);
	    	
	    	//passes the different choice in
	    	intent.putExtra(EXTRA_MESSAGE,choice);
	    	startActivity(intent);
	    }

	 /**
	  * quiz
	  * @param view: The view selected
	  * Brings the user to the quiz choice
	  */
	 public void quiz(View view){
	    	Intent intent = new Intent(this,QuizChoice.class);
	    	
	    	//passes the different choice in
	    	intent.putExtra(EXTRA_MESSAGE,choice);
	    	startActivity(intent);
	    }

}
