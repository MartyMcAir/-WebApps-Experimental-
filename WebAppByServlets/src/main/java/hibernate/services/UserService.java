package hibernate.services;

import hibernate.dao.UserDao;
import hibernate.dao.UserDaoImpl;
import hibernate.models.Auto;
import hibernate.models.User;
import org.hibernate.Session;

import java.util.List;

public class UserService {

    private final UserDao usersDao = new UserDaoImpl();

    public UserService() {
    }

    public User findUser(int id) {
        return usersDao.findById(id);
    }

    public void saveUser(User user) {
        usersDao.save(user);
    }

    public void deleteUser(User user) {
        usersDao.delete(user);
    }

    public void deleteUserInCurrentSession(User user) {
        usersDao.deleteInCurrentSession(user);
    }

    public void updateUser(User user) {
        usersDao.update(user);
    }

    public List<User> findAllUsers() {
        return usersDao.findAll();
    }

    public Auto findAutoById(int id) {
        return usersDao.findAutoById(id);
    }

    // ...................................................
    public void closeSession() {
        usersDao.closeSession();
    }

    public Session getCurrentSession() {
        return usersDao.getCurrentSession();
    }

    public void deleteUserById(int id) {
        usersDao.deleteUserById(id);
    }

    public void saveUserByIdAndSetFields(int id, String name, int age) {
        usersDao.saveUserByIdAndSetFields(id, name, age);
    }
}