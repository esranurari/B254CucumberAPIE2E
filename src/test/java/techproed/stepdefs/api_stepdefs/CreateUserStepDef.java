package techproed.stepdefs.api_stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class CreateUserStepDef {
    private RequestSpecification spec;
    private Map<String,String> payload;
    Response response;

    @Given("base URL {string} ve path parametresi {string} kullanilir")
    public void baseURLVePathParametresiKullanilir(String baseUrl, String path) {
         spec = new RequestSpecBuilder().setBaseUri(baseUrl)
                .setBasePath(path)
                .setContentType(ContentType.JSON)
                .build();
    }

    @And("name {string} ve job {string} ile payload olusturulur")
    public void nameVeJobIlePayloadOlusturulur(String name, String job) {
        //ya pojo olusturup al yada map ile al
        //1.yol:
        payload = new HashMap<>();
        payload.put("name",name);
        payload.put("job",job);

        //2.yol:
        //pojo ile yaparken ornegin; Pojo pojo = new Pojo(name,job);
        //pojo ile isteseydik olusturduktan sonra dynamic olarak payload olusturabilirdik.
    }

    @When("post request gönderilir ve response alinir")
    public void postRequestGönderilirVeResponseAlinir() {
        response = given(spec).body(payload).when().post();

    }

    @Then("status code {int} olmalidir")
    public void statusCodeOlmalidir(int expectedStatusCode) {
        //Bu ASSERT class junit in. junitte once expected yazilir testng tersine.
        Assert.assertEquals(expectedStatusCode,response.statusCode());

    }

    @And("response content type {string} olmalidir")
    public void responseContentTypeOlmalidir(String expectedContentType) {
        Assert.assertEquals(expectedContentType,response.getContentType());
    }

    @And("response name {string} ve job {string} olmalidir")
    public void responseNameVeJobOlmalidir(String expectedName, String expectedJob) {
        String actualName = response.jsonPath().getString("name");
        String actualJob = response.jsonPath().getString("job");
        Assert.assertEquals(expectedName,actualName);
        Assert.assertEquals(expectedJob,actualJob);
    }
}
