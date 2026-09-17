package models;

import java.time.LocalDate;
import java.util.List;
import java.time.LocalDateTime;

// Если нужен будет фильтр по параметрам appealId и useInCheck, то нужно будет делать отдельные ДТО

public class IdsSearch {

    public String createdFrom;
    public String createdTo;
    public List<String> fnos;
    public List<String> groups;
    public Boolean isActual;
    public List<String> objectKinds;
    public List<String> projectChapters;
    public List<String> requirementsFileIds;

    public IdsSearch(String createdFrom, String createdTo, List<String> fnos, List<String> groups,
                     Boolean isActual, List<String> objectKinds, List<String> projectChapters,
                     List<String> requirementsFileIds){
        this.createdFrom = createdFrom;
        this.createdTo = createdTo;
        this.fnos = fnos;
        this.groups = groups;
        this.isActual = isActual;
        this.objectKinds = objectKinds;
        this.projectChapters = projectChapters;
        this.requirementsFileIds = requirementsFileIds;
    }

    public static IdsSearch getIdsWithDateFilter(){
        return new IdsSearch("2026-01-01T00:00:00",
                LocalDate.now().atTime(23, 59, 59).toString(), List.of(),
                List.of(), true, List.of(), List.of(), List.of());
    }

    public static IdsSearch getIdsWithOtherFilter(){
        return new IdsSearch("2026-01-01T00:00:00",
                LocalDate.now().atTime(23, 59, 59).toString(), List.of(),
                List.of(), true, List.of(), List.of("ИОС"), List.of());
    }

    public String getCreatedFrom() {
        return createdFrom;
    }

    public String getCreatedTo() {
        return createdTo;
    }

    public List<String> getFnos() {
        return fnos;
    }

    public List<String> getGroups() {
        return groups;
    }

    public Boolean getActual() {
        return isActual;
    }

    public List<String> getObjectKinds() {
        return objectKinds;
    }

    public List<String> getProjectChapters() {
        return projectChapters;
    }

    public List<String> getRequirementsFileIds() {
        return requirementsFileIds;
    }
}
