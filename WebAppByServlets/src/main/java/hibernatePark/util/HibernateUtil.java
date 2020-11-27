package hibernatePark.util;

import hibernatePark.model.Bus;
import hibernatePark.model.Driver;
import hibernatePark.model.Route;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    // Origin from https://habr.com/ru/post/29694/
//    private static SessionFactory sessionFactory = null;
//    static {     try {
//            sessionFactory = new Configuration().configure().buildSessionFactory();
//        } catch (Throwable ex) {
//            System.err.println("Initial SessionFactory creation failed." + ex);
//            throw new ExceptionInInitializerError(ex);
//        } }
//    public static SessionFactory getSessionFactory() {     return sessionFactory; }

    private static SessionFactory sessionFactory;

    private HibernateUtil() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                // при такому добавлении аннотированных классов и в конфигах
                // <property name="hbm2ddl.auto">update</property> or >create<_
                // HB сам создает таблицы
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
}