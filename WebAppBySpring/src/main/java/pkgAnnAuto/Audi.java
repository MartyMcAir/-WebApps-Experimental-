package pkgAnnAuto;

import org.springframework.stereotype.Component;

// помечаем Spring'у, чтобы он создал бин этого класса
@Component
public class Audi implements Car {
    @Override
    public String getCarLogo() {
        return "car is Audi";
    }
}