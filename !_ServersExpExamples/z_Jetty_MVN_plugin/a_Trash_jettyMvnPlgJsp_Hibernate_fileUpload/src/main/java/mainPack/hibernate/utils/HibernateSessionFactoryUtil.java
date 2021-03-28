package mainPack.hibernate.utils;

import hibernate.models.Auto;
import hibernate.models.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
//                Configuration configuration = new Configuration().configure("C:\\Users\\marty\\OneDrive\\Dropbox\\GitHub\\-WebApps-Experimental-\\WebAppByServlets\\src\\main\\resources\\hibernate.cfg.xml");
//                Configuration configuration = new Configuration().configure("/resources/hibernate.cfg.xml");
//                Configuration configuration = new Configuration().configure("main/resources/hibernate.cfg.xml");
//                Configuration configuration = new Configuration().configure("/main/resources/hibernate.cfg.xml");
//                Configuration configuration = new Configuration().configure("src/main/resources/hibernate.cfg.xml");
//                Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Auto.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }
}