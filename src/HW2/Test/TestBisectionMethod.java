package HW2.Test;

import HW2.Constant;
import HW2.Function;
import HW2.Polynomial;

public class TestBisectionMethod {

    public static void checkFunction(Function func, double a, double b) {
        System.out.println("f(x) = " + func);
        double root1 = func.bisectionMethod(a, b, 0.1);
        double root2 = func.bisectionMethod(a, b);
        double root3 = func.bisectionMethod(a, b, 1e-10);

        System.out.println("The root is: " + root1);
        System.out.println("The root is: " + root2);
        System.out.println("The root is: " + root3);

        System.out.println();
    }
    public static void main(String[] args) {
        checkFunction(new Polynomial(0, 1), -1, 1);
        checkFunction(new Polynomial(-9, 0, 1), -10, 0);
        checkFunction(new Polynomial(-9, 0, 1), 0, 3.5);
//        checkFunction(new Constant(0), 0, 1);




    }
}
