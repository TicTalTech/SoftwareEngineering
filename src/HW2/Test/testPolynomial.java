package HW2.Test;

import HW2.Polynomial;

public class testPolynomial {
    public static void main(String[] args) {
        double[] arr = {0, 0, 0, 4, 0, 5};

        Polynomial p = new Polynomial(arr);
        System.out.println(p);
        System.out.println((p.valueAt(3.8)));
        System.out.print(p.derivative());
    }
}
