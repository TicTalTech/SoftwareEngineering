package HW2;

/**
 * A class that represents a function that's made up of the sum of two or more functions
 */
public class MultiSum extends Function {
    private Function[] functions;

    public MultiSum(Function f, Function g, Function... more) {
        this.functions = new Function[more.length + 2];
        this.functions[0] = f;
        this.functions[1] = g;
        for (int i = 0; i < more.length; i++) {
            this.functions[i + 2] = more[i];
        }
    }

    /**
     * @param functions an array of functions
     * @return returns a Multi sum function that's created from the sum of the functions in the array
     */
    public static MultiSum createMultiSumFromArray(Function[] functions) {
        Function[] lastFunctions = new Function[functions.length - 2];
        for (int i = 2; i < functions.length; i++) {
            lastFunctions[i - 2] = functions[i];
        }
        return new MultiSum(functions[0], functions[1], lastFunctions);
    }

    /**
     * A method which finds the value of the function at a point
     *
     * @param x the point where we want to know the value at
     * @return returns the value at that point
     */
    @Override
    public double valueAt(double x) {
        double value = 0;
        for (Function func : functions)
            value += func.valueAt(x);
        return value;
    }

    @Override
    public String toString() {
        String s = "(";
        for (int i = 0; i < functions.length - 1; ++i)
            s = s + functions[i].toString() + " + ";
        s = s + functions[functions.length - 1].toString();
        return s + ")";
    }

    /**
     * A method which finds the derivative of the function
     *
     * @return returns a function that is the derivative of the current function
     */
    @Override

    public Function derivative() {
        Function[] derivatives = new Function[functions.length];
        for (int i = 0; i < functions.length; ++i)
            derivatives[i] = functions[i].derivative();
        Function der = createMultiSumFromArray(derivatives);
        return der;
    }
}
