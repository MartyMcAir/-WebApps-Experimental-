package util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HbUniversalUtil {
    private static SessionFactory sessionFactory;

    private HbUniversalUtil() {
    }

    //     <T> T get(Class<T> var1, Serializable var2);
    //    public static <K, V> SessionFactory getSessionFactory(Map<K, V> elements) {
    public static SessionFactory getSessionFactory(Object[] elements) {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();

                // добавляем аннотированные объекты _ при этом отпадает необходимость
                // маппинга в hibernate.cfg.xml
                // Но если на некоторые OneToMany не делать маппинга то будет ошибка вида:
                // 'Many To One' attribute type should not be 'Persistence Entity'
                // или 'Many To One' attribute type should not be a container
                // или это может произойти из-за неверного присвоение ManyToOne вместо OneToMany
                for (Object item : elements) configuration.addAnnotatedClass(item.getClass());

                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }

}