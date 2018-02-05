import java.util.*;

/**
 *  1. {[(2+3)*6/10}                            --> 8, 10, 13
    2. {[7*25/5+14)*51]+6}                      --> 3, 5, 8
    3. {60+[15*(520–505)]/5–23                  --> 19, 21, 24
    4. {32*510+(8/4)*2+43]/24}                  --> 2, 5, 9, 15
    5. {[(24+900/300*64]}                       --> 10, 14, 17

    6. 6*[5+(21/3)]+9}                          --> 1, 3
 */
public class ACSL2_Aburustum {

    static char[] supposedThings = {'{', '}', '(', ')', '[', ']'};
    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);
        boolean running = true, inRange = false, goingForward = true, dont = false, lastThingWasAThing = false;
        String input = "";
        char missing = 'a', next, missingAlt;
        int counter = 1;

        do {
            System.out.println("Please input the expression, no spaces included.");
            while (missing == 'a') {
                input = reader.nextLine();
                try {
                    missing = findMissing(input);
                } catch (Exception e) {
                    System.out.println("That was not a valid expression. There was no missing enclosure symbol. Enter a new, valid expression.");
                }
            }

            missingAlt = missing == '}' ? '{' : missing == '{' ? '}' : '\u0000';
            missingAlt = missing == ']' ? '[' : missing == '[' ? ']' : missingAlt;
            missingAlt = missing == ')' ? '(' : missing == '(' ? ')' : missingAlt;

            LinkedList<Character> dudes = new LinkedList<>();
            input.chars().mapToObj(i -> (char)i).forEach(dudes :: add);

            LinkedList<Character> droppedDudes = new LinkedList<>();

            ArrayList<Integer> list = new ArrayList<>();

            while (!dudes.isEmpty()) {

                //Choose which direction to move in
                if (missing == '}' || missing == ']' || missing == ')') {
                    next = dudes.getFirst();
                    dudes.removeFirst();
                    droppedDudes.add(next);
                    goingForward = true;
                } else {
                    next = dudes.getLast();
                    dudes.removeLast();
                    droppedDudes.add(next);
                    goingForward = false;
                }
                if (next == missingAlt) {
                    inRange = true;
                }

                while (inRange && !dudes.isEmpty()) {
                    //End of possible area catchers
                    if (goingForward && !dont) {
                        next = dudes.getFirst();
                        dudes.removeFirst();
                        droppedDudes.add(next);
                        if ((next == '}' || next == ']' || next == ')') && !lastThingWasAThing) {
                            inRange = false;
                            list.add(counter + 1);
                        }
                        if (lastThingWasAThing) {lastThingWasAThing = false; inRange = true;}
                    } else if (!dont){
                        next = dudes.getLast();
                        dudes.removeLast();
                        droppedDudes.add(next);
                        if ((next == '{' || next == '[' || next == '(') && !lastThingWasAThing) {
                            inRange = false;
                            list.add(counter + 1);
                        }
                        if (lastThingWasAThing) {lastThingWasAThing = false; inRange = true;}
                    }

                    if (!dont) { counter++; }
                    dont = false;


                    boolean inSmaller = false;
                    if (goingForward && (next == '{' || next == '[' || next == '(')) {
                        while ((next != '}' && next != ']' && next != ')') || inSmaller) {
                            counter++;
                            next = dudes.getFirst();
                            dudes.removeFirst();
                            droppedDudes.add(next);
                            inSmaller = false;
                            if (next == '{' || next == '[' || next == '(') {
                                inSmaller = true;
                                while (next != '}' && next != ']' && next != ')') {
                                    counter++;
                                    next = dudes.getFirst();
                                    dudes.removeFirst();
                                    droppedDudes.add(next);
                                }
                            }
                        }
                        dont = true;
                        lastThingWasAThing = true;
                        list.add(counter + 1);
                    } else if (!goingForward && (next == '}' || next == ']' || next == ')')) {
                        while ((next != '{' && next != '[' && next != '(' ) || inSmaller) {
                            counter++;
                            next = dudes.getLast();
                            dudes.removeLast();
                            droppedDudes.add(next);
                            inSmaller=false;
                            if (next == '}' || next == ']' || next == ')') {
                                inSmaller = true;
                                while (next != '{' && next != '[' && next != '(') {
                                    counter++;
                                    next = dudes.getLast();
                                    dudes.removeLast();
                                    droppedDudes.add(next);
                                }
                            }

                        }
                        dont = true;
                        lastThingWasAThing = true;
                        list.add(counter + 1);
                    }

                    //Symbol Catchers

                    if (next == '*' || next == '+' || next == '–' || next == '/') {
                        boolean doIt = true, done = false;
                        for (int i = droppedDudes.size() - 1; i >= droppedDudes.size() - 5 && i >= 0 && !done; i--) {
                            char dropped = droppedDudes.get(i);
                            if (goingForward) {
                                if (dropped == '(' || dropped == '{' || dropped == '[') {
                                    doIt = false;
                                } else if (dropped == ')' || dropped == '}' || dropped == ']') {
                                    done = true;
                                }
                            } else {
                                if (dropped == ')' || dropped == '}' || dropped == ']') {
                                    doIt = false;
                                } else if (dropped == '(' || dropped == '{' || dropped == '[') {
                                    done = true;
                                }
                            }
                        }
                        if (doIt) {
                            list.add(counter);
                        }

                    }

                    if (inRange && dudes.isEmpty()) {
                        list.add(counter+1);
                    }
                }
                counter++;

            }

            if (!goingForward) {
                for (int i = 0; i < list.size(); i++) {
                    list.set(i, input.length() - list.get(i) + 2);
                }
            }

            HashSet<Integer> uniques = new HashSet<>(list);
            ArrayList<Integer> sortedUniques = new ArrayList<Integer>(uniques);
            Collections.sort(sortedUniques);

            for (int i = 0; i < sortedUniques.size() - 1; i++) {
                if (sortedUniques.get(i+1) - sortedUniques.get(i) == 1) {
                    sortedUniques.remove(i);
                    i--;
                }
            }

            System.out.println(sortedUniques);

            System.out.print("\nWould you like to continue inputting expressions? Y/N: ");
            input = reader.nextLine();
            if (input.toLowerCase().equals("n")) {
                running = false;
            } else {
                running = true;
            }

            missing = 'a';
            inRange = false;
            counter = 1;
            list.clear();
            System.out.println();
        } while (running);
    }

    public static char findMissing(String input) throws Exception {
        ArrayList<Character> characters = new ArrayList<>();
        input.chars().mapToObj(i -> (char)i).forEach(characters :: add);

        for (char c : supposedThings) {
            if (!characters.contains(c)) {
                return c;
            }
        }

        throw new Exception();

    }

}