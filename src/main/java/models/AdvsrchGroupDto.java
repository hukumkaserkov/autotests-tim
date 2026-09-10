package models;

import java.util.List;

public class AdvsrchGroupDto {

    public String id;
    public String groupNick;
    public String groupName;
    private String parentGroupId;
    private Integer totalFiles;
    private List<AdvsrchFilesDto> files;

    // Пустой конструктор для Jackson
    public AdvsrchGroupDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupNick() {
        return groupNick;
    }

    public void setGroupNick(String groupNick) {
        this.groupNick = groupNick;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getParentGroupId() {
        return parentGroupId;
    }

    public void setParentGroupId(String parentGroupId) {
        this.parentGroupId = parentGroupId;
    }

    public List<AdvsrchFilesDto> getFiles() {
        return files;
    }

    public void setFiles(List<AdvsrchFilesDto> files) {
        this.files = files;
    }

    public Integer getTotalFiles() {
        return totalFiles;
    }

    public void setTotalFiles(Integer totalFiles) {
        this.totalFiles = totalFiles;
    }
}
