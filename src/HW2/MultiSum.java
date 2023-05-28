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
        for(int i=0; i<)
            s = s + func.toString() + "+";
    }
}
