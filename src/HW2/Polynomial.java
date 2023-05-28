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

    private boolean isZero() {
        if (coefficients.length == 0) {
            return true;
        }
        for (double val : coefficients) {
            if (val != 0) {
                return false;
            }
        }
        return true;
    }

    private int findEffectiveSize() {
        int countZeros = 0;
        for (int i = coefficients.length - 1; i >= 0; i--) {
            countZeros++;
            if (coefficients[i] != 0) {
                break;
            }
        }
        return coefficients.length - countZeros + 1;
    }

    private boolean isFirstCoefficientNegative() {
        for (double val : coefficients) {
            if (val > 0) {
                return false;
            } else if (val < 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        if (isZero()) {
            return "(0)";
        }
        String s = "(";
        if (isFirstCoefficientNegative()) {
            s += "-";
        }
        int effectiveSize = findEffectiveSize();
//        System.out.println("effective size: " + effectiveSize);
        for (int degree = 0; degree < effectiveSize; degree++) {
            if (coefficients[degree] == 0) {
                continue;
            }
            double absCoefficient = Math.abs(coefficients[degree]);
            if (absCoefficient != 1 || degree == 0) {
                if (absCoefficient % 1 == 0) {
                    s += (int) absCoefficient;
                } else {
                    s += absCoefficient;
                }
            }

            if (degree >= 1) {
                s += "x";
            }
            if (degree >= 2) {
                s += "^" + degree;
            }

            if (degree != effectiveSize - 1) {
                if (coefficients[degree] > 0) {
                    s += " + ";
                } else {
                    s += " - ";
                }
            }
        }
        s += ")";
        return s;
    }

    public String rawString() {
        String s = "";

        for (int degree = 0; degree < coefficients.length; degree++) {
            s += coefficients[degree];
            s += "x^" + degree + " + ";
        }
        return s;
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
