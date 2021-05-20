package poker;

public class Card {
	
	
	private int denom;
	private String suit;
	
	/**
	 * Constructor for Card taking a suit and denomination
	 * @param denom 	Denomination of the card
	 * @param suit		Suit of the card
	 * @throws Exception for invalid suit or denomination
	 */
	public Card(int denom, String suit) {
		if (2 <= denom && denom <= 14) {
			this.denom = denom;
		}
		
		if (suit.equals("H") || suit.equals("C") || suit.equals("S") || suit.equals("D")) {
			this.suit = suit;
		}
		
	}
	
	public Card(String denom, String suit) {
		if (suit.equals("H") || suit.equals("C") || suit.equals("S") || suit.equals("D")) {
			this.suit = suit;
		}
		else {
			System.out.println("Invalid input.");
			System.exit(1);
		}
		
		if (denom.equals("T")) {
			this.denom = 10;
		}
		else if (denom.equals("J")) {
			this.denom = 11;
		}
		else if (denom.equals("Q")) {
			this.denom = 12;
		}
		else if (denom.equals("K")) {
			this.denom = 13;
		}
		else if (denom.equals("A")) {
			this.denom = 14;
		}
		else if (Integer.parseInt(denom) >= 2 && Integer.parseInt(denom) <= 9) {
			this.denom = Integer.parseInt(denom);
		}
		else {
			System.out.println("Invalid input.");
			System.exit(1);
		}
		
	}
	
	/**
	 * Getter for the denomination
	 * @return	The denomination of the card
	 */
	public int getDenom() {
		return this.denom;
	}
	
	/**
	 * Getter for the suit of the card
	 * @return	The suit
	 */
	public String getSuit() {
		return this.suit;
	}
	
	/**
	 * Prints representation of denomination
	 * Useful for face card representation
	 * @return	String of denomination
	 */
	public String printDenom() {
		return this.toString().substring(0, 1);
	}
	
	/**
	 * String representation of hand in form (DENOM)(SUIT)
	 */
	public String toString() {
		String result = "";
		if (this.denom == 14) {
			result += "A";
		}
		else if (this.denom == 13) {
			result += "K";
		}
		else if (this.denom == 12) {
			result += "Q";
		}
		else if (this.denom == 11) {
			result += "J";
		}
		else if (this.denom == 10) {
			result += "T";
		}
		else {
			result += this.denom;
		}
		result += this.suit;
		return result;
	}
	
	/**
	 * Equals method for cards
	 * @param card	The card being compared to this one
	 * @return	TRUE if cards have same suit and denomination, FALSE otherwise
	 */
	public boolean equals(Card card) {
		return (this.suit.equals(card.getSuit()) && this.denom == card.getDenom());
	}
}
