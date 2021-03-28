package com.utils;

import com.model.Student;
import com.model.StudentEntityUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

// https://www.logicbig.com/tutorials/java-ee-tutorial/jpa/criteria-api-aggregation-methods.html
public class CriteriaUtilsPersistenceUnit {
    private static EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("myUnitXml");

    public static void main(String[] args) {
        try {
            // persistence.PersistenceException: No Persistence provider for EntityManager named myUnitXml
            persist();
//            findCount();
        } finally {
            entityManagerFactory.close();
        }
    }

    private static void findCount() {
        System.out.println("-- COUNT --");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
        Root<Student> employee = query.from(Student.class);
        query.select(criteriaBuilder.count(employee));
        TypedQuery<Long> typedQuery = entityManager.createQuery(query);
        Long count = typedQuery.getSingleResult();
        System.out.println(count);
        entityManager.close();
    }

    public static void persist() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        for (Student student : StudentEntityUtil.getStudentsList()) {
            em.persist(student);
        }

        em.getTransaction().commit();
        em.close();
        System.out.println("-- all --");
        for (Student student : StudentEntityUtil.getStudentsList()) {
            System.out.println(student);
        }
    }
}