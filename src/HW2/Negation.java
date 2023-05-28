package HW2;

public class Negation extends Product{

    public Negation(Function two) {
        super(new Constant(-1), two);
    }

    public String toString() {
        return "-(" + this.getG() + ")";
    }
}
