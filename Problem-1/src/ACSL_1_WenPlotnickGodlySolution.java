import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


enum Card {
    c2(2),
    c3(3),
    c4(4),
    c5(5),
    c6(6),
    c7(7),
    c8(8),
    c9(9),
    cT(10),
    cJ(11),
    cQ(12),
    cK(13),
    cA(14);

    public final int value;

    private Card(int value) {
        this.value = value;
    }
}

public class ACSL_1_WenPlotnick {

    public static void main(String[] args) {
        ArrayList<Card> kms = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        Tuple<Integer, ArrayList<Card>> game = new Tuple(0, null);
        Tuple<Integer, Boolean> result;
        boolean done = true;
        do {
            try {
                System.out.println("Please input the Starting Hands, using capital letters when neccesary and a comma as a delimiter");
                System.out.println("For Example, ");
                System.out.println("8,9,Q,6,7,K,A,5,9,8");
                kms = stringListToCardList(stringToString(removeWhiteSpace(input.nextLine())));
                done = false;
            } catch (ParseException rip) {
                System.out.println(rip.getMessage(true));
                done = true;
            } catch (SizeException rip) {
                System.out.println("Not 10 Long - too many or too little cards");
                done = true;
            }
        } while (done);
        Tuple<ArrayList<Card>, ArrayList<Card>> hands = createHands(kms);


        System.out.println("Please Enter the game configuration");
        System.out.println("enter in same way as before");
        System.out.print("example :  ");
        System.out.println("75,J,7,Q,T,A,6,2,3,4,5");

        done = true;
        do {
            try {
                game = stringListToDeckList(stringToString(removeWhiteSpace(input.nextLine())));
                done = false;
            } catch (ParseException rip) {
                System.out.println(rip.getMessage(true));
                done = true;
            } catch (SizeException rip) {
                System.out.println("Not right size, you are missing something. please check your inputs");
                done = true;
            }
        } while (done);

        result = playTheGameOut(hands, game);
        System.out.println("-----------------------------");

        System.out.println("RESULTS OF GAME 1");
        printResults(result);
        System.out.println("-----------------------------");
        hands = createHands(kms);
        System.out.println("Please Enter the game configuration");
        System.out.println("enter in same way as before");
        System.out.print("example :  ");
        System.out.println("75,J,7,Q,T,A,6,2,3,4,5");

        done = true;
        do {
            try {
                game = stringListToDeckList(stringToString(removeWhiteSpace(input.nextLine())));
                done = false;
            } catch (ParseException rip) {
                System.out.println(rip.getMessage(true));
                done = true;
            } catch (SizeException rip) {
                System.out.println("Not right size, you are missing something. please check your inputs");
                done = true;
            }
        } while (done);

        result = playTheGameOut(hands, game);
        System.out.println("-----------------------------");

        System.out.println("RESULTS OF GAME 2");
        printResults(result);
        System.out.println("-----------------------------");
        hands = createHands(kms);
        System.out.println("Please Enter the game configuration");
        System.out.println("enter in same way as before");
        System.out.print("example :  ");
        System.out.println("75,J,7,Q,T,A,6,2,3,4,5");

        done = true;
        do {
            try {
                game = stringListToDeckList(stringToString(removeWhiteSpace(input.nextLine())));
                done = false;
            } catch (ParseException rip) {
                System.out.println(rip.getMessage(true));
                done = true;
            } catch (SizeException rip) {
                System.out.println("Not right size, you are missing something. please check your inputs");
                done = true;
            }
        } while (done);

        result = playTheGameOut(hands, game);
        System.out.println("-----------------------------");

        System.out.println("RESULTS OF GAME 3");
        printResults(result);
        System.out.println("-----------------------------");
        hands = createHands(kms);
        System.out.println("Please Enter the game configuration");
        System.out.println("enter in same way as before");
        System.out.print("example :  ");
        System.out.println("75,J,7,Q,T,A,6,2,3,4,5");

        done = true;
        do {
            try {
                game = stringListToDeckList(stringToString(removeWhiteSpace(input.nextLine())));
                done = false;
            } catch (ParseException rip) {
                System.out.println(rip.getMessage(true));
                done = true;
            } catch (SizeException rip) {
                System.out.println("Not right size, you are missing something. please check your inputs");
                done = true;
            }
        } while (done);

        result = playTheGameOut(hands, game);
        System.out.println("-----------------------------");

        System.out.println("RESULTS OF GAME 4");
        printResults(result);
        System.out.println("-----------------------------");
        hands = createHands(kms);
        System.out.println("Please Enter the game configuration");
        System.out.println("enter in same way as before");
        System.out.print("example :  ");
        System.out.println("75,J,7,Q,T,A,6,2,3,4,5");

        done = true;
        do {
            try {
                game = stringListToDeckList(stringToString(removeWhiteSpace(input.nextLine())));
                done = false;
            } catch (ParseException rip) {
                System.out.println(rip.getMessage(true));
                done = true;
            } catch (SizeException rip) {
                System.out.println("Not right size, you are missing something. please check your inputs");
                done = true;
            }
        } while (done);

        result = playTheGameOut(hands, game);
        System.out.println("-----------------------------");

        System.out.println("RESULTS OF GAME 5");
        printResults(result);
        System.out.println("-----------------------------");
    }

