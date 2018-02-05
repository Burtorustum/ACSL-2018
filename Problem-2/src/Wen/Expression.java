package Wen;

public abstract class Expression {
    private Expression() {

    }

    public abstract int evaluate();


    public static final class Constant extends Expression {
        public int value;

        public Constant(int value) {
            this.value = value;
        }

        @Override
        public int evaluate() {
            return value;
        }

        @Override
        public String toString() {
            return Integer.toString(value);

        }
    }

    public static final class Operator extends Expression {
        public Expression left;
        public Expression right;
        public Operation opopop;

        public Operator(Operation opopop, Expression valL, Expression valR) {
            this.left = valL;
            this.right = valR;
            this.opopop = opopop;
        }

        @Override
        public int evaluate() {
            if (this.opopop.equals(Operation.MUL)) {
                return (left.evaluate() * right.evaluate());
            } else if (this.opopop.equals(Operation.DIV)) {
                return (left.evaluate() / right.evaluate());
            } else if (this.opopop.equals(Operation.ADD)) {
                return (left.evaluate() + right.evaluate());
            } else if (this.opopop.equals(Operation.SUB)) {
                return (left.evaluate() - right.evaluate());
            }
            //magic number
            //if u see this something fucked up
            return 9128374;
        }

        @Override
        public String toString() {
            return ("-----\n" + this.opopop.toString() + "\n" + left.toString() + "\n" + right.toString() + "\n-----");
        }
    }


}
