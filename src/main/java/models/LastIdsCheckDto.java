package models;

public class LastIdsCheckDto {

    public String requirementsIdsFileId;
    public String checkStartDate;
    public String checkEndDate;
    public String checkStatus;
    public String reqVersion;

    public LastIdsCheckDto(String requirementsIdsFileId, String checkStartDate, String checkEndDate,
                           String checkStatus, String reqVersion) {
        this.requirementsIdsFileId = requirementsIdsFileId;
        this.checkStartDate = checkStartDate;
        this.checkEndDate = checkEndDate;
        this.checkStatus = checkStatus;
        this.reqVersion = reqVersion;
    }

    public LastIdsCheckDto(){
    }

    public String getRequirementsIdsFileId() {
        return requirementsIdsFileId;
    }

    public void setRequirementsIdsFileId(String requirementsIdsFileId) {
        this.requirementsIdsFileId = requirementsIdsFileId;
    }

    public String getCheckStartDate() {
        return checkStartDate;
    }

    public void setCheckStartDate(String checkStartDate) {
        this.checkStartDate = checkStartDate;
    }

    public String getCheckEndDate() {
        return checkEndDate;
    }

    public void setCheckEndDate(String checkEndDate) {
        this.checkEndDate = checkEndDate;
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getReqVersion() {
        return reqVersion;
    }

    public void setReqVersion(String reqVersion) {
        this.reqVersion = reqVersion;
    }
}
