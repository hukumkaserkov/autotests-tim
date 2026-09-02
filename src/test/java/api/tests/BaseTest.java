package api.tests;

import api.clients.ChecksHelpers;
import api.clients.FilesHelpers;
import api.clients.AppealHelpers;
import api.clients.RequirementsHelpers;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.CreateAppeal;
import models.LoginRequest;
import models.ObjectKind;
import org.junit.jupiter.api.BeforeAll;
import transport.Transport;
import utils.AllureRequestTime;

public class BaseTest {

    protected static String token;
    protected static Transport transport = new Transport();
    protected static AllureRequestTime allureRequestTime;
    public static FilesHelpers filesHelpers;
    protected static AppealHelpers appealHelpers;
    public static RequirementsHelpers requirementsHelpers;
    public static ChecksHelpers checksHelpers;

    @BeforeAll
    public static void setUp() {

        RestAssured.baseURI = "https://test-staging.dev.org";

        token = authToken();
        transport.setToken(token);

        allureRequestTime = new AllureRequestTime();
        filesHelpers = new FilesHelpers(transport);
        appealHelpers = new AppealHelpers(transport);
        requirementsHelpers = new RequirementsHelpers(transport);
        checksHelpers = new ChecksHelpers(transport);

        appealHelpers.createAppeal();
        appealHelpers.createAppeal();
    }

    public static String authToken() {

        LoginRequest body = new LoginRequest("TEST", "test");
        String endpoint = "/auth/v1/login";
        Response response = io.restassured.RestAssured.given()
                .contentType(io.restassured.http.ContentType.JSON)
                .body(body)
                .when()
                .post(endpoint);

        return response.path("refreshToken");

    }

}
