package HW2;

/**
 * A class that represents a function that is the quotient of two functions
 */
public class Quotient extends Function {
    private Function f;
    private Function g;

    public Quotient(Function f, Function g) {
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
        return f.valueAt(x) / g.valueAt(x);
    }

    @Override
    public String toString() {
        String s = "(" + f.toString() + " / " + g.toString() + ")";
        return s;
    }

    /**
     * A method which finds the derivative of the function
     *
     * @return returns a function that is the derivative of the current function
     */
    @Override
    public Function derivative() {
        Function func1 = new Product(f.derivative(), g);
        Function func2 = new Product(f, g.derivative());
        Function denominator = new Power(g, 2);
        Function numerator = new Difference(func1, func2);//f'g-g'f
        Function derivative = new Quotient(numerator, denominator);
        return derivative;
    }
}
