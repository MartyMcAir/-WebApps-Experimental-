package hibernate.servlets;

import hibernate.dao.UserDaoOneSession;
import hibernate.models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HibernateUsersCrud extends HttpServlet {

    // HTTP method POST is not supported by this URL _ wtf!?
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDaoOneSession service = new UserDaoOneSession();

        // получаем параметры
        String method = req.getParameter("_method");
        String id = req.getParameter("id");
        String age = req.getParameter("age");
        String name = req.getParameter("name");

        service.startAndGetTransaction();
        switch (method) {
            case "add":
                service.save(new User(name, Integer.parseInt(age)));
// работает, коммит можно делать в конце, после всех операций, а не после каждой
//                service.save(new User(name, 111));
                break;
            case "delete":
                service.delete(service.findById(Integer.parseInt(id)));
                break;
            case "update":
                User user = service.findById(Integer.parseInt(id));
                user.setName(name);
                user.setAge(Integer.parseInt(age));
                service.save(user);
                break;
        }
        service.closeSession();

//        RequestDispatcher dispatcher = req.getRequestDispatcher("/HibernateListUsers");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/HibernateAjaxListUser");
        dispatcher.forward(req, resp);
    }
}