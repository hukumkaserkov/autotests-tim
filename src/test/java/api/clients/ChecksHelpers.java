package api.clients;

import io.restassured.response.Response;
import models.*;
import transport.Transport;

import java.io.File;
import java.util.List;

import static api.tests.BaseTest.requirementsHelpers;

public class ChecksHelpers {

    private final Transport transport;

    public ChecksHelpers(Transport transport) {
        this.transport = transport;
    }

    public Response createNewIds(){
        // Добавляем новый файл требований
        String idRequirementsFile = requirementsHelpers.getRequirementId();
        // Создаем на основе новых требований файл ids
        String endpoint = "/requirement/v1/id/create";
        IdsRequirementsAttributes idsRequirementsAttributes =
                new IdsRequirementsAttributes(List.of("9da78c83-7a89-46c7-b8c0-1dca3027549e"),
                        "499db5c7-702f-4809-aab3-a0a447731745",
                        "915f8afc-9d13-41da-a642-4a722f8a5e15",
                        List.of("b38013d7-590a-4780-bc08-849e7e856d32", "1dc87342-f119-48e3-8d17-cadd01bf7e1a"));
        CreateNewIds body = new CreateNewIds(idRequirementsFile, idsRequirementsAttributes, true);
        File schema = new File("src/test/resources/schemas/create-ids-schema.json");
        return transport.postRequestWithToken(endpoint, body, schema, 200);
    }

    public Response startIdsCheck(String appealId, String idsFileId, AdvsrchFilesDto firstFile){

        String endpoint = "/requirement/v1/id/start-check";
        IdsCheckStartDto body = new IdsCheckStartDto(appealId, idsFileId , List.of(firstFile.getFileId()));
        return transport.postRequestNoSchema(endpoint, body, 200);
    }

    public String getConclusionId(String appealId){
        String endpoint = "/appeal/v1/appeals/{appealId}/conclusion/report";
        Response response = transport.getRequestWithParamsNoChecks(endpoint,
                "appealId", appealId);
        return response.jsonPath().getString("conclusionId");
    }
}
