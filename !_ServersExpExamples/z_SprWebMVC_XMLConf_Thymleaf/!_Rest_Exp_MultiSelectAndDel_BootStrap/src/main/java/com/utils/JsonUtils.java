package com.utils;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Product;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class JsonUtils {

    // https://mkyong.com/java/how-to-convert-java-object-to-from-json-jackson/
    public static void main(String[] args) throws IOException {
        JsonUtils jsonUtils = new JsonUtils();
//        jsonUtils.getFileFromURL();

//        convertJavaObjToJson();
        convertJavaObjToJson2();
//        convertJsonToJavaObj();

//        getFileFromResourceBySpringTool();
    }

    private static File getFileFromResourceBySpringTool() throws IOException {
        return new ClassPathResource("sql").getFile();
    }

    public File getFileFromURL() {
        URL url = this.getClass().getClassLoader().getResource("/sql");
        File file = null;
        try {
            file = new File(url.toURI());
        } catch (URISyntaxException e) {
            file = new File(url.getPath());
        } finally {
            return file;
        }
    }

    private static void convertJavaObjToJson2() throws IOException {
        // ... Convert Java object to JSON, writeValue(...)
        ObjectMapper mapper = new ObjectMapper();

        List<Product> list = new ArrayList<>();
        Collections.addAll(list, new Product("oneObj", 0.3),
                new Product("twoObj", 0.8), new Product("three", 3.14));

        //Object to JSON in file
        mapper.writeValue(new File("myJson.json"), list);

        //Object to JSON in String
        String jsonInString = mapper.writeValueAsString(list);
    }

    private static void convertJavaObjToJson() throws IOException {
        // ... Convert Java object to JSON, writeValue(...)
        ObjectMapper mapper = new ObjectMapper();
        Product obj = new Product("TestName", 3.14);

        //Object to JSON in file
        mapper.writeValue(new File("myJson.json"), obj);

        //Object to JSON in String
        String jsonInString = mapper.writeValueAsString(obj);
    }

    private static void convertJsonToJavaObj() throws IOException {
        // ... Convert JSON to Java object, readValue(...)
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "{'name' : 'mkyong'}";

        //JSON from file to Object
        Product user1 = mapper.readValue(new File("c:\\user.json"), Product.class);

        //JSON from String to Object
        Product user2 = mapper.readValue(jsonInString, Product.class);
    }

    public static <T> List<T> getDataFromJson(String fileName, T t) {
        List<T> list = new ArrayList<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            list = objectMapper.readValue(
//                    getClass().getClassLoader().getResourceAsStream(fileName),
                    JsonUtils.class.getClassLoader().getResourceAsStream(fileName),
                    new TypeReference<List<T>>() {
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}