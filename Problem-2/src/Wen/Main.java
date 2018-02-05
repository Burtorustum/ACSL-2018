package Wen;
import java.text.ParseException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner johnCena = new Scanner(System.in);
        //String input = "{[7*25/5+14)*51]+6}";
        // input = "{60+[15*(520–505)]/5–23";
        // input = "{[(2+3)*6/10}";
        // input = "{32*510+(8/4)*2+43]/24}";
        // input = "{[(24+900/300*64]}";

        boolean done;
        do {
            try {
                System.out.println("Please input the expression with no spaces");
                System.out.println("For example, the following is a proper input");
                System.out.println("{[(24+900/300*64]}");
                System.out.print("Input 1:");
                testString(johnCena.nextLine());
                done = false;
            } catch (ParseException e) {
                System.out.println(e.getMessage());
                System.out.println("1");
                done = true;
            } catch (Parser.MeParseException e) {
                System.out.println("2");
                System.out.println(e.msg);
                done = true;
            }
        } while (done);
        done = false;

        do {
            try {
                System.out.println("Please input the expression with no spaces");
                System.out.println("For example, the following is a proper input");
                System.out.println("{[(24+900/300*64]}");
                System.out.print("Input 2:");
                testString(johnCena.nextLine());
                done = false;
            } catch (ParseException e) {
                System.out.println(e.getMessage());
                done = true;
            } catch (Parser.MeParseException e) {
                System.out.println(e.msg);
                done = true;
            }
        } while (done);

        done = false;

        do {
            try {
                System.out.println("Please input the expression with no spaces");
                System.out.println("For example, the following is a proper input");
                System.out.println("{[(24+900/300*64]}");
                System.out.print("Input 3:");
                testString(johnCena.nextLine());
                done = false;
            } catch (ParseException e) {
                System.out.println(e.getMessage());
                done = true;
            } catch (Parser.MeParseException e) {
                System.out.println(e.msg);
                done = true;
            }
        } while (done);

        done = false;

        do {
            try {
                System.out.println("Please input the expression with no spaces");
                System.out.println("For example, the following is a proper input");
                System.out.println("{[(24+900/300*64]}");
                System.out.print("Input 4:");
                testString(johnCena.nextLine());
                done = false;
            } catch (ParseException e) {
                System.out.println(e.getMessage());
                done = true;
            } catch (Parser.MeParseException e) {
                System.out.println(e.msg);
                done = true;
            }
        } while (done);

        done = false;
        do {
            try {
                System.out.println("Please input the expression with no spaces");
                System.out.println("For example, the following is a proper input");
                System.out.println("{[(24+900/300*64]}");
                System.out.print("Input 5:");
                testString(johnCena.nextLine());
                done = false;
            } catch (ParseException e) {
                System.out.println(e.getMessage());
                done = true;
            } catch (Parser.MeParseException e) {
                System.out.println(e.msg);
                done = true;
            }
        } while (done);

        done = false;


    }

    public static char whatAmIMissing(String input) throws ParseException {
        boolean hasBracketOpen = false;
        boolean hasBracketClos = false;
        boolean hasParenthOpen = false;
        boolean hasParenthClos = false;
        boolean hasSquarebOpen = false;
        boolean hasSquarebClos = false;
        for (int i = 0; i < input.length(); i++) {
            char merp = input.charAt(i);
            if (merp == '(') {
                if (hasParenthOpen) {
                    throw new ParseException("extra open paren", i);
                } else {
                    hasParenthOpen = true;
                }
            } else if (merp == ')') {
                if (hasParenthClos) {
                    throw new ParseException("extra close paren", i);
                } else {
                    hasParenthClos = true;
                }
            } else if (merp == '{') {
                if (hasBracketOpen) {
                    throw new ParseException("extra open curly brace", i);
                } else {
                    hasBracketOpen = true;
                }
            } else if (merp == '}') {
                if (hasBracketClos) {
                    throw new ParseException("extra close curly brace", i);
                } else {
                    hasBracketClos = true;
                }
            } else if (merp == '[') {
                if (hasSquarebOpen) {
                    throw new ParseException("extra open square brace", i);
                } else {
                    hasSquarebOpen = true;
                }
            } else if (merp == ']') {
                if (hasSquarebClos) {
                    throw new ParseException("extra close square brace", i);
                } else {
                    hasSquarebClos = true;
                }
            }
        }
        if ((hasBracketClos ? 1 : 0) + (hasBracketOpen ? 1 : 0) + (hasParenthClos ? 1 : 0) + (hasParenthOpen ? 1 : 0) + (hasSquarebClos ? 1 : 0) + (hasSquarebOpen ? 1 : 0) == 5) {
            if (!hasBracketClos) {
                return '}';
            } else if (!hasBracketOpen) {
                return '{';
            } else if (!hasParenthClos) {
                return ')';
            } else if (!hasParenthOpen) {
                return '(';
            } else if (!hasSquarebClos) {
                return ']';
            } else if (!hasSquarebOpen) {
                return '[';
            } else {
                //so this should never happen
                return '{';
                //it will probably get it wrong but w/e
            }
        } else {
            throw new ParseException("missing proper amount of brackest", 0);
        }
    }

    public static void testString(String input) throws Parser.MeParseException, ParseException {

        String asd = Character.toString(whatAmIMissing(input));

        for (int i = 0; i <= input.length(); i++) {
            try {
                String testme = input.substring(0, i).concat(asd).concat(input.substring(i , input.length()));
                Parser.realistParser(testme);
                System.out.print(i + 1);
                System.out.print(", ");
            } catch (Parser.MeParseException e) {
            }
        }
        System.out.println("\n");
    }
}

