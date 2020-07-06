package pkgAnnAuto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.undo.AbstractUndoableEdit;

// помечаем Spring'у чтобы он создал бин этого класса
// что бы внего можно было внедрить зависимость (т.е. в данный бин)
@Component
public class Garage {
    //    @Autowired
    private Car car;
    public Audi audi;
    public Bmv bmv;

    // Если если всего один класс реализующий интерефейс Car
    // и он помечен как бин т.е. @Component то все сработает
    // но если таких классов несколько, то будет конфликт
//    @Autowired
//    public Garage(Car car) {
//        this.car = car;
//    }

    // принимает тип Audi и Бин Audi есть и это класс не интерфейс
    // т.е. в единственном значении, и нет конфликта в пониманиии какой бин
    // автоматически сюда кидать Spring'у
//    @Autowired
    public void setAudiInGarage(Audi audi) {
        this.audi = audi;
    }

    // внедрение через сеттер эффект тот же
//        @Autowired
    public void setCar(Car car) {
        this.car = car;
    }

    // внедрение 2 бина
    @Autowired
    public void setTwoCar(Bmv bmv, Audi audi) {
        this.bmv = bmv;
        this.audi = audi;
    }

    public String getCarNameFromGarage() {
        return "audi: " + audi.getCarLogo() + " and bmv: " + bmv.getCarLogo();
    }

    public void showCarNameFromGarage() {
//        System.out.println(car.getCarLogo());
        System.out.println(audi.getCarLogo());
        System.out.println(bmv.getCarLogo());
    }
}