package api.clients;

import io.restassured.response.Response;
import models.CreateAppeal;
import models.ObjectKind;
import transport.Transport;

public class AppealHelpers {

    private final Transport transport;

    // Зависимость приходит извне через конструктор
    public AppealHelpers(Transport transport) {
        this.transport = transport;
    }

    public String getAppealId(){
        String endpoint = "/appeals/v1/appeal/search?page=0&size=10";
        String body = "{}";
        Response response = transport.postRequestWithTokenNoChecks(endpoint, body);
        return response.jsonPath()
                .getString("content.find { it.appealNumber?.contains('Авто') }.id");
        // ищет сверху вниз по телу ответа первое совпадение имени, содержащего "Авто"
    }

    // получение групп в обращении и id одной из групп для добавления файлов
    public String getGroupId(String appealId){

        String endpoint = "/appeals/v1/appeal/{appealId}/groups/obj-kind";
        Response response = transport.getRequestWithParamsNoChecks(endpoint, "appealId", appealId);
        return response.jsonPath().getString("dim.structureObjectKinds[0].appealFileGroups[0].subGroups[0].id");
    }

    public String getGroupName(String appealId){

        String endpoint = "/appeals/v1/appeal/{appealId}/groups/obj-kind";
        Response response = transport.getRequestWithParamsNoChecks(endpoint, "appealId", appealId);
        return response.jsonPath().getString("dim.structureObjectKinds[0].appealFileGroups[0].subGroups[0].groupName");
    }

    public String getGroupNick(String appealId){

        String endpoint = "/appeals/v1/appeal/{appealId}/groups/obj-kind";
        Response response = transport.getRequestWithParamsNoChecks(endpoint, "appealId", appealId);
        return response.jsonPath().getString("dim.structureObjectKinds[0].appealFileGroups[0].subGroups[0].groupNick");
    }

    public void createAppeal(){
        String endpoint = "/appeals/v1/appeal";
        ObjectKind objectKind = new ObjectKind("57e9a44c-f6f3-4350-a8fe-bc37d9e902f8",
                "Непроизводственного назначения", "N");
        CreateAppeal body = new CreateAppeal("address-reference", objectKind,
                "тестовый объект", "4.1");
        transport.postRequestWithTokenNoChecks(endpoint, body);
    }

}
