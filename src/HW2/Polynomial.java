package HW2;

import java.lang.Math;

/**
 * A class that represents a polynomial. The coefficients are represented in an array. Where the number in
 * the index i is the coefficient of x^i
 */
public class Polynomial extends Function {
    private final double[] coefficients;

    public Polynomial(double... coefficients) {
        this.coefficients = new double[coefficients.length];
        for (int i = 0; i < coefficients.length; ++i) {
            this.coefficients[i] = coefficients[i];
        }
    }

    /**
     * A method which finds the value of the function at a point
     *
     * @param x the point where we want to know the value at
     * @return returns the value at that point
     */
    @Override
    public double valueAt(double x) {
        double value = 0;
        for (int i = 0; i < coefficients.length; ++i) {
            value += coefficients[i] * Math.pow(x, i);
        }
        return value;
    }

    /**
     * checks if the polynomial represents the zero polynomial
     *
     * @return - true if it is the zero polynomial
     */
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

    /**
     * in cases the there are the most significant coefficients that are zero, it will return the size of the
     * polynomial without them.
     * for example:
     * 0 + x + x^2 + 0x^3 + 0x^4 -> 3 (will not take into account the 0x^3, 0x^4
     *
     * @return the "real" size of the polynomial
     */
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

    /**
     * out of all non-zero coefficients, check if the first one is zero
     * example:
     * 0 + x + x^2 -> false
     * 1 - x -> false
     * 0 + 0x - x^2 -> true
     *
     * @return true if the first non-zero coefficient is negative
     */
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

    /**
     * finds the index in the coefficients array of the first non-zero coefficient
     *
     * @return the index of the first non-zero coefficient
     */
    private int firstCoefficientIndex() {
        int count = 0;
        for (double val : coefficients) {
            if (val != 0) {
                break;
            }
            count++;
        }
        return count;
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
        for (int degree = firstCoefficientIndex(); degree < effectiveSize; degree++) {
            if (coefficients[degree] != 0) {

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
            }

            if (degree != effectiveSize - 1) {
                if (coefficients[degree + 1] > 0) {
                    s += " + ";
                } else if (coefficients[degree + 1] < 0) {
                    s += " - ";
                }
            }
        }
        s += ")";
        return s;
    }

    /**
     * A method which finds the derivative of the function
     *
     * @return returns a function that is the derivative of the current function
     */
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
