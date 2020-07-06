package pkgAnnAuto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// сообщаем что это должно быть бином
@Component
public class HouseForGarage {
    private int id;
    private GarageTwoObj garage;

    // сообщаем куда кидать "сеттить" бин
    @Autowired
    public HouseForGarage(GarageTwoObj garage) {
        this.id = 1;
        this.garage = garage;
    }

    @Override
    public String toString() {
        return "HouseForGarage{" +
                "id=" + id +
                ", garage=" + garage.getCarNamesFromGarage() +
                '}';
    }
}