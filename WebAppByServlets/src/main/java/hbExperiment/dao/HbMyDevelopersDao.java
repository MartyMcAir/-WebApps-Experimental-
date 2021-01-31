package hbExperiment.dao;

import hbExperiment.model.HibernateDevelopersEntity;
import util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class HbMyDevelopersDao {
    private static SessionFactory sessionFactory;
    private static Session session;
    private static Transaction transaction;

    public HbMyDevelopersDao() {
        sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
    }

    public void closeSession() {
        transaction.commit();
        session.close();
    }

    /////////
    public HibernateDevelopersEntity findById(int id) {
        return session.get(HibernateDevelopersEntity.class, id);
    }

    public void save(HibernateDevelopersEntity user) {
        session.save(user);
    }

    public void update(HibernateDevelopersEntity user) {
        session.update(user);
    }

    public void delete(HibernateDevelopersEntity user) {
        session.delete(user);
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
        List<HibernateDevelopersEntity> developers = (List<HibernateDevelopersEntity>)
                session.createQuery("FROM HibernateDevelopersEntity ").list();
        for (HibernateDevelopersEntity developer : developers)
            System.out.println(developer);
    }

    public Integer addDeveloper(HibernateDevelopersEntity developer) {
        Integer developerId = (Integer) session.save(developer);
        return developerId;
    }

    public void updateDeveloperExpirience(int developerId, int experience) {
        HibernateDevelopersEntity developer = (HibernateDevelopersEntity) session.get(
                HibernateDevelopersEntity.class, developerId);
        developer.setExperience(experience);
        session.update(developer);
    }

    public void removeDeveloper(int developerId) {
        // IllegalStateException: Transaction already active
//        Transaction transaction = session.beginTransaction();
        HibernateDevelopersEntity developer = (HibernateDevelopersEntity) session.get(
                HibernateDevelopersEntity.class, developerId);
        session.delete(developer);
//        transaction.commit();
    }

    /////////////////

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void setSessionFactory(SessionFactory sessionFactory) {
        HbMyDevelopersDao.sessionFactory = sessionFactory;
    }

    public static Session getSession() {
        return session;
    }

    public static void setSession(Session session) {
        HbMyDevelopersDao.session = session;
    }

    public static Transaction getTransaction() {
        return transaction;
    }

    public static void setTransaction(Transaction transaction) {
        HbMyDevelopersDao.transaction = transaction;
    }
}