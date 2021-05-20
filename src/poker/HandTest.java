/**
 * 
 */
package poker;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * @author mathewacarter
 *	Test class for Hand
 */
class HandTest {

	Hand hand = new Hand(new String[]{"TH", "4C", "AD", "7D", "TD"});
	/**
	 * Test method for {@link poker.Hand#getCards()}.
	 */
	@Test
	void testGetCards() {
		Card[] cards = hand.getCards();
		assertTrue(cards[0].equals(new Card(4, "C")));
		assertTrue(cards[1].equals(new Card(7, "D")));
		assertTrue(cards[2].equals(new Card(10, "H")));
		assertTrue(cards[3].equals(new Card(10, "D")));
		assertTrue(cards[4].equals(new Card("A", "D")));
	}

	/**
	 * Test method for {@link poker.Hand#getDenoms()}.
	 */
	@Test
	void testGetDenoms() {
		int[] denoms = hand.getDenoms();
		assertEquals(4, denoms[0]);
		assertEquals(7, denoms[1]);
		assertEquals(10, denoms[2]);
		assertEquals(10, denoms[3]);
		assertEquals(14, denoms[4]);
	}

	/**
	 * Test method for {@link poker.Hand#toString()}.
	 */
	@Test
	void testToString() {
		Card card = hand.getCards()[1];
		assertEquals("7D", card.toString());
	}

	/**
	 * Test method for {@link poker.Hand#getHandRank()}.
	 */
	@Test
	void testGetHandRank() {
		//High Card
		Hand hand1 = new Hand(new String[]{"TH", "4C", "AD", "7D", "QD"});
		assertEquals(0, hand1.getHandRank());
		
		//Pair
		Hand hand2 = new Hand(new String[]{"TH", "4C", "AD", "7D", "TD"});
		assertEquals(1, hand2.getHandRank());

		//2 pair
		Hand hand3 = new Hand(new String[]{"TH", "4C", "4D", "7D", "TD"});
		assertEquals(2, hand3.getHandRank());

		//3 of a kind
		Hand hand4 = new Hand(new String[]{"TH", "TC", "AD", "7D", "TD"});
		assertEquals(3, hand4.getHandRank());

		//Straight
		Hand hand5 = new Hand(new String[]{"7H", "4C", "5D", "3D", "6D"});
		assertEquals(4, hand5.getHandRank());

		//Flush
		Hand hand6 = new Hand(new String[]{"TH", "4H", "AH", "7H", "KH"});
		assertEquals(5, hand6.getHandRank());

		//Full House
		Hand hand7 = new Hand(new String[]{"TH", "AC", "AD", "TC", "TD"});
		assertEquals(6, hand7.getHandRank());

		//4 of a kind
		Hand hand8 = new Hand(new String[]{"4H", "4C", "4D", "4S", "TD"});
		assertEquals(7, hand8.getHandRank());

		//Straight Flush
		Hand hand9 = new Hand(new String[]{"AH", "KH", "QH", "JH", "TH"});
		assertEquals(8, hand9.getHandRank());

	}

	/**
	 * Test method for {@link poker.Hand#checkHighCard()}.
	 */
	@Test
	void testCheckHighCard() {
		//High Card
		Hand hand1 = new Hand(new String[]{"TH", "4C", "AD", "7D", "QD"});
		assertTrue(hand1.checkHighCard());
	}

	/**
	 * Test method for {@link poker.Hand#checkPair()}.
	 */
	@Test
	void testCheckPair() {
		//Pair
		Hand hand2 = new Hand(new String[]{"TH", "4C", "AD", "7D", "TD"});
		assertTrue(hand2.checkPair());
	}

	/**
	 * Test method for {@link poker.Hand#checkTwoPair()}.
	 */
	@Test
	void testCheckTwoPair() {
		//2 pair
		Hand hand3 = new Hand(new String[]{"TH", "4C", "4D", "7D", "TD"});
		assertTrue(hand3.checkTwoPair());
	}

	/**
	 * Test method for {@link poker.Hand#checkThreeKind()}.
	 */
	@Test
	void testCheckThreeKind() {
		//3 of a kind
		Hand hand4 = new Hand(new String[]{"TH", "TC", "AD", "7D", "TD"});
		assertTrue(hand4.checkThreeKind());
	}

