package HW2;

public class MultiProduct extends Function {
    private final Function[] functions;

    public MultiProduct(Function f, Function g, Function... more) {
        this.functions = new Function[more.length + 2];
        this.functions[0] = f;
        this.functions[1] = g;
        for (int i = 0; i < more.length; i++) {
            this.functions[i + 2] = more[i];
        }
    }

    @Override
    public double valueAt(double x) {
        double product = 1;
        for (Function func : this.functions) {
            product *= func.valueAt(x);
        }
        return product;
    }

    @Override
    public String toString() {
        String s = "(";
        int count = 0;
        for (Function func : this.functions) {
            s += func.toString();
            if (count != this.functions.length - 1) {
                s += " * ";
            }
            count++;
        }
        s += ")";
        return s;
    }

    public static MultiProduct createMultiProductFromArray(Function[] functions) {
        Function[] lastFunctions = new Function[functions.length - 2];
        for (int i = 2; i < functions.length; i++) {
            lastFunctions[i - 2] = functions[i];
        }
        return new MultiProduct(functions[0], functions[1], lastFunctions);
    }

    @Override
    public Function derivative() {
        Function[] outerSum = new Function[this.functions.length];
        for (int i = 0; i < this.functions.length; i++) {
            Function[] innerProductFunctions = new Function[this.functions.length];
            Function iDerivative = this.functions[i].derivative();
            innerProductFunctions[0] = iDerivative;
            int jCount = 0;
            for (int j = 0; j < this.functions.length; j++) {
                if (i == j) {
                    continue;
                }
                Function jFunc = this.functions[j];
                innerProductFunctions[jCount + 1] = jFunc;
                jCount++;
            }
            MultiProduct innerProduct = createMultiProductFromArray(innerProductFunctions);
            outerSum[i] = innerProduct;
        }
        return MultiSum.createMultiSumFromArray(outerSum);
    }
}
