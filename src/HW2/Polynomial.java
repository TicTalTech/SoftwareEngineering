package HW2;

import java.lang.Math;

public class Polynomial extends Function {
    private final double[] coefficients;

    public Polynomial(double... coefficients) {
        this.coefficients = new double[coefficients.length];
        for (int i = 0; i < coefficients.length; ++i) {
            this.coefficients[i] = coefficients[i];
        }
    }

    @Override

    public double valueAt(double x) {
        double value = 0;
        for (int i = 0; i < coefficients.length; ++i) {
            value += coefficients[i] * Math.pow(x, i);
        }
        return value;
    }

    @Override
    public String toString() {
        int zero = 0;
        String s = "(";
        if (coefficients.length == 1)
            return "(" + coefficients[0] + ")";
        for (int i = 0; i < coefficients.length; ++i)
            zero += coefficients[i];
        if (zero == 0)
            return "(" + 0 + ")";
        else {
            if (coefficients.length > 0) {
                if (coefficients[0] != 0 && coefficients[0] != 1 && coefficients[0] != -1) {
                    if (coefficients[0] % 1 == 0) {
                        int num = (int) coefficients[0];
                        s = s + num;
                    } else
                        s = s + coefficients[0];
                }
                if (coefficients[0] == 1)
                    s = s + "1";
                if (coefficients[0] == -1)
                    s = s + "-1";
            }
            if (coefficients.length > 1) {
                if (coefficients[1] != 0 && coefficients[1] != 1 && coefficients[1] != -1) {
                    if (coefficients[1] % 1 == 0) {
                        int num = (int) coefficients[1];
                        if (num > 0)
                            s = "+" + s + num + "x";
                        if (num < 0)
                            s = "-" + s + num + "x";
                    } else {
                        if (coefficients[1] > 0)
                            s = "+" + s + coefficients[1] + "x";
                        if (coefficients[1] < 0)
                            s = "-" + s + coefficients[1] + "x";
                        s = s + coefficients[1];
                    }
                }
                if (coefficients[1] == 1)
                    s = s + "x";
                if (coefficients[1] == -1)
                    s = s + "-x";
            }
            for (int i = 2; i < coefficients.length - 1; ++i) {
                if (coefficients[i] != 0 && coefficients[i] != 1 && coefficients[i] != -1) {
                    if (coefficients[i] % 1 == 0) {
                        int num = (int) coefficients[i];
                        if (coefficients[i] > 0)
                            s = "+" + s + num + "x" + "^" + i;
                        if (coefficients[i] < 0)
                            s = "-" + s + num + "x" + "^" + i;
                    } else {
                        if (coefficients[i] > 0)
                            s = "+" + s + coefficients[i] + "x" + "^" + i;
                        if (coefficients[i] < 0)
                            s = "-" + s + coefficients[i] + "x" + "^" + i;
                    }
                }
                if (coefficients[i] == 1)
                    s = s + "x" + "^" + i;
                if (coefficients[i] == -1)
                    s = s + "-x" + "^" + i;
            }
            if (coefficients.length > 2) {
                if (coefficients[coefficients.length - 1] != 0 && coefficients[coefficients.length - 1] != 1
                        && coefficients[coefficients.length - 1] != -1) {
                    if (coefficients[coefficients.length - 1] % 1 == 0) {
                        int num = (int) coefficients[coefficients.length - 1];
                        if (coefficients[coefficients.length - 1] > 0)
                            s = "+" + s + coefficients[coefficients.length - 1] + "x" + "^" + (coefficients.length - 1);
                        if (coefficients[coefficients.length - 1] < 0)
                            s = "-" + s + coefficients[coefficients.length - 1] + "x" + "^" + (coefficients.length - 1);
                    } else {
                        if (coefficients[coefficients.length - 1] > 0)
                            s = "+" + s + coefficients[coefficients.length - 1] + "x" + "^" + (coefficients.length - 1);
                        if (coefficients[coefficients.length - 1] < 0)
                            s = "-" + s + coefficients[coefficients.length - 1] + "x" + "^" + (coefficients.length - 1);
                    }
                }
                if (coefficients[coefficients.length - 1] == 1)
                    s = s + "x" + "^" + (coefficients.length - 1);
                if (coefficients[coefficients.length - 1] == -1)
                    s = s + "-x" + "^" + (coefficients.length - 1);
            }
            return s + ")";
        }
    }

    @Override

    public Function derivative() {
        double[] newCoefficients;
        if (coefficients.length > 1) {
            newCoefficients = new double[coefficients.length - 1];
        } else {
            newCoefficients = new double[1];
            newCoefficients[0] = 0;
            Function derivative = new Polynomial(newCoefficients);
            return derivative;
        }
        for (int i = 0; i < coefficients.length - 1; ++i) {
            newCoefficients[i] = coefficients[i + 1] * (i + 1);
        }
        Function derivative = new Polynomial(newCoefficients);
        return derivative;
    }
}
