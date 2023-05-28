package HW2;

public abstract class Function {
    public abstract double valueAt(double x);
    public abstract Function derivative();

    public double bisectionMethod(double a, double b, double epsilon) {
        double left = a, right = b;
        while (right - left > epsilon) {
            double mid = (left + right) / 2.0;
            double fA = this.valueAt(a);
            double fMid = this.valueAt(mid);
//            double fB = this.valueAt(b);
            if (fA * fMid > 0) {
                left = mid;
            } else {
                right = mid;
            }

        }
        return (left + right) / 2.0;
    }
    public double bisectionMethod(double a, double b) {
        return bisectionMethod(a, b, 1e-5);
    }
}
