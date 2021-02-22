package com.dao;

import com.model.Student;
import com.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class StudentDaoImpl {
    public Student findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Student.class, id);
    }

    public void save(Student student) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(student);
        tx1.commit();
        session.close();
    }

    public void update(Student student) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(student);
        tx1.commit();
        session.close();
    }

    public void delete(Student student) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(student);
        tx1.commit();
        session.close();
    }

    public void deleteInCurrentSession(Student student) {
        Session currentSession = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        currentSession.close();

        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(student);
        tx1.commit();
        session.close();
    }

    public Student findAutoById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Student.class, id);
    }

    public List<Student> findAll() {
        List<Student> Students = (List<Student>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Student").list();
        return Students;
    }

    // ...................................................
    public void closeSession() {
        HibernateSessionFactoryUtil.getSessionFactory().close();
    }

    public Session getCurrentSession() {
        return HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
    }

    public void deleteStudentById(int id) {
        // след попытка
        Session session = getCurrentSession();
        //  Calling method 'get' is not valid without an active transaction
        Transaction tx1 = session.beginTransaction();  // сначала должна быть открыта транзакция и ток потом метод get()
        // след ошибка  attempt to create delete event with null entity
        Student student = session.get(Student.class, id);
        session.delete(student);
        tx1.commit();
        session.close();
    }

    public void saveStudentByIdAndSetFields(int id, String name, int age) {
        // HibernateException: Illegal attempt to associate a collection with two open session
//                service.closeSession();
//                service = new StudentService();
        // а теперь IllegalStateException: EntityManagerFactory is closed
//                Student = service.findStudent(Integer.parseInt(id));

        // IllegalStateException: Session/EntityManager is closed
        Session currentSession = getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        Student student = currentSession.get(Student.class, id);
        // org.hibernate.HibernateException: Don't change the reference to a collection with delete-orphan
        // enabled : hibernate.models.Student.autos
//        transaction.commit();
//        return Student;
        student.setName(name);  // обновляем данные юзера _ или сет тим новому юзеру
        student.setAge(age);
        currentSession.save(student);

        transaction.commit();
        currentSession.close();
    }
}