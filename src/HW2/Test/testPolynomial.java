package HW2.Test;

import HW2.Polynomial;

public class testPolynomial {
    public static void main(String[] args) {
        double[] arr = {1, 0, 1};

        Polynomial p = new Polynomial(arr);
        System.out.println(p);
        System.out.println((p.valueAt(2)));
        System.out.print(p.derivative());
    }
}
