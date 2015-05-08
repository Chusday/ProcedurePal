package com.example.procedurepal;
/**
 * @author Dylan Chu
 * Allows the user to choose between two different quizzes
 */

import com.example.myfirstapp.R;

import android.os.Bundle;
import android.app.Activity;

import android.content.Context;
import android.content.Intent;

import android.view.View;

public class QuizChoice extends Activity{
	//constant for passing data to other screens
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
	
	//instance variables
	String choice;
	Context myContext;
	
	/**
	 * onCreate
	 * sets layout and takes in the choice from the previous screen
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_choice);
        Intent intent = this.getIntent();
        choice=intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        myContext = this;
     
    }
	
	/**
	 * multipleChoice
	 * @param view: the view selected
	 * 
	 * Starts the multiple choice quiz
	 */
	public void multipleChoice(View view){
		Intent myIntent = new Intent(myContext, QuizActivity.class);
		myIntent.putExtra(EXTRA_MESSAGE, choice);
		startActivity(myIntent);
	}
	
	/**
	 * dragAndDrop
	 * @param view: the view selected
	 * 
	 * starts the drag and drop activity
	 */
	public void dragAndDrop(View view){
    	Intent intent = new Intent(this, DragActivity.class);
    	startActivity(intent);
    }

}
