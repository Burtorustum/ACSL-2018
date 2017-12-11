import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class ACSL1_Aburustum
{
    final static char[] CARDS = {'\u0000','\u0000','2','3','4','5','6','7','8','9','T','J','Q','K','A'};
    static final int BITS_PER_BYTE = 8;

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        int points, begPoints;
        boolean running;
        boolean p1Turn = true;
        char curCard;

        System.out.println("Please input the starting hands, COMMA AND SPACE DELIMITED. For example (without the tick marks): '8, 9, Q, 6, 7, K, A, 5, 9, 8'");
        String input = reader.nextLine().toUpperCase();
        char[] hand1, hand2;
        final char[] originalHand1 = parseInput(input.substring(0, input.length()/2));
        final char[] originalHand2 = parseInput(input.substring(input.length()/2));

        System.out.println("Please input your first game, beginning with the current point total and " +
                "followed by the cards in the order they would be drawn. COMMA AND SPACE DELIMITED as before.");
        do {
            input = reader.nextLine();
            points = Integer.valueOf(input.substring(0,input.indexOf(',')));
            input = input.substring(input.indexOf(',')+2);
            p1Turn = true;
            hand1 = originalHand1.clone();
            hand2 = originalHand2.clone();

            while (points <= 99) {
                begPoints = points;
                hand1 = sortHand(hand1);
                hand2 = sortHand(hand2);

                //for debugging
                //System.out.println(input);
                //System.out.println("Hand1: " + Arrays.toString(hand1));
                //System.out.println("Hand2: " + Arrays.toString(hand2));

                if (p1Turn){ curCard = hand1[2]; }
                else { curCard = hand2[2]; }

                if (curCard == '9') {}
                else if (curCard == 'T') {
                    points -= 10;
                } else if (curCard == '7') {
                    if (points + 7 <= 99){
                        points += 7;
                    } else {
                        points += 1;
                    }
                } else {
                    points += getPoints(curCard);
                }

                if ((points <= 33 && begPoints >= 34) || (points <= 55 && begPoints >= 56) || (points <= 77 && begPoints >= 78) ||
                        (begPoints <= 33 && points >= 34) || (begPoints <= 55 && points >= 56) || (begPoints <= 77 && points >= 78)) {
                    points += 5;
                }
                try {
                    if (p1Turn) {
                        hand1[2] = input.charAt(0);
                        input = input.substring(input.indexOf(',')+2);
                    } else {
                        hand2[2] = input.charAt(0);
                        input = input.substring(input.indexOf(',')+2);
                    }
                } catch (IndexOutOfBoundsException e){/*was used in debugging*/}

                p1Turn = !p1Turn;
            }
            System.out.println(points + ", " + (p1Turn ? "Player #1" : "Player #2"));

            System.out.println("\nWould you like to continue inputting games? Y/N");
            input = reader.nextLine();
            if (input.toLowerCase().equals("n")) {
                running = false;
            } else if (input.toLowerCase().equals("y")) {
                System.out.println("Please input your next game.");
                running = true;
            } else {
                running = true;
                System.out.println("That is not a valid input so please input your next game.");
            }
        } while (running);

        System.out.println("All done. Goodbye.");
    }

    //Take a 5 length string input and split it into a hand, removing commas and spaces as necessary
    private static char[] parseInput(String input) {
        char[] charInput = input.toCharArray();
        char[] hand = new char[5];
        int counter = 0;
        for (char character: charInput) {
            if (character != ',' && character != ' ') {
                hand[counter++] = character;
            }
        }
        return hand;
    }

    //Take a char and returns its point value
    private static int getPoints(char input){
        for (int i = 0; i < CARDS.length; i++){
            if (input == CARDS[i]) {
                return i;
            }
        }
        return 0;
    }

    //Take a char[] and sort it by point value
    private static char[] sortHand(char[] hand) {
        int[] points = new int[5];
        for (int i = 0; i < hand.length; i++) {
            points[i] = getPoints(hand[i]);
        }
        points = sort(points);
        for (int i = 0; i < hand.length; i++) {
            hand[i] = CARDS[points[i]];
        }
        return hand;
    }

    /**
     * Rearranges the array of 32-bit integers in ascending order.
     * This is about 2-3x faster than Arrays.sort().
     *
     * @param a the array to be sorted
     */
    public static int[] sort(int[] a) {
        final int BITS = 32;                 // each int is 32 bits
        final int R = 1 << BITS_PER_BYTE;    // each bytes is between 0 and 255
        final int MASK = R - 1;              // 0xFF
        final int w = BITS / BITS_PER_BYTE;  // each int is 4 bytes

        int n = a.length;
        int[] aux = new int[n];

        for (int d = 0; d < w; d++) {

            // compute frequency counts
            int[] count = new int[R+1];
            for (int i = 0; i < n; i++) {
                int c = (a[i] >> BITS_PER_BYTE*d) & MASK;
                count[c + 1]++;
            }

            // compute cumulates
            for (int r = 0; r < R; r++)
                count[r+1] += count[r];

            // for most significant byte, 0x80-0xFF comes before 0x00-0x7F
            if (d == w-1) {
                int shift1 = count[R] - count[R/2];
                int shift2 = count[R/2];
                for (int r = 0; r < R/2; r++)
                    count[r] += shift1;
                for (int r = R/2; r < R; r++)
                    count[r] -= shift2;
            }

            // move data
            for (int i = 0; i < n; i++) {
                int c = (a[i] >> BITS_PER_BYTE*d) & MASK;
                aux[count[c]++] = a[i];
            }

            // copy back
            for (int i = 0; i < n; i++)
                a[i] = aux[i];
        }
        return a;
    }

}
