package api.clients;

import io.restassured.response.Response;
import models.AddFileInGroupRequest;
import models.AppealGroupDto;
import models.CreateFileResponse;
import models.FileDto;
import transport.Transport;

import java.io.File;
import java.util.List;

public class FilesHelpers {

    private final Transport transport;

    // Зависимость приходит извне через конструктор
    public FilesHelpers(Transport transport) {
        this.transport = transport;
    }

    // добавляем 1 файл
    public Response createNewFile(){
        String endpoint = "/ws-filelibrary/v1/file/create";
        File file = new File("src/test/resources/files/ifc/К02_КР_П_R24.ifc");
        return transport.postRequestWithFile(endpoint, file);
    }

    // добавляем несколько count количество файлов из папки
    public void createSomeFiles(int count) {
        String endpoint = "/ws-filelibrary/v1/file/create";
        File folder = new File("src/test/resources/files/ifc");
        File[] files = folder.listFiles();

        if (files != null) {
            for (int i = 0; i < Math.min(count, files.length); i++) {
                transport.postRequestWithFile(endpoint, files[i]);
            }
        }
    }

    // добавляем файл в нераспределенные, возвращаем ответ
    public Response addFileInUngroupedHelper(String appealId,
                                   CreateFileResponse fileData){

        String endpoint = "/ws-appeals/v1/appeals/{appealId}/files/ungrouped";
        FileDto bodyDto = new FileDto(fileData.getId(), fileData.getName(), fileData.getFileExt(),
                fileData.getCreateDate(), fileData.getSize(), "NIKTEST");
        List<FileDto> body = List.of(bodyDto);
        return transport.postRequestWithParamsNoChecks(endpoint, "appealId", appealId, body);
    }

    // Запрос всех файлов, которые есть в обращении
    public Response getAllfilesInAppeal(String appealId){

        String endpoint = "/ws-appeals/v1/appeals/{appealId}/files/advsrch";
        String body = "{}";
        return transport.postRequestWithParamsNoChecks(endpoint, "appealId", appealId, body);
    }

    // Добавление файла в группу
    public Response AddFileInGroup(String appealId, String groupId,
                                   String groupName, String groupNick){

        Response response = createNewFile();
        CreateFileResponse fileData = response.as(CreateFileResponse.class);

        String endpoint = "/ws-appeals/v1/appeals/{appealId}/files";
        // ниже мы разбили тело на 3 ДТО и собрали из них тело запроса
        FileDto filesDto = new FileDto(fileData.getId(), fileData.getName(), fileData.getFileExt(),
                fileData.getCreateDate(), fileData.getSize(), "NIKTEST");
        AppealGroupDto<List<FileDto>> appealGroupDto = new AppealGroupDto<>(groupId, groupName,
                groupNick, List.of(filesDto));
        AddFileInGroupRequest<List<FileDto>> body = new AddFileInGroupRequest<>(appealGroupDto);
        File schema = new File("src/test/resources/schemas/add-file-in-group-schema.json");
        return transport.postRequestWithParams(endpoint, "appealId", appealId, body, schema, 200);

    }
}
