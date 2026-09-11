package models;

import java.util.List;

public class CommonChecksGroupDto {

    public String groupId;
    public String groupName;
    public String groupNick;
    public List<FileDto> files;

    public CommonChecksGroupDto(String groupId, String groupName, String groupNick,
                                List<FileDto> files){
        this.groupId = groupId;
        this.groupName = groupName;
        this.groupNick = groupNick;
        this.files = files;
    }

    public CommonChecksGroupDto() {
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
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

    public List<FileDto> getFiles() {
        return files;
    }

    public void setFiles(List<FileDto> files) {
        this.files = files;
    }
}
