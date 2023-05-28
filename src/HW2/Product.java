package HW2;

public class Product extends Function {
    private Function f;
    private Function g;

    public Product(Function f, Function g) {
        this.f = f;
        this.g = g;
    }

    @Override
    public double valueAt(double x) {
        return f.valueAt(x) * g.valueAt(x);
    }

    @Override
    public String toString() {
        String s = "";
        s = s + f.toString();
        s = s = "*";
        s = s + g.toString();
        return s;
    }

    @Override
    public Function derivative() {
        Function derivative1 = new Product(f.derivative(), g);
        Function derivative2 = new Product(g.derivative(), f);
        Function derivative = new Sum(derivative1, derivative2);
        return derivative;
    }
}
