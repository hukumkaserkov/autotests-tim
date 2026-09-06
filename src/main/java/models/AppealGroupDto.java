package models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppealGroupDto<T> {

    private String fileGroupId;
    private String id;
    private String groupName;
    private String groupNick;
    private T files;
    private FileDto file;


    public AppealGroupDto(String id, String groupName, String groupNick, T files) {
        this.id = id;
        this.groupName = groupName;
        this.groupNick = groupNick;
        this.files = files;
    }

    public AppealGroupDto(String fileGroupId, FileDto file){
        this.fileGroupId = fileGroupId;
        this.file = file;
    }

    public AppealGroupDto(String id, String groupName, String groupNick, FileDto file){
        this.id = id;
        this.groupName = groupName;
        this.groupNick = groupNick;
        this.file = file;
    }

    // геттеры и сеттеры
    public String getFileGroupId() {
        return fileGroupId;
    }

    public void setFileGroupId(String fileGroupId) {
        this.fileGroupId = fileGroupId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupNick() {
        return groupNick;
    }

    public void setGroupNick(String groupNick) {
        this.groupNick = groupNick;
    }

    public T getFiles() {
        return files;
    }

    public void setFiles(T files) {
        this.files = files;
    }

    public FileDto getFile() {
        return file;
    }

    public void setFile(FileDto file) {
        this.file = file;
    }
}
