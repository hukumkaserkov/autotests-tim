package models;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FileDto {

    public String id;
    public String fileId;
    public String name;
    public String extension;
    public String createDate;
    public Long size;
    public String author;
    public String code;
    public Integer version;
    public Boolean delete;


    public FileDto(String fileId, String name, String extension,
                                            String createDate, Long size, String author) {
        this.fileId = fileId;
        this.name = name;
        this.extension = extension;
        this.createDate = createDate;
        this.size = size;
        this.author = author;
    }

    public FileDto(String id, String fileId, String name, String code){

        this.id = id;
        this.fileId = fileId;
        this.name = name;
        this.code = code;
    }

    public FileDto(String fileId, String name, String extension,
                   String createDate, Long size, String author, Integer version, String code) {
        this.fileId = fileId;
        this.name = name;
        this.extension = extension;
        this.createDate = createDate;
        this.size = size;
        this.author = author;
        this.version = version;
        this.code = code;
    }

    public FileDto(String fileId, String name, String extension,
                   String createDate, Long size, String author, Integer version, String code,
                   Boolean delete) {
        this.fileId = fileId;
        this.name = name;
        this.extension = extension;
        this.createDate = createDate;
        this.size = size;
        this.author = author;
        this.version = version;
        this.code = code;
        this.delete = delete;
    }

    public FileDto(String id){

        this.id = id;
    }

    public FileDto() {
    }

    public String getFileId() {
        return fileId;
    }

    public String getName() {
        return name;
    }

    public String getExtension() {
        return extension;
    }

    public String getCreateDate() {
        return createDate;
    }

    public Long getSize() {
        return size;
    }

    public String getAuthor() {
        return author;
    }

    public String getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public Integer getVersion() {
        return version;
    }

    public Boolean getDelete() {
        return delete;
    }
}