	/**
	 * Test method for {@link poker.Hand#checkStraight()}.
	 */
	@Test
	void testCheckStraight() {
		//Straight
		Hand hand5 = new Hand(new String[]{"7H", "4C", "5D", "3D", "6D"});
		assertTrue(hand5.checkStraight());
	}

	/**
	 * Test method for {@link poker.Hand#checkFlush()}.
	 */
	@Test
	void testCheckFlush() {
		//Flush
		Hand hand6 = new Hand(new String[]{"TH", "4H", "AH", "7H", "KH"});
		assertTrue(hand6.checkFlush());
	}

	/**
	 * Test method for {@link poker.Hand#checkFullHouse()}.
	 */
	@Test
	void testCheckFullHouse() {
		//Full House
		Hand hand7 = new Hand(new String[]{"TH", "AC", "AD", "TC", "TD"});
		assertTrue(hand7.checkFullHouse());
	}

	/**
	 * Test method for {@link poker.Hand#checkFourKind()}.
	 */
	@Test
	void testCheckFourKind() {
		//4 of a kind
		Hand hand8 = new Hand(new String[]{"4H", "4C", "4D", "4S", "TD"});
		assertTrue(hand8.checkFourKind());
	}

	/**
	 * Test method for {@link poker.Hand#checkStraightFlush()}.
	 */
	@Test
	void testCheckStraightFlush() {
		//Straight Flush
		Hand hand9 = new Hand(new String[]{"AH", "KH", "QH", "JH", "TH"});
		assertTrue(hand9.checkStraight());
	}
	
	/**
	 * Test method for compareTo: high card
	 */
	@Test
	void testCompareToHighCard() {
		Hand hand1 = new Hand(new String[] {"2C", "4S", "TH", "AS", "QH"});
		
		Hand hand2 = new Hand(new String[] {"2H", "4H", "TD", "KS", "QD"});
		assertTrue(hand1.compareTo(hand2) > 0);
		
		Hand hand3 = new Hand(new String[] {"2C", "4S", "TH", "KS", "AH"});
		assertTrue(hand1.compareTo(hand3) < 0);
		
		Hand hand4 = new Hand(new String[] {"2S", "4S", "TS", "AS", "QD"});
		assertEquals(0, hand1.compareTo(hand4));
	}
	
	/**
	 * Test compareTo: pair
	 */
	@Test
	void testCompareToPair() {
		Hand hand1 = new Hand(new String[] {"2C", "QS", "TH", "KS", "QH"});
		
		Hand hand2 = new Hand(new String[] {"2H", "TH", "TD", "AS", "QD"});
		assertTrue(hand1.compareTo(hand2) > 0);
		
		Hand hand3 = new Hand(new String[] {"2C", "4S", "TH", "KS", "KH"});
		assertEquals(-1, hand1.compareTo(hand3));
		assertTrue(hand1.compareTo(hand3) < 0);
		
		Hand hand4 = new Hand(new String[] {"QS", "4S", "JS", "KS", "QD"});
		assertTrue(hand1.compareTo(hand4) < 0);
		
		Hand hand5 = new Hand(new String[] {"2S", "QC", "TD", "KH", "QD"});
		assertEquals(0, hand1.compareTo(hand5));
	}
	
	/**
	 * Test compareTo: 2 pair
	 */
	@Test
	void testCompareToTwoPair() {
		Hand hand1 = new Hand(new String[]{"TH", "TC", "4D", "4H", "QD"});

		Hand hand2 = new Hand(new String[]{"9H", "9C", "4D", "4H", "QD"});
		assertTrue(hand1.compareTo(hand2) > 0);
		
		Hand hand3 = new Hand(new String[]{"TH", "TC", "2D", "2H", "QD"});
		assertTrue(hand1.compareTo(hand3) > 0);
		
		Hand hand4 = new Hand(new String[]{"TH", "TC", "4D", "4H", "AD"});
		assertTrue(hand1.compareTo(hand4) < 0);
		
		Hand hand5 = new Hand(new String[]{"TD", "TS", "4S", "4D", "QC"});
		assertEquals(0, hand1.compareTo(hand5));
	}
	
