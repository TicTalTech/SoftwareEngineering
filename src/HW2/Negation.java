package HW2;

/**
 * A class that represents a function that's the negation of a function
 */
public class Negation extends Function {

    private Function f;

    public Negation(Function f) {
        this.f = f;
    }

    /**
     * A method which finds the value of the function at a point
     *
     * @param x the point where we want to know the value at
     * @return returns the value at that point
     */
    @Override
    public double valueAt(double x) {
        return -f.valueAt(x);
    }

    /**
     * A method which finds the derivative of the function
     *
     * @return returns a function that is the derivative of the current function
     */
    @Override
    public Function derivative() {
        return new Negation(f.derivative());
    }

    public String toString() {
        return "(-" + f + ")";
    }
}
