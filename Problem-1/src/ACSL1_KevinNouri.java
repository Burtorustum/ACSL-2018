
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class ACSL1_KevinNouri {
	
	static ArrayList<Integer> defaultPlayer1hand = new ArrayList<Integer>();
	static ArrayList<Integer> defaultPlayer2hand = new ArrayList<Integer>();

	public static void main(String []args ) {
		System.out.println("Welcome to my ACSL solution. When entering input, please separate each item with commas.");
		System.out.println("In addition, please do not include line numbers such as '#1.'");
		System.out.println("For example: 8,9,Q,6,7,K,A,5,9,8\n");
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter the first line of input with each item separated by commas: ");
		String input1 = scanner.nextLine();
		System.out.println("Please enter the second line of input with each item separated by commas: ");
		String input2 = scanner.nextLine();
		System.out.println("Please enter the third line of input with each item separated by commas: ");
		String input3 = scanner.nextLine();
		System.out.println("Please enter the fourth line of input with each item separated by commas: ");
		String input4 = scanner.nextLine();
		System.out.println("Please enter the fifth line of input with each item separated by commas: ");
		String input5 = scanner.nextLine();
		System.out.println("Please enter the sixth line of input with each item separated by commas: ");
		String input6 = scanner.nextLine();

		//String input1 = "8,9,Q,6,7,K,A,5,9,8";
		//String input2 = "75,J,7,Q,T,A,6,2,3,4,5";
		//String input3 = "50,7,K,T,8,3,Q,9,7,2,3";
		//String input4 = "63,3,6,8,T,7,7,T,3,5,8";
		//String input5 = "79,A,9,7,T,A,9,T,A,6,4";
		//String input6 = "50,A,T,Q,A,T,K,J,T,A,7";

		String[] cards = input1.replace("T","10").replace("J","11").replace("Q","12").replace("K","13").replace("A","14").replace(" ","").split(",");
				

		for(int i = 0; i < cards.length; i++) {
			if (i < 5) {
				defaultPlayer1hand.add(Integer.parseInt(cards[i]));
			} else {
				defaultPlayer2hand.add(Integer.parseInt(cards[i]));
			}				
		}
		System.out.println("\n");
		play(input2);
		play(input3);
		play(input4);
		play(input5);
		play(input6);

		

	}
	
	public static Integer getMedian(ArrayList<Integer> hand) {
		Collections.sort(hand);
		//System.out.println(hand);
		int med = hand.get(2);
		hand.remove(2);
		return med;
	}
	
	public static void play(String input) {
		
		ArrayList<Integer> Player1hand = new ArrayList<Integer>(defaultPlayer1hand);
		ArrayList<Integer> Player2hand = new ArrayList<Integer>(defaultPlayer2hand);
		
		
		String[] cards = input.replace("T","10").replace("J","11").replace("Q","12").replace("K","13").replace("A","14").replace(" ","").split(",");
		int pointTotal = Integer.parseInt(cards[0]);
		ArrayList<Integer> pile = new ArrayList<Integer>();
		for (int i = 1; i < cards.length; i++) {
			pile.add(Integer.parseInt(cards[i]));
		}
		Boolean player1Turn = true;
		
		while(pointTotal <= 99) {
			int playedCardValue;
			if(player1Turn) { 
				playedCardValue = getMedian(Player1hand);
				if(pile.size() > 0) {
					Player1hand.add(pile.get(0));
					pile.remove(0);
				}
			} else {
				playedCardValue = getMedian(Player2hand);
				if(pile.size() > 0) {
					Player2hand.add(pile.get(0));
					pile.remove(0);
				}
			}
			
			int ogPointTotal = pointTotal;
			
			if (playedCardValue == 9){
				//do nothing
			} else if(playedCardValue == 10){
				pointTotal -= 10;
			} else if(playedCardValue == 7) { 
				if (pointTotal < 93) {
					pointTotal += 7;
				} else { 
					pointTotal += 1;
				}
			}else {
				pointTotal += playedCardValue;
			}
			
			if(ogPointTotal < 34 && pointTotal > 33)  
				pointTotal += 5;
			if (ogPointTotal > 33 && pointTotal < 34)
				pointTotal += 5;
			if(ogPointTotal < 56 && pointTotal > 55)  
				pointTotal += 5;
			if (ogPointTotal > 55 && pointTotal < 56)
				pointTotal += 5;
			if(ogPointTotal < 78 && pointTotal > 77)  
				pointTotal += 5;
			if (ogPointTotal > 77 && pointTotal < 78)
				pointTotal += 5;
			
		
			
			
			
			//System.out.println("Original point total = " + ogPointTotal + " New Point Total : " + pointTotal + " Played Card Value: " + playedCardValue + " Player1Turn: " + player1Turn);
			
			player1Turn = !player1Turn;
			
		}
		
		String winner = player1Turn ? "Player #1": "Player #2";
		
		System.out.println(pointTotal + ", " + winner);
		
	}
}
