package hibernateJavaStudy;

import hibernateJavaStudy.model.ContactEntity;
import hibernateJavaStudy.utils.HibernateSessionFactoryTest;
import org.hibernate.Session;

// from https://javastudy.ru/hibernate/hibernate-quick-start/
public class StudyTest {
    public static void main(String[] args) {
        System.out.println("Hibernate tutorial");

//        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Session session = HibernateSessionFactoryTest.getSessionFactory().openSession();

        session.beginTransaction();

        ContactEntity contactEntity = new ContactEntity();

        contactEntity.setBirthDate(new java.util.Date());
        contactEntity.setFirstName("Nick");
        contactEntity.setLastName("VN");

        session.save(contactEntity);
        session.getTransaction().commit();

        session.close();
    }
}