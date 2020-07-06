package pkgConf;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfTest {
    static AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
            SpringConfig.class);

    public static void main(String[] args) {
//        testOneBean();
        // ручное DI без @Autowired
//        diManually();

        // проверка получения значения из properties файла
        testGetFromPropFile();

        context.close();
    }

    private static void testGetFromPropFile() {
        AppleJuice appleJuice = context.getBean("appleJuice", AppleJuice.class);
        System.out.println(appleJuice.getAlcohol());
    }

    private static void diManually() {
        Smoothie smoothie = context.getBean("smoothie", Smoothie.class);
        System.out.println(smoothie.getOrangeJuiceName());
    }

    private static void testOneBean() {
        OrangeJuice orangeJuice = context.getBean("orangeJuice", OrangeJuice.class);
        System.out.println(orangeJuice.getJuiceName());
    }
}
