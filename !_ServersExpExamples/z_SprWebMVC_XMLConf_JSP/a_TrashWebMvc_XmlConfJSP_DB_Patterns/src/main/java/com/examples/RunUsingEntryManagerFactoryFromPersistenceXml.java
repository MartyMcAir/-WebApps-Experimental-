package com.examples;

import com.model.EntitySame;
import com.model.Person;
import org.hibernate.jpa.HibernatePersistenceProvider;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Collections;

// EXCEPTION
//  No Persistence provider for EntityManager named com.baeldung.movie__catalog

// codeflow.site/ru/article/hibernate-entitymanager
// https://www.baeldung.com/hibernate-entitymanager
// https://habr.com/ru/sandbox/24224/
// https://www.infoworld.com/article/3373652/java-persistence-with-jpa-and-hibernate-part-1-entities-and-relationships.html
public class RunUsingEntryManagerFactoryFromPersistenceXml {
//    @PersistenceContext
//    EntityManager entityManager;

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.model.Person");
    ///////////////////
    private static HibernatePersistenceProvider hibernatePersistenceProvider = new HibernatePersistenceProvider();
    private static EntityManagerFactory emf2 = hibernatePersistenceProvider.createEntityManagerFactory("myUtilXml", Collections.emptyMap());

    public static void main(String[] args) {
        Person arti = new Person("Arti", 999);
        Person hulk = new Person("Hulk", 888);
        saveObj(arti);
        saveObj(hulk);

        System.out.println(getObj(new Person(), 1));


    }

    public void persistEmployees() {
        Person employee1 = new Person("Mike", 3000);
        EntityManager em = emf2.createEntityManager();
        em.getTransaction().begin();
        em.persist(employee1);
        em.getTransaction().commit();
        em.close();
    }

    public static void saveObj(EntitySame entity) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();


        em.persist(entity);
        em.getTransaction().commit();
    }

    public static EntitySame getObj(EntitySame entity, int id) {
        EntityManager em = getEntityManager();
        EntitySame item = (EntitySame) em.find(entity.getClass(), id);
        em.detach(item);
        return item;
    }


    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
