package pkgAnnotation;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RunTestBeanByAnnotations {
    // обращается в папку ресурсы в xml файл конфига
    private static final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
            "applicationContext.xml");

    public static void main(String[] args) {
        Color color = context.getBean("redBean", Red.class);
        System.out.println(color.getColorName());   // Red
        context.close();
    }
}
