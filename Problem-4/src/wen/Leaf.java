package wen;



public class Leaf extends Tree {

    public Leaf() {
    }

    public Branch insert(char value) {
        return new Branch(value);
    }

    @Override
    public int acslThing() {
        return 0;
    }
}
