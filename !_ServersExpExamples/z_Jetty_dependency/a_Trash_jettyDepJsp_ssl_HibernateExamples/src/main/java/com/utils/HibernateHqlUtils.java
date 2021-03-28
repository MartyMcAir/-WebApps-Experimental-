package com.utils;

import com.model.Student;
import com.model.StudentEntityUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

// https://www.baeldung.com/hibernate-aggregate-functions
public class HibernateHqlUtils {
    private Session session;
    private Transaction transaction;

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    //    @BeforeClass
    public void setup() {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();

        for(Student student : StudentEntityUtil.getStudentsList()){
            session.save(student);
        }

        transaction.commit();
        session.close();  // added by me
    }

    // для aggregate запросов транзакции не нужны _ это ведь не проведение транзакции а лишь запрос на получение..
    public void openSession() {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
    }

    public void closeSession() {
        session.close();
    }

    public void openSessionAndTransaction() {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
    }

    public void closeSessionAndTransaction() {
        transaction.commit();
        session.close();
    }

    // Aggregate Functions
    public int getMin(String fieldName, String tableName) {
        return (int) session.createQuery("SELECT min(" + fieldName + ") from " + tableName).getSingleResult();
    }

    public int getMax(String fieldName, String tableName) {
        return (int) session.createQuery("SELECT max(" + fieldName + ") from " + tableName).getSingleResult();
    }

    public long getSum(String fieldName, String tableName) {
        return (long) session.createQuery("SELECT sum(" + fieldName + ") from " + tableName).getSingleResult();
    }

    public double getAvg(String fieldName, String tableName) {
        return (double) session.createQuery("SELECT avg(" + fieldName + ") from " + tableName).getSingleResult();
    }

    //    public long getCount(String fieldName, String tableName) {
//        return (int) session.createQuery("SELECT count(" + fieldName + ") from " + tableName).getSingleResult();
    public long getCount(String fieldName, String tableName) {
        return (long) session.createQuery("SELECT count(*) from " + tableName).getSingleResult();
    }
}