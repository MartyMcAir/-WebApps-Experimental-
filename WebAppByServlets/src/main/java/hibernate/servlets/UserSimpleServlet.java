package hibernate.servlets;

import hibernate.models.User;
import hibernate.services.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

// https://javarush.ru/groups/posts/523-vashe-pervoe-prilozhenie-s-ispoljhzovaniem-java-servletov
public class UserSimpleServlet extends HttpServlet {
    private UserService service = new UserService();

    public void init(ServletConfig servletConfig) {
        try {
            super.init(servletConfig);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // from ${contextPath}/hibernateP/users _ // тут вывод всех юзеров в список users
        System.out.println("___ UserSimpleServlet doGet() ");
        List<User> users = service.findAllUsers();
        req.setAttribute("users", users);
//        RequestDispatcher dispatcher = req.getRequestDispatcher("/showUsers.jsp"); // origin path
        RequestDispatcher dispatcher = req.getRequestDispatcher("/HibernatePages/showUsers.jsp");
//        RequestDispatcher dispatcher = req.getRequestDispatcher("/hibernateP/users");
//        RequestDispatcher dispatcher = req.getRequestDispatcher("/HibernatePages/usersFromJsp");
//        RequestDispatcher dispatcher = req.getRequestDispatcher("/MyDbPages/AllinOneCRUD.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // from addUser.jsp _ or Update User _ // тут CRUD операции с юзерами
        System.out.println("___ UserSimpleServlet doPost() ");
        // получаем параметры
        String id = req.getParameter("id");

        User user = null;
        String method = req.getParameter("_method");
        System.out.println("___ method is: " + method);
        if ("delete".equals(method)) {
            service.deleteUserById(Integer.parseInt(id)); // перенес код в .. UserDaoImpl ..
        } else {
            String name = req.getParameter("name");
            int age = Integer.parseInt(req.getParameter("age"));

            if ("add".equals(method)) {
                user = new User(name, age);
                //            assert user != null;
                user.setName(name);  // обновляем данные юзера _ или сет тим новому юзеру
                user.setAge(age);
                service.saveUser(user); // сохраняем в бд
            } else if ("update".equals(method)) {
                service.saveUserByIdAndSetFields(Integer.parseInt(id), name, age);
            }
        }

//        RequestDispatcher dispatcher = req.getRequestDispatcher("/HibernatePages/showUsers.jsp");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/HibernatePages/indexUser.jsp");
        dispatcher.forward(req, resp);
//        resp.sendRedirect("/users"); // origin path
//        resp.sendRedirect("/HibernatePages/usersFromJsp");
//        resp.sendRedirect("/MyDbPages/AllinOneCRUD.jsp");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("___ UserSimpleServlet doPut() ");
        int id = Integer.parseInt(req.getParameter("id"));
        User user = service.findUser(id);
        user.setName(req.getParameter("name"));
        user.setAge(Integer.parseInt(req.getParameter("age")));
        // при попытке обновить данные из формы обновления (updateUser.jsp) вылетает _
        // HibernateException: Illegal attempt to associate a collection with two open sessions. Collection :
        service.updateUser(user);
        resp.sendRedirect("/users"); // origin path
//        resp.sendRedirect("/HibernatePages/usersFromJsp");
//        resp.sendRedirect("/MyDbPages/AllinOneCRUD.jsp");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("___ UserSimpleServlet doDelete() ");
        int id = Integer.parseInt(req.getParameter("id"));
        service.deleteUser(service.findUser(id));
        resp.sendRedirect("/users"); // origin path
//        resp.sendRedirect("/HibernatePages/usersFromJsp");
//        resp.sendRedirect("/MyDbPages/AllinOneCRUD.jsp");
    }
}