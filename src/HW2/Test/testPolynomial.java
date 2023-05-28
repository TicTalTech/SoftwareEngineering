package HW2.Test;

import HW2.Polynomial;

public class testPolynomial {
    public static void main(String[] args) {
        double[] arr = {2, 3.5, 4.5};

        Polynomial p = new Polynomial(arr);
        System.out.println(p);
    }
}
