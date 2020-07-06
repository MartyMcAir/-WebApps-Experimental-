package pkg1;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import pkg1.hierarchy.*;

public class TestSpring {
    // обращается в папку ресурсы в xml файл конфига
    private static final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
            "applicationContext.xml");

    public static void main(String[] args) {
//        testBean();
//        dependencyInjectionDIManually(); // Внедрение зависимости вручную Decency Injection
//        DiSingleTonByXMLConstructor(); // DI by Spring for Obj
//        dependencyInjectionByXMLSetter(); // for obj and primitive type int
//        usingPrototype();
//        initDestroyMethodsBySingleTon();  // Singleton вызывает doMyInit один раз
//        initDestroyMethodsByPrototype();  // Prototype вызывает doMyInit один раз

        PopMusic popMusic = context.getBean("musicBeanFactory", PopMusic.class);
        System.out.println(popMusic.getSong());


        context.close();   // закрываем бин
    }

    private static void initDestroyMethodsByPrototype() {
        // Prototype вызывается doMyInit = кол-во вызовов и +1
        // а Destroy метод не вызывается (хотя у меня он вызывается почему-то)
        // +1 вызов происходит из-за объявления appContext полем класса..
        RocMusic rocMusic1 = context.getBean("musicBeanPrototype", RocMusic.class);
//        RocMusic rocMusic2 = context.getBean("musicBeanPrototype", RocMusic.class);
//        RocMusic rocMusic3 = context.getBean("musicBeanPrototype", RocMusic.class);
//        rocMusic2.setSongName("new rock song");
//        System.out.println(rocMusic1.getSong());
        System.out.println(rocMusic1.getSong());
    }

    private static void initDestroyMethodsBySingleTon() {
        // Singleton вызывается doMyInit один раз
        SoulMusic soulMusic1 = context.getBean("musicBeanInitDestroy", SoulMusic.class);
        SoulMusic soulMusic2 = context.getBean("musicBeanInitDestroy", SoulMusic.class);
        System.out.println(soulMusic1.getSong());
    }

    private static void usingPrototype() {
        // using prototype
        MusicPlayer mPl2 = context.getBean("musicPlayerProtoType", MusicPlayer.class);
        MusicPlayer mPl1 = context.getBean("musicPlayerProtoType", MusicPlayer.class);

        // Singleton ? _ ссылки указывают на один и тот же объект?
        boolean comparison = mPl2 == mPl1;
        System.out.println("comparison, is singleton?: " + comparison); // false

        System.out.println("mPl2 volume is: " + mPl2.getVolume());  // 66
        System.out.println("mPl1 volume is: " + mPl1.getVolume());  // 66
        mPl1.setVolume(77);
        System.out.println("..mPl1.setVolume(77), and now mPl2 volume is: " + mPl2.getVolume());  // 66
        System.out.println("..setVolume(77) and now.. mPl1 volume is: "
                + mPl1.getVolume());  // 77
    }

    private static void dependencyInjectionByXMLSetter() {
        MusicPlayer musicPlayer = context.getBean("musicPlayer", MusicPlayer.class);
        musicPlayer.playMusic();

        // получаем значение указанное в XML конфиге
        System.out.println("getVolume() from xml appContext: " + musicPlayer.getVolume());
        // получаем значение указанное в внешнем properties файле
        System.out.println("getSongName() from song: " + musicPlayer.getSongName());
    }

    private static void DiSingleTonByXMLConstructor() {
        // создали бин musicPlayer, а то какой агрумент (бин) отправлется в его конструктор
        // это указали в XML конфиге _ это и есть DI - не вручную а через Spring прослойку
        MusicPlayer mPlayer1 = context.getBean("musicPlayer", MusicPlayer.class);
        MusicPlayer mPlayer2 = context.getBean("musicPlayer", MusicPlayer.class);

        // Singleton ? _ ссылки указывают на один и тот же объект?
        boolean comparison = mPlayer1 == mPlayer2;
        System.out.println("comparison mPlayer1 == mPlayer2, is singleton?: " + comparison); // true
        mPlayer1.playMusic();

        System.out.println("mPlayer1 volume: " + mPlayer1.getVolume());  // 66
        mPlayer2.setVolume(77);
        System.out.println("mPlayer2.setVolume(77) and mPlayer1 volume: " + mPlayer1.getVolume());  // 77
    }

    private static void dependencyInjectionDIManually() {
        // Внедрение зависимости вручную Decency Injection
        Music music = context.getBean("musicBean", Music.class);
        MusicPlayer musicPlayer = new MusicPlayer(music);
        musicPlayer.playMusic();

        //
//        System.out.println(music.getSong());
    }

    public static void testBean() {
        // получаем бин
        TestBean testBean = context.getBean("testBean", TestBean.class);
        System.out.println(testBean.getName());   // MartyMcAir - значение указанное в бине
    }
}
