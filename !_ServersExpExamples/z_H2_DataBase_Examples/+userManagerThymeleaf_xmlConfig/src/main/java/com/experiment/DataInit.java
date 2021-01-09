package com.experiment;

import com.dao.UserDAO;
import com.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// ApplicationRunner only in Spring Boot
//public class DataInit implements ApplicationRunner {
@Component
public class DataInit {

    private UserDAO personDAO;

    @Autowired
    public DataInit(UserDAO personDAO) {
        this.personDAO = personDAO;
    }

    public static void main(String[] args) {
        DataInit dataInit = new DataInit(new UserDAO());
        dataInit.run();
    }

    //    @Override
//    public void run(ApplicationArguments args) throws Exception {
//    public void run(Objects args) throws Exception {
    public void run() {
        long count = personDAO.getAllUsers().size();
        if (count == 0) {
            User p1 = new User("John 1");
            User p2 = new User("John 2");
            personDAO.save(p1);
            personDAO.save(p2);
        }
    }
}