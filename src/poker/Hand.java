/**
 * 
 */
package poker;

/**
 * @author mathewacarter29
 * Class representing a poker hand
 */
public class Hand {

	private Card[] cards;
	private static final int NUM_CARDS = 5;
	private int[] denoms;
	private String[] suits;
	private int handRank;
	private static final String[] HAND_RANKS = 
			{"High Card", 
			"Pair", 
			"Two Pair", 
			"Three of a Kind",
			"Straight", 
			"Flush", 
			"Full House", 
			"Four of a Kind", 
			"Straight Flush"};
	
	/**
	 * Constructor for card hand
	 * Pre: Cards array is of size NUM_CARDS (5)
	 * @param cards		Array of 5 Cards
	 */
	public Hand(Card[] cards) {
		this.cards = cards;
		this.orderHand();
		this.getDenomsAndSuits(cards);
		this.findHandRank();
	}
	
	/**
	 * Constructor for card hand given an array of strings representing cards
	 * @param cards		Array of strings representing cards
	 */
	public Hand(String[] cards) {
		if (cards.length != NUM_CARDS) {
			System.exit(1);
		}
		Card[] tempCards = new Card[5];
		for (int i = 0; i < NUM_CARDS; i++) {
			assert(cards[i].length() == 2);
			tempCards[i] = new Card(Character.toString(cards[i].charAt(0)), 
					Character.toString(cards[i].charAt(1)));
		}
		this.cards = tempCards;
		this.orderHand();
		this.getDenomsAndSuits(this.cards);
		this.findHandRank();
	}
	
	/**
	 * Method that creates the denoms and suits field variables
	 * @param cards	Array of card objects
	 */
	private void getDenomsAndSuits(Card[] cards) {
		this.denoms = new int[NUM_CARDS];
		this.suits = new String[NUM_CARDS];
		for (int i = 0; i < NUM_CARDS; i++) {
			this.denoms[i] = cards[i].getDenom();
			this.suits[i] = cards[i].getSuit();
		}
	}
	
	/**
	 * Makes the hand ordered from low card
	 * to high card using an Insertion Sort
	 */
	private void orderHand() {
		int j, key;
		Card currCard;
		for (int i = 1; i < NUM_CARDS; i++) {
			j = i - 1;
			key = cards[i].getDenom();
			currCard = cards[i];
			
			while (j > -1 && key < cards[j].getDenom()) {
				cards[j + 1] = cards[j];
				j--;
			}
			cards[j + 1] = currCard;
		}
	}
	
	/**
	 * Sets the field variable handRank
	 */
	private void findHandRank() {
		if (this.checkStraightFlush()) {
			this.handRank = 8;
		}
		else if (this.checkFourKind()) {
			this.handRank = 7;
		}
		else if (this.checkFullHouse()) {
			this.handRank = 6;
		}
		else if (this.checkFlush()) {
			this.handRank = 5;
		}
		else if (this.checkStraight()) {
			this.handRank = 4;
		}
		else if (this.checkThreeKind()) {
			this.handRank = 3;
		}
		else if (this.checkTwoPair()) {
			this.handRank = 2;
		}
		else if (this.checkPair()) {
			this.handRank = 1;
		}
		else {
			this.handRank = 0;
		}
	}
	
	/**
	 * Getter for cards in hand
	 * @return	Array of Cards in the hand
	 */
	public Card[] getCards() {
		return this.cards;
	}
	
	/**
	 * Getter for denoms
	 * @return	Denoms array
	 */
	public int[] getDenoms() {
		return this.denoms;
	}
	
	/**
	 * Returns a string representation of the Hand
	 */
	public String toString() {
		String result = "";
		for (int i = 0; i < NUM_CARDS; i++) {
			result += cards[i] + "\t";
		}
		return result;
	}
	
	/**
	 * Getter for the rank of the hand
	 * @return	The int representation of the hand rank
	 */
	public int getHandRank() {
		return this.handRank;
	}
	
