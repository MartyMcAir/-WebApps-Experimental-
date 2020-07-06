package pkgAdditionalAnno;

import org.springframework.beans.factory.annotation.Value;

public class Triangle implements GeometricFigure {
    // способ сеттинга value из *.properties файла указанного в xml context'е
    // используя ключ triangle.valueFromPropByAnno (из properties файла)
    @Value("${triangle.valueFromPropByAnno}")
    public String valueFromPropByAnno;   // сеттер не требуется
    // для осальных полей сеттер обяхателен
    public Oval oval;
    public String valueFromProp, valueFromXML, valueFromConstructor;

    @Override
    public String getFigureName() {
        return "its a Triangle";
    }

    // using from constructor arg in XML
    public Triangle(String valueFromConstructor) {
        this.valueFromConstructor = valueFromConstructor;
    }

    public String getValueFromConstructor() {
        return valueFromConstructor;
    }

    // using from XML bean .. value + file properties
    public void setValueFromProp(String color) {
        this.valueFromProp = color;
    }

    public String getValueFromProp() {
        return valueFromProp;
    }

    // using from XML
    public void setValueFromXML(String valueFromXML) {
        this.valueFromXML = valueFromXML;
    }

    public String getValueFromXML() {
        return valueFromXML;
    }

    public void setOval(Oval oval) {
        this.oval = oval;
    }

    public Oval getOval() {
        return oval;
    }

    public String getValueFromPropByAnno() {
        return valueFromPropByAnno;
    }

}