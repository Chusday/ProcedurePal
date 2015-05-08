package com.example.procedurepal;

import java.io.FileOutputStream;

import com.example.myfirstapp.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
/**
 * 
 * @author Dylan Chu
 * 
 * This activity is a multiple choice quiz
 *
 */
public class QuizActivity extends Activity {

	//instance variables
	long longestTime, startTime, elapsedTime;
	String choice;
	public Question[] questionArray = new Question[10];
	Context context;
	int numCorrect,numQuestions,counter,correct,longestQ;
	TextView stringView ;
	String[] answers;
	View correctAnswer;
	Button A,B,C,D;
	
	//constant for data passing
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
	
	/**
	 * onCreate
	 * 
	 * sets layout and populates filler with questions
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//set layout
		super.onCreate(savedInstanceState);
		
		//set times to 0
		startTime = System.currentTimeMillis();
		longestTime = 0;
		elapsedTime = 0;
		longestQ = 0;
		setContentView(R.layout.activity_display_message);
		
		//get the activity choice
		Intent intent = getIntent();
		choice = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
		stringView = (TextView) findViewById(R.id.textView1);
		
		//sample answers arrays
		//you can modify the answers here or make your own answers
		/*
		 * 
		 * String[] sampleAnswers = new String[4];
		 * sampleAnswers[0] = "Whatever you want"; 
		 * sampleAnswers[1] = "Answer B";
		 * sampleAnswers[2] = "asdf";
		 * sampleAnswers[3] = "type answer here";
		 */
		String[] sampleAnswers = new String[4];
		sampleAnswers[0] = "Answer 1";
		sampleAnswers[1] = "Answer 2";
		sampleAnswers[2] = "Answer 3";
		sampleAnswers[3] = "Answer 4";
		
		String[] sampleAnswers2 = new String[4];
		sampleAnswers2[0] = "Answer FOO";
		sampleAnswers2[1] = "Answer FEE";
		sampleAnswers2[2] = "Answer FI";
		sampleAnswers2[3] = "Answer FUM";
		
		//populate with questions
		//you can modify the questions here
		//the number at the end indicates which answer is correct
		// 1=A, 2=B, 3=C,4=D
		if(choice.equals("doctor")){
			questionArray[0] = new Question("Doctor Filler Question 1",sampleAnswers,1);
			questionArray[1] = new Question("Doctor Filler Question 2",sampleAnswers2,2);
			questionArray[2] = new Question("Doctor Filler Question 3",sampleAnswers,3);
			questionArray[3] = new Question("Doctor Filler Question 4",sampleAnswers2,4);
			questionArray[4] = new Question("Doctor Filler Question 5",sampleAnswers,1);
			questionArray[5] = new Question("Doctor Filler Question 6",sampleAnswers2,2);
			questionArray[6] = new Question("Doctor Filler Question 7",sampleAnswers,3);
			questionArray[7] = new Question("Doctor Filler Question 8",sampleAnswers2,4);
			questionArray[8] = new Question("Doctor Filler Question 9",sampleAnswers,1);
			questionArray[9] = new Question("Doctor Filler Question 10",sampleAnswers2,2);
		}
		//you can modify the questions here
		else if(choice.equals("laundry"))
		{
			questionArray[0] = new Question("Filler Question 1",sampleAnswers,1);
			questionArray[1] = new Question("Filler Question 2",sampleAnswers2,2);
			questionArray[2] = new Question("Filler Question 3",sampleAnswers,3);
			questionArray[3] = new Question("Filler Question 4",sampleAnswers2,4);
			questionArray[4] = new Question("Filler Question 5",sampleAnswers,1);
			questionArray[5] = new Question("Filler Question 6",sampleAnswers2,2);
			questionArray[6] = new Question("Filler Question 7",sampleAnswers,3);
			questionArray[7] = new Question("Filler Question 8",sampleAnswers2,4);
			questionArray[8] = new Question("Filler Question 9",sampleAnswers,1);
			questionArray[9] = new Question("Filler Question 10",sampleAnswers2,2);
		}

		//populate the buttons and set scores to 0
	    numQuestions = questionArray.length;
	    numCorrect = 0;
	    counter = 0;
	    //set up the question text and answers
		stringView.setText(questionArray[0].getQuestion());
		answers = questionArray[0].getAnswers();
		A = (Button) findViewById(R.id.button1);
		B = (Button) findViewById(R.id.button2);
		C = (Button) findViewById(R.id.button3);
		D = (Button) findViewById(R.id.button4);
		correct = questionArray[counter].getCorrect();
		A.setText(answers[0]);
		B.setText(answers[1]);
		C.setText(answers[2]);
		D.setText(answers[3]);
		
		context = this;
		
		setupActionBar();
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_message, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	/**
	 * makeChoice
	 * @param view: the view selected
	 * accepts a button input and determines if its a correct answer
	 */
	public void makeChoice(View view)
	{
		//calculate time taken for question
		elapsedTime = System.currentTimeMillis() - startTime;
		if (elapsedTime > longestTime)
		{
			longestTime = elapsedTime;
			longestQ = counter + 1;
		}

		//get new time
		startTime = System.currentTimeMillis();
		
		//set views
		View view1 = findViewById(R.id.button1);
		View view2 = findViewById(R.id.button2);
		View view3 = findViewById(R.id.button3);
		View view4 = findViewById(R.id.button4);
		
		
		//find correct answer
		if(correct == 1 && view == view1)
			numCorrect++;
		else if(correct == 2 && view == view2)
			numCorrect++;
		else if(correct == 3 && view == view3)
			numCorrect++;
		else if(correct == 4 && view == view4)
			numCorrect++;
		
		//move to next question
		if (counter < questionArray.length-1)
			counter++;
		else
		{
			
			//write to file
			Long t = new Long(longestTime);
			Intent myIntent = new Intent(this,DisplayMessageActivity.class);
			String score ="You got "+numCorrect + "/" + questionArray.length +
					" correct!\n" + "You spent " + t.intValue()/100 + " seconds on question "
					+ longestQ +"\n\n";
			
			String ster = "MC Number Correct: "+
			numCorrect + "/"+questionArray.length +"\n"+"Time spent on question " +
					longestQ + " : "+t.intValue()/100;
			FileOutputStream outputStream;
			String filename = "data";
		
			try {
				  outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
				  outputStream.write(ster.getBytes());
				  outputStream.close();
				} catch (Exception e) {
				  e.printStackTrace();
				}
			myIntent.putExtra(EXTRA_MESSAGE, score);
	    	startActivity(myIntent);
		}
			
		//change values to next question
		answers = questionArray[counter].getAnswers();
		stringView.setText(questionArray[counter].getQuestion());
		correct = questionArray[counter].getCorrect();
		A.setText(answers[0]);
		B.setText(answers[1]);
		C.setText(answers[2]);
		D.setText(answers[3]);
			
	}

}
