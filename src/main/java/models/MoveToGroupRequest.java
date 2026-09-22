package models;

public class MoveToGroupRequest {

    public String id;
    public String fileId;
    public String name;
    public String extension;
    public String createDate;
    public Long size;
    public String author;

    public MoveToGroupRequest(String id, String fileId, String name, String extension,
                   String createDate, Long size, String author) {
        this.id = id;
        this.fileId = fileId;
        this.name = name;
        this.extension = extension;
        this.createDate = createDate;
        this.size = size;
        this.author = author;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
