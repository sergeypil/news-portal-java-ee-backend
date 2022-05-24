package com.epam.newsportal.cucumber;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class StepDefinition {
    private static final String CREATE_PATH = "/articles";
    private static final String APPLICATION_JSON = "application/json";

    private final InputStream jsonInputStream = this.getClass().getClassLoader().getResourceAsStream("article.json");
    private final String jsonString = new Scanner(jsonInputStream, "UTF-8").useDelimiter("\\Z").next();

    private final WireMockServer wireMockServer = new WireMockServer(options().dynamicPort());
    private final CloseableHttpClient httpClient = HttpClients.createDefault();


    @When("user uploads article")
    public void usersUploadArticle() throws IOException {
        wireMockServer.start();

        configureFor("localhost", wireMockServer.port());
        stubFor(post(urlEqualTo(CREATE_PATH))
                .withHeader("content-type", equalTo(APPLICATION_JSON))
                .withRequestBody(containing("title"))
                .withRequestBody(containing("brief"))
                .withRequestBody(containing("content"))
                .willReturn(aResponse().withStatus(200)));

        HttpPost request = new HttpPost("http://localhost:" + wireMockServer.port() + "/articles");
        StringEntity entity = new StringEntity(jsonString);
        request.addHeader("content-type", APPLICATION_JSON);
        request.setEntity(entity);
        HttpResponse response = httpClient.execute(request);

        assertEquals(200, response.getStatusLine().getStatusCode());
        verify(postRequestedFor(urlEqualTo(CREATE_PATH))
                .withHeader("content-type", equalTo(APPLICATION_JSON)));

        wireMockServer.stop();
    }

    @When("user wants to get information on article with id {long}")
        public void userWantInformationOnArticleById (long id) throws IOException {
            wireMockServer.start();

            configureFor("localhost", wireMockServer.port());
            stubFor(get(urlEqualTo("/articles/4")).withHeader("accept", equalTo(APPLICATION_JSON))
                    .willReturn(aResponse().withBody(jsonString)));

            HttpGet request = new HttpGet("http://localhost:" + wireMockServer.port() + "/articles/" + id);
            request.addHeader("accept", APPLICATION_JSON);
            HttpResponse httpResponse = httpClient.execute(request);
            String responseString = convertResponseToString(httpResponse);

            assertThat(responseString, containsString("\"title\":\"new_title\""));
            assertThat(responseString, containsString("\"brief\":\"new\""));
            assertThat(responseString, containsString("\"content\":\"new\""));
            verify(getRequestedFor(urlEqualTo("/articles/4")).withHeader("accept", equalTo(APPLICATION_JSON)));

            wireMockServer.stop();
        }

        @Then("the server should handle it and return a success status")
        public void theServerShouldReturnASuccessStatus () {
        }

        @Then("the requested data is returned")
        public void theRequestedDataIsReturned () {
        }
        private String convertResponseToString (HttpResponse response) throws IOException {
            InputStream responseStream = response.getEntity().getContent();
            Scanner scanner = new Scanner(responseStream, "UTF-8");
            String responseString = scanner.useDelimiter("\\Z").next();
            scanner.close();
            return responseString;
        }
}
