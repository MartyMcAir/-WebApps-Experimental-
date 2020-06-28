package app.servlets;

import app.model.AppModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

// будет обрабатывать запросы, поступившие по адресу /list.
public class ListServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("Method Get From ListServlet");

        // Благодаря тому, что при передаче процесса выполнения из сервлета во вьюху мы передаем туда
        // эти же объекты запроса и ответа, что получил сам сервлет, то и добавив наш список имен к объекту
        // запроса мы потом из этого объекта запроса во вьюхе сможем наш список имен пользователей и получить.
        AppModel model = AppModel.getInstance();
        List<String> names = model.list();
        req.setAttribute("userNames", names);

        // My т.е. в мэппинге этому классу присвоенн URL адрес этому классу
        // Сервлет перехватывает запрос к указанному URL'у
        // обрабатывает здесь в методе, запрос к данному URL - атрибуты там и прочее
        //  и после перенаправляет на определенную jsp страницу через метод getRequestDispatcher(..)
        // и если надо сеттит туда какие либо данные

        //  передает управление во вьюху list.jsp.
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/appView/list.jsp");
        requestDispatcher.forward(req, resp);
    }
}
