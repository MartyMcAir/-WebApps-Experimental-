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

public class HibernateAjaxListUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDaoOneSession service = new UserDaoOneSession();
        service.startAndGetTransaction();
        // если переместить в doPost то не выводит список юзеров
        List<User> users = service.findAll();
        req.setAttribute("users", users);
        service.closeSession();
        RequestDispatcher dispatcher = req.getRequestDispatcher("/ajaxPages/ajaxHibernateListUsers.jsp");
        dispatcher.forward(req, resp);
    }
}
