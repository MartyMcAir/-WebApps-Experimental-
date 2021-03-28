package com.utils;

import com.ajaxServlets.model.EmployeeForCriteriaApi;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;

// https://www.logicbig.com/tutorials/java-ee-tutorial/jpa/criteria-api-aggregation-methods.html
public class CriteriaUtils2 {
    private static EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("myUnitXml");
    static EmployeeForCriteriaApi employeeObj = new EmployeeForCriteriaApi();

    public static void main(String[] args) {
        try {
            // persistence.PersistenceException: No Persistence provider for EntityManager named myUnitXml
            persistEmployees();
//            findEmployeeCount();
        } finally {
            entityManagerFactory.close();
        }
    }

    private static void findEmployeeCount() {
        System.out.println("-- Employee COUNT --");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
        Root<EmployeeForCriteriaApi> employee = query.from(EmployeeForCriteriaApi.class);
        query.select(criteriaBuilder.count(employee));
        TypedQuery<Long> typedQuery = entityManager.createQuery(query);
        Long count = typedQuery.getSingleResult();
        System.out.println(count);
        entityManager.close();
    }

    public static void persistEmployees() {
        EmployeeForCriteriaApi employee1 = employeeObj.create("Diana", 3000, LocalDate.of(1999, 6, 1));
        EmployeeForCriteriaApi employee2 = employeeObj.create("Rose", 4000, LocalDate.of(2005, 2, 1));
        EmployeeForCriteriaApi employee3 = employeeObj.create("Denise", 1500, LocalDate.of(2011, 1, 1));
        EmployeeForCriteriaApi employee4 = employeeObj.create("Mike", 2000, LocalDate.of(2007, 8, 1));
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(employee1);
        em.persist(employee2);
        em.persist(employee3);
        em.persist(employee4);
        em.getTransaction().commit();
        em.close();
        System.out.println("-- all employees --");
        System.out.println(employee1);
        System.out.println(employee2);
        System.out.println(employee3);
        System.out.println(employee4);
    }
}