package com.services;

import com.model.Person;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

// persistence.xml в пути src/main/webapp/META-INF or..

// https://www.baeldung.com/jpa-hibernate-persistence-context
// used in data..*xml <bean id="entityManagerFactory" _ or <context:annotation-config/>
// https://pro-prof.com/forums/topic/%D0%B8%D1%81%D0%BF%D0%BE%D0%BB%D1%8C%D0%B7%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D0%B5-%D0%B0%D0%BD%D0%BD%D0%BE%D1%82%D0%B0%D1%86%D0%B8%D0%B8-persistencecontext-%D0%B2-spring
public class PerSerUsingEntityManagerByAnnotatPersistenceContext {
    //    @PersistenceContext(unitName = Constants.ADM_ENTITY_MANAGER_FACTORY_NAME)
    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    public static void main(String[] args) {
        PerSerUsingEntityManagerByAnnotatPersistenceContext instance = new PerSerUsingEntityManagerByAnnotatPersistenceContext();
//        personServiceUsingEntityManager.findEmployeeSalariesSum(); // NPE

        instance.insertWithoutTransaction(new Person("Iron", 999)); // NPE
    }

    public List<Person> getAllUsers() {
        TypedQuery<Person> query = em.createQuery(
                "SELECT p FROM Person p ORDER BY p.id", Person.class);
        return query.getResultList();
    }

    @Transactional
    public void delete(int userId) {
        Person user = em.getReference(Person.class, userId);
        em.remove(user);
    }

    @Transactional
    public void save(Person aUser) {
        em.persist(aUser);
    }
    ///

    public Person queryFindById(int id) {
//        System.out.println("ORMService queryFindUserById is called");
        return em.find(Person.class, id);
    }

    public List<Person> queryFindAllUsersJPA() {
//        System.out.println("ORMService queryfindAllUsersJPA is called");
        String query = "from User order by id";
        TypedQuery<Person> typedQuery = em.createQuery(query, Person.class);
        return typedQuery.getResultList();
    }

    public boolean insertUser(int id, String name, int age) {
        System.out.println("ORMExample insertUser is called");

        String qlString = "insert into Person (id,name,age) values (?,?,?)";
        Query query = em.createNativeQuery(qlString);
        query.setParameter(1, id);
        query.setParameter(2, name);
        query.setParameter(3, age);
        int result = query.executeUpdate();

        return result > 0;
    }

    public boolean deleteUser(int idUser) {
//        System.out.println("ORMExample deleteUser is called");
        String qlString = "delete from Person where id=?";
        Query query = em.createNativeQuery(qlString);
        query.setParameter(1, idUser);
        int result = query.executeUpdate();
        return result > 0;
    }

    public boolean updateUser(int id, String name) {
//        System.out.println("ORMService updateUser is called");
        String query = "update Person set name = ? where id = ?";
        Query nativeQuery = em.createNativeQuery(query);
        nativeQuery.setParameter(1, name);
        nativeQuery.setParameter(2, id);
        int result = nativeQuery.executeUpdate();
        return result > 0; // result show how many rows was updated.
    }

    ////
    @Transactional
    public Person insertWithTransaction(Person user) {
        em.persist(user);
        return user;
    }

    public Person insertWithoutTransaction(Person user) {
        em.persist(user);
        return user;
    }

    public Person find(long id) {
        return em.find(Person.class, id);
    }

    ////
    public List<Person> getListWhere(Long idMin) {
        return em.createQuery("SELECT p FROM Person p WHERE p.id > :paramId", Person.class)
                .setParameter("paramId", idMin).getResultList();
    }

    public void findEmployeeSalariesSum() {
        System.out.println("-- Employee SUM Salary --");
        Query query = em.createQuery("SELECT SUM(p.id) FROM Person p");
        Long result = (Long) query.getSingleResult();
        System.out.println(result);
        em.close();
    }
}