package pkgConf;

import org.springframework.beans.factory.annotation.Value;

public class AppleJuice implements Juice {
    @Value("${appleJuice.alcohol}")   // сеттим значение по ключу из *.properties файла
    public String alcohol;

    @Override
    public String getJuiceName() {
        return "its Apple Juice";
    }

    public String getAlcohol() {
        return alcohol;
    }
}