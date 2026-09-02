package api.tests;

import io.restassured.common.mapper.TypeRef;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import jdk.jfr.Description;
import models.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class EveryDayApiTests extends BaseTest{

    // 1. Добавить проверки content-type во все тесты и параметров ответа
    // 2. Добавить во все запросы вывод времени в аллюр(как в первых тестах)

    // ВАЖНО! Завести на всех контурах аккаунт для тестов с одинаковыми кредами везде,
    // чтобы не менять постоянно данные в тестах

    // В некоторых тестах придумать способ более быстрой смены данных для разных
    // контуров. В тесте подписано, где нужно

    // Разобраться с тем, что все тесты вразнобой, а поэтому нужно в BeforeAll создавать два
    // обращения заранее

    // Варианты удаления тестовых данных:
    // 1. Использование изолированных БД (Testcontainers) - вроде круто,
    // но как будто бы лучше и полезнее тестировать на рабочем окружении,
    // чем на каких-то искусственно созданных БД
    // 2. Руками - в целом, не так много, и более всего актуально для прода
    // 3. @AfterEach - сохранять айдишники созданных сущностей и удалять их - слегка
    // жесть и перегруз, но по-моему лучше, чем тестконтейнерс
    // 4. Сброс БД к исходному состоянию до тестов. Тут надо разбираться

    // Думаю, бэковые и UI тесты лучше разделить на два отдельных проекта


//    @Description("Получение реестра обращений")
//    @Test
//    public void GetAppealsList() {
//        String endpoint = "/ws-appeals/v1/appeals/advsearch?page=0&size=10";
//        String body = "{}";
//        File schema = new File("src/test/resources/schemas/appeal-list-schema.json");
//        Response response = transport.postRequestWithToken(endpoint, body, schema, 200);
//
//        // Для примера тест, где не только проверка схемы и кода, но и вывод в аллюр информации по времени запроса
//        allureRequestTime.setResponse(response);
//        allureRequestTime.allureReqTime();
//        assertThat(response.getTime()).as("Значение должно быть < 2000 мс").isLessThan(2000);
//        assertThat(response.getHeader("content-type")).isEqualTo("application/json;charset=utf-8");
//    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("api.data_providers.AppealData#provideDifferentFilterBodies")
    @Description("Получение реестра обращений + фильтрация")
    void testSearchWithDifferentFilters(String testName, String body) {
        String endpoint = "/ws-appeals/v1/appeals/advsearch?page=0&size=10";
        File schema = new File("src/test/resources/schemas/appeal-list-with-filter-schema.json");
        Response response = transport.postRequestWithToken(endpoint, body, schema, 200);

        allureRequestTime.setResponse(response);
        allureRequestTime.allureReqTime();
        assertThat(response.getTime()).as("Значение должно быть < 5000 мс").isLessThan(5000);
        assertThat(response.getHeader("content-type")).isEqualTo("application/json;charset=utf-8");

    }

//    Тест выше заменяет все закомментированные ниже и выше
//    @Description("Фильтрация списка обращений по ВО POST /appeals/advsearch")
//    @Test
//    public void GetAppealsListWithFilterVO() {
//        String endpoint = "/ws-appeals/v1/appeals/advsearch?page=0&size=10";
//        String body = "{\"objectKind\":[\"57e9a44c-f6f3-4350-a8fe-bc37d9e902f8\"]}";
//        File schema = new File("src/test/resources/schemas/appeal-list-with-filter-schema.json");
//        transport.postRequestWithToken(endpoint, body, schema,200);
//    }
//
//    @Description("Фильтрация списка обращений по дате POST /appeals/advsearch")
//    @Test
//    public void GetAppealsListWithFilterDate() {
//        String endpoint = "/ws-appeals/v1/appeals/advsearch?page=0&size=10";
//        String body = "{\"dateFrom\":\"2026-04-30T21:00:00Z\",\"dateTo\":\"2026-05-20T20:59:59.999999999Z\"}";
//        File schema = new File("src/test/resources/schemas/appeal-list-with-filter-schema.json");
//        transport.postRequestWithToken(endpoint, body, schema, 200);
//    }
//
//    @Description("Фильтрация списка обращений по Функц.назначению POST /appeals/advsearch")
//    @Test
//    public void GetAppealsListWithFilterFNO() {
//        String endpoint = "/ws-appeals/v1/appeals/advsearch?page=0&size=10";
//        String body = "{\"objectFunctionalPurpose\":[\"bad5234e-e94b-41f3-bb33-06e7150e7985\"]}";
//        File schema = new File("src/test/resources/schemas/appeal-list-with-filter-schema.json");
//        transport.postRequestWithToken(endpoint, body, schema,200);
//    }
//
//    @Description("Фильтрация списка обращений по Заявителю POST /appeals/advsearch")
//    @Test
//    public void GetAppealsListWithFilterApplicant() {
//        String endpoint = "/ws-appeals/v1/appeals/advsearch?page=0&size=10";
//        String body = "{\"applicant\":[\"d03da375-97f4-4502-a03e-8a798483f16b\"]}";
//        File schema = new File("src/test/resources/schemas/appeal-list-with-filter-schema.json");
//        transport.postRequestWithToken(endpoint, body, schema,200);
//    }

    // ПАРАМЕТРИЗИРОВАННЫЙ ТЕСТ
    @ParameterizedTest(name = "Поиск по слову: {0}")
    @ValueSource(strings = {"Тест", "Проверка", "Обращение"})
    @Description("Поиск по номеру обращения POST /appeals/advsearch?page=0&size=10")
    public void GetAppealsListSearchByWord(String searchWord) {
        String endpoint = "/ws-appeals/v1/appeals/advsearch?page=0&size=10";
        String body = String.format("{\"search\":\"%s\"}", searchWord);
        File schema = new File("src/test/resources/schemas/appeal-list-with-filter-schema.json");
        Response response = transport.postRequestWithToken(endpoint, body, schema, 200);
        List<Object> content = response.jsonPath().getList("content");

        // 4. Проверяем, что ХОТЯ БЫ ОДИН элемент содержит searchTerm
        assertThat(content)
                .as("В списке 'content' должны быть элементы со словом/номером '%s'", searchWord)
                .anyMatch(element -> element.toString().contains(searchWord));
    }

    @Description("Создание обращения POST /ws-appeals/v1/appeals")
    @Test
    public void createAppeal() {
        String endpoint = "/ws-appeals/v1/appeals";
        ObjectKind objectKind = new ObjectKind("57e9a44c-f6f3-4350-a8fe-bc37d9e902f8",
                "Непроизводственного назначения", "N");
        CreateAppeal body = new CreateAppeal("address-reference", objectKind,
                "тестовый объект", "4.1");
        File schema = new File("src/test/resources/schemas/create-appeal-schema.json");
        transport.postRequestWithToken(endpoint, body, schema, 200);
        // добавить проверки параметров ответа
    }

    @Description("Получение данных обращения GET appeals/{appeal_id}")
    @Test
    public void getAppealData(){

        // 1. Получаем список обращений с 1 страницы и берем id первого в списке обращения
        String appealId = appealHelpers.getAppealId();
        // 2. Основная проверка - Получаем данные обращения по id, которое вытащили ранее
        String endpoint = "/ws-appeals/v1/appeals/{appealId}";
        File schema = new File("src/test/resources/schemas/appeal-data-schema.json");
        Response response = transport.getRequestWithParams(endpoint, "appealId", appealId, schema, 200);
        allureRequestTime.setResponse(response);
        allureRequestTime.allureReqTime();
    }

    @Description("Настройка столбцов в реестре PATCH /appeals/register/settings")
    @Test
    public void patchRegisterSettings() {
        String endpoint = "/ws-appeals/v1/appeals/register/settings";
        String body = "{\"appealDate\":false}";
        File schema = new File("src/test/resources/schemas/register-settings-schema.json");
        transport.patchRequest(endpoint, body, schema, 200);
    }

    @Description("Получение настройки столбцов GET /appeals/register/settings")
    @Test
    public void getRegisterSettings(){
        String endpoint = "/ws-appeals/v1/appeals/register/settings";
        File schema = new File("src/test/resources/schemas/register-settings-schema.json");
        Response response = transport.getRequest(endpoint, schema, 200);
        System.out.println(response.asString());
    }

    @Description("Получение списка общих проверок GET /ws-audit/v1/general")
    @Test
    public void getGeneralChecksList(){
        String endpoint = "/ws-audit/v1/general";
        File schema = new File("src/test/resources/schemas/general-checks-list-schema.json");
        transport.getRequest(endpoint, schema, 200);
    }

    @Description("Деактивация проверки POST /ws-audit/v1/general")
    @Test
    public void deactivateGeneralCheck() {
        String endpoint = "/ws-audit/v1/general";
        File schema = new File("src/test/resources/schemas/general-checks-activation-schema.json");
        GeneralChecksCommon body = GeneralChecksCommon.activeFalse();
        transport.postRequestWithToken(endpoint, body, schema, 200);
    }

    @Description("Активация проверки POST /ws-audit/v1/general")
    @Test
    public void activateGeneralChecks() {
        String endpoint = "/ws-audit/v1/general";
        File schema = new File("src/test/resources/schemas/general-checks-activation-schema.json");
        GeneralChecksCommon body = GeneralChecksCommon.activeTrue();
        transport.postRequestWithToken(endpoint, body, schema, 200);
    }

    @Description("Добавление нового файла требований POST /ws-requirements/v1/requirements/file/create")
    @Test
    public void addNewRequirementFile4_1(){
        String endpoint = "/ws-requirements/v1/requirements/file/create?version=4.1";
        File file = new File("src/test/resources/files/requirements/AutoTests_Requirements MGE 4.1 MSSK 5.0 EC_PC.xlsm");
        File schema = new File("src/test/resources/schemas/add-requirement-file-schema.json");

        // Проверяем только сам метод добавления новых требований
        transport.postRequestWithFile(endpoint, file, schema, 200);
    }

    @Description("Получение всех файлов требований GET /ws-requirements/v1/requirements/file/all" +
            "и проверка наличия добавленного файла в ответе")
    @Test
    public void getAllRequirementsFiles(){

        // 1. Добавляем файл и извлекаем idRequirementsFile
        String idRequirementsFile = requirementsHelpers.getRequirementId();
        // 2. Проверяем, что в ответе на /requirements/file/all есть idRequirementsFile
        String endpoint = "/ws-requirements/v1/requirements/file/all";
        File schema = new File("src/test/resources/schemas/requirements-file-all-schema.json");
        Response response = transport.getRequest(endpoint, schema, 200);

        assertThat(response.getBody().asString()).contains(idRequirementsFile);
    }

    @Description("Получение файлов требований пагинированный GET /ws-requirements/v1/requirements/file/paged" +
            "и проверка, что добавленный файл первый в ответе")
    @Test
    public void getRequirementsPagination(){

        // 1. Добавляем файл требований и извлекаем idRequirementsFile
        String idRequirementsFile = requirementsHelpers.getRequirementId();
        // 2. Получаем список файлов требований на 1 странице и проверяем, что 1ым стоит idRequirementsFile
        String endpoint = "/ws-requirements/v1/requirements/file/paged?page=0&size=10&sort=createdAt,DESC";
        File schema = new File("src/test/resources/schemas/requirements-files-pagination-schema.json");
        Response response = transport.getRequest(endpoint, schema, 200);
        String requirementId = response.jsonPath().getString("content[0].id");

        assertThat(requirementId).isEqualTo(idRequirementsFile);
    }

    // Отдельным тестом можно проверку пагинации добавить:
    // Запрос на первую страницу, запрос на вторую, смотрим, что элементы не совпадают

    @Description("Создание IDS - POST /ws-requirements/v1/ids/create")
    @Test
    public void createNewIds(){

        // 1. Добавляем новый файл требований
        String idRequirementsFile = requirementsHelpers.getRequirementId();
        // 2. Создаем на основе новых требований файл ids и проверяем по схеме и коду ответа
        String endpoint = "/ws-requirements/v1/ids/create";
        IdsRequirementsAttributes idsRequirementsAttributes =
                new IdsRequirementsAttributes(List.of("9da38c83-7a89-46c7-b8c0-1dca3027549e"),
                        "499db5c7-702f-4809-aab3-a0a697731745",
                        "915f8afc-9d13-41da-a642-4a703f8a5e15",
                        List.of("b38013d7-590a-4780-bc08-849e7e263d32", "1dc87342-f089-48e3-8d17-cadd01bf7e1a"));
        // Сделать метод фабрику, чтобы вынести все это нагромождение создания тела запроса в отдельный класс
        CreateNewIds body = new CreateNewIds(idRequirementsFile, idsRequirementsAttributes, true);
        File schema = new File("src/test/resources/schemas/create-ids-schema.json");
        transport.postRequestWithToken(endpoint, body, schema, 200);
    }

    @Description("Получение списка IDS POST /ids/file/search и проверка, что созданная ids наверху списка")
    @Test
    public void getIdsList(){

        // 1. Запрашиваем 1ую страницу требований и находим на ней id первого файла требований
        String idRequirementsFile = requirementsHelpers.getFirstPageRequirementId();
        // 2. Делаем IDS из файла требований выше и извлекаем ее id
        String idsId = requirementsHelpers.createIdsAndGetId(idRequirementsFile);
        // 3. Отправляем запрос на получение списка ids и проверяем, что созданная ids первая в ответе
        String endpoint = "/ws-requirements/v1/ids/file/search?page=0&size=10&sort=createdAt,DESC";
        File schema = new File("src/test/resources/schemas/ids-file-list-schema.json");
        Response response = transport.postRequestWithToken(endpoint, IdsSearch.getIdsWithDateFilter(), schema, 200);
        String idsFileId = response.jsonPath().getString("content[0].idsFileId");

        assertThat(idsFileId).isEqualTo(idsId);
    }

    @Description("Получение списка IDS с фильтрацией POST /ids/file/search")
    @Test
    public void getIdsListWithFilter(){
        String endpoint = "/ws-requirements/v1/ids/file/search?page=0&size=10&sort=createdAt,DESC";
        File schema = new File("src/test/resources/schemas/ids-file-list-schema.json");
        IdsSearch body = IdsSearch.getIdsWithOtherFilter();
        transport.postRequestWithToken(endpoint, body, schema, 200);
    }

    @Description("Добавление(create) файла в файлстор - POST /file/create")
    @Test
    public void addFileInFilelibrary(){
        String endpoint = "/ws-filelibrary/v1/file/create";
        File file = new File("src/test/resources/files/ifc/К02_КР_П_R24.ifc");
        File schema = new File("src/test/resources/schemas/file-create-schema.json");
        Response response = transport.postRequestWithFile(endpoint, file, schema, 200);

        assertThat(response.jsonPath().getString("name")).isEqualTo("К02_КР_П_R24");
    }

    @Description("Добавление файлов в нераспределенные POST /files/ungrouped")
    @Test
    public void addFileInUngrouped(){

        // 1. Добавляем файл в файлстор
        Response response = filesHelpers.createNewFile();
        // 2. Достаем тело ответа как джава класс, потом используем геттеры для установки параметров тела запроса ниже
        CreateFileResponse fileData = response.as(CreateFileResponse.class);
        // 3. Через вспомогательный метод достаем id первого в реестре обращения, чтобы вставить в урл
        String appealId = appealHelpers.getAppealId();
        // 4. Основной шаг теста - отправка запроса на добавление файла в нераспределенные
        File schema = new File("src/test/resources/schemas/add-in-ungrouped-schema.json");
        Response resp = filesHelpers.addFileInUngroupedHelper(appealId, fileData);
        resp.then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchema(schema));

        // добавить сюда проверки content-type и параметров ответа
    }

    @Description("Получение структуры групп в обращении GET /groups/object-kind")
    @Test
    public void getGroupsObjectKind(){
        // 1. Получаем appealId
        String appealId = appealHelpers.getAppealId();
        // 2. Запрашиваем группы
        String endpoint = "/ws-appeals/v1/appeals/{appealId}/groups/object-kind";
        File schema = new File("src/test/resources/schemas/object-kind-schema.json");
        transport.getRequestWithParams(endpoint, "appealId", appealId, schema, 200);
    }

    @Description("Добавление файлов в группу/подгруппу POST /files")
    @Test
    public void addFileInGroup(){

        // 1. Получаем айди обращения
        String appealId = appealHelpers.getAppealId();
        // 2. Получаем айди, имя и ник подгруппы в обращении
        String groupId = appealHelpers.getGroupId(appealId);
        String groupName = appealHelpers.getGroupName(appealId);
        String groupNick = appealHelpers.getGroupNick(appealId);
        // 3. Создаем и добавляем файл в группу найденного выше обращения и проверяем схему с кодом
        filesHelpers.AddFileInGroup(appealId, groupId, groupName, groupNick);
        // Возможно проверку кода и схемы лучше вынести из хэлпера
    }

    @Description("Получение списка файлов в обращении POST /files/advsrch")
    @Test
    public void filesListInAppeal(){
        // 1. Получаем обращение и его id
        String appealId = appealHelpers.getAppealId();
        // 2. Запрашиваем список файлов в обращении и проверяем
        File schema = new File("src/test/resources/schemas/files-advsearch-schema.json");
        Response resp = filesHelpers.getAllfilesInAppeal(appealId);
        resp.then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchema(schema));
    }

    @Description("Получение нераспределенных файлов GET /files/ungrouped")
    @Test
    public void ungroupedFilesListInAppeal(){
        // 1. Получаем обращение и его id
        String appealId = appealHelpers.getAppealId();
        // 2. Запрашиваем список файлов в обращении и проверяем
        String endpoint = "/ws-appeals/v1/appeals/{appealId}/files/ungrouped";
        File schema = new File("src/test/resources/schemas/files-ungrouped-schema.json");
        transport.getRequestWithParams(endpoint, "appealId", appealId, schema, 200);
    }

    @Description("Добавление файлов из нераспределенных в группу POST /files/move-to-group")
    @Test
    public void moveToGroup(){
        // 1. Create файл
        Response response = filesHelpers.createNewFile();
        // 2. Достаем тело ответа после создания файла как дата класс
        CreateFileResponse fileData = response.as(CreateFileResponse.class);
        // 3. Запросить номер обращения и тд нужное из обращения для дальнейшего использования
        String appealId = appealHelpers.getAppealId();
        String groupId = appealHelpers.getGroupId(appealId);
        String groupName = appealHelpers.getGroupName(appealId);
        String groupNick = appealHelpers.getGroupNick(appealId);
        // 4. Добавляем в нераспределенные с помощью вспомагательного метода,
        // забираем тело ответа как массив(лист),
        // чтобы потом можно было его использовать как дата класс
        Response resp = filesHelpers.addFileInUngroupedHelper(appealId, fileData);
        // 5. Получаем список объектов DTO
        List <AddUngroupedResponse> respList = resp.as(new TypeRef<>() {});
        // 6. Из массива мы берем первый объект в {}, чтобы потом брать данные из этого тела ответа для теста
        AddUngroupedResponse moveFileData = respList.getFirst();
        // 7. Сам тест - добавление из нераспределенных в группу, для него из moveFileData набираем данных
        String endpoint = "/ws-appeals/v1/appeals/{appealId}/files/move-to-group";
        File schema = new File("src/test/resources/schemas/move-to-group-schema.json");
        MoveToGroupRequest moveToGroupRequest = new MoveToGroupRequest(moveFileData.getId(), moveFileData.getFileId(),
                moveFileData.getName(), moveFileData.getExtension(),
                moveFileData.getCreateDate(), moveFileData.getSize(), "NIKTEST");
        AppealGroupDto<List<MoveToGroupRequest>> body = new AppealGroupDto<>(groupId, groupName,
                groupNick, List.of(moveToGroupRequest));
        transport.postRequestWithParams(endpoint, "appealId", appealId, body, schema, 200);
    }

    @Description("Замена(Версия) файла из нераспределенных PATCH /files/update-version")
    @Test
    public void addVersionFromUngrouped(){

        // 1. Запросить номер обращения(первое в реестре) и тд нужное из обращения для дальнейшего использования
        String appealId = appealHelpers.getAppealId();
        String fileGroupId = appealHelpers.getGroupId(appealId);
        String groupName = appealHelpers.getGroupName(appealId);
        String groupNick = appealHelpers.getGroupNick(appealId);
        // 2. Создаем файл и добавляем в группу, чтобы в обращении точно был файл в группе,
        // к которому мы будем делать версию
        filesHelpers.AddFileInGroup(appealId, fileGroupId, groupName, groupNick);
        // 3. Create файл
        Response response = filesHelpers.createNewFile();
        // 4. Достаем тело ответа после создания файла как дата класс
        CreateFileResponse fileData = response.as(CreateFileResponse.class);
        // 5. Добавляем в нераспределенные с помощью вспомагательного метода,
        // забираем тело ответа как массив(лист),
        // чтобы потом можно было его использовать как дата класс
        Response resp = filesHelpers.addFileInUngroupedHelper(appealId, fileData);
        // 6. Получаем список объектов DTO
        List <AddUngroupedResponse> respList = resp.as(new TypeRef<>() {});
        // 7. Из массива мы берем первый объект в {}, чтобы потом брать данные из этого тела ответа для теста
        AddUngroupedResponse moveFileData = respList.getFirst();
        // 8. Запрашиваем все файлы, которые есть в обращении и находим code у первого в списке
        Response allFiles = filesHelpers.getAllfilesInAppeal(appealId);
        String code = allFiles.jsonPath().getString("content[0].files[0].code");
        // 9. Основной запрос и проверка
        String endpoint = "/ws-appeals/v1/appeals/{appealId}/files/update-version";
        FileDto file = new FileDto(moveFileData.getId(), moveFileData.getFileId(), moveFileData.getName(), code);
        AppealGroupDto<FileDto> body = new AppealGroupDto<>(fileGroupId, file);
        File schema = new File("src/test/resources/schemas/update-version-from-ungrouped-schema.json");
        transport.patchRequestWithParams(endpoint, "appealId", appealId, body, schema, 200);
    }

    @Description("Замена(Версия) файла в группе/подгруппе PATCH /files")
    @Test
    public void addVersionInGroup(){

        // 1. Запросить номер обращения(первое в реестре) и тд нужное из обращения для дальнейшего использования
        String appealId = appealHelpers.getAppealId();
        String fileGroupId = appealHelpers.getGroupId(appealId);
        String groupName = appealHelpers.getGroupName(appealId);
        String groupNick = appealHelpers.getGroupNick(appealId);
        // 2. Создаем файл и добавляем в группу, чтобы в обращении точно был файл в группе,
        // к которому мы будем делать версию
        filesHelpers.AddFileInGroup(appealId, fileGroupId, groupName, groupNick);
        // только для НН (для ЛО нужно писать отдельный метод)
        Response response = filesHelpers.createNewFile();
        // 3. Достаем тело ответа после создания файла как дата класс
        CreateFileResponse fileData = response.as(CreateFileResponse.class);
        // 4. Запрашиваем все файлы в обращении, берем первый и достаем из него код
        Response allFiles = filesHelpers.getAllfilesInAppeal(appealId);
        String code = allFiles.jsonPath().getString("content[0].files[0].code");
        // 5. Основная проверка - добавление версии через PATCH
        String endpoint = "/ws-appeals/v1/appeals/{appealId}/files";
        FileDto fileDto = new FileDto(fileData.getId(), fileData.getName(), fileData.getFileExt(),
                fileData.getCreateDate(), fileData.getSize(), "NIKTEST", 2, code);
        AppealGroupDto<FileDto> body = new AppealGroupDto<>(fileGroupId, groupName, groupNick, fileDto);
        File schema = new File("src/test/resources/schemas/add-version-in-group-schema.json");
        transport.patchRequestWithParams(endpoint, "appealId", appealId, body, schema, 200);

        // + проверить, что есть файл и есть версия
    }

    @Description("Удаление файла PATCH /files")
    @Test
    public void deleteFileInGroup() {
        // 1. Нужно для запроса получить номер обращения и тд
        String appealId = appealHelpers.getAppealId();
        String fileGroupId = appealHelpers.getGroupId(appealId);
        String groupName = appealHelpers.getGroupName(appealId);
        String groupNick = appealHelpers.getGroupNick(appealId);
        // 2. Создаем файл и добавляем в группу, чтобы в обращении точно был файл в группе
        filesHelpers.AddFileInGroup(appealId, fileGroupId, groupName, groupNick);
        // 3. Получить инфу по файлу
        Response allFiles = filesHelpers.getAllfilesInAppeal(appealId);
        List<AdvsrchGroupDto> respList = allFiles.jsonPath()
                .getList("content", AdvsrchGroupDto.class);
        // 4. Из массива мы берем первый объект в {}, чтобы потом брать данные из этого тела ответа для теста
        AdvsrchFilesDto firstFile = respList.getFirst().getFiles().getFirst();
        // 5. Удалить файл
        String endpoint = "/ws-appeals/v1/appeals/{appealId}/files";
        FileDto fileDto = new FileDto(firstFile.getFileId(), firstFile.getName(),
                firstFile.getExtension(), firstFile.getCreateDate(), firstFile.getSize(),
                "NIKTEST", firstFile.getVersion(), firstFile.getCode(), true);
        AppealGroupDto<FileDto> body = new AppealGroupDto<>(fileGroupId, groupName, groupNick, fileDto);
        File schema = new File("src/test/resources/schemas/delete-file-in-group-schema.json");
        Response response = transport.patchRequestWithParams(endpoint, "appealId", appealId, body, schema, 200);
        String deletedId = response.jsonPath().getString("file.fileId");
        // 6. Проверка, что файла больше нет в запросе всех файлов /advsrch/
        Response filesInAppeal = filesHelpers.getAllfilesInAppeal(appealId);
        List<String> allIds = filesInAppeal.jsonPath().getList("id", String.class);
        assertThat(allIds)
                .as("Список ID не должен содержать удалённый ID: " + deletedId)
                .doesNotContain(deletedId);
    }

    @Description("Удаление файла из нераспределенных DELETE /files/ungrouped/delete-file")
    @Test
    public void deleteFileInUngrouped(){

        String appealId = appealHelpers.getAppealId();
        Response response = filesHelpers.createNewFile();
        // 1. Достаем тело ответа после создания файла как дата класс
        CreateFileResponse fileData = response.as(CreateFileResponse.class);
        // 2. Добавляем в нераспределенные с помощью вспомагательного метода,
        // забираем тело ответа как массив(лист),
        // чтобы потом можно было его использовать как дата класс
        Response resp = filesHelpers.addFileInUngroupedHelper(appealId, fileData);
        // 3. Получаем список объектов DTO
        List <AddUngroupedResponse> respList = resp.as(new TypeRef<>() {});
        // 4. Из массива мы берем первый объект в {}, чтобы потом брать данные из этого тела ответа для теста
        AddUngroupedResponse moveFileData = respList.getFirst();
        String fileId = moveFileData.getFileId();
        String endpoint = "/ws-appeals/v1/appeals/{appealId}/files/ungrouped/delete-file";
        transport.deleteRequestWithParams(endpoint, "appealId", appealId, "fileId",
                fileId, 204);

        // Сделать проверку, что файла с таким айди больше нет в нераспрделенных
    }

    @Description("Запуск общей проверки POST /checks")
    @Test
    public void commonCheckStart() throws InterruptedException {

        // 1. Нужно для запроса получить номер обращения и тд
        String appealId = appealHelpers.getAppealId();
        String fileGroupId = appealHelpers.getGroupId(appealId);
        String groupName = appealHelpers.getGroupName(appealId);
        String groupNick = appealHelpers.getGroupNick(appealId);
        // 2. Создаем файл и добавляем в группу, чтобы в обращении точно был файл в группе
        filesHelpers.AddFileInGroup(appealId, fileGroupId, groupName, groupNick);
        // 3. Получаем список всех файлов в обращении
        Response allFiles = filesHelpers.getAllfilesInAppeal(appealId);
        List<AdvsrchGroupDto> respList = allFiles.jsonPath()
                .getList("content", AdvsrchGroupDto.class);
        // 4. Из массива мы берем первый объект в {}, чтобы потом брать данные из этого тела ответа для теста
        AdvsrchFilesDto firstFile = respList.getFirst().getFiles().getFirst();
         // 5. Подождать, пока файл распарсится, запустить проверку с указанием хотя бы одного файла
        Thread.sleep(30000);
        String endpoint = "/ws-appeals/v1/appeals/{appealId}/checks";
        FileDto file = new FileDto(firstFile.getFileId());
        CommonChecksGroupDto checksGroupDto = new CommonChecksGroupDto(fileGroupId, groupName, groupNick,
                 List.of(file));
        CommonChecksHighLevelDto body = new CommonChecksHighLevelDto(List.of(checksGroupDto));
        transport.postRequestWithParamsNoSchema(endpoint, "appealId", appealId, body, 201);
    }

    @Description("Получение результатов общей проверки GET /common-check-results")
    @Test
    public void commonCheckResults(){
        // Не совсем самодостаточный тест, так как нужно, чтобы были проверки в обращении,
        // Но именно для запроса это не так важно, даже если будет пустым
        String appealId = appealHelpers.getAppealId();
        String endpoint = "/ws-appeals/v1/appeals/{appealId}/common-check-results";
        File schema = new File("src/test/resources/schemas/common-check-results-schema.json");
        transport.getRequestWithParams(endpoint, "appealId", appealId, schema, 200);
    }

    @Description("Запуск проверки по ids POST /ids/start-check")
    @Test
    public void idsCheckStart() throws InterruptedException {
        // 1. Получить appealId, idsFileId, ifcFileId
        String appealId = appealHelpers.getAppealId();
        String fileGroupId = appealHelpers.getGroupId(appealId);
        String groupName = appealHelpers.getGroupName(appealId);
        String groupNick = appealHelpers.getGroupNick(appealId);
        // 2. Добавить файл в группу
        filesHelpers.AddFileInGroup(appealId, fileGroupId, groupName, groupNick);
        // 3. Получение всех файлов
        Response allFiles = filesHelpers.getAllfilesInAppeal(appealId);
        List<AdvsrchGroupDto> respList = allFiles.jsonPath()
                .getList("content", AdvsrchGroupDto.class);
        // 4. Из массива мы берем первый объект в {}, чтобы потом брать данные из этого тела ответа для теста
        AdvsrchFilesDto firstFile = respList.getFirst().getFiles().getFirst();
        // 5. Создаем новую ids
        Response response = checksHelpers.createNewIds();
        String idsFileId = response.jsonPath().getString("[0].id");

        Thread.sleep(30000);
        checksHelpers.startIdsCheck(appealId, idsFileId, firstFile);

        // + можно проверить, что при запросе POST /files/advsrch приходит проверка в "lastIdsCheck"
    }

    @Description("Получение детального отчета ids/check-summary-report-data")
    @Test
    public void getCheckSummaryReport() throws InterruptedException {
        // 1. Найти обращение и получить данные по группе и обращению
        String appealId = appealHelpers.getAppealId();
        String fileGroupId = appealHelpers.getGroupId(appealId);
        String groupName = appealHelpers.getGroupName(appealId);
        String groupNick = appealHelpers.getGroupNick(appealId);
        // 2. Добавить файл в группу
        filesHelpers.AddFileInGroup(appealId, fileGroupId, groupName, groupNick);
        // 3. Получить все файлы в обращении
        Response allFiles = filesHelpers.getAllfilesInAppeal(appealId);
        List<AdvsrchGroupDto> respList = allFiles.jsonPath()
                .getList("content", AdvsrchGroupDto.class);
        // 4. Из массива мы берем первый объект в {}, чтобы потом брать данные из этого тела ответа для теста
        AdvsrchFilesDto firstFile = respList.getFirst().getFiles().getFirst();
        // 5. Создать ids, получить ее id
        Response response = checksHelpers.createNewIds();
        String idsFileId = response.jsonPath().getString("[0].id");
        // 6. Подождать 26 сек пока создастся ids и добавленный файл распарсится
        Thread.sleep(26000);
        // 7. Запустить проверку по ids
        checksHelpers.startIdsCheck(appealId, idsFileId, firstFile);
        // 8. Подождать 20 сек, пока проверка не закончится
        Thread.sleep(20000);
        // 9. Получить детальный отчет
        String endpoint = "/ws-requirements/v1/ids/check-summary-report-data";
        File schema = new File("src/test/resources/schemas/check-summary-report-data-schema.json");
        transport.getRequestWithQueryParams(endpoint, "appealId", appealId, schema, 200);
    }

    // ТЕСТ НИЖЕ НАДО ДИЗЕЙБЛИТЬ НА ПРОДЕ!!!
    // @Disabled
    @Description("Сформировать заключение GET /ws-appeals/v1/conclusions/{appealId}/report")
    @Test
    public void getConclusionReport(){
        // 1. Подготовка и сам запрос для формирования заключения
        String appealId = appealHelpers.getAppealId();
        String conclusionId = checksHelpers.getConclusionId(appealId);
        String endpoint = "/ws-appeals/v1/conclusions/{conclusionId}/report";
        transport.getReqWithParamsNoSchema(endpoint, "conclusionId", conclusionId, 200);
        // 2. Проверка, что заключение сформировано и есть в списке репортов в ответе на conclusion/reports
        endpoint = "/ws-appeals/v1/appeals/{appealId}/conclusion/reports";
        Response response = transport.getRequestWithParamsNoSchema(endpoint, "appealId",
                appealId, Map.of("size", 999999, "sort", "create_date,desc"), 200);
        String fileId = response.jsonPath().getString("reportFiles.content[0].fileId");
        assertThat(fileId)
                .as("Параметр 'fileId' должен присутствовать в ответе и не быть пустым")
                .isNotNull()
                .isNotBlank();
    }

    @Description("Редактирование заключения /ws-appeals/v1/conclusions/{conclusionId}")
    @Test
    public void patchConclusion() throws IOException {
        // 1. Получить appealId
        String appealId = appealHelpers.getAppealId();
        // 2. Вызвать запрос /conclusion/reports и взять из него ConclusionId
        String conclusionId = checksHelpers.getConclusionId(appealId);
        // 3. Запросить /model-description-attachments и взять оттуда fileGroupId(два)
        String endpoint = "/ws-appeals/v1/appeals/{appealId}/model-description-attachments";
        Response resp = transport.getRequestWithParamsNoChecks(endpoint,
                "appealId", appealId);
        String fileGroupId1 = resp.jsonPath().getString("dim[0].fileGroups[0].fileGroupId");
        String fileGroupId2 = resp.jsonPath().getString("dim[0].fileGroups[0].subGroups[0].fileGroupId");
        // 4. Основная проверка - Отправить запрос на редактирование с телом из payloads,
        // вставив в это тело полученные ранее fileGroupId
        String body = Files.readString(Path.of("src/test/resources/payloads/patch-conclusion.json"))
                .replace("{{fileGroupId1}}", fileGroupId1)
                .replace("{{fileGroupId2}}", fileGroupId2);
        endpoint = "/ws-appeals/v1/conclusions/{conclusionId}";
        transport.patchRequestWithParamsNoSchema(endpoint, "conclusionId",
                conclusionId, body, 204);
    }

    @Description("Скачать файл из файлстора")
    @Test
    public void loadFileFromFileLibrary() throws InterruptedException, IOException {

        // 1. Получить данные обращения, группы и тд
        String appealId = appealHelpers.getAppealId();
        String fileGroupId = appealHelpers.getGroupId(appealId);
        String groupName = appealHelpers.getGroupName(appealId);
        String groupNick = appealHelpers.getGroupNick(appealId);
        // 2. Добавить файл в группу
        filesHelpers.AddFileInGroup(appealId, fileGroupId, groupName, groupNick);
        Thread.sleep(30000);
        // 3. Запрашиваем все файлы в обращении, берем fileId
        Response allFiles = filesHelpers.getAllfilesInAppeal(appealId);
        String fileId = allFiles.jsonPath().getString("content[0].files[0].fileId");
        // 4. Путь в директорию
        Path downloadDir = Paths.get("target/downloads");
        // 5. Если директории не будет, создаем ее
        Files.createDirectories(downloadDir);
        // 6. Основной запрос на скачивание и сохранение в файл
        Path filePath = downloadDir.resolve("downloaded_file.ifc");
        String endpoint = "/ws-filelibrary/v1/file/{fileId}";
        Response response = transport.getReqWithParamsNoSchema(endpoint, "fileId",
                fileId, 200);
        byte[] fileBytes = response.asByteArray();
        Files.write(filePath, fileBytes, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        // 7. Проверяем, что файл действительно существует на диске и это обычный файл
        assertThat(filePath)
                .exists()
                .isRegularFile();

        // 8. Проверяем, что размер файла больше 0 байт (он не пустой)
        assertThat(Files.size(filePath))
                .as("Размер скачанного файла должен быть больше 0 байт")
                .isGreaterThan(0);

    }

    @Description("Удалить обращение PATCH /ws-appeals/v1/appeals/{appealId}")
    @Test
    public void deleteAppeal(){
        // 1. Основной запрос на удаление
        String appealId = appealHelpers.getAppealId();
        String endpoint = "/ws-appeals/v1/appeals/{appealId}";
        String body = "{\"deleted\":true}";
        File schema = new File("src/test/resources/schemas/delete-appeal-schema.json");
        Response response = transport.patchRequestWithParams(endpoint, "appealId",
                appealId, body, schema, 200);
        // 2. Достаем данные из ответа и проверяем, что deleted = true
        Boolean deleted = response.jsonPath().getBoolean("deleted");
        assertThat(deleted)
                .as("Файл должен быть помечен как удаленный (deleted = true)")
                .isTrue();
    }
}

















