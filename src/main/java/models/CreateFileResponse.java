package models;

public class CreateFileResponse {

    public String id;
    public String name;
    public String fileExt;
    public String createDate;
    public Long size;
    public String jsonCustomData;
    public Boolean isSign;
    public String signId;
    public Boolean detached;
    public String dateOfSign;
    public String lastModify;
    public String path;
    public String mimeType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileExt() {
        return fileExt;
    }

    public void setFileExt(String fileExt) {
        this.fileExt = fileExt;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getJsonCustomData() {
        return jsonCustomData;
    }

    public void setJsonCustomData(String jsonCustomData) {
        this.jsonCustomData = jsonCustomData;
    }

    public Boolean getSign() {
        return isSign;
    }

    public void setSign(Boolean sign) {
        isSign = sign;
    }

    public String getSignId() {
        return signId;
    }

    public void setSignId(String signId) {
        this.signId = signId;
    }

    public Boolean getDetached() {
        return detached;
    }

    public void setDetached(Boolean detached) {
        this.detached = detached;
    }

    public String getDateOfSign() {
        return dateOfSign;
    }

    public void setDateOfSign(String dateOfSign) {
        this.dateOfSign = dateOfSign;
    }

    public String getLastModify() {
        return lastModify;
    }

    public void setLastModify(String lastModify) {
        this.lastModify = lastModify;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
}