package HW2;

public class MultiProduct extends Function{
    private final Function[] functions;

    public MultiProduct(Function... functions) {
        this.functions = functions;
    }

    @Override
    public double valueAt(double x) {
        double product = 1;
        for(Function func : this.functions){
            product *= func.valueAt(x);
        }
        return product;
    }
    @Override
    public String toString() {
        String s = "";
        int count = 0;
        for(Function func : this.functions){
            s += "( " + func.toString() + " )";
            if (count != this.functions.length - 1) {
                s += " * ";
            }
                count++;
        }
        return s;
    }

    @Override
    public Function derivative() {
        Function[] outerSum = new Function[this.functions.length];
        for(int i = 0; i < this.functions.length; i++) {
            Function[] innerProductFunctions = new Function[this.functions.length];
            Function iDerivative = this.functions[i].derivative();
            innerProductFunctions[0] = iDerivative;
            int jCount = 0;
            for(int j = 0; j < this.functions.length; j++) {
                if (i == j) {
                    continue;
                }
                Function jFunc = this.functions[j];
                innerProductFunctions[jCount + 1] = jFunc;
                jCount++;
            }
            MultiProduct innerProduct = new MultiProduct(innerProductFunctions);
            outerSum[i] = innerProduct;
        }
        // TODO - create a MultiSum from the "outerSum" array
        return null;
    }
}
