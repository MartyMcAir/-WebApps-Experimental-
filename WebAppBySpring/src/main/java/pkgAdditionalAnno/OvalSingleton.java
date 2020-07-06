package pkgAdditionalAnno;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class OvalSingleton implements GeometricFigure {
    @Override
    public String getFigureName() {
        return "its Oval";
    }

    @Override
    public String toString() {
        return "value from ref=beanName in xml (Oval obj)";
    }
}