	/**
	 * Test compareTo: 3 of a kind
	 */
	@Test
	void testCompareToThreeKind() {
		Hand hand1 = new Hand(new String[]{"KH", "KC", "KD", "4H", "QD"});

		Hand hand2 = new Hand(new String[]{"7H", "7C", "7D", "4C", "QH"});
		assertTrue(hand1.compareTo(hand2) > 0);
		
		Hand hand3 = new Hand(new String[]{"KD", "KS", "KH", "4D", "JD"});
		assertTrue(hand1.compareTo(hand3) > 0);
		
		Hand hand4 = new Hand(new String[]{"KD", "KS", "KC", "4S", "QC"});
		assertEquals(0, hand1.compareTo(hand4));
	}
	
	/**
	 * Test compareTo: straight
	 */
	@Test
	void testCompareToStraight() {
		Hand hand1 = new Hand(new String[] {"8S", "9D", "TS", "JH", "QS"});
		
		Hand hand2 = new Hand(new String[] {"2S", "3H", "4D", "5C", "6D"});
		assertTrue(hand1.compareTo(hand2) > 0);
		
		Hand hand3 = new Hand(new String[] {"KS", "9H", "TC", "JD", "QC"});
		assertTrue(hand1.compareTo(hand3) < 0);
		
		Hand hand4 = new Hand(new String[] {"8C", "9H", "TC", "JD", "QC"});
		assertEquals(0, hand1.compareTo(hand4));
	}
	
	/**
	 * Test compareTo: flush
	 */
	@Test
	void testCompareToFlush() {
		Hand hand1 = new Hand(new String[] {"QS", "8S", "TS", "JS", "3S"});
		
		Hand hand2 = new Hand(new String[] {"9H", "8H", "TH", "JH", "3H"});
		assertTrue(hand1.compareTo(hand2) > 0);
		
		Hand hand3 = new Hand(new String[] {"QH", "8H", "TH", "JH", "2H"});
		assertTrue(hand1.compareTo(hand3) > 0);
		
		Hand hand4 = new Hand(new String[] {"KS", "8S", "TS", "JS", "3S"});
		assertTrue(hand1.compareTo(hand4) < 0);
		
		Hand hand5 = new Hand(new String[] {"QH", "8H", "TH", "JH", "3H"});
		assertEquals(0, hand1.compareTo(hand5));
	}
	
	/**
	 * Test compareTo: Full House
	 */
	@Test
	void testCompareToFullHouse() {
		Hand hand1 = new Hand(new String[]{"TH", "TC", "TD", "4H", "4D"});

		Hand hand2 = new Hand(new String[]{"TH", "TC", "TD", "3H", "3D"});
		assertEquals(1, hand1.compareTo(hand2));
		assertTrue(hand1.compareTo(hand2) > 0);
		
		Hand hand3 = new Hand(new String[]{"AH", "AC", "AD", "4H", "4D"});
		assertTrue(hand1.compareTo(hand3) < 0);
		
		Hand hand4 = new Hand(new String[]{"TH", "TC", "TD", "4H", "4S"});
		assertEquals(0, hand1.compareTo(hand4));
	}
	
	/**
	 * Test compareTo: 4 of a kind
	 */
	@Test
	void testCompareToFourKind() {
		Hand hand1 = new Hand(new String[] {"8S", "8D", "8C", "8H", "QS"});
		
		Hand hand2 = new Hand(new String[] {"6S", "6D", "6C", "6H", "QS"});
		assertTrue(hand1.compareTo(hand2) > 0);
		
		Hand hand3 = new Hand(new String[] {"8S", "8D", "8C", "8H", "AS"});
		assertTrue(hand1.compareTo(hand3) < 0);
		
		Hand hand4 = new Hand(new String[] {"8S", "8D", "8C", "8H", "QH"});
		assertEquals(0, hand1.compareTo(hand4));
	}
	
	/**
	 * Test compareTo: Straight Flush
	 */
	@Test
	void testCompareToStraightFlush() {
		Hand hand1 = new Hand(new String[] {"8S", "9S", "TS", "JS", "QS"});
		
		Hand hand2 = new Hand(new String[] {"8S", "9S", "TS", "JS", "7S"});
		assertTrue(hand1.compareTo(hand2) > 0);
		
		Hand hand3 = new Hand(new String[] {"8H", "9H", "TH", "JH", "QH"});
		assertEquals(0, hand1.compareTo(hand3));
	}
}
