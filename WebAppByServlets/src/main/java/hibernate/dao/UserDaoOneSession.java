package hibernate.dao;


import hibernate.models.User;
import hibernate.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoOneSession {
    // при таком подходе:
    // HibernateException: The internal connection pool has reached its maximum size and no connection is currently available!
    // ведь зайдя на страницу ничего не сделав (например на страницу добавления юзера),
    // не добавив не удалив юзера, а значит и не сделав closeSession().. _ в то время как сессия то открыта и beginTransaction()..
    // кол-во сессий..
    private Session session;
    private Transaction transaction = null;

    public UserDaoOneSession() {
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

    public User findById(int id) {
        return session.get(User.class, id);
    }

    public void save(User user) {
        session.save(user);
    }

    public void update(User user) {
        session.update(user);
    }

    public void delete(User user) {
        session.delete(user);
    }

    public List<User> findAll() {
        return (List<User>) session.createQuery("From User").list();
    }

    public Integer getMaxId() {
        return (Integer) session.createQuery("SELECT max(id) from User").getSingleResult();
    }

    public void closeSession() {
        this.transaction.commit();
        this.session.close();
    }
}