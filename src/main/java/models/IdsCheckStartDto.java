package models;

import java.util.List;

public class IdsCheckStartDto {

    public String appealId;
    public String idsFileId;
    public List<String> ifcFileIds;

    public IdsCheckStartDto(){}

    public IdsCheckStartDto(String appealId, String idsFileId, List<String> ifcFileIds){
        this.appealId = appealId;
        this.idsFileId = idsFileId;
        this.ifcFileIds = ifcFileIds;
    }

    public String getAppealId() {
        return appealId;
    }

    public void setAppealId(String appealId) {
        this.appealId = appealId;
    }

    public String getIdsFileId() {
        return idsFileId;
    }

    public void setIdsFileId(String idsFileId) {
        this.idsFileId = idsFileId;
    }

    public List<String> getIfcFileIds() {
        return ifcFileIds;
    }

    public void setIfcFileIds(List<String> ifcFileIds) {
        this.ifcFileIds = ifcFileIds;
    }
}
