package servlets;

import entity.Cart;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class SessionCartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Получаем сессию
        HttpSession session = req.getSession();
        // Получаем корзину клиента
        Cart cart = (Cart) session.getAttribute("cart");

        // получаем параметры если они есть
        String name = req.getParameter("name");

        int quantity = 0;
        try {
            quantity = Integer.parseInt(req.getParameter("quantity"));
        } catch (NumberFormatException ignore) {

        }

        // Записываем в корзину если пуста
        if (cart == null) cart = new Cart(name, quantity);
        // Сеттим новые атрибуты
        session.setAttribute("cart", cart);

        // перенаправляем (Forward) на JSP файл
        getServletContext().getRequestDispatcher("/showCart.jsp").forward(req, resp);
    }
}
