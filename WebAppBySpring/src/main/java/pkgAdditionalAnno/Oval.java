package pkgAdditionalAnno;

public class Oval implements GeometricFigure {
    @Override
    public String getFigureName() {
        return "its Oval";
    }

    @Override
    public String toString() {
        return "value from ref=beanName in xml (Oval obj)";
    }
}
