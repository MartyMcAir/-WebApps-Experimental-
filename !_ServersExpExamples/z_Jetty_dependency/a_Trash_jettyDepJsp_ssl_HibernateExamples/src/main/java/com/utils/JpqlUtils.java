package com.utils;

import com.model.Student;
import com.model.StudentEntityUtil;
import org.hibernate.jpa.HibernatePersistenceProvider;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.Collections;

// https://www.logicbig.com/tutorials/java-ee-tutorial/jpa/jpql-aggregate-functions.html
public class JpqlUtils {
    //    PersistenceUnitInfoImpl pui = new PersistenceUnitInfoImpl( "myUtilXml", "2.1" )
//            .addMappingFileName( "org/hibernate/test/jpa/xml/versions/valid-orm-2_1.xml" );
    private static HibernatePersistenceProvider hibernatePersistenceProvider = new HibernatePersistenceProvider();
    private static EntityManagerFactory entityManagerFactory =
            hibernatePersistenceProvider.createEntityManagerFactory("myUtilXml", Collections.emptyMap());

    //            Persistence.createEntityManagerFactory("myUnitXml");
    public static void main(String[] args) {
        JpqlUtils jpqlUtils = new JpqlUtils();
        try {
            jpqlUtils.persist();
//            jpqlUtils.findCount();
//            jpqlUtils.findAvgSalary();
//            jpqlUtils.findMaxSalary();
//            jpqlUtils.findMinSalary();
//            jpqlUtils.findSalariesSum();

        } finally {
            entityManagerFactory.close();
        }
    }

    public void persist() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        for (Student student : StudentEntityUtil.getStudentsList()) {
            em.persist(student);
        }

        em.getTransaction().commit();
        em.close();
    }


    // Original methods
    private void findCount() {
        System.out.println("-- COUNT --");
        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("SELECT COUNT(e) FROM Student e");
        Long result = (Long) query.getSingleResult();
        System.out.println(result);
        em.close();
    }

    private void findAvgSalary() {
        System.out.println("-- AVG Salary --");
        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("SELECT AVG(e.salary) FROM Student e");
        Double result = (Double) query.getSingleResult();
        System.out.println(result);
        em.close();
    }

    private void findMaxSalary() {
        System.out.println("-- MAX Salary --");
        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("SELECT MAX(e.salary) FROM Student e");
        Long result = (Long) query.getSingleResult();//salary of type long
        System.out.println(result);
        em.close();
    }

    private void findMinSalary() {
        System.out.println("-- MIN Salary --");
        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("SELECT MIN(e.salary) FROM Student e");
        Long result = (Long) query.getSingleResult();//salary of type long
        System.out.println(result);
        em.close();
    }

    private void findSalariesSum() {
        System.out.println("-- SUM Salary --");
        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("SELECT SUM(e.salary) FROM Student e");
        Long result = (Long) query.getSingleResult();
        System.out.println(result);
        em.close();
    }
}