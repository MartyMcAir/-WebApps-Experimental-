package com.dao;

import com.model.Student;
import com.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class StudentDaoOneSession {
    // при таком подходе:
    // HibernateException: The internal connection pool has reached its maximum size and no connection is currently available!
    // ведь зайдя на страницу ничего не сделав (например на страницу добавления юзера),
    // не добавив не удалив юзера, а значит и не сделав closeSession().. _ в то время как сессия то открыта и beginTransaction()..
    // кол-во сессий..
    private Session session;
    private Transaction transaction = null;

    public StudentDaoOneSession() {
        this.session = getSession();
//        this.session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
//        startTransaction(); // авто старт beginTransaction() в конструкторе плохой выбор
    }

    public Transaction startAndGetTransaction() {
        if (transaction != null) return transaction;
        else this.transaction = session.beginTransaction();

        return transaction;
//        Transaction transaction = session.getTransaction();
//        if (transaction != null) this.transaction = transaction;
    }

    private Transaction getTransaction() {
        return transaction;
    }

    public Session getSession() {
        if (session != null) return session;

        Session currentSession = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        if (currentSession != null) return currentSession;

        this.session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return session;
    }

    public Student findById(int id) {
        return session.get(Student.class, id);
    }

    public void save(Student student) {
        session.save(student);
    }

    public void update(Student student) {
        session.update(student);
    }

    public void delete(Student student) {
        session.delete(student);
    }

    public List<Student> findAll() {
        return (List<Student>) session.createQuery("From student").list();
    }

    public Integer getMaxId() {
        return (Integer) session.createQuery("SELECT max(id) from student").getSingleResult();
    }

    public void closeSession() {
        this.transaction.commit();
        this.session.close();
    }
}