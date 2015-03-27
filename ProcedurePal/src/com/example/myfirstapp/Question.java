package com.example.myfirstapp;
/**
 * 
 * @author Dylan Chu
 * Simple java class that simulates a question with 4 answers
 *
 */
public class Question 
{
	//instance variables
	private String question;
	private String[] answers;
	private int correct;
	
	/**
	 * Default constructor
	 */
	public Question()
	{
		question = "";
		answers = new String[4];
		correct = 0;
	}
	
	/**
	 * specific constructor
	 * @param question: the text of the question
	 * @param answers: an array of the answers
	 * @param correct: the number of the correct answer in the array
	 */
	public Question(String question, String[] answers, int correct)
	{
		this.question = question;
		this.answers = answers;
		this.correct = correct;
	}
	
	//getters

	/**
	 * 
	 * @return: the text of the question
	 */
	public String getQuestion()
	{
		return question;
	}
	
	/**
	 * 
	 * @return: the answers array
	 */
	public String[] getAnswers()
	{
		return answers;
	}
	
	/**
	 * 
	 * @return: the number of the correct answer
	 */
	public int getCorrect()
	{
		return correct;
	}

}
