package wen;

public class Branch extends Tree {
    char value;
    int count;
    Tree left;
    Tree right;

    public Branch(char value, int count, Tree left, Tree right) {
        this.value = value;
        this.count = count;
        this.left = left;
        this.right = right;
    }

    public Branch(char value) {
        this.value = value;
        count = 1;
        left = new Leaf();
        right = new Leaf();
    }

    @Override
    public Branch insert(char value) {
        if (value == this.value) {
            this.count = this.count + 1;
            return this;
        } else {
            if (value < this.value) {
                this.left = this.left.insert(value);
                return this;
            } else {
                this.right = this.right.insert(value);
                return this;
            }
        }

    }

    public int acslThing() {
        if (this.left instanceof Leaf ^ this.right instanceof Leaf) {
            return(this.count + this.left.acslThing() + this.right.acslThing());
        } else {
            return(this.left.acslThing() + this.right.acslThing());
        }
    }
}