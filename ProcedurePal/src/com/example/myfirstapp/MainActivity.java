package com.example.myfirstapp;
/**
 * @author Dylan Chu
 * Main activity for ProcedurePal, this is the first screen that they see when they 
 * start the app
 * 
 * 
 */
import java.io.FileOutputStream;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

@SuppressLint("WorldReadableFiles")
public class MainActivity extends Activity{
	//constant for passing data to other screens
	 public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

	 /**
	  * onCreate
	  * creates the instance and sets the view format
	  */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     
    }

    
   /**
    * onCreateOptionsMenu
    */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    /**
     * Name selection
     * @param view: the view selected
     * This method either gets the name from the face button pressed 
     * OR
     * This method gets the named typed in
     */
    @SuppressWarnings("deprecation")
	public void name(View view){
    	//instance variables
    	String nameSelected="";
    	
    	//read in the different buttons as views
    	View textbutton = findViewById(R.id.button5);
    	View name1 = findViewById(R.id.name1);
    	View name2 = findViewById(R.id.name2);
    	View name3 = findViewById(R.id.name3);
    	View name4 = findViewById(R.id.name4);
    	
    	//if-else statement to choose between the text field and face buttons
    	if(view == textbutton){
    		EditText editText = (EditText) findViewById(R.id.edit_message);
    		nameSelected = editText.getText().toString();}
    	else{
    		//you can change the names set to the face buttons here
    		if(view == name1)
    			nameSelected = "John";
    		if(view == name2)
    			nameSelected = "Jane";
    		if(view == name3)
    			nameSelected = "Joe";
    		if(view == name4)
    			nameSelected = "Sally";
    	}
    	
    	//create file output
    	FileOutputStream outputStream;
    	//make filename
		String filename = "data";
		//make string of what to write to file
		String writeToFile = "Name: "+nameSelected+"\n";
		//write to file
		try {
			  outputStream = openFileOutput(filename, Context.MODE_APPEND| Context.MODE_WORLD_READABLE);
			  outputStream.write(writeToFile.getBytes());
			  outputStream.close();
			} catch (Exception e) {
			  e.printStackTrace();
			}
		
		//then select activity as laundry(Will be changed later) and pass that to the next
		//menu screen and start the next menu
		Intent intent = new Intent(this,MenuActivity.class);
		intent.putExtra(EXTRA_MESSAGE, "laundry");
		startActivity(intent);
    	
    }
    
    
   
    /**
     * viewData
     * @param view: the view selected
     * This opens the quiz results
     */
    public void viewData (View view){
    	Intent intent = new Intent(this, DataActivity.class);
    	startActivity(intent);
    }
}
    

