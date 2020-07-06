package pkgAnnAuto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// помечаем Spring'у чтобы он создал бин этого класса
// что бы внего можно было внедрить зависимость (т.е. в данный бин)
@Component
public class GarageOneObj {
    // внедрение через поле
    @Autowired
    private Bmv car;

    // принимает тип Bmv и Бин Bmv есть и это класс не интерфейс
    // т.е. в единственном значении, и нет конфликта в пониманиии какой бин
    // автоматически сюда кидать Spring'у _ а если был бы тип Car
    // - и есть два подходящих бина для внедрения = конфликт и Exception
//    @Autowired
    public void setCar(Bmv car) {
        this.car = car;
    }

    public String getCarName() {
        return car.getCarLogo();
    }

    public void showCarNameFromGarage() {
        System.out.println(car.getCarLogo());
    }
}