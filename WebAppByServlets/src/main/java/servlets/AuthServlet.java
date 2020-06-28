package servlets;

import entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         // Проверяем авторизацию юзера
        HttpSession session = req.getSession();
        User current_user = (User) session.getAttribute("current_user");

        if(current_user == null){
            // response для анонимного пользователя
            // т.е. авторизацися или регистрация
            current_user = new User("TestName", "123456", 1);
            session.setAttribute("current_user", current_user.getId());
        }else {
            // response для авторизованного юзера _т.е. доступ к его персонализ страницам
            // и страницам доступных только после авторизации..
        }
    }
}
