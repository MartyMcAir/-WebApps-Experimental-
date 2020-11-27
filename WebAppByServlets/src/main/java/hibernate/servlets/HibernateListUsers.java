package hibernate.servlets;

import hibernate.dao.UserDaoOneSession;
import hibernate.models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

// http://localhost:8080/WebAppByServlets_war_exploded/deleteUser
//@WebServlet("/deleteUser")
public class HibernateListUsers extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDaoOneSession service = new UserDaoOneSession();
        service.startAndGetTransaction();
        // если переместить в doPost то не выводит список юзеров
        List<User> users = service.findAll();
        req.setAttribute("users", users);

        // а crud тут же не работают из-за..:
        // HibernateException: The internal connection pool has reached its maximum size and no connection is currently available!
        // получаем параметры
//        String method = req.getParameter("_method");
//        String id = req.getParameter("id");
//        String age = req.getParameter("age");
//        String name = req.getParameter("name");
//        if ("add".equals(method))          service.save(new User(name, Integer.parseInt(age)));
//        if ("delete".equals(method))          service.delete(service.findById(Integer.parseInt(id)));
//        if ("update".equals(method)) {
//            User user = service.findById(Integer.parseInt(id));
//            user.setName(name);
//            user.setAge(Integer.parseInt(age));
//            service.save(user);
//        }
        service.closeSession();

        RequestDispatcher dispatcher = req.getRequestDispatcher("/HibernatePages/hibernateListUsers.jsp");
        dispatcher.forward(req, resp);
    }

    // перемещений CRUD операций в doPost не помогло, идёт бесконечное добавление в бд и..:
    // ServletException: Servlet execution threw an exception _ StackOverflowError
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        UserDaoOneSession service = new UserDaoOneSession();
//
//        // получаем параметры
//        String method = req.getParameter("_method");
//        String id = req.getParameter("id");
//        String age = req.getParameter("age");
//        String name = req.getParameter("name");
//
//        service.startTransaction();
//        if ("add".equals(method)) {
//            System.out.println(" ____ add ____ ");
//            service.save(new User(name, Integer.parseInt(age)));
//        }
//        if ("delete".equals(method)) {
//            System.out.println(" ____ delete ____ ");
//            service.delete(service.findById(Integer.parseInt(id)));
//        }
//        if ("update".equals(method)) {
//            System.out.println(" ____ update ____ ");
//            User user = service.findById(Integer.parseInt(id));
//            user.setName(name);
//            user.setAge(Integer.parseInt(age));
//            service.save(user);
//        }
//        service.closeSession();
//
////        RequestDispatcher dispatcher = req.getRequestDispatcher("/HibernatePages/hibernateListUsers.jsp");
//        RequestDispatcher dispatcher = req.getRequestDispatcher("/HibernateListUsers");
//        dispatcher.forward(req, resp);
//    }
}