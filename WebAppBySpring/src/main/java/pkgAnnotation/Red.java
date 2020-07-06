package pkgAnnotation;

import org.springframework.stereotype.Component;

@Component("redBean")
public class Red implements Color {
    @Override
    public String getColorName() {
        return "Red";
    }
}
