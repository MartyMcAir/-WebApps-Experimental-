package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SimpleServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> list = new ArrayList<String>();
        for(int i=0; i<10; i++){
            list.add("test str #"+i);
        }
        req.setAttribute("listAttributes", list);
        req.setAttribute("usenName", "TestName");
        req.getRequestDispatcher("/web/public_resources/js/myJs.js").forward(req, resp);
    }
}
