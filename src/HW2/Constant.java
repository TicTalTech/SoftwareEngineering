package HW2;

public class Constant extends Function {
    private final double constant;

    public Constant(double constant) {
        this.constant = constant;
    }

    @Override
    public double valueAt(double x) {
        return this.constant;
    }

    @Override
    public Function derivative() {
        return new Constant(0);
    }

    @Override
    public String toString() {
        if (constant % 1 == 0)
            return "(" + (int) constant + ")";
        else
            return "(" + constant + ")";
    }
}
