package hibernateJavaStudy.utils;

import hibernateJavaStudy.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryTest {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryTest() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                // при такому добавлени анотированных классов и в конфигах
                // <property name="hbm2ddl.auto">update</property> or >create<_
                // HB сам создает таблицы
                configuration.addAnnotatedClass(ContactEntity.class);
                configuration.addAnnotatedClass(ContactHobbyDetailEntity.class);
                configuration.addAnnotatedClass(ContactHobbyDetailEntityPK.class);
                configuration.addAnnotatedClass(ContactTelDetailEntity.class);
                configuration.addAnnotatedClass(HobbyEntity.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }
}