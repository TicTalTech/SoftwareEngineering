package HW2;

public class Power extends Function {
    private Function func;
    private int power;

    public Power(Function func, int power) {
        this.func = func;
        this.power = power;
    }

    @Override
    public double valueAt(double x) {
        return Math.pow(func.valueAt(x), power);
    }

    @Override
    public String toString() {
        return "(" + func.toString() + "" + "^" + power + ")";
    }

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
