package com.example.helloservice;

import java.util.LinkedList;
import java.util.Random;

import android.util.Log;

public class Cards
{
	private static int spades = 0;
	private static int clubs = 1;
	private static int hearts = 2;
	private static int diamonds = 3;
	private static int joker = 4;
	
	private static int decks = 1;
	private static int jokers_per_deck = 2; 
	private static LinkedList<Integer> deck = new LinkedList<Integer>();
	
	public void build_deck()
	{
		int maxcards = decks * (52 + jokers_per_deck);
		/* Build a fresh deck */
		deck.clear();
		for(int i=1; i<=maxcards; i++)
		{
			deck.add(i);
		}	
	}
	
	public void shuffle_deck()
	{
		int maxcards = decks * (52 + jokers_per_deck);
		try
		{
			Random rand = new Random();
	
		    // nextInt is normally exclusive of the top value,
			for(int i=(maxcards-1); i>0; i--)
			{
				int randomIndex = rand.nextInt(maxcards);
				int last = deck.get(i);
				int candidate = deck.get(randomIndex);
				/* Swap last element with the random index. */			
				deck.set(i, candidate);
				deck.set(randomIndex, last);
			}
			Log.e("Shuffle","Total cards : "+ deck.size());
			String toprint = "";
			for(int i=0; i<deck.size(); i++)
			{
				toprint += deck.get(i)+ ",";
			}
			Log.e("Shuffle", "Values are :"+toprint);
		}
		catch(Exception ex)
		{
			Log.e("Shuffle", ex.toString());
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
	
	public int deal_card()
	{
		try
		{
			if(deck.isEmpty() != true)
			{		
				int card = deck.remove();
				find_card_type(card);
				return card;
			}
			return -1;
		}
		catch(Exception ex)
		{
			Log.e("Card",ex.toString());
			return -1;
		}
	}	
}