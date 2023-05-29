package HW2;

/**
 * A class that represents a function that's the product of two functions
 */
public class Product extends Function {
    private Function f;
    private Function g;

    public Product(Function f, Function g) {
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
        return f.valueAt(x) * g.valueAt(x);
    }

    @Override
    public String toString() {
        String s = "(";
        s += f.toString();
        s += " * ";
        s += g.toString();
        s += ")";
        return s;
    }

    /**
     * A method which finds the derivative of the function
     *
     * @return returns a function that is the derivative of the current function
     */
    @Override
    public Function derivative() {
        Function derivative1 = new Product(f.derivative(), g);
        Function derivative2 = new Product(f, g.derivative());
        Function derivative = new Sum(derivative1, derivative2);
        return derivative;
    }

    public Function getG() {
        return g;
    }
}
