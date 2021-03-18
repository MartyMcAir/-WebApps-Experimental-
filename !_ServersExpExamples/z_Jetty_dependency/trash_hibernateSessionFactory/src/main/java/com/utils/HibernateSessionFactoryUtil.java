package com.utils;

import com.model.Student;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory = buildSessionFactory();

    protected static SessionFactory buildSessionFactory() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy(registry);
            throw new ExceptionInInitializerError("Initial SessionFactory failed" + e);
        }
        return sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }


    public static SessionFactory getSessionFactory2() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                // при такому добавлени анотированных классов и в конфигах
                // <property name="hbm2ddl.auto">update</property> or >create<_
                // HB сам создает таблицы
                configuration.addAnnotatedClass(Student.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
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

    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }
}