	/**
	 * Checks if hand is just based on High Card
	 * @return	True if hand is nothing but high card
	 */
	public boolean checkHighCard() {
		return !(this.checkStraightFlush() || this.checkFourKind() || this.checkFullHouse()
				|| this.checkFlush() || this.checkStraight() || this.checkThreeKind()
				|| this.checkTwoPair() || this.checkPair());
	}
	
	/**
	 * Checks if the hand is a pair
	 * @return True if hand is a pair
	 */
	public boolean checkPair() {
		for (int i = 1; i < NUM_CARDS; i++) {
			if (denoms[i] == denoms[i - 1]) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Checks if the hand is a 2 pair
	 * @return True if hand is a 2 pair
	 */
	public boolean checkTwoPair() {
		int pairCount = 0;
		int denom = 0;
		for (int i = 1; i < NUM_CARDS; i++) {
			if (denoms[i] == denoms[i - 1] && denoms[i] != denom) {
				pairCount++;
				denom = denoms[i];
			}
		}
		return (pairCount == 2);
	}
	
	/**
	 * Checks if the hand is a 3 of a kind
	 * @return True if hand is a 3 of a kind
	 */
	public boolean checkThreeKind() {
		for (int i = 2; i < NUM_CARDS; i++) {
			if (denoms[i] == denoms[i - 1] && denoms[i] == denoms[i - 2]) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Checks if the hand is a straight
	 * @return	True if hand is a straight
	 */
	public boolean checkStraight() {
		for (int i = 1; i < NUM_CARDS; i++) {
			if (denoms[i] != (denoms[i-1] + 1)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Checks if the hand is a flush
	 * @return True if hand is a flush
	 */
	public boolean checkFlush() {
		String suit = suits[0];
		for (int i = 1; i < NUM_CARDS; i++) {
			if (!suit.equals(suits[i])) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Checks if the hand is a full house
	 * @return True if hand is a full house
	 */
	public boolean checkFullHouse() {
		return this.checkThreeKind() && this.checkTwoPair();
	}
	
	/**
	 * Checks if the hand is a 4 of a kind
	 * @return True if hand is a 4 of a kind
	 */
	public boolean checkFourKind() {
		for (int i = 3; i < NUM_CARDS; i++) {
			if (denoms[i] == denoms[i - 1] && denoms[i] == denoms[i - 2] 
					&& denoms[i] == denoms[i - 3]) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Checks if hand is a straight flush
	 * @return	True if hand is straight flush
	 */
	public boolean checkStraightFlush() {
		return (this.checkFlush() && this.checkStraight());
	}
	
	/**
	 * Prints the rank of the hand
	 * @return	String representation of strength of the hand
	 */
	public String printHand() {
		return HAND_RANKS[this.handRank];
	}
	
	/**
	 * Compares two hands
	 * @param hand		The hand being compared to this one
	 * @return	Positive number if this hand is better,
	 * 			negative number if parameter hand is better,
	 * 			0 if both hands are identical.
	 */
	public int compareTo(Hand hand) {
		int compare = this.handRank - hand.getHandRank();
		if (compare != 0) {
			return compare;
		}
		
		int[] handDenoms = hand.getDenoms();
		int thisDenom = 0;
		int handDenom = 0;
		
		//Compares two straights, flushes, straight flushes or hands with only high card
		if (this.checkStraightFlush() || this.checkFlush() || this.checkStraight()
				|| this.checkHighCard()) {
			for (int i = NUM_CARDS - 1; i >= 0; i--) {
				compare = denoms[i] - handDenoms[i];
				if (compare != 0) {
					return compare;
				}
			}
			return compare;
		}
		//Compares 2 hands with 4 of a kind
		else if (this.checkFourKind()) {
			
			for (int i = 3; i < NUM_CARDS; i++) {
				if (denoms[i] == denoms[i - 1] && denoms[i] == denoms[i - 2] 
						&& denoms[i] == denoms[i - 3]) {
					thisDenom = denoms[i];
				}
				if (handDenoms[i] == handDenoms[i - 1] && handDenoms[i] == handDenoms[i - 2] 
						&& handDenoms[i] == handDenoms[i - 3]) {
					handDenom = handDenoms[i];
				}
			}
			compare = thisDenom - handDenom;
			if (compare != 0) {
				return compare;
			}
			else {
				return (this.denoms[0] - handDenoms[0]) + 
						(this.denoms[NUM_CARDS - 1] - handDenoms[NUM_CARDS - 1]);
			}
		}
		//Compares 2 hands with full houses
		else if (this.checkFullHouse()) {
			//Checks which hand has bigger 3 of a kind
			for (int i = 2; i < NUM_CARDS; i++) {
				if (denoms[i] == denoms[i - 1] && denoms[i] == denoms[i - 2]) {
					thisDenom = denoms[i];
				}
				if (handDenoms[i] == handDenoms[i - 1] && handDenoms[i] == handDenoms[i - 2]) {
					handDenom = handDenoms[i];
				}
			}
			compare = thisDenom - handDenom;
			if (compare != 0) {
				return compare;
			}
			
			//If both hands have same 3 of a kind, check pair
			else {
				int topDenom = thisDenom;
				if (denoms[0] == topDenom) {
					thisDenom = denoms[4];
					handDenom = handDenoms[4];
				}
				else {
					thisDenom = denoms[0];
					handDenom = handDenoms[0];
				}
				return thisDenom - handDenom;
			}
		}
		//Compares 2 hands with 3 of a kind
		else if (this.checkThreeKind()) {
			
			for (int i = 2; i < NUM_CARDS; i++) {
				if (denoms[i] == denoms[i - 1] && denoms[i] == denoms[i - 2]) {
					thisDenom = denoms[i];
				}
				if (handDenoms[i] == handDenoms[i - 1] && handDenoms[i] == handDenoms[i - 2]) {
					handDenom = handDenoms[i];
				}
			}
			compare = thisDenom - handDenom;
			if (compare != 0) {
				return compare;
			}
			else {
				for (int i = NUM_CARDS - 1; i >= 0; i--) {
					if (denoms[i] != thisDenom) {
						compare = denoms[i] - handDenoms[i];
						if (compare != 0) {
							return compare;
						}
					}
				}
				return compare;
			}
		}
		//Compares 2 hands with 2 pair
		else if (this.checkTwoPair()) {
			int[] this2PairDenoms = new int[2];
			int[] hand2PairDenoms = new int[2];
			int thisI = 0;
			int handI = 0;
			for (int i = 1; i < NUM_CARDS; i++) {
				if (denoms[i] == denoms[i - 1]) {
					this2PairDenoms[thisI] = denoms[i];
					thisI++;
				}
				if (handDenoms[i] == handDenoms[i - 1]) {
					hand2PairDenoms[handI] = handDenoms[i];
					handI++;
				}
			}
			for (int i = 1; i >= 0; i--) {
				compare = this2PairDenoms[i] - hand2PairDenoms[i];
				if (compare != 0) {
					return compare;
				}
			}
			for (int i = NUM_CARDS - 1; i >= 0; i--) {
				compare = denoms[i] - handDenoms[i];
				if (compare != 0) {
					return compare;
				}
			}
			return compare;
		}
		//Compares two hands with a pair
		else {
			for (int i = 1; i < NUM_CARDS; i++) {
				if (handDenoms[i] == handDenoms[i-1]) {
					handDenom = handDenoms[i];
				}
				if (denoms[i] == denoms[i-1]) {
					thisDenom = denoms[i];
				}
			}
			compare = thisDenom - handDenom;
			if (compare == 0) {
				for (int i = NUM_CARDS - 1; i >= 0; i--) {
					compare = denoms[i] - handDenoms[i];
					if (compare != 0) {
						return compare;
					}
				}
			}
			return compare;
		}
		
	}
}