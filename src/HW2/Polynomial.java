package HW2;

import java.lang.Math;

public class Polynomial extends Function{
    final double[] coefficients;

    public Polynomial(double... coefficients) {
        this.coefficients = new double[coefficients.length];
        for (int i = 0; i < coefficients.length; ++i) {
            this.coefficients[i] = coefficients[i];
        }
    }

    public double valueAt(double x) {
        double value = 0;
        for (int i = 0; i < coefficients.length; ++i) {
            value = +coefficients[i] * Math.pow(x, i);
        }
        return value;
    }

    @Override
    public String toString() {
        String s = "";
        s = s + coefficients[0];
        s += "+";
        for (int i = 1; i < coefficients.length - 1; ++i) {
            if (coefficients[i] != 0 && coefficients[i] != 1 && coefficients[i] != -1)
                s = s + coefficients[i] + "x" + "^" + i + "+";
            if (coefficients[i] == 1)
                s = s + "x" + "^" + i + "+";
            if (coefficients[i] == -1)
                s = s + "-x" + "^" + i + "+";
        }
        if (coefficients[coefficients.length - 1] != 0 && coefficients[coefficients.length - 1] != 1
                && coefficients[coefficients.length - 1] != -1)
            s = s + coefficients[(coefficients.length) - 1] + "x" + "^" + (coefficients.length - 1);
        if (coefficients[coefficients.length - 1] == 1)
            s = s + "x" + "^" + (coefficients.length - 1);
        if (coefficients[coefficients.length - 1] == -1)
            s = s + "-x" + "^" + (coefficients.length - 1);
        return s;
    }

    public Polynomial derivative() {
        double[] newCoefficients = new double[coefficients.length - 1];
        for (int i = 0; i < coefficients.length - 1; ++i) {
            newCoefficients[i] = coefficients[i + 1] * (i + 1);
        }
        Polynomial derivative = new Polynomial(newCoefficients);
        return derivative;
    }
}
