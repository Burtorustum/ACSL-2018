package Wen;
abstract class Token {

    private Token() {

    }

    public static boolean isMUL(Token asd) {
        if (asd instanceof Sym) {
            return ((Sym) asd).symbol.equals(Symbol.MUL);
        } else {
            return false;
        }
    }

    public static boolean isDIV(Token asd) {
        if (asd instanceof Sym) {
            return ((Sym) asd).symbol.equals(Symbol.DIV);
        } else {
            return false;
        }
    }

    public static boolean isADD(Token asd) {
        if (asd instanceof Sym) {
            return ((Sym) asd).symbol.equals(Symbol.ADD);
        } else {
            return false;
        }
    }

    public static boolean isSUB(Token asd) {
        if (asd instanceof Sym) {
            return ((Sym) asd).symbol.equals(Symbol.SUB);
        } else {
            return false;
        }
    }

    public static boolean isPARENOpen(Token asd) {
        if (asd instanceof Sym) {
            return ((Sym) asd).symbol.equals(Symbol.PARENOpen);
        } else {
            return false;
        }
    }

    public static boolean isSQUAROpen(Token asd) {
        if (asd instanceof Sym) {
            return ((Sym) asd).symbol.equals(Symbol.SQUAROpen);
        } else {
            return false;
        }
    }

    public static boolean isBRACEOpen(Token asd) {
        if (asd instanceof Sym) {
            return ((Sym) asd).symbol.equals(Symbol.BRACEOpen);
        } else {
            return false;
        }
    }

    public static boolean isPARENClose(Token asd) {
        if (asd instanceof Sym) {
            return ((Sym) asd).symbol.equals(Symbol.PARENClose);
        } else {
            return false;
        }
    }

    public static boolean isBRACEClose(Token asd) {
        if (asd instanceof Sym) {
            return ((Sym) asd).symbol.equals(Symbol.BRACEClose);
        } else {
            return false;
        }
    }

    public static boolean isSQUARClose(Token asd) {
        if (asd instanceof Sym) {
            return ((Sym) asd).symbol.equals(Symbol.SQUARClose);
        } else {
            return false;
        }
    }


    public static class Num extends Token {
        public int value;

        public Num(int value) {
            this.value = value;
        }

        public void addDigit(int a) {
            this.value = this.value * 10 + a;
        }

        @Override
        public String toString() {
            return Integer.toString(this.value);
        }
    }

    public static class Sym extends Token {
        public Symbol symbol;

        public Sym(Symbol symbol) {
            this.symbol = symbol;
        }

        @Override
        public String toString() {
            return (symbol.toString());
        }
    }

}
