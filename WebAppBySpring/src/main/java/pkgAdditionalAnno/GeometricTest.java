package pkgAdditionalAnno;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GeometricTest {
    static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
            "myContext.xml");

    public static void main(String[] args) {
        // Варианты внедрения зависимостей
//        settingVariants();

        // Варианты пометок Singleton / Prototype использую аннотацию @Scope
//        scopeSingletonTest();
//        scopePrototypeTest();

        // Варианты пометки методов аннотациями
        // @PostConstruct - для пометки метода как init-method
        // @PreDestroy - для пометки метода как destroy-method
        anoInitDestroy();

        context.close();
    }

    public static void anoInitDestroy() {
        Hexagon hexagon = context.getBean("hexagon", Hexagon.class);
        System.out.println(hexagon.getFigureName());
    }

    public static void scopePrototypeTest() {
        OvalPrototype oval1 = context.getBean("ovalPrototype", OvalPrototype.class);
        OvalPrototype oval2 = context.getBean("ovalPrototype", OvalPrototype.class);
        System.out.println("oval1 != oval2 is Prototype?: " + (oval1 != oval2));
    }

    public static void scopeSingletonTest() {
        OvalSingleton oval1 = context.getBean("ovalSingleton", OvalSingleton.class);
        OvalSingleton oval2 = context.getBean("ovalSingleton", OvalSingleton.class);
        System.out.println("oval1 == oval2 is SingleTon?: " + (oval1 == oval2));
    }

    public static void settingVariants() {
        Triangle triangle = context.getBean("triangle", Triangle.class);
        // value засеттенное через конструктор указанный в xml _ сеттер оябзателен
        System.out.println("getValueFromConstructor:" + triangle.getValueFromConstructor());

        // value засеттенное через value.. - in xml _ сеттер оябзателен
        System.out.println("getValueFromXML: " + triangle.getValueFromXML());

        // value засеттенное через другой Bean т.е. ref ссылка на него _ сеттер оябзателен
        System.out.println("getDI Bean from ref" + triangle.getOval());

        // сеттер в классе оябзателен
        // value засеттенное через импорт в xml, файла properties и внсение от туда value в xml
        System.out.println("getValueFromProp: " + triangle.getValueFromProp());

        // value засеттенное через аннотацию @Value _ сеттер не нужен
        System.out.println("valueFromPropByAnno" + triangle.getValueFromPropByAnno());

        // 6ой вариант _ с тойным разветвлением на под варианты и + DI вручную
        // value засеттенное через использование ил комбинацию аннотаций @Component @Autowired
        // указываются над полем / сеттером / конструктором куда сетится др бин подходящего типа
    }
}
