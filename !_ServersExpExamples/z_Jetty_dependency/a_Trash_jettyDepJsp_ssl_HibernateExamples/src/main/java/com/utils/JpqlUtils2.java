package com.utils;

import com.ajaxServlets.model.Employee;
import org.hibernate.jpa.HibernatePersistenceProvider;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.Collections;

// https://www.logicbig.com/tutorials/java-ee-tutorial/jpa/jpql-aggregate-functions.html
public class JpqlUtils2 {
    //    PersistenceUnitInfoImpl pui = new PersistenceUnitInfoImpl( "myUtilXml", "2.1" )
//            .addMappingFileName( "org/hibernate/test/jpa/xml/versions/valid-orm-2_1.xml" );
    private static HibernatePersistenceProvider hibernatePersistenceProvider = new HibernatePersistenceProvider();
    private static EntityManagerFactory entityManagerFactory =
            hibernatePersistenceProvider.createEntityManagerFactory("myUtilXml", Collections.emptyMap());

    //            Persistence.createEntityManagerFactory("myUnitXml");
    public static void main(String[] args) {
        JpqlUtils2 jpqlUtils2 = new JpqlUtils2();
        try {
            jpqlUtils2.persistEmployees();
//            jpqlUtils.findEmployeeCount();
//            jpqlUtils.findEmployeeAvgSalary();
//            jpqlUtils.findEmployeeMaxSalary();
//            jpqlUtils.findEmployeeMinSalary();
//            jpqlUtils.findEmployeeSalariesSum();

        } finally {
            entityManagerFactory.close();
        }
    }

    public void persistEmployees() {
        Employee employee1 = Employee.create("Diana", 3000);
        Employee employee2 = Employee.create("Rose", 4000);
        Employee employee3 = Employee.create("Denise", 1500);
        Employee employee4 = Employee.create("Mike", 2000);
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(employee1);
        em.persist(employee2);
        em.persist(employee3);
        em.persist(employee4);
        em.getTransaction().commit();
        em.close();
    }


    // Original methods
    private void findEmployeeCount() {
        System.out.println("-- Employee COUNT --");
        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("SELECT COUNT(e) FROM Employee e");
        Long result = (Long) query.getSingleResult();
        System.out.println(result);
        em.close();
    }

    private void findEmployeeAvgSalary() {
        System.out.println("-- Employee AVG Salary --");
        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("SELECT AVG(e.salary) FROM Employee e");
        Double result = (Double) query.getSingleResult();
        System.out.println(result);
        em.close();
    }

    private void findEmployeeMaxSalary() {
        System.out.println("-- Employee MAX Salary --");
        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("SELECT MAX(e.salary) FROM Employee e");
        Long result = (Long) query.getSingleResult();//salary of type long
        System.out.println(result);
        em.close();
    }

    private void findEmployeeMinSalary() {
        System.out.println("-- Employee MIN Salary --");
        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("SELECT MIN(e.salary) FROM Employee e");
        Long result = (Long) query.getSingleResult();//salary of type long
        System.out.println(result);
        em.close();
    }

    private void findEmployeeSalariesSum() {
        System.out.println("-- Employee SUM Salary --");
        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("SELECT SUM(e.salary) FROM Employee e");
        Long result = (Long) query.getSingleResult();
        System.out.println(result);
        em.close();
    }
}