package HW2;

public class Product extends Function {
    Function one;
    Function two;

    public Product(Function one, Function two) {
        this.one = one;
        this.two = two;
    }

    @Override
    public double valueAt(double x) {
        return one.valueAt(x) * two.valueAt(x);
    }

    @Override
    public String toString() {
        String s = "(";
        s = s + one.toString();
        s = s = " * ";
        s = s + two.toString();
        s += ")";
        return s;
    }

    @Override
    public Function derivative() {
        Function derivative1 = new Product(one.derivative(), two);
        Function derivative2 = new Product(two.derivative(), one);
        Function derivative = new Sum(derivative1, derivative2);
        return derivative;
    }
}
