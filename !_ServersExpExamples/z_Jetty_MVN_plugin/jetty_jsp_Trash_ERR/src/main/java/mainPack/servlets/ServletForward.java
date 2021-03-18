package mainPack.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/forwardServlet"})
public class ServletForward extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.getWriter().write("<html><ht>Hello from MyServlet 38 </h1></html>");

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/jsp/one.jsp");
        requestDispatcher.forward(req, resp);
    }
}