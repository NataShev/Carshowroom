import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Seller extends ReentrantLock {
    final int TIME_AUTO_BULD = 3000;
    final int WAITING_AT_THE_CAR_DEALERSHIP = 1000;
    private Carshowroom carshowroom;
    private Lock lock = new ReentrantLock(true);
    private Condition condition = lock.newCondition();

    public Seller(Carshowroom carshowroom) {
        this.carshowroom = carshowroom;
    }

    public  void production() {
        try {
            lock.lock();
            Thread.sleep(TIME_AUTO_BULD);
            carshowroom.getCarList().add(new Car());
            System.out.println(Thread.currentThread().getName() + "выпустил 1 авто");
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public Car sellCar() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " зашёл в автосалон.");
            while (carshowroom.getCarList().size() == 0) {
                System.out.println("Машин нет");
                condition.await();
            }
            Thread.sleep(WAITING_AT_THE_CAR_DEALERSHIP);
            System.out.println(Thread.currentThread().getName() + " уехал на новеньком авто");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return carshowroom.getCarList().remove(0);
    }
}
