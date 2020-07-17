package ajaxServlets;

import MyDB_CRUD.SQL_Operations;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class ExperimentalAjaxServletPostJson extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SQL_Operations sql_operations = new SQL_Operations();
        String get_what = req.getParameter("get_what");
//        String get_what = (String) req.getAttribute("get_what");

        System.out.println("ExperimentalAjaxServletPost get_what from doPost: " + get_what);

//        resp.setContentType("text/plain"); // а иначе в браузере Firefox синтаксич ошибка..
//        resp.setContentType("text/htm"); // а иначе в браузере Firefox синтаксич ошибка..
        resp.setCharacterEncoding("UTF-8"); // при JSON обязательный параметр
        resp.setContentType("application/json");


        try (PrintWriter writer = resp.getWriter()) {
//            String parse = JSONWriter.parse(Context.getContext(), "code", "name", true);
//        JSONObject jsonObj = (JSONObject) JSONValue.parse(req.getParameter("get_what")); // NPE
//        System.out.println(jsonObj.get("message"));

            ///////////
//            jacksonExample(writer);   // don't work

            jsonSimpleExample(writer);   // WORK
//            writer.write(getJsonString());   // WORK
        }
    }

    private void jacksonExample(PrintWriter writer) throws IOException {
        StringWriter catResponse = getCatResponse();
        writer.print(catResponse.toString());
    }

    private void jsonSimpleExample(PrintWriter writer) {
        JSONObject obj = new JSONObject();
        obj.put("message", "hello from server");
        writer.print(obj);
    }

    public String getJsonString() {
        return "{\n" +
                "  \"array\": [\n" +
                "    1,\n" +
                "    2,\n" +
                "    3\n" +
                "  ],\n" +
                "  \"boolean\": true,\n" +
                "  \"color\": \"gold\",\n" +
                "  \"null\": null,\n" +
                "  \"number\": 123,\n" +
                "  \"object\": {\n" +
                "    \"a\": \"b\",\n" +
                "    \"c\": \"d\"\n" +
                "  },\n" +
                "  \"string\": \"Hello World\"\n" +
                "}";
    }

    public StringWriter getCatResponse() throws IOException {
//        CatForJson cat2 = new CatForJson(); //создание объекта для сериализации в JSON
//        cat2.name = "Murka2";
//        cat2.age = 5;
//        cat2.weight = 4;
        // писать результат сериализации будем во Writer(StringWriter)
        StringWriter writer2 = new StringWriter();

        // это объект Jackson, который выполняет сериализацию
//        ObjectMapper mapper2 = new ObjectMapper();

        /////???
//        SimpleModule module = new SimpleModule();
//        mapper2.registerModule(module);

        // сама сериализация: 1-куда, 2-что
//        mapper2.writeValue(writer2, cat2);

//        mapper2.writeValueAsString(cat2);
        // преобразовываем все записанное во StringWriter в строку
//        return writer2.toString();   // возвращаем готовый JSON
        return writer2;
    }
}