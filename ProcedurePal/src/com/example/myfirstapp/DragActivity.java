package com.example.myfirstapp;

import java.io.FileOutputStream;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
/**
 * 
 * @author Dylan Chu
 * This activity is a simple 4 panel drag and drop quiz
 */
public class DragActivity extends Activity {
	
	//instance variables
  long startTime,t;
  int numCorrect;
/** Called when the activity is first created. */
 
  //Constant for passing data
  public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
  
  /**
   * onCreate
   * 
   * sets layout and images
   */
  @Override
  public void onCreate(Bundle savedInstanceState) {
	//set layout
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_drag);
    
    //set the images
    //change images here
    numCorrect = 0;
    findViewById(R.id.myimage1).setOnTouchListener(new MyTouchListener());
    findViewById(R.id.myimage2).setOnTouchListener(new MyTouchListener());
    findViewById(R.id.myimage3).setOnTouchListener(new MyTouchListener());
    findViewById(R.id.myimage4).setOnTouchListener(new MyTouchListener());
    findViewById(R.id.topleft).setOnDragListener(new MyDragListener());
    findViewById(R.id.topright).setOnDragListener(new MyDragListener());
    findViewById(R.id.bottomleft).setOnDragListener(new MyDragListener());
    findViewById(R.id.bottomright).setOnDragListener(new MyDragListener());
    findViewById(R.id.bank).setOnDragListener(new MyDragListener());
    
