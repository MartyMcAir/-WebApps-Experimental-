package com.ajaxServlets.controllers;

import com.ajaxServlets.JSON_Operations;
import com.utils.SQL_Operations;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

public class ExpAjaxServletGetJson extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ExpAjaxServletGetJson running ");
        SQL_Operations sql_operations = new SQL_Operations();
        JSON_Operations json_Operations = new JSON_Operations();

        // получаем ResultSet SELECT из табицы books
        ResultSet resBooks = sql_operations.getResultSetByTable("books");
        // Конвертим ResultSet из БД в в Список JSONObject
//        List<JSONObject> listOfJsonObjects = json_Operations.getFormattedResult(resBooks);
        JSONArray listOfJsonObjects = json_Operations.getJsonFromResultSetMyEdition(resBooks);

        // выводим для теста, что запросил (юзая JS) клиент
        String get_what = req.getParameter("get_what");
        System.out.println("ExpAjaxServletGetJson: get_what: " + get_what);

        // Настраиваем наш ответ кодировку и тип контента ответа
        resp.setCharacterEncoding("UTF-8"); // при JSON обязательный параметр
        // а иначе в браузере Firefox синтаксич ошибка..
        resp.setContentType("application/json"); // text/plain text/htm application/x-www-form-url

        try (PrintWriter writer = resp.getWriter()) {
//            String parse = JSONWriter.parse(Context.getContext(), "code", "name", true);
//        JSONObject jsonObj = (JSONObject) JSONValue.parse(req.getParameter("get_what")); // NPE
//        System.out.println(jsonObj.get("message"));

            ///////////
//            jacksonExample(writer);   // don't work

            // кидаем в JSONObject наш список JSON объектов
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("booksJson", listOfJsonObjects);

            writer.print(jsonObject.toJSONString()); // отправляем ответ клиенту

//            json_Operations.printByJsonSimple(writer);   // WORK
//            json_Operations.jsonSimpleExample(writer);   // WORK
//            writer.write(json_Operations.getJsonStrExample());   // WORK
//            writer.write(json_Operations.getJsonStrAmazon());   // WORK is very good
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}