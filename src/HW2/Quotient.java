package HW2;

public class Quotient extends Function {
    private Function f;
    private Function g;

    public Quotient(Function f, Function g) {
        this.f = f;
        this.g = g;
    }

    @Override
    public double valueAt(double x) {
        return f.valueAt(x) / g.valueAt(x);
    }

    @Override
    public String toString() {
        String s = f.toString() + "/" + g.toString();
        return s;
    }

    @Override
    public Function derivative() {
        Function func1 = new Product(f.derivative(), g);
        Function func2 = new Product(g.derivative(), f);
        Function denominator = new Product(g, g);
        Function numerator = new Difference(func1, func2);//f'g-g'f
        Function derivative = new Quotient(numerator, denominator);
        return derivative;
    }
}
