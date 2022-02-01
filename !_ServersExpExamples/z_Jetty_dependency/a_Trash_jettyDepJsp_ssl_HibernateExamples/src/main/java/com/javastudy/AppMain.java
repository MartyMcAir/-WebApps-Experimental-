package com.javastudy;

import com.javastudy.utils.HibernateSessionFactory;
import org.hibernate.Session;

/**
 * Created by Nick on 05.09.2015.
 */
public class AppMain {

    public static void main(String[] args) {
        System.out.println("Hibernate tutorial");

        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        session.beginTransaction();

//        ContactEntity contactEntity = new ContactEntity();
//
//        contactEntity.setBirthDate(new java.util.Date());
//        contactEntity.setFirstName("Nick");
//        contactEntity.setLastName("VN");

        User_m andru = new User_m();
        andru.setId(1);
        andru.setName("Andru");
        andru.setPostIndex("23414");
        session.save(andru);

        session.getTransaction().commit();

        session.close();


    }
}
