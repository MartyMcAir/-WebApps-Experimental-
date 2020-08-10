package hibernate.servlets;

import hibernate.dao.UserDaoOneSession;
import hibernate.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class HibernateAjaxCrudUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDaoOneSession service = new UserDaoOneSession();
        User user = new User();

        // получаем параметры
        String method = req.getParameter("_method");
        String id = req.getParameter("id");

        List<String> allFieldsNames = user.getAllFieldsNames();
        List<String> newFieldsParams = new ArrayList<>();
        for (String item : allFieldsNames) {
            String parameter = req.getParameter(item);
            newFieldsParams.add(parameter);
        }

//        List<User> results = service.getSession()
//                .createCriteria(User.class)
//                .setProjection(Projections.max("id")).list();
////                .add(Restrictions.between("id", 30000, 40000)).list();
//        System.out.println(results.toString());

        service.startAndGetTransaction();
        resp.setStatus(200);
        switch (method) {
            case "add":
                PrintWriter writer = resp.getWriter();
                user.setParamsAndLoadAllFields(newFieldsParams);
                service.save(user);
// работает, коммит можно делать в конце, после всех операций, а не после каждой
//                service.save(new User(name, 111));
                System.out.println("operating add in success");
                writer.print(service.getMaxId());
                writer.close();
                service.closeSession();
                break;
            case "delete":
                PrintWriter writer1 = resp.getWriter();
                service.delete(service.findById(Integer.parseInt(id)));
                writer1.print("operating delete in success");
                writer1.close();
                service.closeSession();
                break;
            case "update":
                user = service.findById(Integer.parseInt(id));
                user.setParamsAndLoadAllFields(newFieldsParams);
                service.save(user);
                // closeSession() в каждом case блоке, а иначе redirect будет с Exception
                service.closeSession();

                // при форварде проблема с русской кириллицей на странице
                // и проблема была в использовании resp.getWriter()..
//                writer.print("operating update in success");
//                req.setCharacterEncoding("UTF-8");
//                resp.setContentType("text/html; charset=utf-8");
                // !
//                resp.sendRedirect("./HibernateAjaxListUser"); // тогда поле пути в браузере будет чисто
//                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/HibernateAjaxListUser");
//                requestDispatcher.forward(req, resp); // а при форварде с кучей ?key=value&..
                break;
//            default:   // при попытке повторно добавить из формы _ далее не дает добавить
//                resp.setStatus(501);
        }
    }
}
