package HW2.Test;

import HW2.*;

public class TestFunctions {
    public static void main(String[] args) {
        double[] xCoordinates = {0, 1, 2, 3, -1, -2, 100, -100};
        Function[] functions = {
//                new Constant(1),
//                new Constant(2),
//                new Constant(3),
//                new Constant(-1),
//                new Constant(0),

//                new Polynomial(1, 2, 3)

//                new Sum(new Constant(1), new Constant(2)),
//                new Sum(new Constant(1), new Constant(-1)),
//                new Sum(new Constant(-2), new Constant(-8)),
//                new Sum(new Constant(1), new Sum(new Constant(2), new Constant(3))),

//                new Difference(new Constant(1), new Constant(2)),
//                new Difference(new Constant(1), new Constant(-1)),
//                new Difference(new Constant(-2), new Constant(-8)),
//                new Difference(new Constant(1), new Sum(new Constant(2), new Constant(3))),

//                new MultiProduct(new Function[] {new Constant(2), new Constant(3)})

                new Negation(new Constant(1)),
                new Negation(new Constant(0)),
                new Negation(new Constant(-1)),
                new Negation(new Polynomial(1, 1, 1)),
                new Negation(new Sum(new Constant(1), new Constant(2))),

        };
        for (Function func : functions){
            System.out.println("f(x) = " + func);
            System.out.println("f'(x) = " + func.derivative());
            System.out.println("calculating values:");
            for (double x : xCoordinates) {
                System.out.println("f(" + x + ") = " + func.valueAt(x));
            }
            System.out.println();
        }
    }
}
