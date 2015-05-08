package com.example.procedurepal;

import com.example.myfirstapp.R;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
/**
 * 
 * @author Dylan Chu
 * Instructions Activity
 * Displays the instructions
 *
 */
public class Instructions extends Activity {

	//instance variables
	String choice;
	Button buttonNext,buttonBack,buttonSound;

	//constant for passing data to other screens
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
	
	//more instance variables
	ImageView image;
	TextView prompt;
	int counter;
	LayoutParams params;
	int[] images;
	String[] prompts;
	Context myContext;
	SoundPoolPlayer sound;
	
	/**
	 * onCreate
	 * creates layout for the activity
	 * 
	 * Sets the images and directions based on what string was passed in
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//set layout
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_instructions);
		
		//get the choice
		Intent intent = getIntent();
		choice = intent.getStringExtra(MenuActivity.EXTRA_MESSAGE);
		
		//create image array and instructions for doctor
		//Edit the image names to change what image shows up
		//edit the size of the array to add more steps
		//edit the prompts to change the text displayed
		if (choice.equals("doctor")){
			images = new int[9];
			images[0]= R.raw.doctor_step_1;
			images[1]= R.raw.doctor_step_2;
			images[2]= R.raw.doctor_step_3;
			images[3]= R.raw.doctor_step_4;
			images[4]= R.raw.doctor_step_5;
			images[5]= R.raw.doctor_step_6;
			images[6]= R.raw.doctor_step_7;
			images[7]= R.raw.doctor_step_8;
			images[8]= R.raw.doctor_step_9;
			prompts = new String[9];
			prompts[0] = "";
			prompts[1] = "";
			prompts[2] = "";
			prompts[3] = "";
			prompts[4] = "";
			prompts[5] = "";
			prompts[6] = "";
			prompts[7] = "";
			prompts[8] = "";
			
		}
		//laundry arrays
		else if(choice.equals("laundry"))
		{
			images = new int[10];
			images[0]= R.raw.laundry_1;
			images[1]= R.raw.laundry_2;
			images[2]= R.raw.laundry_3;
			images[3]= R.raw.laundry_4;
			images[4]= R.raw.laundry_4;
			images[5]= R.raw.laundry_5;
			images[6]= R.raw.laundry_6;
			images[7]= R.raw.laundry_6;
			images[8]= R.raw.laundry_5;
			images[9]= R.raw.laundry_7;
			
			prompts = new String[10];
			prompts[0] = "1. Seperate white clothes from colored clothes.";
			prompts[1] = "2. Put either pile in washer.";
			prompts[2] = "3. Add detergent.";
			prompts[3] = "4. Select desired setting.";
			prompts[4] = "5. Start washer";
			prompts[5] = "6. Remove clothes from washer and place in dryer";
			prompts[6] = "7. Select desired setting.";
			prompts[7] = "8. Start dryer";
			prompts[8] = "9. When dryer stops, remove clothes from dryer.";
			prompts[9] = "10. Fold and put away clothes.";
		}
		//create soundPoolPlayer
		sound = new SoundPoolPlayer(this); 
		myContext = this;
		//set counter to 0 to count steps
		counter = 0;
		
		//add the buttonlistener
		addButtonListener();
	}

	/**
	 * Menu creation for action bar
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.instructions, menu);
		return true;
	}

	/**
	 * addButtonListener
	 * 
	 * This method makes things happen when buttons are pressed
	 */
	public void addButtonListener() {

		//set the image in the initial screen
		image = (ImageView) findViewById(R.id.image);
		image.setImageResource(images[0]);
		
		//change the image size
		params = (LayoutParams) image.getLayoutParams();
		params.height = 15000;
		params.width = 15000;
		
		//set the text in the initial screen
		prompt = (TextView) findViewById(R.id.prompts);
		prompt.setText(prompts[0]);
		prompt.setTextSize(20);
		
		//set the next button
		buttonNext = (Button) findViewById(R.id.button);
		buttonNext.setOnClickListener(new OnClickListener() {

			//Sets the onClick for the next buton
			@Override
			public void onClick(View view) {
				//if there are still images left
				if (counter < images.length - 1){
					//step through and set the next image in places
					counter ++;
					image.setImageResource(images[counter]);
					params = (LayoutParams) image.getLayoutParams();
					params.height = 15000;
					params.width = 15000;
					
					//set the next text
					prompt.setText(prompts[counter]);
					prompt.setTextSize(20);
				
					
				}
				//otherwise go to quiz choice
				else{
					Intent myIntent = new Intent(myContext, QuizChoice.class);
					myIntent.putExtra(EXTRA_MESSAGE, choice);
					startActivity(myIntent);
				}
			}
			
			
		

		
		});
		
		//set the back button
		buttonBack = (Button) findViewById(R.id.button1);
		buttonBack.setOnClickListener(new OnClickListener(){
			
			public void onClick(View view){
				//if we aren't at the first image, go back to the previous image and text
				if (counter >0){
					counter --;
					image.setImageResource(images[counter]);
					prompt.setText(prompts[counter]);
					prompt.setTextSize(20);
					
				}
				
			}
		});
		//set the sound button
		buttonSound = (Button) findViewById(R.id.button2);
		buttonSound.setOnClickListener(new OnClickListener(){
			//use the counter to figure out which soud to play
			public void onClick(View view){
				if(counter ==0)
					sound.playShortResource(R.raw.laundry_step_1);
				
				if(counter ==1)
					sound.playShortResource(R.raw.laundry_step_2);
				
				if(counter ==2)
					sound.playShortResource(R.raw.laundry_step_3);
				
				if(counter ==3)
					sound.playShortResource(R.raw.laundry_step_4);
				
				if(counter ==4)
					sound.playShortResource(R.raw.laundry_step_5);
				
				if(counter ==5)
					sound.playShortResource(R.raw.laundry_step_6);
				
				if(counter ==6)
					sound.playShortResource(R.raw.laundry_step_7);
				
				if(counter ==7)
					sound.playShortResource(R.raw.laundry_step_8);
				
				if(counter ==8)
					sound.playShortResource(R.raw.laundry_step_9);
				
				if(counter ==9)
					sound.playShortResource(R.raw.laundry_step_10);
				
				
				
			}
		});
	}
	
	//exit button, which goes back
	public void exit(View v)
	{
		finish();
        System.exit(0);
	}

}
