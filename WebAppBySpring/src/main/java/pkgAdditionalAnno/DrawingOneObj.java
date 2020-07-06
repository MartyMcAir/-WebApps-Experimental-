package pkgAdditionalAnno;

public class DrawingOneObj {
    Triangle triangle;
    int size;
    String color;

    public void setTriangle(Triangle triangle) {
        this.triangle = triangle;
    }

    public String getTriangle() {
        return triangle.getFigureName();
    }
}