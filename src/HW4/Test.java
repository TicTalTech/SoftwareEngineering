package HW4;

public class Test {
    public static void main(String[] args) {
        Database db = new Database(5);
        db.readAcquire();
//        db.readAcquire();
//        db.readRelease();
//        db.writeTryAcquire();
//        db.writeAcquire();
//        db.writeRelease();
//        db.readRelease();
        db.readRelease();


    }
}
