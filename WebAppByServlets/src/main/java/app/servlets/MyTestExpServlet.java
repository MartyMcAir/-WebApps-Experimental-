package app.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class MyTestExpServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("<html>");
        // чтоб русск яз норм. выводился указываем кодировку
//        printWriter.println("<%@ tag pageEncoding=\"utf-8\" language=\"java\" %>");
        printWriter.println("<head><meta charset=\"utf-8\"></head>");
        printWriter.println("<h1>Hello this page from class MyTestExpServlet _ method doGet(..) </h1>");
        printWriter.println("<a href=\"..\"> to Main Page</a>");
        printWriter.println("<p></p>");
        printWriter.println("<a href=\"..\\appIndex.jsp\"> come Back to appIndex.jsp </a>");
        printWriter.println("<p></p>");

        // получаем Атрибуты с  GET формы пользователя ___ запроса юзера и выводим в консоль результ
        String num1 = req.getParameter("num1");
        String num2 = req.getParameter("num2");

        // http://localhost:8080/FirstWebApp_war_exploded/appIndex.jsp/JRush-MyTestExpServlet
        // Использование Integer.parseInt(..) почемуто все ломает
//        System.out.println(num1 + " + " + num2 + " = " + (Integer.parseInt(num1) + Integer.parseInt(num2)));
        System.out.println(num1 + " + " + num2 + " = " + num1 + num2);

        // My т.е. в мэппинге этому классу присвоенн URL адрес этому классу
        // Сервлет перехватывает запрос к указанному URL'у
        // обрабатывает здесь в методе, запрос к данному URL - атрибуты там и прочее
        //  и после перенаправляет на определенную jsp страницу через метод getRequestDispatcher(..)
        // и если надо сеттит туда какие либо данные

        HttpSession session = req.getSession();
        session.setAttribute("result", (num1+num2));

        // передаем место расположение JSP страницы, которую необходимо показать
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/appView/myList.jsp");

        // отправляем результ "вычислений" назад на страницу..
        req.setAttribute("result", (num1+num2));
        requestDispatcher.forward(req, resp);

        printWriter.println("</html>");
    }
}
