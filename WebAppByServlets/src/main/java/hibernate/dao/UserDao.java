package hibernate.dao;

import hibernate.models.Auto;
import hibernate.models.User;

import java.util.List;

public interface UserDao {

    User findById(int id);

    void save(User user);

    void delete(User user);

    void update(User user);

    List<User> findAll();

    Auto findAutoById(int id);
}
