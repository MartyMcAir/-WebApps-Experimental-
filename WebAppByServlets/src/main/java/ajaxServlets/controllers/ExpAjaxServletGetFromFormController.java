package ajaxServlets.controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ExpAjaxServletGetFromFormController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // если не указывать кодировку то будут проблемы с выводом в консоль браузера..
        resp.setCharacterEncoding("UTF-8"); // при JSON обязательный параметр
        resp.setContentType("application/x-www-form-url");
        //        trashSomething(req, resp);
        String get_what = req.getParameter("get_what");

        try (PrintWriter writer = resp.getWriter()) {
            String responseText = "response from ExpAjaxServletGetFromForm: " + get_what;
//            System.out.println(responseText);
            writer.print(responseText);
        }
    }


































    private void trashSomething(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id_product = req.getParameter("id_product"), qty_product = req.getParameter("qty_product");
        String respText = "your data id_product is: " + id_product + "/n and qty_product is: " + qty_product;

        System.out.println("ExperimentalAjaxServlet getWhat from doGet: " + id_product);
        //        resp.setContentType("text/plain");

//        resp.setStatus(4);   // статус 4 - означает все ок
//        resp.setContentLengthLong(1_000_000);  // принимет размер в long, для более крупного контенте
//        resp.setLocale(new Locale("RU-ru"));  // верно ли указал?
//        resp.setContentType("text/html");   // по умолчанию html
//        resp.setCharacterEncoding("UTF-8");   //  а эо=то в виде простого текста


        // при первой же загрузке страницы отправляет ответ в виде текста а js выводит в консоль
//        try (ServletOutputStream outputStream = resp.getOutputStream()) {
//            byte[] bytes = respText.getBytes();  // text/plain - ставить необязат
//            resp.setContentLength(bytes.length);   // размер отправляемого контента в байтах?
//            outputStream.write(bytes);
//        }
        // тож самое и без установки text/plain
        try (PrintWriter writer = resp.getWriter()) {
            writer.println(respText);
        }

        // но если нажать на кнопку из <form..>, то вместо страницы будет текст
//        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/ajaxPages/ajaxGet.jsp");
//        requestDispatcher.forward(req, resp);
    }
}