    public static void printResults(Tuple<Integer, Boolean> result) {
        System.out.print("The final point total was : ");
        System.out.println(result.first);
        System.out.print("The winner was : ");
        if (result.second) {
            System.out.println("Player 2");
        } else {
            System.out.println("Player 1");
        }

    }

    public static void printHand(ArrayList<Card> hand) {
        for (int i = 0; i < hand.size(); i++) {
            System.out.print(hand.get(i));
        }
        System.out.println();
    }

    public static Tuple<Integer, Boolean> playTheGameOut(Tuple<ArrayList<Card>, ArrayList<Card>> hands, Tuple<Integer, ArrayList<Card>> gameDef) {

        ArrayList<Card> hand1 = hands.first;
        ArrayList<Card> hand2 = hands.second;
        Integer startingValue = gameDef.first;
        ArrayList<Card> deck = gameDef.second;
        boolean player1Turn = true;
        Tuple<Card, ArrayList<Card>> handStuff = null;

        for (int i = 0; i < deck.size(); i++) {
            if (player1Turn) {
                handStuff = getCardToPlay(hand1);
                startingValue = playCard(handStuff.first, startingValue);

                if (startingValue >= 100) {
                    return new Tuple<Integer, Boolean>(startingValue, true);
                } else {
                    player1Turn = false;
                }
                hand1.add(deck.remove(0));
                i = i - 1;
            } else {

                handStuff = getCardToPlay(hand2);

                startingValue = playCard(handStuff.first, startingValue);
                if (startingValue >= 100) {
                    return new Tuple<Integer, Boolean>(startingValue, false);
                } else {
                    player1Turn = true;
                }
                hand2.add(deck.remove(0));
                i = i - 1;
            }
        }

        if (startingValue < 100 && deck.size() == 0) {
            //aka we ran out of stuff to add, we still can do one turn
            //since drawing happens after winning is checked
            System.out.println(":thonking:");
            if (player1Turn) {

                handStuff = getCardToPlay(hand1);
                startingValue = playCard(handStuff.first, startingValue);

                if (startingValue >= 100) {
                    return new Tuple<Integer, Boolean>(startingValue, true);
                } else {
                    player1Turn = false;
                }
            } else {
                handStuff = getCardToPlay(hand2);

                startingValue = playCard(handStuff.first, startingValue);
                if (startingValue >= 100) {
                    return new Tuple<Integer, Boolean>(startingValue, false);
                } else {
                    player1Turn = true;
                }
            }
        }

        System.out.println("something went wrong with that input - no winner was found");
        System.out.println("the values following this are likely incorrect");
        return new Tuple<>(startingValue, false);
    }

    public static Integer playCard(Card card, Integer startingTotal) {
        Integer newValue = startingTotal;
        if (card.equals(Card.c9)) {
            ;
        } else if (card.equals(Card.cT)) {
            newValue = newValue - 10;
        } else if (card.equals(Card.c7)) {
            if (newValue + 7 > 99) {
                newValue = newValue + 1;
            } else {
                newValue = newValue + 7;
            }
        } else {
            newValue = newValue + card.value;
        }

        if (startingTotal > 33.5 && newValue < 33.5) {
            newValue = newValue + 5;
        } else if (startingTotal < 33.5 && newValue > 33.5) {
            newValue = newValue + 5;
        } else if (startingTotal < 55.5 && newValue > 55.5) {
            newValue = newValue + 5;
        } else if (startingTotal > 55.5 && newValue < 55.5) {
            newValue = newValue + 5;
        } else if (startingTotal < 77.5 && newValue > 77.5) {
            newValue = newValue + 5;
        } else if (startingTotal > 77.5 && newValue < 77.5) {
            newValue = newValue + 5;
        }

        return newValue;
    }