    //start timer
    startTime = System.currentTimeMillis();

  }
  
  /**
   * 
   * @author Dylan Chu
   * listener class that allows images to be dragged
   */
  private final class MyTouchListener implements OnTouchListener {
	  //start on touch
    public boolean onTouch(View view, MotionEvent motionEvent) {
    	
    	//when touch make a shadow
      if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
        ClipData data = ClipData.newPlainText("", "");
        DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
        view.startDrag(data, shadowBuilder, view, 0);
        view.setVisibility(View.INVISIBLE);
        return true;
      } else {
        return false;
      }
    }
  }
  
  //drag listener for receiving views
  class MyDragListener implements OnDragListener {
	//make new drawables
	//change backgrounds here
    Drawable one = getResources().getDrawable(R.drawable.number_1);
    Drawable oneb = getResources().getDrawable(R.drawable.number_1b);
    Drawable two = getResources().getDrawable(R.drawable.number_2);
    Drawable twob = getResources().getDrawable(R.drawable.number_2b);
    Drawable three = getResources().getDrawable(R.drawable.number_3);
    Drawable threeb = getResources().getDrawable(R.drawable.number_3b);
    Drawable four = getResources().getDrawable(R.drawable.number_4);
    Drawable fourb = getResources().getDrawable(R.drawable.number_4b);
    
    //set the views
    View vone = findViewById(R.id.topleft);
    View vtwo = findViewById(R.id.topright);
    View vthree = findViewById(R.id.bottomleft);
    View vfour = findViewById(R.id.bottomright);
   

    /**
     * onDrag, for when things are getting dragged
     */
    @SuppressWarnings("deprecation")
	@Override
    public boolean onDrag(View v, DragEvent event) {
    	
      
      switch (event.getAction()) {
      case DragEvent.ACTION_DRAG_STARTED:
        // do nothing
        break;
      case DragEvent.ACTION_DRAG_ENTERED:
    	  //change background to blue outline
    	  if (v== vone)
    		  v.setBackgroundDrawable(oneb);
    	  
    	  if (v==vtwo)
    		  v.setBackgroundDrawable(twob);
    	  
    	  if(v==vthree)
    		  v.setBackgroundDrawable(threeb);
    	  
    	  if(v==vfour)
    		  v.setBackgroundDrawable(fourb);
        
        break;
      case DragEvent.ACTION_DRAG_EXITED:
    	  //change background to normal
    	  LinearLayout container2 = (LinearLayout) v;
    	  if (container2.getChildCount() == 0 &&v== vone)
    		  v.setBackgroundDrawable(one);
    	  
    	  if (container2.getChildCount() == 0 &&v==vtwo)
    		  v.setBackgroundDrawable(two);
    	  
    	  if(container2.getChildCount() == 0 &&v==vthree)
    		  v.setBackgroundDrawable(three);
    	  
    	  if(container2.getChildCount() == 0 &&v==vfour)
    		  v.setBackgroundDrawable(four);
        
        break;
      case DragEvent.ACTION_DROP:
        // Dropped, reassign View to ViewGroup
        View view = (View) event.getLocalState();
        View bank =  findViewById(R.id.bank);
        
        LinearLayout container = (LinearLayout) v;
        
        //check for no objects in the group and it not being the bank
        ViewGroup owner = (ViewGroup) view.getParent();
        if ((container.getChildCount() == 0 && v!=bank)|| v==bank){
        	//reassign view
        	owner.removeView(view);	
        	container.addView(view);
        	view.setVisibility(View.VISIBLE);
        }
        else
        {
        	view.setVisibility(View.VISIBLE);
        }
        break;
      case DragEvent.ACTION_DRAG_ENDED:
    	  //when done dragging, reset background
    	  LinearLayout container3 = (LinearLayout) v;
    	  if (container3.getChildCount() == 0 &&v== vone)
    		  v.setBackgroundDrawable(one);
    	  
    	  if (container3.getChildCount() == 0 &&v==vtwo)
    		  v.setBackgroundDrawable(two);
    	  
    	  if(container3.getChildCount() == 0 &&v==vthree)
    		  v.setBackgroundDrawable(three);
    	  
    	  if(container3.getChildCount() == 0 &&v==vfour)
    		  v.setBackgroundDrawable(four);
      default:
        break;
      }
      return true;
    }
  }
  
  /**
   * dragAnswer
   * calculates the answer
   * @param view: the view selected
   */
  public void dragAnswer(View view)
  {
	  //calculate time taken
	  t = System.currentTimeMillis() - startTime;
	  
	  //set layouts and views
	  LinearLayout one = (LinearLayout) findViewById(R.id.topleft);
	  LinearLayout two = (LinearLayout) findViewById(R.id.topright);
	  LinearLayout three = (LinearLayout) findViewById(R.id.bottomleft);
	  LinearLayout four = (LinearLayout) findViewById(R.id.bottomright);
	  
	  View answerOne = findViewById(R.id.myimage1);
	  View answerTwo = findViewById(R.id.myimage2);
	  View answerThree = findViewById(R.id.myimage3);
	  View answerFour = findViewById(R.id.myimage4);
	  
	  //check for correct answer
	  if (one.indexOfChild(answerOne) != -1)
	  {
		  numCorrect++;
	  }
	  
	  if (two.indexOfChild(answerTwo) != -1)
	  {
		  numCorrect++;
	  }
	
	  if (three.indexOfChild(answerThree) != -1)
	  {
		  numCorrect++;
	  }
	 
	  if (four.indexOfChild(answerFour) != -1)
	  {
		  numCorrect++;
	  }
	  
	  //write the number correct and elapsed time into file
	  Long qTime = new Long(t);
	  Intent myIntent = new Intent(this,DisplayMessageActivity.class);
	  myIntent.putExtra(EXTRA_MESSAGE,"Number correct: "+numCorrect + "\n Elapsed Time: " + qTime.intValue()/100);
	  String ster = "Drag and Drop Number correct: "+numCorrect + "\n Elapsed Time: " + qTime.intValue()/100;
	  FileOutputStream outputStream;
		String filename = "data";
		try {
			  outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
			  outputStream.write(ster.getBytes());
			  outputStream.close();
			} catch (Exception e) {
			  e.printStackTrace();
			}
	  startActivity(myIntent);
  }
  /**
   * reset
   * @param view: the view selected
   * resets the images
   */
  public void reset(View view){
	  Intent intent = new Intent(this, DragActivity.class);
  	  startActivity(intent);
	  
  }
} 