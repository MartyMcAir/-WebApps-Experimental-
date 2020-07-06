package controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.NameService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// указываем что наш сервлет доступен по URL адресу /search
@WebServlet("/search")
public class SearchController extends HttpServlet {
    // наш логгер
    private static final long serialVersionUID = 2847786613235117603L;
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.debug("doGet search");
        // делаем лог, и переадресуем на JSP страницу рельтатов поиска
        req.getRequestDispatcher("/WEB-INF/JSP/search-form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.debug("doPost search: {}", req.getParameterMap());
        String name = req.getParameter("query");

        if (!isValid(name)) {
            // если запрос НЕ валиден, ставим атрибут invalid в true и форвард
            req.setAttribute("invalid", Boolean.TRUE);
            req.getRequestDispatcher("/WEB-INF/JSP/search-form.jsp").forward(req, resp);
            return;
        }
        // а иначе запрос валиден, значит делаем запрос как бы в поисковый сервис
        // и возвращаем результ в ключ атрибута name _ и форвард
        String result = NameService.getInstance().convertName(name);
        req.setAttribute("name", result);
        req.getRequestDispatcher("/WEB-INF/JSP/search-result.jsp").forward(req, resp);
    }

    private boolean isValid(String name) {
        return name != null && name.trim().length() != 0;
    }
}