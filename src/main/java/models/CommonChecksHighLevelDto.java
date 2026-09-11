package models;

import java.util.List;

public class CommonChecksHighLevelDto {

    public List<CommonChecksGroupDto> groups;

    public CommonChecksHighLevelDto(){
    }

    public CommonChecksHighLevelDto(List<CommonChecksGroupDto> groups){
        this.groups = groups;
    }

    public List<CommonChecksGroupDto> getGroups() {
        return groups;
    }

    public void setGroups(List<CommonChecksGroupDto> groups) {
        this.groups = groups;
    }
}
