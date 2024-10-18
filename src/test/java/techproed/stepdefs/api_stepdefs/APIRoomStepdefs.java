package techproed.stepdefs.api_stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import techproed.pojos.RoomPojo;
import techproed.stepdefs.db_stepdefs.DBRoomStepDefs;
import techproed.stepdefs.ui_stepdefs.UIMedunnaStepDefs;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static techproed.stepdefs.Hook.spec;

public class APIRoomStepdefs {
    Response response ;
    RoomPojo expectedData;

    @Given("A Get request is sent")
    public void aGetRequestIsSent() {
        //set the url   https://medunna.com/api/rooms/124092
        spec.pathParams("first","api","second","rooms","third", DBRoomStepDefs.roomId);//direkt roomId yapip import da yapabilirsin

        //set the expected data
        expectedData = new RoomPojo(UIMedunnaStepDefs.roomNumber,"SUITE",true,123.00,"End To End Test icin olusturulmustur" );

        //send request get response
        response = given(spec).when().get("{first}/{second}/{third}");
    }

    @Then("Response is validated")
    public void responseIsValidated() {
        //do assertion
        RoomPojo actualData = response.as(RoomPojo.class);
        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getRoomNumber(),actualData.getRoomNumber());
        assertEquals(expectedData.isStatus(),actualData.isStatus());
        assertEquals(expectedData.getPrice(),actualData.getPrice());
        assertEquals(expectedData.getDescription(),actualData.getDescription());
    }
}
