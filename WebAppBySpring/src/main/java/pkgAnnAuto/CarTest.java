package pkgAnnAuto;


import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CarTest {
    // обращается в папку ресурсы в xml файл конфига
    private static final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
            "applicationContext.xml");

    // Исползование @Autowired
    // если хоть, что нибудь пойдет не так, например @Autowired лишний
    // то будет ошибка котораяобнаружится только при компиляции..
    // т.е. IDE из за Аннотации, не так шибко что либо подскажет, если что не так..

    public static void main(String[] args) {
        // DI для одного объекта - при этом желательно, что бы была возможность
        // засеттить бин только через один из способов (т.е. из вариантов поле/сеттер/конструктор)
        // а иначе может вылететь Exception _ - хотя такое и не всегда _
        // да и что бы не путаться желательно выбрать один из способов и юзать его
//        diOneObj();

        // множественная зависимость _ множественное внедрение
//        multiplyDI();

        // спутанная множественная зависимость
        // в HouseForGarage - находится Garage, а в нем две машины Bmv & Audi
        // а у машин нет никаких зависимостей..
//        confusedAddiction();

        // внедерение множественной зависоимости используя интерефейс как связующее
        // и уточнение конкретного типа через аннотацию @Qualifier
        qualifierDI();

        context.close();
    }

    private static void diOneObj() {
        GarageOneObj garage = context.getBean("garageOneObj", GarageOneObj.class);
        System.out.println(garage.getCarName());   // car is Bmv
    }

    private static void multiplyDI() {
        GarageTwoObj garage = context.getBean("garageTwoObj", GarageTwoObj.class);
        System.out.println(garage.getCarNamesFromGarage()); // audi: car is Audi and bmv: car is Bmv
    }

    private static void confusedAddiction() {
        HouseForGarage houseForGarage = context.getBean("houseForGarage", HouseForGarage.class);
        System.out.println(houseForGarage);
    }

    private static void qualifierDI() {
        GarageQualifier garageQualifier = context.getBean("garageQualifier", GarageQualifier.class);
        System.out.println(garageQualifier.getCarNamesFromGarage());   // car1: car is Bmv and car2: car is Audi
    }
}