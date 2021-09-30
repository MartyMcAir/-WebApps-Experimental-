package com.experimental;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Rest_HttpClient {
    //    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final Logger LOGGER = LoggerFactory.getLogger(Rest_HttpClient.class);

    public static void main(String[] args) throws IOException {
        // https://itnext.io/how-to-create-a-simple-rest-client-for-testing-your-api-a0554d8380f8
//        getRequest_itNext();
//        postRequest_itNext();

        // https://automation-remarks.com/java-rest-client/
//        getRequest_automationRemarks();
//        postRequest_automationRemarks();

        // https://www.javacodegeeks.com/2012/09/simple-rest-client-in-java.html
        getRequest_javaCodeGeeks();
//        postRequest_javaCodeGeeks();
//        multiParametersPost_javaCodeGeeks();
    }

    // for skip error unchecked or unsafe..
    @SuppressWarnings(value = "unchecked")
    // https://www.javacodegeeks.com/2012/09/simple-rest-client-in-java.html
    public static void getRequest_javaCodeGeeks() throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        ;
        String uri1 = "http://restUrl";
        String uri2 = "https://api.github.com/users/defunkt";
        HttpGet request = new HttpGet(uri2);
        HttpResponse response = client.execute(request);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuilder sb = new StringBuilder(); // one line
        for (String line = ""; line != null; line = rd.readLine()) {
            sb.append(line);
        }

        // https://mkyong.com/java/how-to-enable-pretty-print-json-output-jackson/ - Don't Work!(
        // https://stackoverflow.com/questions/17617370/pretty-printing-json-from-jackson-2-2s-objectmapper - GOOD solve it
        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
//        HashMap<String, Object> hashMap = (HashMap<String, Object>) mapper.readValue(sb.toString(), HashMap.class);
        HashMap<String, Object> hashMap = mapper.readValue(sb.toString(), HashMap.class);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(hashMap));

    }

    public static void postRequest_javaCodeGeeks() throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("http://restUrl");

        StringEntity input = new StringEntity("product");
        // You can also send full JSON or XML of a POJO by putting String representing JSON or XML
        // as a parameter of StringEntity and then set the input content type.
        StringEntity input2 = new StringEntity("{'name1':'value1','name2':'value2'}"); //here instead of JSON you can also have XML
        input2.setContentType("application/json");

        // For JSON you can use JSONObject to create string representation of JSON.
//        JSONObject json = new JSONObject();
//        json.put('name1', 'value1');
//        json.put('name2', 'value2');
//        StringEntity se = new StringEntity( json.toString());

        post.setEntity(input);
        HttpResponse response = client.execute(post);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line = "";
        while ((line = rd.readLine()) != null) System.out.println(line);
    }

    public static void multiParametersPost_javaCodeGeeks() throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("http://restUrl");
        List<BasicNameValuePair> nameValuePairs = new ArrayList<BasicNameValuePair>(1);
        nameValuePairs.add(new BasicNameValuePair("name", "value")); //you can as many name value pair as you want in the list.
        post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        HttpResponse response = client.execute(post);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line = "";
        while ((line = rd.readLine()) != null) System.out.println(line);
    }

    // https://automation-remarks.com/java-rest-client/
    public static void getRequest_automationRemarks() throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet("http://restUrl");
        HttpResponse response = client.execute(request);

        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        for (String str = ""; str != null; str = rd.readLine()) {
            System.out.println(str);
        }

//        String line = "";
//        while ((line = rd.readLine()) != null) System.out.println(line);

    }

    public static void postRequest_automationRemarks() throws IOException {
        HttpPost post = new HttpPost("http://restUrl");
        post.setEntity(new StringEntity("product"));
        HttpResponse response = HttpClientBuilder.create().build().execute(post);

        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line = "";
        while ((line = rd.readLine()) != null) System.out.println(line);
    }

    public static void multiParametersPost_automationRemarks() throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("http://restUrl");
        List<BasicNameValuePair> nameValuePairs = new ArrayList<BasicNameValuePair>(1);
        nameValuePairs.add(new BasicNameValuePair("name", "value")); //you can as many name value pair as you want in the list.
        post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        HttpResponse response = client.execute(post);

        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line = "";
        while ((line = rd.readLine()) != null) System.out.println(line);
    }

    // https://itnext.io/how-to-create-a-simple-rest-client-for-testing-your-api-a0554d8380f8
    public static void getRequest_itNext() {
        final HttpClient httpClient = HttpClientBuilder.create().build();
        final HttpGet httpGet = new HttpGet("http://localhost:8080");

        HttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
        } catch (IOException ex) {   // if (LOGGER.isLoggable(Level.INFO))
            LOGGER.info("The method is down." + ex.getMessage());
        }
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        } catch (IOException ex) {
            LOGGER.info("The method is down." + ex.getMessage());
        }

        String line = "";
        while (true) {
            try {
                if (!((line = reader.readLine()) != null)) break;
            } catch (IOException ex) {
                LOGGER.info("The method is down." + ex.getMessage());
            }
            LOGGER.info(line);
        }
    }

    public static void postRequest_itNext() {
        final HttpClient httpClient = HttpClientBuilder.create().build();
        final HttpPost httpPost = new HttpPost("http://localhost:8080");
        StringEntity input = null;
        try {
            input = new StringEntity("id");
        } catch (UnsupportedEncodingException ex) {
//            if (LOGGER.isLoggable(Level.INFO)) LOGGER.info("The method is down." + ex.getMessage());
        }
        httpPost.setEntity(input);

        HttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
        } catch (IOException ex) {
//            if (LOGGER.isLoggable(Level.INFO)) LOGGER.info("The method is down." + ex.getMessage());
        }

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        } catch (IOException ex) {
//            if (LOGGER.isLoggable(Level.INFO)) LOGGER.info("The method is down." + ex.getMessage());
        }

        String line = "";
        while (true) {
            try {
                if (!((line = reader.readLine()) != null)) break;
            } catch (IOException ex) {
//                if (LOGGER.isLoggable(Level.INFO)) LOGGER.info("The method is down." + ex.getMessage());
            }
            LOGGER.info(line);
        }
    }
}