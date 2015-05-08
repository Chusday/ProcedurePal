package com.example.procedurepal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.example.myfirstapp.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
/**
 * 
 * @author Dylan Chu
 * This activity displays the quiz data
 */
public class DataActivity extends Activity {

	/**
	 * onCreate
	 * Sets layout and reads in the data
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//Set layout
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_data);
		
		//try-catch statement for reading data from the file
		try {
			//open file
		    BufferedReader inputReader = new BufferedReader(new InputStreamReader(
		            openFileInput("data")));
		    
		    //read the data into a string
		    String inputString;
		    StringBuffer stringBuffer = new StringBuffer();                
		    while ((inputString = inputReader.readLine()) != null) {
		        stringBuffer.append(inputString + "\n");
		    }

		    //display the data string
			TextView tv = (TextView)findViewById(R.id.textViewData);  

		    tv.setText(stringBuffer.toString());
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		
		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.data, menu);
		return true;
	}

}
