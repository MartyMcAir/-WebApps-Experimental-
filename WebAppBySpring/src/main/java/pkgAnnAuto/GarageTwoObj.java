package pkgAnnAuto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// помечаем Spring'у чтобы он создал бин этого класса
// что бы внего можно было внедрить зависимость (т.е. в данный бин)
@Component
public class GarageTwoObj {
    public Audi audi;
    public Bmv bmv;

    // внедрение 2х бинов
    @Autowired
    public void setTwoCar(Bmv bmv, Audi audi) {
        this.bmv = bmv;
        this.audi = audi;
    }

    public String getCarNamesFromGarage() {
        return "audi: " + audi.getCarLogo() + " and bmv: " + bmv.getCarLogo();
    }

    public void showCarNamesFromGarage() {
        System.out.println(audi.getCarLogo());
        System.out.println(bmv.getCarLogo());
    }
}