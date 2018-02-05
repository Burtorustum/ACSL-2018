package Wen;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    public static ArrayList<Token> listOfTokens(String input) throws MeParseException {
        ArrayList<Token> output = new ArrayList<>();
        Token tempToken;
        for (int i = 0; i < input.length(); i++) {

            char merp = input.charAt(i);
            if (merp == '0') {
                output.add(new Token.Num(0));
            } else if (merp == '1') {
                output.add(new Token.Num(1));
            } else if (merp == '2') {
                output.add(new Token.Num(2));
            } else if (merp == '3') {
                output.add(new Token.Num(3));
            } else if (merp == '4') {
                output.add(new Token.Num(4));
            } else if (merp == '5') {
                output.add(new Token.Num(5));
            } else if (merp == '6') {
                output.add(new Token.Num(6));
            } else if (merp == '7') {
                output.add(new Token.Num(7));
            } else if (merp == '8') {
                output.add(new Token.Num(8));
            } else if (merp == '9') {
                output.add(new Token.Num(9));
            } else if (merp == '*') {
                output.add(new Token.Sym(Symbol.MUL));
            } else if (merp == '/') {
                output.add(new Token.Sym(Symbol.DIV));
            } else if (merp == '+') {
                output.add(new Token.Sym(Symbol.ADD));
            } else if (merp == '-') {
                output.add(new Token.Sym(Symbol.SUB));
            } else if (merp == '–') {
                output.add(new Token.Sym(Symbol.SUB));
            } else if (merp == '(') {
                output.add(new Token.Sym(Symbol.PARENOpen));
            } else if (merp == ')') {
                output.add(new Token.Sym(Symbol.PARENClose));
            } else if (merp == '{') {
                output.add(new Token.Sym(Symbol.BRACEOpen));
            } else if (merp == '}') {
                output.add(new Token.Sym(Symbol.BRACEClose));
            } else if (merp == '[') {
                output.add(new Token.Sym(Symbol.SQUAROpen));
            } else if (merp == ']') {
                output.add(new Token.Sym(Symbol.SQUARClose));
            } else {
                throw new MeParseException(null, "tilt" + Integer.toString(i) + "--" + input.charAt(i));
            }
        }
        return output;
    }

    public static ArrayList<Token> finishParse(ArrayList<Token> input) {
        for (int i = 0; i < input.size(); i++) {
            Token me = input.get(i);
            if (me instanceof Token.Num) {

                try {
                    if (input.get(i + 1) instanceof Token.Num) {
                        ((Token.Num) me).addDigit(((Token.Num) input.get(i + 1)).value);
                        input.remove(i + 1);
                        i = i - 1;
                    }
                } catch (IndexOutOfBoundsException e) {
                    i = i + 1;
                }
            } else {
            }
        }
        return input;
    }

    public static Expression completeParse(List<Token> input) throws MeParseException {
        ;
        if (input.size() == 1) {
            if (input.get(0) instanceof Token.Num) {
                //System.out.println("constant");
                return new Expression.Constant(((Token.Num) input.get(0)).value);
            } else {
                throw new MeParseException(input, "got some s--- that was alone and not a number");
            }
        }

        int boundLower = 0;
        int boundUpper = input.size();
        Token first = input.get(boundLower);
        Token last = input.get(boundUpper - 1);
        if ((Token.isBRACEOpen(first) && Token.isBRACEClose(last)) || (Token.isPARENOpen(first) && Token.isPARENClose(last)) || (Token.isSQUAROpen(first) && Token.isSQUARClose(last))) {
            boundLower++;
            boundUpper--;
        }
        first = input.get(boundLower);
        last = input.get(boundUpper - 1);

        if ((Token.isBRACEOpen(first) && Token.isBRACEClose(last)) || (Token.isPARENOpen(first) && Token.isPARENClose(last)) || (Token.isSQUAROpen(first) && Token.isSQUARClose(last))) {
            boundLower++;
            boundUpper--;
        }
        first = input.get(boundLower);
        last = input.get(boundUpper - 1);

        if ((Token.isBRACEOpen(first) && Token.isBRACEClose(last)) || (Token.isPARENOpen(first) && Token.isPARENClose(last)) || (Token.isSQUAROpen(first) && Token.isSQUARClose(last))) {
            boundLower++;
            boundUpper--;
        }

        boolean inBrace = false;
        boolean inParen = false;
        boolean inSquar = false;
        for (int i = boundLower; i < boundUpper; i++) {
            Token tempThing = input.get(i);
            if (Token.isADD(tempThing) && !inBrace && !inParen && !inSquar) {
                // System.out.println("add");
                List<Token> left = input.subList(boundLower, i);
                List<Token> right = input.subList(i + 1, boundUpper);
                return new Expression.Operator(Operation.ADD, completeParse(left), completeParse(right));
            } else if (inBrace && Token.isBRACEClose(tempThing)) {
                inBrace = false;
            } else if (!inBrace && Token.isBRACEOpen(tempThing)) {
                inBrace = true;
            } else if (inParen && Token.isPARENClose(tempThing)) {
                inParen = false;
            } else if (!inParen && Token.isPARENOpen(tempThing)) {
                inParen = true;
            } else if (inSquar && Token.isSQUARClose(tempThing)) {
                inSquar = false;
            } else if (!inBrace && Token.isSQUAROpen(tempThing)) {
                inSquar = true;
            }
        }
        inBrace = false;
        inParen = false;
        inSquar = false;
        for (int i = boundLower; i < boundUpper; i++) {
            Token tempThing = input.get(i);
            if (Token.isSUB(tempThing) && !inBrace && !inParen && !inSquar) {
                // System.out.println("sub");
                List<Token> left = input.subList(boundLower, i);
                List<Token> right = input.subList(i + 1, boundUpper);
                return new Expression.Operator(Operation.SUB, completeParse(left), completeParse(right));
            } else if (inBrace && Token.isBRACEClose(tempThing)) {
                inBrace = false;
            } else if (!inBrace && Token.isBRACEOpen(tempThing)) {
                inBrace = true;
            } else if (inParen && Token.isPARENClose(tempThing)) {
                inParen = false;
            } else if (!inParen && Token.isPARENOpen(tempThing)) {
                inParen = true;
            } else if (inSquar && Token.isSQUARClose(tempThing)) {
                inSquar = false;
            } else if (!inBrace && Token.isSQUAROpen(tempThing)) {
                inSquar = true;
            }
        }
        inBrace = false;
        inParen = false;
        inSquar = false;
        for (int i = boundLower; i < boundUpper; i++) {
            Token tempThing = input.get(i);
            if (Token.isMUL(tempThing) && !inBrace && !inParen && !inSquar) {
                //System.out.println("mul");
                List<Token> left = input.subList(boundLower, i);
                List<Token> right = input.subList(i + 1, boundUpper);
                return new Expression.Operator(Operation.MUL, completeParse(left), completeParse(right));
            } else if (inBrace && Token.isBRACEClose(tempThing)) {
                inBrace = false;
            } else if (!inBrace && Token.isBRACEOpen(tempThing)) {
                inBrace = true;
            } else if (inParen && Token.isPARENClose(tempThing)) {
                inParen = false;
            } else if (!inParen && Token.isPARENOpen(tempThing)) {
                inParen = true;
            } else if (inSquar && Token.isSQUARClose(tempThing)) {
                inSquar = false;
            } else if (!inBrace && Token.isSQUAROpen(tempThing)) {
                inSquar = true;
            }
        }
        inBrace = false;
        inParen = false;
        inSquar = false;
        for (int i = boundLower; i < boundUpper; i++) {
            Token tempThing = input.get(i);
            if (Token.isDIV(tempThing) && !inBrace && !inParen && !inSquar) {
                // System.out.println("div");
                List<Token> left = input.subList(boundLower, i);
                List<Token> right = input.subList(i + 1, boundUpper);
                return new Expression.Operator(Operation.DIV, completeParse(left), completeParse(right));
            } else if (inBrace && Token.isBRACEClose(tempThing)) {
                inBrace = false;
            } else if (!inBrace && Token.isBRACEOpen(tempThing)) {
                inBrace = true;
            } else if (inParen && Token.isPARENClose(tempThing)) {
                inParen = false;
            } else if (!inParen && Token.isPARENOpen(tempThing)) {
                inParen = true;
            } else if (inSquar && Token.isSQUARClose(tempThing)) {
                inSquar = false;
            } else if (!inBrace && Token.isSQUAROpen(tempThing)) {
                inSquar = true;
            }
        }

        throw new MeParseException(input, "no operator");
    }

    public static Expression realistParser(String input) throws MeParseException {
        return (completeParse(finishParse(listOfTokens(input))));
    }

    public static class MeParseException extends Exception {
        List<Token> thingInQuestion;
        String msg;

        public MeParseException(List<Token> a, String m) {
            thingInQuestion = a;
            msg = m;
        }
    }

}


