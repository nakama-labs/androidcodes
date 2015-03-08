package com.labs.nakama.fivecard;

import android.util.Log;

public class Human extends Player
{
	public Human(int no_of_decks, int jokers_per_deck)
	{
		super(no_of_decks, jokers_per_deck);
		Log.e("Human","This is human class");
	}
}
