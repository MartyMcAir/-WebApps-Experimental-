package util;

import hbExperiment.model.HibernateDevelopersEntity;
import hibernate.models.Auto;
import hibernate.models.User;
import hibernateJavaStudy.model.*;
import hibernatePark.model.Bus;
import hibernatePark.model.Driver;
import hibernatePark.model.Route;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
//    private static Session sessionFactory;
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {
    }

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

    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                // при такому добавлени анотированных классов и в конфигах
                // <property name="hbm2ddl.auto">update</property> or >create<_
                // HB сам создает таблицы
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Auto.class);
                configuration.addAnnotatedClass(ContactEntity.class);
                configuration.addAnnotatedClass(ContactHobbyDetailEntity.class);
                configuration.addAnnotatedClass(ContactHobbyDetailEntityPK.class);
                configuration.addAnnotatedClass(ContactTelDetailEntity.class);
                configuration.addAnnotatedClass(HobbyEntity.class);
                configuration.addAnnotatedClass(Bus.class);
                configuration.addAnnotatedClass(Driver.class);
                configuration.addAnnotatedClass(Route.class);

                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }

    public static SessionFactory getSessionFactory2() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(HibernateDevelopersEntity.class);


                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(
                        configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }
}