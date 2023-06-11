package HW3;


public class TestArrayStack {
    public static void main(String[] args) {
        ArrayStack<MyCloneable> as = new ArrayStack<>(5);
        System.out.println("The size is: " + as.size());
        as.push(new MyCloneable(0));
        System.out.println("peek: " + as.peek());
        as.push(new MyCloneable(1));
        System.out.println("peek: " + as.peek());
        System.out.println("The size is: " + as.size());

        System.out.println();

        System.out.println("pop: " + as.pop());
        System.out.println("The size is: " + as.size());
        System.out.println("pop: " + as.pop());
        System.out.println("The size is: " + as.size());

//        System.out.println("pop: " + as.pop());

        as.push(new MyCloneable(8));


        System.out.println("-=cloning=-");

        ArrayStack<MyCloneable> as2 = as.clone();
        System.out.println("peek: " + as.peek());
        System.out.println("peek: " + as2.peek());

        as.peek().setNum(10);
        System.out.println("peek: " + as.peek());
        System.out.println("peek: " + as2.peek());

        as2.push(new MyCloneable(20));
        System.out.println("peek: " + as.peek());
        System.out.println("peek: " + as2.peek());

        System.out.println("-=iterating=-");

        ArrayStack<MyCloneable> as3 = new ArrayStack<>(5);
        as3.push(new MyCloneable(11));
        as3.push(new MyCloneable(12));
        as3.push(new MyCloneable(13));
        as3.push(new MyCloneable(14));
        as3.push(new MyCloneable(15));

        for (MyCloneable mc : as3) {
            System.out.println(mc);
        }
    }
}
