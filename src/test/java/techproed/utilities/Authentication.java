package techproed.utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Authentication {

    public static String generateToken(){

        //set the url    https://medunna.com/api/authenticate
        String url ="https://medunna.com/api/authenticate";

        //set the payload
        String credentials ="{\n" +
                "  \"password\": \"Techpro123.\",\n" +
                "  \"rememberMe\": true,\n" +
                "  \"username\": \"techproed\"\n" +
                "}";
        //send request get response
        Response response = given()
                .body(credentials)
                .contentType(ContentType.JSON)
                .when()
                .post(url);

        //return token
        return response.jsonPath().getString("id_token");
    }



}
