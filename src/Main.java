public class Main {
    public static void main(String[] args) {
        final Carshowroom carshowroom = new Carshowroom();

        for (int x = 0; x < 3; x++) {
            new Thread(null, carshowroom::sellCar, "Покупатель " + (x + 1)).start();
            new Thread(null, carshowroom::production, "Производитель Toyota ").start();
        }
    }
}