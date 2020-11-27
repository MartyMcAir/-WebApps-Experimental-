package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet(urlPatterns = {"/GetFileFromUser"})
// указываем куда сохранять файл
@MultipartConfig(location = "C:\\Users\\marty\\OneDrive\\Dropbox\\GitHub\\-WebApps-Experimental-\\WebAppByServlets\\src\\main\\resources\\userFiles")
public class FileServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        final ServletInputStream inputStream = req.getInputStream(); // можно и так просто записывать

        // https://www.youtube.com/watch?v=Ic-kWTx0VqU&list=PLU2ftbIeotGoQGD51e0qb98lE0xhgNDF1&index=2
        // 18я минута
        for (Part item : req.getParts()) {
//            item.getName();
            // UUID- имя для файла во избежание коллизий _ и это же имя с путем обычно сохраняют в БД
//            item.write(UUID.randomUUID().toString());
            // или вот так имя как имя файла юзера отправившего файл
            item.write(item.getSubmittedFileName());
        }

        final RequestDispatcher requestDispatcher = req.getRequestDispatcher("/simpleJSP/getFileFromUserExamplePage.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final RequestDispatcher requestDispatcher = req.getRequestDispatcher("/simpleJSP/getFileFromUserExamplePage.jsp");
        requestDispatcher.forward(req, resp);
    }
}