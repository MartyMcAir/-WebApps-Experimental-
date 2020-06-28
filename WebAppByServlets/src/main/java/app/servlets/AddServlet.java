package app.servlets;

import app.entities.AppUser;
import app.model.AppModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//  будет обрабатывать запросы, поступившие по адресу /add;
public class AddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // получаем данные из формы Post запроса страницы appView/add.jsp
        String name = req.getParameter("name");
        String password = req.getParameter("pass");
        AppUser user = new AppUser(name, password);
        AppModel model = AppModel.getInstance();
        model.add(user);

        // https://javarush.ru/groups/posts/356-sozdanie-prostogo-veb-prilozhenija-na-servletakh-i-jsp-chastjh-2
        // сеттим полученные атрибуты из формы
        req.setAttribute("userName", name);
        // и передаем инфу об этом в метод doGet(..)
        doGet(req, resp);
        // А метод doGet() уже передает управление во вьюху, куда и отправляет объект запроса с
        // прикрепленным именем добавленного пользователя в качестве атрибута.
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("Method Get From AddServlet");

        // My т.е. в мэппинге этому классу присвоенн URL адрес этому классу
        // Сервлет перехватывает запрос к указанному URL'у
        // обрабатывает здесь в методе, запрос к данному URL - атрибуты там и прочее
        //  и после перенаправляет на определенную jsp страницу через метод getRequestDispatcher(..)
        // и если надо сеттит туда какие либо данные

        // получаем из объекта запроса объект диспетчера запросов, куда передаем адрес jsp странички,
        // которой мы хотим передать управление;
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/appView/add.jsp");
        // используя полученный объект — передаем управление в указанную jsp страницу,
        // и не забываем вложить туда те объекты запроса и ответа, которые мы получили от Tomcat.
        requestDispatcher.forward(req, resp);
    }
}
