package HW2;

/**
 * A class that represents a function that is a certain function to the power of a positive integer.
 */
public class Power extends Function {
    private Function func;
    private int power;

    public Power(Function func, int power) {
        this.func = func;
        this.power = power;
    }

    /**
     * A method which finds the value of the function at a point
     *
     * @param x the point where we want to know the value at
     * @return returns the value at that point
     */
    @Override
    public double valueAt(double x) {
        return Math.pow(func.valueAt(x), power);
    }

    @Override
    public String toString() {
        return "(" + func.toString() + "" + "^" + power + ")";
    }

    /**
     * A method which finds the derivative of the function
     *
     * @return returns a function that is the derivative of the current function
     */
    @Override
    public Function derivative() {
        if (power == 1)
            return func.derivative();
        else {
            Function n = new Constant(power);
            Function func2 = new Power(func, power - 1);
            Function der = func.derivative();
            return new MultiProduct(n, func2, der);
        }

    }
}
