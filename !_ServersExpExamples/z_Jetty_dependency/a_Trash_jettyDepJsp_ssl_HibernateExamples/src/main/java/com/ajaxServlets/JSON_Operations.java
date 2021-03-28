package com.ajaxServlets;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JSON_Operations {
    public static void main(String[] args) {
        JSON_Operations jsonOp = new JSON_Operations();
        JSONObject obj = jsonOp.getJsonObj();
        List<String> allKeys = jsonOp.getAllKeys(obj);
        // name //messages //age
//        for (String item : allKeys) System.out.println(item);

        List<Object> allKeys2 = jsonOp.getAllSubKeys(obj);
        // printed all elements for 2 floor
        jsonOp.printKeyValueFromListOfJson(obj, allKeys2);
    }


    // https://stackoverrun.com/ru/q/13050248
    //returns all keys from JSON Objects
    public List<String> getAllKeys(JSONObject jsonObject) {
        List<String> keys = new ArrayList<>();

        Iterator<String> iterator = jsonObject.keySet().iterator();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            keys.add(key);
        }
        return keys;
    }

    // My modification
    public List<Object> getAllSubKeys(JSONObject jsonObject) {
        List<Object> keys = new ArrayList<>();

        Iterator<Object> iterator = jsonObject.keySet().iterator();
        while (iterator.hasNext()) {
            Object key = (Object) iterator.next();
            keys.add(key);
        }
        return keys;
    }

    // https://stackoverflow.com/questions/6514876/most-efficient-conversion-of-resultset-to-json
    /*
     * Convert ResultSet to a common JSON Object array
     * Result is like: [{"ID":"1","NAME":"Tom","AGE":"24"}, {"ID":"2","NAME":"Bob","AGE":"26"}, ...]
     */
    public List<JSONObject> getFormattedResult(ResultSet rs) {

        List<JSONObject> resList = new ArrayList<>();
        try (ResultSet rsIs = rs) {
            // get column names
            ResultSetMetaData rsMeta = rsIs.getMetaData();
            int columnCnt = rsMeta.getColumnCount();
            List<String> columnNames = new ArrayList<>();
            for (int i = 1; i <= columnCnt; i++) {
                columnNames.add(rsMeta.getColumnName(i).toUpperCase());
            }

            while (rsIs.next()) { // convert each object to an human readable JSON object
                JSONObject obj = new JSONObject();
                for (int i = 1; i <= columnCnt; i++) {
                    String key = columnNames.get(i - 1);
                    String value = rs.getString(i);
                    obj.put(key, value);
                }
                resList.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resList;
    }

    // My Modification
    public JSONArray getJsonFromResultSetMyEdition(ResultSet rs) {
        JSONArray resList = new JSONArray();

        // на случай если таблица пуста передаем список имен колонок в JSON формате
        // идет первым в списке
//        List<String> booksNames = sql_operations.getAllColumnsNamesFromTable("books");
//        JSONObject objRow = new JSONObject();
//        for (int j = 0; j < booksNames.size(); j++) {
//            objRow.put(booksNames.get(j), " ");
//        }
//        JSONArray newJsonList = new JSONArray();
//        newJsonList.add(objRow);
//
//        listOfJsonObjects.add(objRow);


        try (ResultSet rsIs = rs) {
            // get column names
            ResultSetMetaData rsMeta = rsIs.getMetaData();
            int columnCnt = rsMeta.getColumnCount();

            List<String> columnNames = new ArrayList<>();

            // первый массив просто пустые имена
            // на случай пустой таблицы чтоб, возможно было создать Head таблицы
            JSONObject objNames = new JSONObject();
            for (int i = 1; i <= columnCnt; i++) {
                String currentName = rsMeta.getColumnName(i).toUpperCase();
                columnNames.add(currentName);
                objNames.put(currentName, " ");
            }
            resList.add(objNames);

            int k = 0;
            while (rsIs.next()) { // convert each object to an human readable JSON object
                JSONObject obj = new JSONObject();
                for (int i = 1; i <= columnCnt; i++) {
                    String key = columnNames.get(i - 1);
                    String value = rs.getString(i);
                    obj.put(key, value);
                }
                resList.add(obj);
//                resList.put(++k, obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resList;
    }

    // JSON SIMPLE _ JSONObject _ JSONArray _  org.json.simple.*..
    public void jsonSimpleExample(PrintWriter writer) {
        JSONObject obj = new JSONObject();
        obj.put("message", "hello from server");
        writer.print(obj);
    }

    public void printByJsonSimple(PrintWriter writer) {
        JSONObject obj = new JSONObject();
        obj.put("name", "mkyong.com");
        obj.put("age", 100);

        JSONArray list = new JSONArray();
        list.add("msg 1");
        list.add("msg 2");
        list.add("msg 3");

        obj.put("messages", list);
        writer.print(obj.toJSONString());
    }

    // MY ...................................................................
    ///////////////////////////
    public JSONObject getJsonObj() {
        JSONObject obj = new JSONObject();
        obj.put("name", "mkyong.com");
        obj.put("age", 100);

        JSONArray list = new JSONArray();
        list.add("msg 1");
        list.add("msg 2");
        list.add("msg 3");

        obj.put("messages", list);
        return obj;
    }

    public void printKeyValueFromListOfJson(JSONObject obj, List<Object> allKeys2) {
        for (Object item : allKeys2) {
//            if (item instanceof JSONArray) {
            Object value = obj.get(item);
            if (value instanceof JSONArray) {
                JSONArray jsonArr = (JSONArray) value;
                System.out.println("key arr: " + item + ", value arr: " + jsonArr);
            } else System.out.println("key: " + item + ", value: " + value);
        }
    }

    public String getJsonStrExample() {
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

    public String getJsonStrAmazon() {
        return "{\n" +
                "   \"name\" : \"Amazon\",\n" +
                "   \"ceo\" : \"Jeff Bezos\",\n" +
                "   \"employees\":[\n" +
                "     {\"firstName\":\"John\", \"lastName\":\"Doe\"},\n" +
                "     {\"firstName\":\"Anna\", \"lastName\":\"Smith\"},\n" +
                "     {\"firstName\":\"Peter\", \"lastName\":\"Jones\"}\n" +
                "   ]\n" +
                "}";
    }

    //..................................................................
    //..................................................................
    //..................................................................
    // JACKSON Don't Work in Servlets
    public StringWriter getCatResponseByJackson() throws IOException {
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

    private void jacksonExample(PrintWriter writer) throws IOException {
        StringWriter catResponse = getCatResponseByJackson();
        writer.print(catResponse.toString());
    }
}