package pkgAnnAuto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

// помечаем Spring'у чтобы он создал бин этого класса
// что бы внего можно было внедрить зависимость (т.е. в данный бин)
@Component
public class GarageQualifier {
    private Car car1, car2;

    @Autowired
    public void setTwoCar(@Qualifier("bmv") Car car1, @Qualifier("audi") Car car2) {
        this.car1 = car1;
        this.car2 = car2;
    }

    public String getCarNamesFromGarage() {
        return "car1: " + car1.getCarLogo() + " and car2: " + car2.getCarLogo();
    }

    public void showCarNameFromGarage() {
        System.out.println(car1.getCarLogo());
        System.out.println(car2.getCarLogo());
    }
}