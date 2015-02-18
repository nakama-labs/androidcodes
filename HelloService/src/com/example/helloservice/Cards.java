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
	private static LinkedList<Integer> open_cards = new LinkedList<Integer>();
	
	public void build_deck()
	{
		int maxcards = decks * (52 + jokers_per_deck);
		/* Build a fresh deck */
		deck.clear();
		open_cards.clear();
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
	
	public void shuffle_open_cards()
	{
		int maxcards = open_cards.size();
		try
		{
			Random rand = new Random();
	
		    // nextInt is normally exclusive of the top value,
			for(int i=(maxcards-1); i>0; i--)
			{
				int randomIndex = rand.nextInt(maxcards);
				int last = open_cards.get(i);
				int candidate = open_cards.get(randomIndex);
				/* Swap last element with the random index. */			
				open_cards.set(i, candidate);
				open_cards.set(randomIndex, last);
			}
			Log.e("Shuffle","Total cards : "+ open_cards.size());
			String toprint = "";
			for(int i=0; i<open_cards.size(); i++)
			{
				toprint += open_cards.get(i)+ ",";
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
	
	public void set_open_card()
	{
		try
		{
			if(deck.isEmpty() != true)
			{		
				open_cards.addFirst(deck.remove());		
			}
		}
		catch(Exception ex)
		{
			Log.e("Card",ex.toString());
		}
	}
	
	void rebuild_deck_from_open_cards()
	{
		int open_card = open_cards.remove();
		deck.clear();
		
		//Not sure if the next two lines will work. Commenting out as of now. 
		//deck = (LinkedList<Integer>) open_cards.clone();
		//open_cards.clear();
		
		//Doing normal loop and copy for time being
		while(open_cards.size() > 0)
		{
			deck.add(open_cards.remove());
		}
		/* Reset the latest open card as open card */
		open_cards.add(open_card);
	}
	
	int draw_card_from_deck()
	{
		int card = -1;
		if(deck.isEmpty() != true)
		{
			 card = deck.remove();
		}
		/* If the last card has been drawn, shuffle open cards and make it the deck */
		if(deck.isEmpty() == true)
		{
			rebuild_deck_from_open_cards();
		}
		return card;
	}
	
	int draw_open_card()
	{
		int card = -1;
		if(open_cards.isEmpty() != true)
		{
			 card = open_cards.remove();
		}
		return card;
	}
}