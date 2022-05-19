/**
 * 
 */
package poker;

/**
 * @author mathewacarter29
 * Driver for the Poker program
 */
public class Driver {

	/**
	 * Main method for Poker program
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Deck deck = new Deck();
		deck.shuffle();
		System.out.print(deck.toString() + "\n\n");

		Card[] cards1 = {deck.pop(), deck.pop(), deck.pop(), deck.pop(), deck.pop()};
		Hand hand1 = new Hand(cards1);
		System.out.print(hand1.toString() + "\n\n");
		System.out.print(hand1.printHand() + "\n\n");		
		
		Card[] cards2 = {deck.pop(), deck.pop(), deck.pop(), deck.pop(), deck.pop()};
		Hand hand2 = new Hand(cards2);
		System.out.print(hand2.toString() + "\n\n");
		System.out.print(hand2.printHand() + "\n\n");	
		int comp = hand1.compareTo(hand2);
		if (comp > 0) {
			System.out.println("Hand 1 > Hand 2: compareTo value = " + comp);
		}
		else if (comp < 0) {
			System.out.println("Hand 1 < Hand 2: compareTo value = " + comp);
		}
		else {
			System.out.println("Hands are identical");
		}

	}

}
