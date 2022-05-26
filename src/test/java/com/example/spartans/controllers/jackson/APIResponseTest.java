package com.example.spartans.controllers.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APIResponseTest {
    @Test
    public void getApiTest() {

//        JSONObject json = new JSONObject();
//        try {
//            json.put("email", "ahaglington2@washington.edu");
//            json.put("password", "1YgxO9");
//        } catch (JSONException e) {
//            throw new RuntimeException(e);
//        }

//        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        String paramss="{\"email\": \"ahaglington2@washington.edu\",\"password\": \"1YgxO9\"}";
        try {
//            HttpPatch request = new HttpPatch("http://localhost:8080/api/set-api-key/628bbb7e320a7ff994449e5f");
//            StringEntity params = new StringEntity(json.toString());
//            request.addHeader("content-type", "application/json");
//            request.setEntity(params);
//            CloseableHttpResponse response = httpClient.execute(request);


            HttpRequest.BodyPublisher jsonPayload = HttpRequest.BodyPublishers.ofString(paramss);
            HttpRequest httpRequest= HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/api/set-api-key/628bbb7e320a7ff994449e5f"))
                    .method("PATCH", jsonPayload)
                    .header("Content-Type", "application/json")
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> responsee =
                    client.send(httpRequest, HttpResponse.BodyHandlers.ofString());



            ObjectMapper objectMapper=new ObjectMapper();
            APIResponse apiResponse=objectMapper.readValue(responsee.body(),APIResponse.class);
            Assertions.assertEquals(apiResponse.getRoleUser(),"admin");

//            HttpRequest request = HttpRequest
//                    .newBuilder()
//                    .uri(URI.create("https://api.chucknorris.io/jokes/random"))
//                    .build();
//            HttpClient httpClient = HttpClient.newHttpClient();
//            try {
//                HttpResponse<String> response =
//                        httpClient.send(request, HttpResponse.BodyHandlers.ofString());
//                String responseBody = response.body();
//                JSONParser jsonParser = new JSONParser();
//                JSONObject jsonObject = (JSONObject) jsonParser.parse(responseBody);
//                System.out.println(jsonObject.get("created_at"));
//            } catch (IOException | InterruptedException | ParseException e) {
//                e.printStackTrace();
//            }




//            Assertions.assertEquals(response.getStatusLine().getStatusCode(), 200);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
