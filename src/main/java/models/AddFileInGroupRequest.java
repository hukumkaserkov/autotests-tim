package models;

public class AddFileInGroupRequest<T> {

    private AppealGroupDto<T> fileGroup;

    public AddFileInGroupRequest(AppealGroupDto<T> fileGroup) {
        this.fileGroup = fileGroup;
    }

    public AppealGroupDto<T> getFileGroup() {
        return fileGroup;
    }

    public void setFileGroup(AppealGroupDto<T> fileGroup) {
        this.fileGroup = fileGroup;
    }
}
