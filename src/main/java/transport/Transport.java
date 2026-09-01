package transport;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.filter.log.LogDetail;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import io.restassured.module.jsv.JsonSchemaValidator;
import java.io.File;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class Transport {

    private String token;

    public String getToken() {
        return token;
    }

    // Метод для установки токена
    public void setToken(String token) {
        this.token = token;
    }

    // ПОЛОВИНУ МЕТОДОВ МОЖНО ЗАМЕНИТЬ ПЕРЕГРУЗКОЙ - ОПТИМИЗИРОВАТЬ
    public Response postRequest(String endpoint, Object body, int code){
        return given()
                .filter(new AllureRestAssured())
                .contentType("application/json; charset=UTF-8")
                .body(body)
                .when()
                .log().ifValidationFails(LogDetail.ALL)
                .post(endpoint)
                .then()
                .log().ifValidationFails(LogDetail.BODY)
                .statusCode(code)
                .extract()
                .response();
    }

    public Response postRequestWithToken(String endpoint, Object body, File schema, int code){

        return given()
                .filter(new AllureRestAssured())
                .contentType("application/json; charset=UTF-8")
                .header("X-Authorization", "Bearer " + token)
                .body(body)
                .when()
                .log().ifValidationFails(LogDetail.ALL)
                .post(endpoint)
                .then()
                .log().ifValidationFails(LogDetail.ALL)
                .statusCode(code)
                .body(
                        JsonSchemaValidator.matchesJsonSchema
                                (schema) // метод matchesJsonSchema проверяет ответ по JSON схеме
                )
                .extract()
                .response();
    }

    public Response postRequestNoSchema(String endpoint, Object body, int code){

        return given()
                .filter(new AllureRestAssured())
                .contentType("application/json; charset=UTF-8")
                .header("X-Authorization", "Bearer " + token)
                .body(body)
                .when()
                .log().ifValidationFails(LogDetail.ALL)
                .post(endpoint)
                .then()
                .log().ifValidationFails(LogDetail.ALL)
                .statusCode(code)
                .extract()
                .response();
    }

    public Response postRequestWithTokenNoChecks(String endpoint, Object body){

        return given()
                .filter(new AllureRestAssured())
                .contentType("application/json; charset=UTF-8")
                .header("X-Authorization", "Bearer " + token)
                .body(body)
                .when()
                .log().ifValidationFails(LogDetail.ALL)
                .post(endpoint)
                .then()
                .log().ifValidationFails(LogDetail.ALL)
                .extract()
                .response();
    }

    public Response postRequestWithParams(String endpoint, String pathParamName, String pathParamValue,
                                          Object body, File schema, int code){

        return given()
                .filter(new AllureRestAssured())
                .contentType("application/json; charset=UTF-8")
                .header("X-Authorization", "Bearer " + token)
                .pathParams(pathParamName, pathParamValue)
                .body(body)
                .when()
                .log().ifValidationFails(LogDetail.ALL)
                .post(endpoint)
                .then()
                .log().ifValidationFails(LogDetail.ALL)
                .statusCode(code)
                .body(
                        JsonSchemaValidator.matchesJsonSchema
                                (schema) // метод matchesJsonSchema проверяет ответ по JSON схеме
                )
                .extract()
                .response();
    }

    public Response postRequestWithParamsNoSchema(String endpoint, String pathParamName, String pathParamValue,
                                          Object body, int code){

        return given()
                .filter(new AllureRestAssured())
                .contentType("application/json; charset=UTF-8")
                .header("X-Authorization", "Bearer " + token)
                .pathParams(pathParamName, pathParamValue)
                .body(body)
                .when()
                .log().ifValidationFails(LogDetail.ALL)
                .post(endpoint)
                .then()
                .log().ifValidationFails(LogDetail.ALL)
                .statusCode(code)
                .extract()
                .response();
    }

    public Response postRequestWithParamsNoChecks(String endpoint, String pathParamName, String pathParamValue,
                                          Object body){

        return given()
                .filter(new AllureRestAssured())
                .contentType("application/json; charset=UTF-8")
                .header("X-Authorization", "Bearer " + token)
                .pathParams(pathParamName, pathParamValue)
                .body(body)
                .when()
                .log().ifValidationFails(LogDetail.ALL)
                .post(endpoint)
                .then()
                .log().ifValidationFails(LogDetail.ALL)
                .extract()
                .response();
    }


    public Response getRequestWithParams(String endpoint, String pathParamName, String pathParamValue,
                                         File schema, int code){
        return given()
                .filter(new AllureRestAssured())
                .contentType("application/json; charset=UTF-8")
                .header("X-Authorization", "Bearer " + token)
                .pathParams(pathParamName, pathParamValue)
                .when()
                .log().ifValidationFails(LogDetail.ALL)
                .get(endpoint)
                .then()
                .log().ifValidationFails(LogDetail.ALL)
                .statusCode(code)
                .body(
                        JsonSchemaValidator.matchesJsonSchema
                                (schema) // метод matchesJsonSchema проверяет ответ по JSON схеме
                )
                .extract()
                .response();
    }

    public Response getRequestWithQueryParams(String endpoint, String queryParamName, String queryParamValue,
                                         File schema, int code){
        return given()
                .filter(new AllureRestAssured())
                .contentType("application/json; charset=UTF-8")
                .header("X-Authorization", "Bearer " + token)
                .queryParams(queryParamName, queryParamValue)
                .when()
                .log().ifValidationFails(LogDetail.ALL)
                .get(endpoint)
                .then()
                .log().ifValidationFails(LogDetail.ALL)
                .statusCode(code)
                .body(
                        JsonSchemaValidator.matchesJsonSchema
                                (schema) // метод matchesJsonSchema проверяет ответ по JSON схеме
                )
                .extract()
                .response();
    }

    public Response getReqWithParamsNoSchema(String endpoint, String pathParamName,
                                                  String pathParamValue, int code){
        return given()
                .filter(new AllureRestAssured())
                .contentType("application/json; charset=UTF-8")
                .header("X-Authorization", "Bearer " + token)
                .pathParams(pathParamName, pathParamValue)
                .when()
                .log().ifValidationFails(LogDetail.ALL)
                .get(endpoint)
                .then()
                .log().ifValidationFails(LogDetail.ALL)
                .statusCode(code)
                .extract()
                .response();
    }

    public Response getRequestWithParamsNoChecks(String endpoint, String pathParamName, String pathParamValue){
        return given()
                .filter(new AllureRestAssured())
                .contentType("application/json; charset=UTF-8")
                .header("X-Authorization", "Bearer " + token)
                .pathParams(pathParamName, pathParamValue)
                .when()
                .log().ifValidationFails(LogDetail.ALL)
                .get(endpoint)
                .then()
                .log().ifValidationFails(LogDetail.ALL)
                .extract()
                .response();
    }


    public Response patchRequest(String endpoint, String body, File schema, int code){
        return given()
                .filter(new AllureRestAssured())
                .contentType("application/json; charset=UTF-8")
                .header("X-Authorization", "Bearer " + token)
                .body(body)
                .when()
                .log().ifValidationFails(LogDetail.ALL)
                .patch(endpoint)
                .then()
                .log().ifValidationFails(LogDetail.ALL)
                .statusCode(code)
                .body(
                        JsonSchemaValidator.matchesJsonSchema
                                (schema) // метод matchesJsonSchema проверяет ответ по JSON схеме
                )
                .extract()
                .response();

    }

    public Response patchRequestWithParams(String endpoint, String pathParamName, String pathParamValue,
                                           Object body, File schema, int code){
        return given()
                .filter(new AllureRestAssured())
                .contentType("application/json; charset=UTF-8")
                .header("X-Authorization", "Bearer " + token)
                .pathParams(pathParamName, pathParamValue)
                .body(body)
                .when()
                .log().ifValidationFails(LogDetail.ALL)
                .patch(endpoint)
                .then()
                .log().ifValidationFails(LogDetail.ALL)
                .statusCode(code)
                .body(
                        JsonSchemaValidator.matchesJsonSchema
                                (schema) // метод matchesJsonSchema проверяет ответ по JSON схеме
                )
                .extract()
                .response();

    }

    public Response patchRequestWithParamsNoSchema(String endpoint, String pathParamName, String pathParamValue,
                                           Object body, int code){
        return given()
                .filter(new AllureRestAssured())
                .contentType("application/json; charset=UTF-8")
                .header("X-Authorization", "Bearer " + token)
                .pathParams(pathParamName, pathParamValue)
                .body(body)
                .when()
                .log().ifValidationFails(LogDetail.ALL)
                .patch(endpoint)
                .then()
                .log().ifValidationFails(LogDetail.ALL)
                .statusCode(code)
                .extract()
                .response();

    }

    public Response getRequest(String endpoint, File schema, int code){
        return given()
                .filter(new AllureRestAssured())
                .contentType("application/json; charset=UTF-8")
                .header("X-Authorization", "Bearer " + token)
                .when()
                .log().ifValidationFails(LogDetail.ALL)
                .get(endpoint)
                .then()
                .log().ifValidationFails(LogDetail.ALL)
                .statusCode(code)
                .body(
                        JsonSchemaValidator.matchesJsonSchema
                                (schema) // метод matchesJsonSchema проверяет ответ по JSON схеме
                )
                .extract()
                .response();
    }

    public Response getRequestWithoutChecks(String endpoint){
        return given()
                .filter(new AllureRestAssured())
                .contentType("application/json; charset=UTF-8")
                .header("X-Authorization", "Bearer " + token)
                .when()
                .log().ifValidationFails(LogDetail.ALL)
                .get(endpoint)
                .then()
                .log().ifValidationFails(LogDetail.ALL)
                .extract()
                .response();
    }

    public Response getRequestWithParamsNoSchema(String endpoint, String pathParamName, String pathParamValue,
                                                 Map<String, ?> queryParams, int code) {
        return given()
                .filter(new AllureRestAssured())
                .contentType("application/json; charset=UTF-8")
                .header("X-Authorization", "Bearer " + token)
                .pathParams(pathParamName, pathParamValue)
                .queryParams(queryParams != null ? queryParams : Map.of())
                .when()
                .log().ifValidationFails(LogDetail.ALL)
                .get(endpoint)
                .then()
                .log().ifValidationFails(LogDetail.ALL)
                .statusCode(code)
                .extract()
                .response();
    }

    public Response postRequestWithFile(String endpoint, File file, File schema, Integer code){
        String encodedFileName = URLEncoder.encode(file.getName(), StandardCharsets.UTF_8);
        var responseSpec = given()
                .filter(new AllureRestAssured())
                .contentType("multipart/form-data; charset=UTF-8")
                .header("X-Authorization", "Bearer " + token)
                .multiPart(new MultiPartSpecBuilder(file)
                        .controlName("file") // Имя ключа, которое ждет сервер (обычно "file")
                        .header("Content-Disposition", String.format(
                                "form-data; name=\"file\"; filename=\"%s\"; filename*=UTF-8''%s",
                                file.getName(), encodedFileName))
                        .build()
                )
                .when()
                .log().ifValidationFails(LogDetail.ALL)
                .post(endpoint)
                .then()
                .log().ifValidationFails(LogDetail.ALL);

        if (code != null) {
            responseSpec.statusCode(code);
        }
        if (schema != null) {
            responseSpec.body(JsonSchemaValidator.matchesJsonSchema(schema));
        }
                return responseSpec.extract().response();
    }

    public Response postRequestWithFile(String endpoint, File file){
        return postRequestWithFile(endpoint, file, null, null);
    }

    public Response deleteRequestWithParams(String endpoint, String pathParamName, String pathParamValue,
                                            String queryParamName, String queryParamValue, int code){
        return given()
                .filter(new AllureRestAssured())
                .contentType("application/json; charset=UTF-8")
                .header("X-Authorization", "Bearer " + token)
                .pathParams(pathParamName, pathParamValue)
                .queryParam(queryParamName, queryParamValue)
                .when()
                .log().ifValidationFails(LogDetail.ALL)
                .delete(endpoint)
                .then()
                .log().ifValidationFails(LogDetail.ALL)
                .statusCode(code)
                .extract()
                .response();
    }

}