    public static Tuple<Card, ArrayList<Card>> getCardToPlay(ArrayList<Card> hand) {
        Collections.sort(hand);
        Card middle = hand.get(2);
        hand.remove(2);
        return (new Tuple<Card, ArrayList<Card>>(middle, hand));

    }

    public static Tuple<ArrayList<Card>, ArrayList<Card>> createHands(ArrayList<Card> starting) {
        ArrayList<Card> hand1 = new ArrayList<>();
        ArrayList<Card> hand2 = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            hand1.add(starting.get(i));
        }
        for (int i = 5; i < 10; i++) {
            hand2.add(starting.get(i));
        }

        return (new Tuple<>(hand1, hand2));
    }

    public static Tuple<Integer, ArrayList<Card>> stringListToDeckList(ArrayList<String> list) throws ParseException, SizeException {
        Tuple finalForm;
        int ripER;
        try {
            ripER = Integer.parseInt(list.get(0));
        } catch (NumberFormatException rip) {
            throw new ParseException("Integer Parse Error", -1);
        }


        list.remove(0);
        if (list.size() != 10) {
            throw new SizeException("asD");
        }
        return (new Tuple<Integer, ArrayList<Card>>(ripER, stringListToCardList(list)));

    }

    public static ArrayList<Card> stringListToCardList(ArrayList<String> list) throws ParseException, SizeException {
        ArrayList<Card> finalThing = new ArrayList<Card>();
        for (int i = 0; i < list.size(); i++) {
            try {
                Card merp = stringToCard(list.get(i));
                finalThing.add(merp);
            } catch (ParseException rip) {
                throw new ParseException(rip.getMessage(), i);
            }
        }
        if (finalThing.size() != 10) {
            throw new SizeException("asd");
        }
        return finalThing;
    }

    public static Card stringToCard(String input) throws ParseException {
        if (input.equals("2")) {
            return (Card.c2);
        } else if (input.equals("3")) {
            return (Card.c3);
        } else if (input.equals("4")) {
            return (Card.c4);
        } else if (input.equals("5")) {
            return (Card.c5);
        } else if (input.equals("6")) {
            return (Card.c6);
        } else if (input.equals("7")) {
            return (Card.c7);
        } else if (input.equals("8")) {
            return (Card.c8);
        } else if (input.equals("9")) {
            return (Card.c9);
        } else if (input.equals("T")) {
            return (Card.cT);
        } else if (input.equals("J")) {
            return (Card.cJ);
        } else if (input.equals("Q")) {
            return (Card.cQ);
        } else if (input.equals("K")) {
            return (Card.cK);
        } else if (input.equals("A")) {
            return (Card.cA);
        } else
            throw new ParseException("No Matching Card");
    }

    public static ArrayList<String> stringToString(String input) {
        ArrayList<String> finalList = new ArrayList<String>();
        String currentThingie = "";
        for (int i = 0; i < input.length(); i++) {
            if (input.substring(i, i + 1).equals(",")) {
                finalList.add(currentThingie);
                currentThingie = "";
            } else {
                currentThingie = currentThingie.concat(input.substring(i, i + 1));
            }
        }
        finalList.add(currentThingie);
        return finalList;
    }

    public static String removeWhiteSpace(String tabsOrSpaces) {
        String neither = tabsOrSpaces.replaceAll("\\s", "");
        return neither;
    }
}

class Tuple<T, S> {
    T first;
    S second;

    public Tuple(T first, S second) {
        this.first = first;
        this.second = second;
    }
}

class ParseException extends Exception {
    int loopVal = 0;

    public ParseException(String msg) {
        super(msg);
    }

    public ParseException(String msg, int loopVal) {
        super(msg);
        this.loopVal = loopVal;
    }

    public String getMessage(boolean shouldVal) {
        if (loopVal == -1) {
            return super.getMessage().concat(" | Please reenter, and check the first integer elemnt ");

        }
        if (shouldVal) {
            return super.getMessage().concat(" | Error at Card Number ").concat(Integer.toString(loopVal + 1));
        } else {
            return super.getMessage();
        }
    }
}

class SizeException extends Exception {
    public SizeException(String msg) {
        super(msg);
    }
}
