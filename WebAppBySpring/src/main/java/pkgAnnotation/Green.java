package pkgAnnotation;

import org.springframework.stereotype.Component;

@Component("greenBean")
public class Green implements Color{
    @Override
    public String getColorName() {
        return "Green";
    }
}
