package com.example.helloservice;

import android.util.Log;

public class AI_Difficulty extends Player
{
	static String val="hello";
	String newval;
	
	public AI_Difficulty(int no_of_decks, int jokers_per_deck)
	{
		super(no_of_decks, jokers_per_deck);
	}
	public void setnewval(String str)
	{
		try
		{
			newval = str;
		}
		catch(Exception ex)
		{
			Log.e("AI_DIFFICULTY",ex.toString());
		}
	}
	
	public String getnewval()
	{
		try
		{
			return newval;
		}
		catch(Exception ex)
		{
			Log.e("AI_DIFFICULTY",ex.toString());
			return "";
		}
	}
}

