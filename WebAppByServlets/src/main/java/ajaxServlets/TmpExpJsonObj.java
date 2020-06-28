package ajaxServlets;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// must bee
// {"booksJson":[{"TESTCOLUMN":"","QUANTITY":"3333","TITLE":"Title 24 06 2020","ID":"63","AUTHOR":"autho TO","FAQA":""}]}
// have
// {"booksJson":[{"id":" "},{"title":" "},{"author":" "},{"quantity":" "},{"testcolumn":" "},{"faqa":" "}]}

public class TmpExpJsonObj {
    public static void main(String[] args) {
        List<String> booksNames = new ArrayList<>();
        Collections.addAll(booksNames, "ID", "quantity", "title");

        JSONArray newJsonList = new JSONArray();
        JSONObject obj = new JSONObject();
        for (int j = 0; j < booksNames.size(); j++) {
            obj.put(booksNames.get(j), " ");
        }
        newJsonList.add(obj);

        JSONObject jObj = new JSONObject();
        jObj.put("books", newJsonList);

        System.out.println(jObj.toJSONString());
    }
}
