package sad;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;

import org.junit.Test;

public class auzmorserialize extends Functionalities {
    @Test
    public void getjsonfromclassobject() throws JsonProcessingException {
        Functionalities s = new Functionalities();
        s.setLogin("gundubillipravallika@gmail.com");
        s.setPassword("Test@123");
        System.out.println("The values of the objects");
        System.out.println(s.getLogin());
        System.out.println(s.getPassword());


        ObjectMapper objmapper = new ObjectMapper();
        String auzmorJSON = objmapper.writeValueAsString(s);

        System.out.println(auzmorJSON);
        RequestSpecification req = RestAssured.given();
        req.baseUri("https://learn-staging.api.auzmor.com");
        req.contentType(ContentType.JSON);


        req.body(auzmorJSON);
        Response response = req.post("/api/v1/users/login");
        response.prettyPrint();
        System.out.println(response.statusCode());
        if (response.statusCode() == 200) {
            System.out.println("The Transaction was successful");
        } else {
            System.out.println("Not successfull");
        }


    }


    @Test
    public void postdata() {
        // Create a Map to represent the JSON data
        JSONObject Jsonobject = new JSONObject();
        Jsonobject.put("login", "gundubillipravallika@gmail.com");
        Jsonobject.put("password", "Test@123");

        RestAssured.baseURI = "https://learn-staging.api.auzmor.com";


        RestAssured.given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .body(Jsonobject)
                .when()
                .post("/api/v1/users/login")  // Replace with your actual endpoint
                .then()
                .statusCode(200).log().all();
        Response response = RestAssured.get("https://learn-staging.api.auzmor.com");

        if (response.getStatusCode() == 200) {
            System.out.println("Successfully executed");
            System.out.println("hii");
        } else {
            System.out.println("Oops!!...Not success");
        }

    }
}
