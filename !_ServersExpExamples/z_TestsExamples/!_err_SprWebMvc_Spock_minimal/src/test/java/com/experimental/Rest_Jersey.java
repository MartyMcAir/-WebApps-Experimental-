package com.experimental;

import com.model.Customer;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;

public class Rest_Jersey {
    private static final Logger LOGGER = LoggerFactory.getLogger(Rest_Jersey.class);

    public static void main(String[] args) {
        // https://itnext.io/how-to-create-a-simple-rest-client-for-testing-your-api-a0554d8380f8
//        getRequest_itNext();
//        postRequest_itNext();

        // https://automation-remarks.com/java-rest-client/
//        getRequest_automationRemarks();
//        multiParametersPostRequest_automationRemarks();
//        ourPostRequest_automationRemarks();

        // https://www.javacodegeeks.com/2012/09/simple-rest-client-in-java.html

    }

    public static void getRequest_javaCodeGeeks() {
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource service = client.resource(UriBuilder.fromUri("http://restUrl").build());
        // getting XML data
        System.out.println(service.path("restPath").path("resourcePath").accept(MediaType.APPLICATION_JSON).get(String.class));
        // getting JSON data
        System.out.println(service.path("restPath").path("resourcePath").accept(MediaType.APPLICATION_XML).get(String.class));
    }

    // for skip error unchecked or unsafe..
    @SuppressWarnings(value = "unchecked")
    public static void postRequest_javaCodeGeeks() {
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource webResource = client.resource(UriBuilder.fromUri("http://restUrl").build());
        MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
        formData.add("name1", "val1");
        formData.add("name2", "val2");
        ClientResponse response = webResource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).post(ClientResponse.class, formData);
        System.out.println("Response " + response.getEntity(String.class));
    }

    public static void postPojo_javaCodeGeeks() {
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource webResource = client.resource(UriBuilder.fromUri("http://restUrl").build());
        Customer myPojo = new Customer();
        ClientResponse response = webResource.path("restPath").path("resourcePath").
                type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, myPojo);
        System.out.println("Response " + response.getEntity(String.class));
    }

    public static void multiParameterPost_javaCodeGeeks() {
        // You can also use Form class from Jersey to submit multiple parameters in POST request:
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource service = client.resource(UriBuilder.fromUri("http://restUrl").build());
//        Form form = new Form();
//        form.add("name1", "value1");
//        form.add("name2", "value1");
        Customer myPojo = new Customer();
        ClientResponse response = service.path("restPath").path("resourcePath").
                type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class, myPojo);
        System.out.println("Response " + response.getEntity(String.class));
    }

    // https://automation-remarks.com/java-rest-client/
    public static void getRequest_automationRemarks() {
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource service = client.resource(UriBuilder.fromUri("http://restUrl").build());
        // getting XML data
        System.out.println(service.path("restPath").path("resourcePath").accept(MediaType.APPLICATION_JSON).get(String.class));
        // getting JSON data
        System.out.println(service.path("restPath").path("resourcePath").accept(MediaType.APPLICATION_XML).get(String.class));
    }

    // for skip error unchecked or unsafe..
    @SuppressWarnings(value = "unchecked")
    public static void multiParametersPostRequest_automationRemarks() {
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource webResource = client.resource(UriBuilder.fromUri("http://restUrl").build());
        MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
        formData.add("name1", "val1");
        formData.add("name2", "val2");
        ClientResponse response = webResource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).post(ClientResponse.class, formData);
        System.out.println("Response " + response.getEntity(String.class));
    }

    public static void ourPostRequest_automationRemarks() {
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource webResource = client.resource(UriBuilder.fromUri("http://restUrl").build());

        Customer myPojo = new Customer();
        ClientResponse response = webResource.path("restPath").path("resourcePath").
                type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, myPojo);
        System.out.println("Response " + response.getEntity(String.class));
    }

    // https://itnext.io/how-to-create-a-simple-rest-client-for-testing-your-api-a0554d8380f8
    public static void getRequest_itNext() {
        final ClientConfig clientConfig = new DefaultClientConfig();
        final Client client = Client.create(clientConfig);
        final WebResource webResource = client.resource(UriBuilder.fromUri("http://localhost:8080").build());

        LOGGER.info(webResource.path("restPath").path("resourcePath").accept(MediaType.APPLICATION_JSON).get(String.class));
        LOGGER.info(webResource.path("restPath").path("resourcePath").accept(MediaType.APPLICATION_XML).get(String.class));
    }

    // for skip error unchecked or unsafe..
    @SuppressWarnings(value = "unchecked")
    public static void postRequest_itNext() {
        final ClientConfig clientConfig = new DefaultClientConfig();
        final Client client = Client.create(clientConfig);
        final WebResource webResource = client.resource(UriBuilder.fromUri("http://localhost:8080").build());
        final MultivaluedMap<String, String> multivaluedMap = new MultivaluedMapImpl();
        multivaluedMap.add("name1", "val1");
        final ClientResponse clientResponse = webResource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).post(ClientResponse.class, multivaluedMap);
        LOGGER.info("Response " + clientResponse.getEntity(String.class));
    }

    public static void ownClassPojoForPost() {
        final ClientConfig clientConfig = new DefaultClientConfig();
        final Client client = Client.create(clientConfig);
        final WebResource webResource = client.resource(UriBuilder.fromUri("http://localhost:8080").build());

        Customer myPojoObject = new Customer(); // own POJO
        final ClientResponse clientResponse = webResource.path("restPath").path("resourcePath").
                type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, myPojoObject);
        LOGGER.info("Response " + clientResponse.getEntity(String.class));
    }
}