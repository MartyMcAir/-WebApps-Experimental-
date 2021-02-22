package com.dao;

import com.model.Student;
import com.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;


public class StudentDao {
    private static SessionFactory sessionFactory;
    private static Session session;
    private static Transaction transaction;

    public StudentDao() {
        sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
    }

    public void closeSession() {
        transaction.commit();
        session.close();
    }

    /////////
    public Student findById(int id) {
        return session.get(Student.class, id);
    }

    public void save(Student user) {
        session.save(user);
    }

    public void update(Student user) {
        session.update(user);
    }

    public void delete(Student user) {
        session.delete(user);
    }

    public List<Student> findAll() {
        // IllegalArgumentException: org.hibernate.hql.internal.ast.QuerySyntaxException: hibernate_students is not mapped [FROM hibernate_students]
        // Когда вы пишете HQL, то обращаетесь не к таблице (это не SQL), а к сущности. По умолчанию entity name совпадает с именем класса.
        List<Student> students = (List<Student>)
                HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Student ").list();
        return students;
    }

    ////////////////
    public void showList() {
        List<Student> students = (List<Student>)
                session.createQuery("FROM Student ").list();
        for (Student student : students)
            System.out.println(student);
    }

    public Integer add(Student student) {
        Integer studentId = (Integer) session.save(student);
        return studentId;
    }

    public void updateNameById(int studentId, String name) {
        Student student = (Student) session.get(
                Student.class, studentId);
        student.setName(name);
        session.update(student);
    }

    public void remove(int studentId) {
        // IllegalStateException: Transaction already active
//        Transaction transaction = session.beginTransaction();
        Student student = (Student) session.get(
                Student.class, studentId);
        session.delete(student);
//        transaction.commit();
    }

    /////////////////

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void setSessionFactory(SessionFactory sessionFactory) {
        StudentDao.sessionFactory = sessionFactory;
    }

    public static Session getSession() {
        return session;
    }

    public static void setSession(Session session) {
        StudentDao.session = session;
    }

    public static Transaction getTransaction() {
        return transaction;
    }

    public static void setTransaction(Transaction transaction) {
        StudentDao.transaction = transaction;
    }
}