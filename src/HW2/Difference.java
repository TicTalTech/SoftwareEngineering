package HW2;

/**
 * A class that represents a function which is a function minus another function
 */
public class Difference extends Function {
    private final Function f, g;

    public Difference(Function f, Function g) {
        this.f = f;
        this.g = g;
    }

    /**
     * A method which finds the value of the function at a point
     *
     * @param x the point where we want to know the value at
     * @return returns the value at that point
     */
    @Override
    public double valueAt(double x) {
        return f.valueAt(x) - g.valueAt(x);
    }

    /**
     * A method which finds the derivative of the function
     *
     * @return returns a function that is the derivative of the current function
     */
    @Override
    public Function derivative() {
        return new Difference(f.derivative(), g.derivative());
    }
    @Override
    public String toString() {
        return "(" + f.toString() + " - " + g.toString() + ")";
    }
}