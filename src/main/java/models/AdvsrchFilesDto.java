package models;

import java.util.List;

public class AdvsrchFilesDto {

    private String id;
    private String fileId;
    private Integer version;
    private Long size;
    private String extension;
    private String name;
    private String createDate;
    private AdvsrchAuthorDto author;
    private String code;
    private LastIdsCheckDto lastIdsCheck;
    private String status;
    private String message;
    private String parsingErrorsFileId;
    private List<Object> reports;
    private String ifcVersion;

    // Пустой конструктор для Jackson
    public AdvsrchFilesDto() {
    }

    public AdvsrchFilesDto(String fileId, String name, String extension,
                   String createDate, Long size, AdvsrchAuthorDto author, Integer version,
                           String code) {

        this.fileId = fileId;
        this.name = name;
        this.extension = extension;
        this.createDate = createDate;
        this.size = size;
        this.author = author;
        this.version = version;
        this.code = code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public AdvsrchAuthorDto getAuthor() {
        return author;
    }

    public void setAuthor(AdvsrchAuthorDto author) {
        this.author = author;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LastIdsCheckDto getLastIdsCheck() {
        return lastIdsCheck;
    }

    public void setLastIdsCheck(LastIdsCheckDto lastIdsCheck) {
        this.lastIdsCheck = lastIdsCheck;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getParsingErrorsFileId() {
        return parsingErrorsFileId;
    }

    public void setParsingErrorsFileId(String parsingErrorsFileId) {
        this.parsingErrorsFileId = parsingErrorsFileId;
    }

    public List<Object> getReports() {
        return reports;
    }

    public void setReports(List<Object> reports) {
        this.reports = reports;
    }

    public String getIfcVersion() {
        return ifcVersion;
    }

    public void setIfcVersion(String ifcVersion) {
        this.ifcVersion = ifcVersion;
    }
}
