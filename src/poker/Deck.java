/**
 * 
 */
package poker;

import java.util.ArrayList;

public class Deck {
	
	private ArrayList<Card> deck;
	private static final String[] SUITS = {"H", "C", "S", "D"};
	private static final int DECK_SIZE = 52;
	
	/**
	 * Creates a new deck of cards
	 */
	public Deck() {
		this.deck = new ArrayList<Card>();
		for (int i = 0; i < 4; i++) {
			for (int j = 2; j <= 14; j++) {
				deck.add(new Card(j, SUITS[i]));
			}
		}
	}
	
	/**
	 * Shuffles the deck of cards
	 */
	public void shuffle() {
		for (int i = 0; i < DECK_SIZE; i++) {
			int newPlace = (int)(Math.random() * 52);
			Card temp = deck.get(i);
			deck.set(i, deck.get(newPlace));
			deck.set(newPlace, temp);
		}
	}
	
	/**
	 * Prints a String representation of the deck of cards
	 */
	public String toString() {
		String result = "";
		int i = 0;
		while (i < deck.size()) {
			result += deck.get(i).toString() + "\t";	
			i++;
			if (i != 0 && i % 13 == 0) {
				result += "\n";
			}
		}
		return result;
	}
	
	public Card pop() {
		return deck.remove(0);
	}
}
