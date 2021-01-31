package hbExperiment.dao;

import hbExperiment.model.HibernateDevelopersEntity;
import util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class HbDevelopersDaoImpl {
    private static SessionFactory sessionFactory;

    {
//        sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
    }

    public HibernateDevelopersEntity findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(HibernateDevelopersEntity.class, id);
    }

    public void save(HibernateDevelopersEntity user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        session.close();
    }

    public void update(HibernateDevelopersEntity user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(user);
        tx1.commit();
        session.close();
    }

    public void delete(HibernateDevelopersEntity user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(user);
        tx1.commit();
        session.close();
    }

    public List<HibernateDevelopersEntity> findAll() {
        // IllegalArgumentException: org.hibernate.hql.internal.ast.QuerySyntaxException: hibernate_developers is not mapped [FROM hibernate_developers]
        // Когда вы пишете HQL, то обращаетесь не к таблице (это не SQL), а к сущности. По умолчанию entity name совпадает с именем класса.
        List<HibernateDevelopersEntity> users = (List<HibernateDevelopersEntity>)
                HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From HibernateDevelopersEntity ").list();
        return users;
    }

    ////////////////
    public void listDevelopers() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<HibernateDevelopersEntity> developers = (List<HibernateDevelopersEntity>)
                session.createQuery("FROM HibernateDevelopersEntity ").list();
        for (HibernateDevelopersEntity developer : developers) {
            System.out.println(developer);
            System.out.println("\n================\n");
        }
    }

    public Integer addDeveloper(String firstName, String lastName, String specialty, int experience) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Integer developerId = null;

        transaction = session.beginTransaction();
        HibernateDevelopersEntity developer = new HibernateDevelopersEntity(firstName, lastName, specialty, experience);
        developerId = (Integer) session.save(developer);
        transaction.commit();
        return developerId;
    }

    public void updateDeveloper(int developerId, int experience) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        HibernateDevelopersEntity developer = (HibernateDevelopersEntity) session.get(
                HibernateDevelopersEntity.class, developerId);
        developer.setExperience(experience);
        session.update(developer);
        transaction.commit();
    }

    public void removeDeveloper(int developerId) {
//        Session session = sessionFactory.openSession();
        Session session = sessionFactory.getCurrentSession();
        // HibernateException: The internal connection pool has reached its maximum size and no connection is currently available!
        Transaction transaction = session.beginTransaction();
        HibernateDevelopersEntity developer = (HibernateDevelopersEntity) session.get(
                HibernateDevelopersEntity.class, developerId);
        session.delete(developer);
        transaction.commit();
    }

    ///////////////

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void setSessionFactory(SessionFactory sessionFactory) {
        HbDevelopersDaoImpl.sessionFactory = sessionFactory;
    }
}