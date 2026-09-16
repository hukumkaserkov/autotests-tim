package models;

import java.util.List;

public class GeneralChecksCommon {
    public String id;
    public String nick;
    public String name;
    public String description;
    public Boolean active;
    public String fileExt;
    public List<Object> ifcVersion;
    public String fileSize;
    public String dateCreated;
    public String dateChanged;
    public ModifiedBy modifiedBy;

    public GeneralChecksCommon(String id, String nick, String name, String description, Boolean active,
                               String fileExt, List<Object> ifcVersion, String fileSize, ModifiedBy modifiedBy){
        this.id = id;
        this.nick = nick;
        this.name = name;
        this.description = description;
        this.active = active;
        this.fileExt = fileExt;
        this.ifcVersion = ifcVersion;
        this.fileSize = fileSize;
        this.dateCreated = setDateCreated();
        this.dateChanged = setDateChanged();
        this.modifiedBy = modifiedBy;
    }

    private String setDateCreated(){
        return java.time.LocalDate.now().toString();
    }

    private String setDateChanged(){
        return java.time.LocalDate.now().toString();
    }

    public static GeneralChecksCommon activeTrue(){
        return new GeneralChecksCommon("673ff939-69ca-0375-2fb2-f64ae88b292e", "check_storeyName",
                "Проверка ЦИМ на соответствие требованиям к наименованию уровней", "", true,
                null, List.of(), null, ModifiedBy.setDefaultValues());
    }

    public static GeneralChecksCommon activeFalse(){
        return new GeneralChecksCommon("673ff939-69ca-0375-2fb2-f64ae88b292e", "check_storeyName",
                "Проверка ЦИМ на соответствие требованиям к наименованию уровней", "", false,
                null, List.of(), null, ModifiedBy.setDefaultValues());
    }

    public String getId() {
        return id;
    }

    public String getNick() {
        return nick;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getActive() {
        return active;
    }

    public String getFileExt() {
        return fileExt;
    }

    public List<Object> getIfcVersion() {
        return ifcVersion;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public String getFileSize() {
        return fileSize;
    }

    public String getDateChanged() {
        return dateChanged;
    }

    public ModifiedBy getModifiedBy() {
        return modifiedBy;
    }
}
