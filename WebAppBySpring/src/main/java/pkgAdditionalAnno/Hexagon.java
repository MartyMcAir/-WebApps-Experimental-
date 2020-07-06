package pkgAdditionalAnno;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component   // для создания бина
public class Hexagon implements GeometricFigure {

    @Override
    public String getFigureName() {
        return "its a Hexagon";
    }

    @PostConstruct   // помечаем как init method
    public void doMyInit() {
        System.out.println("Doing my initialization");
    }

    @PreDestroy   // помечаем как destroy method
    public void doMyDestroy() {
        System.out.println("Doing my destruction");
    }
}
