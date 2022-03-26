public class Seller {
    private Carshowroom carshowroom;

    public Seller(Carshowroom carshowroom) {
        this.carshowroom = carshowroom;
    }

    public synchronized void production() {
        try {
            Thread.sleep(3000);
            carshowroom.getCarList().add(new Car());
            System.out.println(Thread.currentThread().getName() + "выпустил 1 авто");
            notify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized Car sellCar() {
        try {
            System.out.println(Thread.currentThread().getName() + " зашёл в автосалон.");
            while (carshowroom.getCarList().size() == 0) {
                System.out.println("Машин нет");
                wait();
            }
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " уехал на новеньком авто");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return carshowroom.getCarList().remove(0);
    }
}
