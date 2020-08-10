package hibernate.dao;

import hibernate.models.Auto;
import hibernate.models.User;
import org.hibernate.Session;

import java.util.List;

public interface UserDao {

    User findById(int id);

    void save(User user);

    void delete(User user);

    void deleteInCurrentSession(User user);

    void update(User user);

    List<User> findAll();

    Auto findAutoById(int id);

    // ...................................................
    void closeSession();

    Session getCurrentSession();

    void deleteUserById(int id);

    void saveUserByIdAndSetFields(int id, String name, int age);
}
