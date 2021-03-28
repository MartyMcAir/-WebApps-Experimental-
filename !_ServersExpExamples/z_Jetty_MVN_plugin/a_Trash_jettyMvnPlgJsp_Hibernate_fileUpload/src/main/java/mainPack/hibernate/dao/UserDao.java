package mainPack.hibernate.dao;

import mainPack.hibernate.models.Auto;
import mainPack.hibernate.models.User;

import java.util.List;

public interface UserDao {

    User findById(int id);

    void save(User user);

    void delete(User user);

    void update(User user);

    List<User> findAll();

    Auto findAutoById(int id);
}
