package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.locks.ReadWriteLock;

public class RedirectForward extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        printHtml(resp, req.getParameter("pageName"));
        String pageName = req.getParameter("pageName");
        if (!pageName.contains("/")) {
            resp.sendRedirect("https://www.google.com/search?q=" + pageName);
            return;
        }
        runForward(req, resp);
//        getServletContext().getRequestDispatcher("/myPage.jsp").forward(req, resp);
    }

    private void runForward(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //        String jspPage = "/pageOne.jsp";
//        String jspPage = "testForward1.jsp";
        String jspPage = req.getParameter("pageName");
        RequestDispatcher requestDispatcher1 = forwardV1(jspPage);
        RequestDispatcher requestDispatcher2 = forwardV2(jspPage, req);
        RequestDispatcher requestDispatcher3 = forwardV3(jspPage, req);
        requestDispatcher1.forward(req, resp);
    }

    private void printHtml(HttpServletResponse resp, String printText) throws IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("<html>");
        writer.println("<h1>Your parameter pageName is: " + printText + "</h1>");
        writer.println("<a href=\".\">come Back to Main Page</a>");
        writer.println("</html>");
    }

    public RequestDispatcher forwardV1(String jspPage) {
        ServletContext servletContext = getServletContext();
        return servletContext.getRequestDispatcher(jspPage);
    }

    public RequestDispatcher forwardV2(String jspPage, HttpServletRequest request) {
        ServletContext servletContext = request.getServletContext();
        return servletContext.getRequestDispatcher(jspPage);
    }

    public RequestDispatcher forwardV3(String jspPage, HttpServletRequest request) {
        return request.getRequestDispatcher(jspPage);
    }
}
