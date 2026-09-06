package api.clients;

import io.restassured.response.Response;
import models.CreateNewIds;
import models.IdsRequirementsAttributes;
import transport.Transport;

import java.io.File;
import java.util.List;

public class RequirementsHelpers {

    private final Transport transport;

    // Зависимость приходит извне через конструктор
    public RequirementsHelpers(Transport transport) {
        this.transport = transport;
    }

    public void addRequirementsFile(){
        String endpoint = "/requirements/v1/requirement/file/create?version=4.1";
        File file = new File("src/test/resources/files/requirements/AutoTests_2 RequirementsMGE 4.1 MSSK 5.0 EC_PC(from prod).xlsm");
        transport.postRequestWithFile(endpoint, file);
    }

    public String getRequirementId(){

        String endpoint = "/requirements/v1/requirement/file/create?version=4.1";
        File file = new File("src/test/resources/files/requirements/AutoTests_2 RequirementsMGE 4.1 MSSK 5.0 EC_PC(from prod).xlsm");
        Response response = transport.postRequestWithFile(endpoint, file);
        return response.jsonPath().getString("idRequirementsFile");
    }

    public String getFirstPageRequirementId(){

        String endpoint = "/requirements/v1/requirement/file/paged?page=0&size=10&sort=createdAt,DESC";
        Response response = transport.getRequestWithoutChecks(endpoint);
        return response.jsonPath().getString("content[0].id");
    }

    public String createIdsAndGetId(String idRequirementsFile){

        String endpoint = "/requirements/v1/ids/create";
        IdsRequirementsAttributes idsRequirementsAttributes =
                new IdsRequirementsAttributes(List.of("9da11c83-7a89-46c7-b8c0-1dca3027549e"),
                        "499db5c7-702f-9809-aab3-a0a697731745",
                        "915f8afc-9d13-81da-a642-4a703f8a5e15",
                        List.of("b38013d7-590a-4120-bc08-849e7e263d32", "1dc85872-f089-48e3-8d17-cadd01bf7e1a"));
        CreateNewIds body = new CreateNewIds(idRequirementsFile, idsRequirementsAttributes, true);
        Response idsResponse = transport.postRequestWithTokenNoChecks(endpoint, body);
        return idsResponse.jsonPath().getString("[0].id");
    }


}
