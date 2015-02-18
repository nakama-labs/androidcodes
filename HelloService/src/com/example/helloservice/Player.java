package com.example.helloservice;

import java.util.LinkedList;

import android.util.Log;

public class Player
{

	private static int spades = 0;
	private static int clubs = 1;
	private static int hearts = 2;
	private static int diamonds = 3;
	private static int joker = 4;
	
	private static int decks;
	private static int jokers_per_deck;
	
	private LinkedList<Integer> my_cards = new LinkedList<Integer>();
	
	public Player(int no_decks, int jokers_pd)
	{
		Log.e("Player", "Player constructor called");
		my_cards.clear();
		decks = no_decks;
		jokers_per_deck = jokers_pd;
	}

	public LinkedList<Integer> get_my_cards()
	{
		return my_cards;
	}

	public void set_my_cards(int card)
	{
		my_cards.add(card);
	}
	
	public void print_my_cards()
	{
		for(int i=0; i<my_cards.size(); i++)
		{
			Log.e("Player",my_cards.get(i).toString());
		}
	}
	
	public void find_card_type(int card)
	{
		int card_type;
		int card_value = card%13;
		if(card > (decks*52))
			card_type = joker;
		else
		{
			card_type = (card/13)%4;
			if(card_value == 0)
			{
				if(card_type == 0)
					card_type = 3;
				else
					card_type--; 
			}
			/* King Spade = 13 which would result card type = 1, card value = 0. Hence the --*/
		}
		
		if(card_type == spades)
		{
			Log.e("Suit","Spades");
		}
		else if(card_type == clubs)
		{
			Log.e("Suit","Clubs");
		}
		else if(card_type == hearts)
		{
			Log.e("Suit","Hearts");
		}
		else if(card_type == diamonds)
		{
			Log.e("Suit","Diamonds");
		}
		else if(card_type == joker)
		{
			Log.e("Suit", "joker");
		}
		else
		{
			Log.e("Suit","Invalid");
		}
		
		if((card_value > 0) && (card_value <= 10))
		{
			Log.e("Card",Integer.toString(card_value));
		}
		if(card_value == 0)
			Log.e("Card","King");
		if(card_value == 11)
			Log.e("Card","Jack");
		if(card_value == 12)
			Log.e("Card","Queen");
	}
	
	int play_card(int index)
	{
		int card = -1;
		try
		{
			if((index >= 0) && (index < my_cards.size()))
				card = my_cards.remove(index);
			else
				card = -1;
			return card;
		}
		catch(Exception ex)
		{
			Log.e("Player",ex.toString());
			return -1;
		}
	}

}
