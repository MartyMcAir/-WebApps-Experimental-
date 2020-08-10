package hibernate.dao;


import hibernate.models.Auto;
import hibernate.models.User;
import hibernate.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoImpl implements UserDao {
    public User findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(User.class, id);
    }

    public void save(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        session.close();
    }

    public void update(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(user);
        tx1.commit();
        session.close();
    }

    public void delete(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(user);
        tx1.commit();
        session.close();
    }

    public void deleteInCurrentSession(User user) {
        Session currentSession = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        currentSession.close();

        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(user);
        tx1.commit();
        session.close();
    }

    public Auto findAutoById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Auto.class, id);
    }

    public List<User> findAll() {
        List<User> users = (List<User>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From User").list();
        return users;
    }

    // ...................................................
    public void closeSession() {
        HibernateSessionFactoryUtil.getSessionFactory().close();
    }

    public Session getCurrentSession() {
        return HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
    }

    public void deleteUserById(int id) {
        // след попытка
        Session session = getCurrentSession();
        //  Calling method 'get' is not valid without an active transaction
        Transaction tx1 = session.beginTransaction();  // сначала должна быть открыта транзакция и ток потом метод get()
        // след ошибка  attempt to create delete event with null entity
        User user = session.get(User.class, id);
        session.delete(user);
        tx1.commit();
        session.close();
    }

    public void saveUserByIdAndSetFields(int id, String name, int age) {
        // HibernateException: Illegal attempt to associate a collection with two open session
//                service.closeSession();
//                service = new UserService();
        // а теперь IllegalStateException: EntityManagerFactory is closed
//                user = service.findUser(Integer.parseInt(id));

        // IllegalStateException: Session/EntityManager is closed
        Session currentSession = getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        User user = currentSession.get(User.class, id);
        // org.hibernate.HibernateException: Don't change the reference to a collection with delete-orphan
        // enabled : hibernate.models.User.autos
//        transaction.commit();
//        return user;
        user.setName(name);  // обновляем данные юзера _ или сет тим новому юзеру
        user.setAge(age);
        currentSession.save(user);

        transaction.commit();
        currentSession.close();
    }
}