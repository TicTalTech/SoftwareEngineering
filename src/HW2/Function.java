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

    /**
     * A method that finds an approximation of the square root of the function at a point a
     *
     * @param a       the point where we want to find the square root
     * @param epsilon the error from the actual value
     * @return returns an approximation of the value of the square root of the function at point a within epsilon
     * of the actual value
     */
    public double newtonRaphsonMethod(double a, double epsilon) {
        double point = a;
        while (Math.abs(valueAt(point)) >= epsilon)
            point = point - (valueAt(point) / derivative().valueAt(point));
        return point;
    }

    /**
     * Finds the nth degree taylor polynomial of the function
     *
     * @param n the degree of which we want to calculate the taylor polynomial for
     * @return returns the nth degree taylor polynomial of the function
     */
    public Function taylorPolynomial(int n) {
        double factorial = 1;
        Function derivative = derivative();
        double derivativeValue;
        double value;
        double Coefficients[] = new double[n + 1];
        Coefficients[0] = valueAt(0);
        for (int i = 1; i <= n; ++i) {
            factorial = factorial * i;
            derivativeValue = derivative.valueAt(0);
            value = derivativeValue / factorial;
            Coefficients[i] = value;
            derivative = derivative.derivative();
        }
        Polynomial polynomial = new Polynomial(Coefficients);
        return polynomial;
    }

    /**
     * A method that finds an approximation of the square root of the function at a point a
     *
     * @param a the point where we want to find the square root
     * @return returns an approximation of the value of the square root of the function at point a within 10^-5
     * of the actual value
     */
    public double newtonRaphsonMethod(double a) {
        return (newtonRaphsonMethod(a, Math.pow(10, -5)));
    }
}
