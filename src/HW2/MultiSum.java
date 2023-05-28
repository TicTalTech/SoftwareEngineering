package HW2;

public class MultiSum {
    public Sum[] functions;

    public MultiSum(Sum[] functions) {
        this.functions = new Sum[functions.length];
        for (int i = 0; i < functions.length; ++i) {
            this.functions[i] = functions[i];
        }
    }

    public double valueAt(double x) {
        double value = 0;
        for (Sum func : functions)
            value += func.valueAt(x);
        return value;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < functions.length - 1; ++i)
            s = s + functions[i].toString() + "+";
        s = s + functions[functions.length - 1].toString();
        return s;
    }

    public MultiSum derivative() {
        Function[] derivatives = new Function[functions.length];
        for (int i = 0; i < functions.length; ++i)
            derivatives[i] = functions[i].derivative();
        MultiSum der = new MultiSum(derivatives);
        return der;
    }
}
