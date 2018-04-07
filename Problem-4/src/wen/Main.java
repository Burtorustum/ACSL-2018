package wen;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input #1");
        System.out.println("Please enter the string");
        System.out.println("Note: all non letters will be ignored, and the string will be converted to upercase");
        String merp = handleInput(scanner.nextLine());
        System.out.print("Output for test 1: ");
        System.out.println(iterateTreeLoop(merp).acslThing());
        System.out.println("Input #2");
        System.out.println("Please enter the string");
        System.out.println("Note: all non letters will be ignored, and the string will be converted to upercase");
        merp = handleInput(scanner.nextLine());
        System.out.print("Output for test 1: ");
        System.out.println(iterateTreeLoop(merp).acslThing());
        System.out.println("Input #3");
        System.out.println("Please enter the string");
        System.out.println("Note: all non letters will be ignored, and the string will be converted to upercase");
        merp = handleInput(scanner.nextLine());
        System.out.print("Output for test 1: ");
        System.out.println(iterateTreeLoop(merp).acslThing());
        System.out.println("Input #4");
        System.out.println("Please enter the string");
        System.out.println("Note: all non letters will be ignored, and the string will be converted to upercase");
        merp = handleInput(scanner.nextLine());
        System.out.print("Output for test 1: ");
        System.out.println(iterateTreeLoop(merp).acslThing());
        System.out.println("Input #5");
        System.out.println("Please enter the string");
        System.out.println("Note: all non letters will be ignored, and the string will be converted to upercase");
        merp = handleInput(scanner.nextLine());
        System.out.print("Output for test 1: ");
        System.out.println(iterateTreeLoop(merp).acslThing());
        }

    public static Branch iterateTreeLoop(String input) {
        Branch tree = new Branch(input.charAt(0));
        for (int i = 1; i < input.length(); i++) {
            tree = tree.insert(input.charAt(i));
        }

        return tree;
    }

    public static String handleInput(String input) {
        String merp = input.replaceAll("[^A-za-z]", "");
        String merp2 = merp.replaceAll(" ", "");
        return merp2.toUpperCase();
    }
}
