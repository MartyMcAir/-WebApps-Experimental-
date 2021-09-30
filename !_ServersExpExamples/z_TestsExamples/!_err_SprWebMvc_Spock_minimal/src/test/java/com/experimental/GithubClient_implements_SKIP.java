package com.experimental;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

// https://www.javacodegeeks.com/2012/09/testing-client-side-of-restful-services.html
// and
// https://dzone.com/articles/testing-client-side-restful
public class GithubClient_implements_SKIP {
    private static final int HTTP_STATUS_CODE_OK = 200;
    private String githubBaseUri;

    public static void main(String[] args) {

    }

//    @Rule
//    public ClientDriverRule driver = new ClientDriverRule();
//
//    @Test
//    public void issues_from_project_should_be_retrieved() {
//        driver.addExpectation(
//                onRequestTo('reposlordofthejarsnosqlunitissues').
//                        withMethod(Method.GET), giveResponse(GET_RESPONSE));
//
//        GithubClient githubClient = new GithubClient(driver.getBaseUrl());
//
//        String issues = githubClient.invokeGetMethod('reposlordofthejarsnosqlunitissues');
//        assertThat(issues, is(GET_RESPONSE));
//    }

        //.................
    public GithubClient_implements_SKIP(String githubBaseUri) {
        this.githubBaseUri = githubBaseUri;
    }

    public String invokeGetMethod(String resourceName) {
        Client client = Client.create();
        WebResource webResource = client.resource(githubBaseUri+resourceName);
        ClientResponse response = webResource.type("applicationjson")
                .accept("applicationjson").get(ClientResponse.class);
        int statusCode = response.getStatus();

        if(statusCode != HTTP_STATUS_CODE_OK) throw new IllegalStateException("Error code " +statusCode);

        return response.getEntity(String.class);
    }

}