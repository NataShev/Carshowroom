import java.util.ArrayList;
import java.util.List;

public class Carshowroom {

    Seller seller = new Seller(this);
    List<Car> carList = new ArrayList<>(3);

    public List<Car> getCarList() {
        return carList;
    }

    public Car sellCar() {
        return seller.sellCar();
    }

    public void production() {
        seller.production();
    }
}
